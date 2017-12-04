/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import geometrywars.GeometryWars.PlayingField;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MaxBu
 */
public class GeometryWarsTest {
    
    PlayingField pf;
    int x, y;
    
    @Before
    public void setUp() {
        pf = new PlayingField();
        pf.setxPos(540);
        pf.setyPos(540);
        pf.firingDown = false;
        pf.firingUp = false;
        pf.firingLeft = false;
        pf.firingRight = false;
        x = pf.getxPos();
        y = pf.getyPos();
    }
    
    @Test
    public void isFiringDown() {
        pf.fireDown();
        assertTrue(pf.firingDown);
    }
    @Test
    public void isFiringUp() {
        pf.fireUp();
        assertTrue(pf.firingUp);
    }
    @Test
    public void isFiringLeft() {
        pf.fireLeft();
        assertTrue(pf.firingLeft);
    }
    @Test
    public void isFiringRight() {
        pf.fireRight();
        assertTrue(pf.firingRight);
    }
    @Test
    public void isMoveUp() {
        pf.moveUp();
        assertEquals(pf.yPos,y+10);
    }
    @Test
    public void isMoveDown() {
        pf.moveDown();
        assertEquals(pf.yPos,y-10);
    }
    @Test
    public void isMoveLeft() {
        pf.moveLeft();
        assertEquals(pf.xPos,x-10);
    }
    @Test
    public void isMoveRight() {
        pf.moveRight();
        assertEquals(pf.xPos,x+10);
    }

    /**
     * Test of main method, of class GeometryWars.
     */
    /*@Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        GeometryWars.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
