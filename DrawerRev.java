package lab8;
/**
 * The Drawer class handles all visual output of the game.
 * It is responsible for displaying the game board, the player's spaceship,
 * enemies, bullets, and game statistics such as score and health.
 *
 * This class is intentionally static — it does not store any game state.
 * Instead, it receives all data from GameEngine and GameField to draw
 * the current frame each turn.
 */
import java.util.Arrays;
import java.util.List;

public class DrawerRev {

    /**
     * Prints the game’s control instructions to the console.
     * This method is called once at the start of the game.
     */
    public static void printControls() {
        System.out.println("Controls: A=Left | D=Right | S=Shoot | Enter=Wait | Q=Quit");
        System.out.println("Every turn: Enemies spawn at the top and move down. Avoid them!");
    }

    /**
     * Draws the current state of the game board.
     * It creates a 2D grid of characters, fills it with spaces,
     * and then places all active game entities (enemies, bullets, spaceship).
     * After filling the grid, it calls printField() to render it on screen.
     *
     * @param spaceShip  the player’s spaceship object
     * @param enemies    list of current enemy objects
     * @param bullets    list of active bullets fired by the player
     * @param score      the player’s current score
     */
    public static void render(SpaceShip spaceShip, List<Enemy> enemies, List<Bullet> bullets,List<HealthStone> healthStones, int score) {
        // Create an empty 2D character grid
        char[][] grid = new char[GameEngine.HEIGHT][GameEngine.WIDTH];
        for (char[] row : grid) {
            Arrays.fill(row, ' ');
        }

        // Place enemies on the grid
        for (Enemy e : enemies) {
            place(grid, e.getX(), e.getY(), e.getSymbol());
        }

        // Place bullets on the grid
        for (Bullet b : bullets) {
            place(grid, b.getX(), b.getY(), b.getSymbol());
        }
        for(HealthStone h : healthStones){
            place(grid, h.getX(), h.getY(), h.getSymbol());
        }

        // Place the spaceship on the grid
        place(grid, spaceShip.getX(), spaceShip.getY(), spaceShip.getSymbol());

        // Print the grid and HUD (score, health, etc.)
        printField(grid);
        System.out.printf("\nScore: %-2d | Health: %-2d | Enemies: %-2d%n",
                score, spaceShip.getHealth(), enemies.size());
    }

    /**
     * Places a single character (representing an entity) on the grid,
     * if its coordinates are within the grid boundaries.
     *
     * @param grid the 2D grid to draw on
     * @param x    horizontal position
     * @param y    vertical position
     * @param c    the character symbol to draw
     */
    private static void place(char[][] grid, int x, int y, char c) {
        if (y >= 0 && y < grid.length && x >= 0 && x < grid[0].length) {
            grid[y][x] = c;
        }
    }

    /**
     * Prints the entire grid to the console with visual borders.
     * The top and bottom borders use '=' symbols, and the sides use '|'.
     * This gives the display a framed look like a classic console game.
     *
     * @param grid the 2D character grid representing the current game state
     */
    private static void printField(char[][] grid) {
        // Top border
        for (int i = 0; i < GameEngine.WIDTH + 2; i++) {
            System.out.print('=');
        }
        System.out.println();

        // Each row with left and right borders
        for (char[] row : grid) {
            System.out.print('|');
            System.out.print(row);
            System.out.println('|');
        }

        // Bottom border
        for (int i = 0; i < GameEngine.WIDTH + 2; i++) {
            System.out.print('=');
        }
    }
}