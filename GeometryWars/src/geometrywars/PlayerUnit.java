/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

/**
 *
 * @author MaxBu
 */
public class PlayerUnit {
    /**
     * Triggers when player unit takes a hit from EnemyUnits
     */
    private int lives = 0;
    private boolean gameOver = false;

    public int getLives() {
        return lives;
    }

    public boolean isGameOver() {
        return gameOver;
    }
    
    public void onHit() {
        lives--;
    }
    /**
     * Triggers when player unit takes a destroy enemy unit and increases score
     */
    public int onKill() {
        return 10;
    }
    /**
     * Creation method
     */
    public void onCreate() {
        this.lives = 3;
    }
    
    /**
     * Unit removes from playing field
     */
    public boolean onDeath() {
        if (this.lives == 0)
            this.gameOver = true;
        return this.gameOver;
    }
}
