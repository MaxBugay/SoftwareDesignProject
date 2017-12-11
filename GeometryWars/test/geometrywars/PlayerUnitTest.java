/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import junit.framework.TestCase;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Saif
 */
public class PlayerUnitTest extends TestCase {
    PlayerUnit p;
     public PlayerUnitTest() {
        
    }
    
    @Before
    public void setUp() {
        p = new PlayerUnit();
    }

    /**
     * Test of onCreate method, of class Enemy1.
     */
    @Test
    public void testOnCreate() {
        p.onCreate();
        assertTrue(p.getLives() == 3);
    }

    /**
     * Test of onHit method, of class Enemy1.
     */
    @Test
    public void testOnHit() {
        p.onCreate();
        p.onHit();
        assertTrue(p.getLives() == 2);
    }

    /**
     * Test of onDeath method, of class Enemy1.
     */
    @Test
    public void testOnDeath() {
        p.onCreate();
        p.onHit();
        p.onHit();
        p.onHit();
        p.onDeath();
        assertTrue(p.isGameOver() == true);
    }
    
}
