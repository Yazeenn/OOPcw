public class Main {
    public static void main(String[] args) {

        Race race = new Race(100, 3);

        // Create three horses
        Horse horse1 = new Horse('♘', "Horse1", 0.2);
        Horse horse2 = new Horse('♞', "Horse2", 0.5);
        Horse horse3 = new Horse('♖', "Horse3", 0.4);


        // Add horses to the race in different lanes
        race.addHorse(horse1, 1);
        race.addHorse(horse2, 2);
        race.addHorse(horse3, 3);

        // Start the race
        race.startRace();
    }
}