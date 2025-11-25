package lab7;

public class Tower {
    
    // Instance variables
    private int health;
    private int score;
    private String symbol;

    /*
     * Initializes the Tower with a specified health, score and symbol.
     * When initializing the health variable, call setHealth to prevent negative values.
     */
    public Tower (int health, int score, String symbol) {
        this.score = score;
        this.symbol = symbol;
        setHealth(health); // Negatif değer kontrolü için setter kullanıldı [cite: 68]
    }

    /*
     * Returns true if the tower's health is greater than zero, otherwise false.
     */
    public boolean isStanding () {
        return health > 0; // [cite: 69]
    }

    /*
     * Takes a damage value and decreases the tower's health by that amount.
     * The health value should never go below zero.
     */
    public void takeDamage (int damage) {
        setHealth(this.health - damage); // [cite: 71]
    }

    /*
     * Increases the tower's score by the given value.
     */
    public void incrementScore (int score) {
        this.score += score; // [cite: 72]
    }

    /*
     * Returns a string representation of the tower,
     * including its symbol, health, and score.
     */
    
    public String toString () {
        // Örnek çıktı formatı: Tower: symbol (X), health (Y), score (Z) [cite: 73]
        return "Tower: symbol (" + symbol + "), health (" + health + "), score (" + score + ")";
    }

    // Getters & Setters
    public String getSymbol () {
        return symbol; // [cite: 74]
    }

    public int getScore () {
        return score; // [cite: 75]
    }

    public void setScore (int score) {
        this.score = score; // [cite: 76]
    }

    public int getHealth () {
        return health; // [cite: 78]
    } 

    /*
     * Sets the tower's health to the given value.
     * The health value must not be negative.
     */
    public void setHealth (int health) {
        if (health < 0) {
            this.health = 0; // Can asla 0'ın altına düşemez [cite: 81]
        } else {
            this.health = health;
        }
    }

}