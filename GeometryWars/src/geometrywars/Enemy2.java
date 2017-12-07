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
public class Enemy2 implements EnemyUnit{
    private int HP = 0;
    private int xPosition;
    private int yPosition;
    public Enemy2(int xPos, int yPos) {
        xPosition = xPos;
        yPosition = yPos;
        this.onCreate();
    }

    @Override
    public void onHit() {
        this.HP = 0;
        this.onDeath();
    }

    @Override
    public void onCreate() {
        this.HP = 1;
    }

    @Override
    public void onDeath() {
        
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getHP() {
        return HP;
    }
}
