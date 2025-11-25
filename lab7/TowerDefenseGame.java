package lab7;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TowerDefenseGame {

    // Game Settings
    public final static int ROWS = 6; 
    public final static int COLUMNS = 10;
    public final static int INITIAL_HEALTH = 15;
    public final static int ENEMY_WAVES = 10;
    public final static int MAX_ENEMY_SPAWN_RATE = 4;
    public final static String VICTORY_MESSAGE = "Victory! Your tower stands strong";
    public final static String DEFEAT_MESSAGE = "Defeat! Your tower has fallen!";

    // Game Symbols
    public final static String TOWER_SYMBOL = "🏰";
    public final static String ENEMY_SYMBOL = "👾";
    public final static String EMPTY_SYMBOL = "⬜";

    // Game Management
    private static GameManager gameManager;
    private static Scanner scanner;
    public static Random random;


    public static void main (String[] args) {
        initializeVariables();
        playGame();
        handleGameEnding();
    }

    /*
     * Initializes game variables such as Random, Scanner, and GameManager.
     */
    private static void initializeVariables () {
        random = new Random();
        scanner = new Scanner(System.in);
        gameManager = new GameManager();        
    }

    /*
     * Main game loop.
     * Renders the map, handles user attacks, applies tower damage, and adds new enemy waves.
     */
    private static void playGame () {
        while (!gameManager.isGameOver()) {
            renderGraphics();
            handleHit();
            gameManager.handleTowerDamage();
            
            if (gameManager.hasEnemyWavesLeft() || !gameManager.isGameOver()) { 
                // Not: Oyun bitmediyse dalga ekle/kaydır
                if (!gameManager.isGameOver()) {
                     gameManager.addNextEnemyWave();
                }
            }
        }
    }

    /*
     * Prints the final game result, including tower status and score, and closes the scanner.
     */
    private static void handleGameEnding () {
        renderGraphics();

        Tower tower = gameManager.getTower();
        System.out.println(tower.isStanding() ? VICTORY_MESSAGE : DEFEAT_MESSAGE);
        System.out.println("Final Score: " + tower.getScore());

        scanner.close();
    }

    /*
     * Renders the game visuals in the console, including the map and game information.
     */
    private static void renderGraphics () {
        System.out.println("\n" + "=".repeat(2 * COLUMNS + 3));
        renderGameMap();
        renderGameInformation();
        System.out.println("=".repeat(2 * COLUMNS + 3));
    }

    /*
     * Renders the enemy waves map and the tower position on the console.
     */
    private static void renderGameMap() {
        ArrayList<EnemyWave> enemyWaves = gameManager.getEnemyWaves();

        System.out.print("    ");
        for (int i = 0; i < COLUMNS; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int row = 0; row < ROWS; row++) {
            System.out.print(row + " ");
            System.out.print((row == ROWS / 2) ? TOWER_SYMBOL : EMPTY_SYMBOL);

            for (int col = 0; col < COLUMNS; col++) {
                String[] enemyWave = enemyWaves.get(col).getEnemyWave();
                System.out.print(enemyWave[row]);
            }
            System.out.println();
        }
    }

    /*
     * Displays tower information (symbol, health, score) and the number of enemy waves left.
     */
    private static void renderGameInformation() {
        Tower tower = gameManager.getTower();
        int wavesRemaining = gameManager.getEnemyWavesLeft();
        System.out.println(tower + " | Enemy Waves Left: " + wavesRemaining);
    }

    /*
     * Handles user input for attacking enemies.
     * Reads column and row indexes from the player.
     * Updates the tower's score based on the number of enemies destroyed.
     */
    private static void handleHit () {
        int columnIndex = getValidInput("Enter column index (0 to " + (COLUMNS - 1) + "): ", 0, COLUMNS - 1);
        int rowIndex = getValidInput("Enter row index (0 to " + (ROWS - 1) + "): ", 0, ROWS - 1);

        // TODO implementasyonu:
        // GameManager üzerinden ilgili düşmana vur [cite: 55]
        int hitResult = gameManager.hitEnemy(columnIndex, rowIndex);
        
        // Eğer vuruş başarılıysa (1 döndüyse) puanı artır
        if (hitResult == 1) {
            gameManager.getTower().incrementScore(1); // [cite: 72]
        }
    }

    /*
     * Reads a valid integer input from the player within a specified range.
     * Repeats until a valid input is entered.
     */
    private static int getValidInput (String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }
   
}