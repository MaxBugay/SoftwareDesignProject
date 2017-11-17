/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

/**
 *
 * @author Saif
 */
public interface EnemyUnit {
    int HP = 0;
    
    /**
     * Triggers when enemy unit takes a hit from playerUnit
     */
    public void onHit();
    
    /**
     * Creation method
     */
    public void onCreate();
    
    /**
     * Unit removes from playing field
     */
    public void onDeath();
}
