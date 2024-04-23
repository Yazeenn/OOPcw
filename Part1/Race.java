import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * A three-horse race, each horse running in its own lane
 * for a given distance
 *
 * @author McFarewell
 * @version 1.0
 */
public class Race
{
    private final int raceLength;
    private Horse lanes[];


    /**
     * Constructor for objects of class Race
     * Initially there are no horses in the lanes
     *
     * @param distance the length of the racetrack (in metres/yards...)
     */
    public Race(int distance, int numberOfLanes) {
        raceLength = distance;
        lanes = new Horse[numberOfLanes];
    }

    /**
     * Adds a horse to the race in a given lane
     *
     * @param theHorse the horse to be added to the race
     * @param laneNumber the lane that the horse will be added to
     */

    public void addHorse(Horse theHorse, int laneNumber)
    {
        if (theHorse == null) {
            //null horse
            System.out.println("Cannot add null horse to lane " + laneNumber);
            return;
        }
        if (laneNumber >= 1 && laneNumber <= lanes.length) {
            if (!(lanes[laneNumber - 1] == null)){
                //already a horse on lane
                System.out.println("Cannot add " + theHorse.getName() +" to lane " + laneNumber + " because there is already a horse on that lane");
            } else {
            lanes[laneNumber - 1] = (theHorse);
            }
        } else {
            System.out.println("Cannot add horse to lane " + laneNumber + " because there is no such lane");
        }
    }

    /**
     * Start the race
     * The horse are brought to the start and
     * then repeatedly moved forward until the
     * race is finished
     */
    public void startRace() {
        //declare a local variable to tell us when the race is finished
        boolean finished = false;

        //reset all the lanes (all horses not fallen and back to 0).
        for (Horse horse : lanes) {
            if (horse != null) {
                horse.goBackToStart();
            }
        }


        // move each horse
        while (!finished) {
            for (Horse horse : lanes) {
                if (horse != null) {
                    moveHorse(horse);
                }
            }
            //print the race positions
            printRace();

            //if any of the three horses has won the race is finished

            // if any of the horses has won the race or all horses have fallen, the race is finished
            if (raceWon() || allHorsesHaveFallen()) {
                finished = true;
            }

            //wait for 100 milliseconds
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (Exception e) {
            }
        }
    }

    private boolean allHorsesHaveFallen() {
        for (Horse horse : lanes) {
            if (horse != null && !horse.hasFallen()) {
                return false;
            }
        }
        System.out.println("All horses have fallen");
        return true;
    }

    /**
     * Randomly make a horse move forward or fall depending
     * on its confidence rating
     * A fallen horse cannot move
     *
     * @param theHorse the horse to be moved
     */
    private void moveHorse(Horse theHorse)
    {
        double scalingFactor = 1.0;
        //if the race is long enough (60m in this case, can be changed as per client request)
        if(raceLength>=60){
            //calculating scaling factor based on race distance
            scalingFactor = 30.0/raceLength;
        }

        //if the horse has fallen it cannot move,
        //so only run if it has not fallen

        if  (!theHorse.hasFallen())
        {
            //the probability that the horse will move forward depends on the confidence;
            if (Math.random() < theHorse.getConfidence())
            {
                theHorse.moveForward();
            }

            //the probability that the horse will fall is very small (max is 0.1)
            //but will also will depends exponentially on confidence
            //so if you double the confidence, the probability that it will fall is *2
            if (Math.random() < (0.1*theHorse.getConfidence()*theHorse.getConfidence()*scalingFactor))
            {
                theHorse.fall();
            }
        }
    }

    /**
     * Determines if a horse has won the race
     *
     * @return true if a horse has won, false otherwise.
     */
    private boolean raceWon()
    {
        for (Horse horse : lanes) {
            if (horse != null && horse.getDistanceTravelled() == raceLength) {
                System.out.println("And the winner is " + winningHorseName);
                return true;
            }
        }
        return false;
    }
    private void printRace()
    {
        System.out.print('\u000C');  //clear the terminal window

        multiplePrint('=',raceLength+3); //top edge of track
        System.out.println();

        for (Horse horse : lanes) {
            if (horse != null) {
                printLane(horse);
                System.out.println();
            }
        }

        multiplePrint('=',raceLength+3); //bottom edge of track
        System.out.println();
    }
    private void printLane(Horse theHorse)
    {

        //calculate how many spaces are needed before
        //and after the horse
        int spacesBefore = theHorse.getDistanceTravelled();
        int spacesAfter = raceLength - theHorse.getDistanceTravelled();

        //print a | for the beginning of the lane
        System.out.print('|');

        //print the spaces before the horse
        multiplePrint(' ', spacesBefore);

        //if the horse has fallen then print dead
        //else print the horse's symbol
        if (theHorse.hasFallen()) {
            System.out.print('☠');
        } else {
            System.out.print(theHorse.getSymbol());
        }

        //print the spaces after the horse
        multiplePrint(' ', spacesAfter);

        //print the | for the end of the track
        System.out.print('|');

    }

    private void multiplePrint(char aChar, int times)
    {
        int i = 0;
        while (i < times)
        {
            System.out.print(aChar);
            i = i + 1;
        }
    }

}
