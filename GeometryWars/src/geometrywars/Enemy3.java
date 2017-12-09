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
public class Enemy3 implements EnemyUnit{
    private int HP = 0;
    private int xPosition;
    private int yPosition;
    public Enemy3(int xPos, int yPos) {
        xPosition = xPos;
        yPosition = yPos;
        onCreate();
    }
    
    public int getxPosition() {
        return this.xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return this.yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    @Override
    public void onHit() {
        HP = 1;
        onDeath();
    }

    @Override
    public void onCreate() {
        HP = 1;
    }

    @Override
    public void onDeath() {
        //Do nothing
    }

    public int getHP() {
        return HP;
    }
}
