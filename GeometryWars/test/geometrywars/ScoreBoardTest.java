/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author MaxBu
 */
public class ScoreBoardTest {
    
    ScoreBoard sb;
    String initials;
    int score; 

    public ScoreBoardTest() throws Exception {
        this.sb = new ScoreBoard();
    }
    
    protected void setUp() throws Exception{
       initials = "MAX";
       score = 500;
       sb.setInitials(initials);
       sb.setScore(score);
       sb.insertScoreBoardDB(initials);
       sb.selectScoreBoardDB();
    }
    
    @Test
    public void checkScore() {
        assertEquals(sb.score, score);
    }
    
    @Test
    public void checkInitials() {
        assertEquals(sb.initials, initials);
    }   
}
