/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author Saif
 */
public class Enemy1 implements EnemyUnit{

    Graphics g;
    private int HP = 0;
    public Enemy1() {
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
        //paintComponent(g);
    }

    /*@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.GREEN);
        g.drawOval(500, 500, 15, 15);
        g.fillOval(500, 500, 15, 15);
    }*/
    
    @Override
    public void onDeath() {
        
    }

    public int getHP() {
        return HP;
    }
    
    
}
