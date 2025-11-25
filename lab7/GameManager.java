package lab7;

import java.util.ArrayList;

public class GameManager {
    
    // Instance variables
    private Tower tower;
    private ArrayList<EnemyWave> enemyWaves;
    private int enemyWavesLeft; // [cite: 109-113]

    /*
     * Initializes the GameManager.
     * Creates a new tower with initial health and score.
     * Prepares an empty map filled with empty enemy waves.
     * Adds the first enemy wave to start the game.
     */
    public GameManager () {
        this.tower = new Tower(TowerDefenseGame.INITIAL_HEALTH, 0, TowerDefenseGame.TOWER_SYMBOL);
        this.enemyWaves = new ArrayList<>();
        this.enemyWavesLeft = TowerDefenseGame.ENEMY_WAVES;
        
        generateEmptyMap(); // Haritayı boşluklarla doldur
        addNextEnemyWave(); // İlk gerçek dalgayı ekle [cite: 115]
    }

    /*
     * Fills the map with empty enemy waves.
     */
    private void generateEmptyMap () {
        // Harita genişliği (COLUMNS) kadar boş dalga ekler
        for (int i = 0; i < TowerDefenseGame.COLUMNS; i++) {
            enemyWaves.add(generateNextEnemyWave(false));
        } // [cite: 116]
    }

    /*
     * Creates a new enemy wave.
     * If hasEnemies is true, a random number of enemies is spawned
     * up to the maximum enemy spawn rate.
     */
    private EnemyWave generateNextEnemyWave (boolean hasEnemies) {
        int numEnemies = 0;
        if (hasEnemies) {
            // 0 ile MAX_ENEMY_SPAWN_RATE arasında rastgele sayı [cite: 117]
            numEnemies = TowerDefenseGame.random.nextInt(TowerDefenseGame.MAX_ENEMY_SPAWN_RATE + 1);
        }
        return new EnemyWave(numEnemies);
    }

    /*
     * Returns the number of enemies left in the wave at the given column index.
     */
    public int getNumberOfEnemies (int index) {
        if (index >= 0 && index < enemyWaves.size()) {
            return enemyWaves.get(index).getNumberOfEnemies();
        }
        return 0; // [cite: 118]
    }

    /*
     * Removes the oldest enemy wave from the map.
     * Adds a new enemy wave at the end of the map.
     * Decreases the count of remaining waves.
     */
    public void addNextEnemyWave () {
        enemyWaves.remove(0); // En baştaki (kuleye en yakın) dalgayı sil
        
        if (enemyWavesLeft > 0) {
            // Eğer hala gelecek dalga varsa düşmanlı dalga ekle
            enemyWaves.add(generateNextEnemyWave(true));
            enemyWavesLeft--; // [cite: 120-121]
        } else {
            // Dalgalar bittiyse boş dalga eklemeye devam et
            enemyWaves.add(generateNextEnemyWave(false));
        }
    }

    /*
     * Attempts to hit an enemy at the given column and row index.
     * Returns 1 if an enemy is hit, and 0 otherwise.
     */
    public int hitEnemy (int columnIndex, int rowIndex) {
        if (columnIndex >= 0 && columnIndex < enemyWaves.size()) {
            return enemyWaves.get(columnIndex).hitEnemy(rowIndex);
        }
        return 0; // [cite: 122]
    }

    /*
     * Returns the tower object used in the game.
     */
    public Tower getTower () {
        return tower; // [cite: 123]
    }

    /*
     * Returns true if there are still enemy waves left to spawn.
     */
    public boolean hasEnemyWavesLeft () {
        return enemyWavesLeft > 0; // [cite: 124]
    }

    /*
     * Checks whether the game is over.
     * The game ends if the tower is destroyed or no enemy waves remain.
     */
    public boolean isGameOver () {
        // Oyun bitme koşulları: Kule yıkıldıysa [cite: 125]
        if (!tower.isStanding()) {
            return true;
        }
        
        // Veya dalgalar bitti VE haritada hiç düşman kalmadıysa (Victory durumu için haritanın temizlenmesi gerekir)
        boolean mapIsEmpty = true;
        for (EnemyWave wave : enemyWaves) {
            if (wave.getNumberOfEnemies() > 0) {
                mapIsEmpty = false;
                break;
            }
        }
        return (enemyWavesLeft == 0 && mapIsEmpty);
    }

    /*
     * Reduces the tower's health based on enemies that reached the front column.
     * Each remaining enemy decreases the tower's health by 1.
     */
    public void handleTowerDamage () {
         // Kuleye en yakın sütun (index 0) içindeki düşman sayısı kadar hasar ver [cite: 126]
         int damage = enemyWaves.get(0).getNumberOfEnemies();
         tower.takeDamage(damage);
    }

    // Getters
    public ArrayList<EnemyWave> getEnemyWaves () {
        return enemyWaves; // [cite: 128]
    }

    public int getEnemyWavesLeft () {
        return enemyWavesLeft; // [cite: 129]
    }
   
}