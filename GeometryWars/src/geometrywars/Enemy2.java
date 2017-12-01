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
    public Enemy2() {
        onCreate();
    }

    @Override
    public void onHit() {
        HP = 0;
        onDeath();
    }

    @Override
    public void onCreate() {
        HP = 1;
    }

    @Override
    public void onDeath() {
        
    }

    public int getHP() {
        return HP;
    }
}
