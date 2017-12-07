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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Saif
 */
public class Enemy1 extends JPanel implements EnemyUnit, Runnable {

    private int xPosition;
    private int yPosition;
    private int playerX, playerY;
    Graphics g;
    private int HP = 0;
    
    public Enemy1() {
        xPosition = (int)(Math.random() * (1080));
        yPosition = (int)(Math.random() * (1080));
        onCreate();
    }
    
    public Enemy1(int xPos, int yPos) {
        xPosition = xPos;
        yPosition = yPos;
        this.playerX = playerX;
        this.playerY = playerY;
        onCreate();
    }

    @Override
    public void onHit() {
        
        this.onDeath();
    }

    @Override
    public void onCreate() {
        HP = 1;
        Thread t = new Thread(this);
        //t.start();
    }
    
    @Override
    public void run() {
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setColor(Color.GREEN);
        g.drawOval(xPosition, yPosition, 15, 15);
        g.fillOval(xPosition, yPosition, 15, 15);
        while (HP > 0) {
            System.out.println("Memes");
            if (playerX > xPosition)
                xPosition += 5;
            else
                xPosition -=5;
            if (playerY > yPosition)
                yPosition += 5;
            else
                yPosition -= 5;
        }
    }
    
    @Override
    public void onDeath() {
        this.HP = 0;
    }

    public int getHP() {
        return HP;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    
    
}
