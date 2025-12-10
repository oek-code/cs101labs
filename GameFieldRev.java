package lab8;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The GameField class manages the core gameplay logic and keeps track of all
 * active entities in the game (SpaceShip, Enemies, and Bullets).
 * It acts as a container for the game's state, handling spawning, movement,
 * and collisions.
 *
 * Responsibilities:
 * - Spawning new enemies
 * - Detecting and handling collisions
 * - Tracking the player’s score
 */
public class GameFieldRev {
    /** Reference to the player’s spaceship (controlled by the user). */
    private final SpaceShip spaceShip;

    /** List of all active enemy objects currently on the field. */
    private final List<Enemy> enemies;

    /** List of all bullets currently fired by the spaceship. */
    private final List<Bullet> bullets;

    /** Player’s score, increased whenever an enemy is destroyed. */
    private int score;
    
    /** Random object for generating random numbers. */
    private final Random random = new Random();

    private final List<HealthStone> healthStones;

    /**
     * Constructs a new GameField with the player’s ship and shared entity lists.
     *
     * @param spaceShip  the player’s spaceship
     * @param enemies    list of all enemy objects
     * @param bullets    list of all bullet objects
     */
    public GameFieldRev(SpaceShip spaceShip, List<Enemy> enemies, List<Bullet> bullets, List<HealthStone> healthStones) {
        this.spaceShip = spaceShip;
        this.enemies = enemies;
        this.bullets = bullets;
        this.score = 0;
        this.healthStones = healthStones;
    }

    /**
     * Spawns a fixed number of new health stones at the top of the grid each turn.
     * Health stones are placed in random horizontal positions, but never stacked
     * directly on top of each other in the same column.
     */
    
    public void spawnHealthStones(){
        if(random.nextInt(100)<5){
            int randomX = random.nextInt(GameEngine.WIDTH);
            boolean occupied = false;
            for( Enemy e : enemies){
                if (e.getX() == randomX && e.getY() == 0)
                    occupied = true;
            }
            if(!occupied){
                healthStones.add(new HealthStone(randomX, 0));
            
            }
            }
        }
    

 /**
     * Spawns a fixed number of new enemies at the top of the grid each turn.
     * Enemies are placed in random horizontal positions, but never stacked
     * directly on top of each other in the same column.
     */

    public void spawnEnemies() {
        int count = 0;
        int attempts = 0;
        
        while (count < GameEngine.ENEMY_PER_ROW && attempts < 50) {
            int randomX = random.nextInt(GameEngine.WIDTH);
            
            boolean occupied = false;
            
            for (Enemy e : enemies) {
                if (e.getX() == randomX && e.getY() == 0) {
                    occupied = true;
                }
                
            }

            if (!occupied) {
                enemies.add(new Enemy(randomX, 0));
                count++;
            }
            attempts++;
        }    
    }

    /**
     * Checks whether any enemy has collided with the player's spaceship.
     * If a collision is detected:
     * - The enemy is removed.
     * - The spaceship loses one health point.
     *
     * @return true if a collision occurred; false otherwise
     */

    public boolean checkHealthStoneCollision(){
        List<HealthStone> toRemove = new ArrayList<>();
        boolean healed = false;
        for(HealthStone h : healthStones){
            if (h.collidesWith(spaceShip)){
                toRemove.add(h);
                spaceShip.setHealth(spaceShip.getHealth()+1);
                healed = true;
            }
        }
        healthStones.removeAll(toRemove);
        return healed;
    }
    public boolean checkSpaceShipCollusion() {
        List<Enemy> toRemove = new ArrayList<>();
        boolean collisionDetected = false;

        for (Enemy e : enemies) {
            if (e.attack(spaceShip)) {
                toRemove.add(e);
                collisionDetected = true;
            }
        }
        
        enemies.removeAll(toRemove);
        return collisionDetected;
    }

    /**
     * Checks for bullet–enemy collisions.
     * If a bullet hits an enemy:
     * - Both are removed from the field.
     * - The player’s score increases by one.
     *
     * @return number of enemies destroyed this turn
     */
    public int checkBulletCollusion() {
       List<Bullet> bulletsToRemove = new ArrayList<>();
        List<Enemy> enemiesToRemove = new ArrayList<>();
        List<HealthStone> stonesToRemove = new ArrayList<>();
        int killCount = 0;

        for (Bullet b : bullets) {
            boolean bulletHit = false; 

            for (Enemy e : enemies) {
                
                if (!bulletHit) {
                    if (b.collidesWith(e) && !enemiesToRemove.contains(e)) {
                        bulletsToRemove.add(b);
                        enemiesToRemove.add(e);
                        score++;
                        killCount++;
                        bulletHit = true; 
                        
                    }
                }
            }
            for(HealthStone h : healthStones) {
                if(!bulletHit){
                    if(b.collidesWith(h) && !stonesToRemove.contains(h)){
                        bulletsToRemove.add(b);
                        stonesToRemove.add(h);
                        bulletHit = true;
                    }
                }
            }
        }
        
        bullets.removeAll(bulletsToRemove);
        enemies.removeAll(enemiesToRemove);
        healthStones.removeAll(stonesToRemove);
        
        return killCount;
    }

    /** @return the current player score. */
    public int getScore() {
        return score;    
    }
}