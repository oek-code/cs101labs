package lab7;

public class EnemyWave {
    
    // Instance variable
    private String[] enemyWave; // [cite: 86]
     
    /*
     * Initializes an EnemyWave with a given number of enemies.
     * Enemies are first placed into the array and then shuffled randomly.
     */
    public EnemyWave (int numberOfEnemies) {
        // Dizi boyutu oyun alanının satır sayısı kadardır
        this.enemyWave = new String[TowerDefenseGame.ROWS];
        initializeWave(numberOfEnemies);
        shuffleArray(); // [cite: 90]
    } 
    
    /*
     * Fills the wave array with enemies for the first 'numberOfEnemies' positions
     * and empty symbols in the remaining positions.
     */
    private void initializeWave (int numberOfEnemies) {
        for (int i = 0; i < enemyWave.length; i++) {
            if (i < numberOfEnemies) {
                enemyWave[i] = TowerDefenseGame.ENEMY_SYMBOL;
            } else {
                enemyWave[i] = TowerDefenseGame.EMPTY_SYMBOL;
            }
        } // [cite: 91]
    }

    /*
     * Randomly shuffles the positions of enemies and empty symbols
     * using Fisher–Yates shuffle algorithm.
     */
    private void shuffleArray () {
        // Fisher-Yates Algoritması [cite: 98-102]
        // 1. Dizinin sonundan başla
        for (int i = enemyWave.length - 1; i > 0; i--) {
            // 2. 0 ile mevcut indeks arasında rastgele bir indeks seç
            int index = TowerDefenseGame.random.nextInt(i + 1);
            
            // 3. Mevcut eleman ile rastgele seçilen elemanı takas et (Swap)
            String temp = enemyWave[index];
            enemyWave[index] = enemyWave[i];
            enemyWave[i] = temp;
        }
    }    

    /*
     * Attempts to hit an enemy at the given index.
     * If an enemy is present, it is replaced with an empty symbol and the method returns 1. 
     * Otherwise, it returns 0.
     */
    public int hitEnemy (int index) {
        if (index >= 0 && index < enemyWave.length) {
            if (enemyWave[index].equals(TowerDefenseGame.ENEMY_SYMBOL)) {
                enemyWave[index] = TowerDefenseGame.EMPTY_SYMBOL; // Düşmanı sil
                return 1; // Başarılı vuruş [cite: 95]
            }
        }
        return 0; // Boş veya geçersiz
    }

    /*
     * Counts and returns the number of enemies still alive in this wave.
     */
    public int getNumberOfEnemies () {
        int count = 0;
        for (String s : enemyWave) {
            if (s.equals(TowerDefenseGame.ENEMY_SYMBOL)) {
                count++;
            }
        }
        return count; // [cite: 96]
    }

    // Getter
    public String[] getEnemyWave () {
        return enemyWave; // [cite: 97]
    }
    
}