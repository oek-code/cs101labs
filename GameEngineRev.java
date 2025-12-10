package lab8;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The GameEngine class controls the main flow of the game.
 * It manages user input, updates the game state, and triggers rendering each turn.
 *
 * The class also defines global constants such as grid size, entity symbols,
 * and initial spaceship health.
 */
public class GameEngineRev {
    /** Symbol used to display an enemy. */
    public static final char ENEMY_SYMBOL = 'M';
    /** Symbol used to display the player’s spaceship. */
    public static final char SPACE_SHIP_SYMBOL = '^';
    /** Symbol used to display bullets. */
    public static final char BULLET_SYMBOL = '|';
    /** Number of enemies spawned per turn. */
    public static final int ENEMY_PER_ROW = 3;
    /** Initial health value of the spaceship. */
    public static final int SPACE_SHIP_HEALTH = 3;
    /** Width of the game grid. */
    public static final int WIDTH = 24;
    /** Height of the game grid. */
    public static final int HEIGHT = 12;
    /** Symbol used to display health stones. */
    public static final char HEALTH_STONE_SYMBOL = '+';

    private final GameFieldRev field;
    private final Scanner in = new Scanner(System.in);

    private final SpaceShip spaceShip;
    private final List<Enemy> enemies;
    private final List<Bullet> bullets;
    private final List<HealthStone> healthStones;

    /**
     * Initializes the game engine by creating the spaceship,
     * and empty lists for enemies and bullets.
     * Also sets up the game field that manages these entities.
     */
    public GameEngineRev() {
        this.enemies = new ArrayList<>();
        this.healthStones = new ArrayList<>();
        this.bullets = new ArrayList<>();
        this.spaceShip = new SpaceShip(WIDTH / 2, HEIGHT - 1, SPACE_SHIP_HEALTH);
        this.field = new GameFieldRev(spaceShip, enemies, bullets, healthStones);
        
    }

    /**
     * Starts the main game loop.
     * Handles user input, updates the game state, and redraws the screen each turn.
     * The loop terminates when the spaceship’s health reaches zero or the player quits.
     */
    public void run() {
        Drawer.printControls();

        boolean running = true;
        while (running) {
            DrawerRev.render(spaceShip, enemies, bullets,healthStones, field.getScore());

            if (spaceShip.getHealth() <= 0) {
                System.out.println("\nGAME OVER!");
                running = false;
                
            }

            System.out.print("Move (A/D), Shoot (S), Wait (Enter), Quit (Q): ");
            String input = in.nextLine().trim().toUpperCase();
            if (input.isEmpty()) {
                input = " ";
            }

            char command = input.charAt(0);
            if (command == 'Q') {
                running = false;
                
            }

            update(command);
        }

        System.out.println("Final Score: " + field.getScore());
    }

    /**
     * Processes a single turn of the game based on the player’s command.
     * Updates all entities, checks collisions, and spawns new enemies.
     *
     * @param command  the player’s input command (A, D, S, or Enter)
     */
    private void update(char command) {
        if(command =='A'){
            spaceShip.moveLeft();
        }else if(command =='D'){
            spaceShip.moveRight();
        }else if (command =='S'){
            bullets.add(spaceShip.shoot());
        }
        

        for (Bullet b : bullets) {
            b.update();
        }

        int collusionCount = field.checkBulletCollusion();
        if (collusionCount > 0) {
            System.out.println("You killed " + collusionCount + " monsters!");
        }

        for (Enemy e : enemies) {
            e.update();
        }

        if (field.checkSpaceShipCollusion()) {
            System.out.println("You were hit by a monster!");
        }

        field.spawnEnemies();
        enemies.removeIf(e -> e.getY() >= HEIGHT - 1);
        bullets.removeIf(b -> b.getY() < 0);

        field.spawnHealthStones();

        for(HealthStone h : healthStones){
            h.update();
        }
            if(field.checkHealthStoneCollision()){
                System.out.println(" You healed by one health point ! ");


            }
            
        
    }
}