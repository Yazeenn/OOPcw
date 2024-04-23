public class Horse {
    private String name;
    private char symbol;
    private int distance = 0 ;
    private boolean fallen = false;
    private double confidence;

    public Horse(char horseSymbol, String horseName, double horseConfidence){
        setSymbol(horseSymbol);
        setName(horseName);
        setConfidence(horseConfidence);
    }

    public void fall(){
        fallen = true;
    }

    public double getConfidence(){
        return confidence;
    }
    public int getDistanceTravelled(){
        return distance;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
    public void goBackToStart(){
        fallen  = false;
        distance = 0;
    }
    public boolean hasFallen(){
        if (fallen){
            return true;
        } else {return false;}
    }
    public void moveForward(){
        distance+=1;
    }

    public void setName(String newName) {
        // Ensures there is a name (not null or empty)
        if (newName != null && !newName.trim().isEmpty()) {
            name = newName;
        } else {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
    }
    public void setConfidence (double newConfidence){

        // Ensure confidence is between 0 and 1
        if (newConfidence >= 0 && newConfidence <= 1) {
            confidence = newConfidence;
        } else throw new IllegalArgumentException("Confidence must be between 0 and 1");
    }
    public void setSymbol(char newSymbol){
        // Ensure symbol is a unicode character..
        if (Character.isDefined(newSymbol)) {
            symbol = newSymbol;
        } else {
            throw new IllegalArgumentException("Symbol must be in unicode");
        }
    }
}
