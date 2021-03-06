/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Saif
 */
public class Enemy1Test {
    
    Enemy1 T;
    public Enemy1Test() {
        
    }
    
    @Before
    public void setUp() {
        T = new Enemy1();
        T.onCreate();
    }

    /**
     * Test of onCreate method, of class Enemy1.
     */
    @Test
    public void testOnCreate() {
        T.onCreate();
        System.out.println("HP = after creation" + T.getHP() + "\n");
        assertTrue(T.getHP() == 1);
    }

    /**
     * Test of onHit method, of class Enemy1.
     */
    @Test
    public void testOnHit() {
        T.onHit();
        System.out.println("HP after hit = " + T.getHP() + "\n");
        assertTrue(T.getHP() == 0);
    }

    /**
     * Test of onDeath method, of class Enemy1.
     */
    @Test
    public void testOnDeath() {
        T.onHit();
        System.out.println("HP after death = " + T.getHP() + "\n");
        assertTrue(T.getHP() == 0);
    }
}
