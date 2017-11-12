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
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GeometryWars extends JApplet {
    public static void main(String[] args) {
        JFrame field = new JFrame("Geometry Wars"); //New Window
        PlayingField PlayingField = new PlayingField(); //New Panel
        field.setContentPane(PlayingField);//Put panel in window
        field.setSize(1920,1080); //Size of Window
        field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit
        field.setVisible(true); //Visible Window
        field.setBackground(Color.BLACK);
    }
    public static class PlayingField extends JPanel implements KeyListener{ 
        private int xPos, yPos;
        
        PlayingField() {
            setBackground(Color.BLACK);
            addKeyListener(this);
        }
        public void fieldComponent(Graphics g) {
            //super.fieldComponent(g);
            
        }
        /*public getX(int x) {
            xPos = this.x;
            return xPos;
        }
        public getY(int y) {
            yPos = this.y;
            return yPos;
        }
        public void setX(int x) {
            xPos = this.x;
        }
        public void setY {
            yPos = this.x;
        }*/

        @Override
        public void keyTyped(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
