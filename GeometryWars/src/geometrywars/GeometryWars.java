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
        new PlayingField(); //New Panel
        /*field.setContentPane(PlayingField); //Put panel in window
        field.setSize(1920,1080); //Size of Window
        field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit
        field.setVisible(true); //Visible Window*/
    }
    public static class PlayingField extends JPanel implements ActionListener{ 
        private int xPos, yPos;
        
        PlayingField(){
            JFrame f = new JFrame("Main Menu");
            f.setSize(1920, 1080);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //setBackground(Color.BLACK);
            JMenuBar jmb = new JMenuBar();
            JMenu MainMenu = new JMenu("Main Menu");
            JMenuItem jmiStart = new JMenu("Start Game");
            JMenuItem jmiHighScore = new JMenu("High Scores");
            JMenuItem jmiExit = new JMenu("Exit");
            MainMenu.add(jmiStart);
            MainMenu.add(jmiHighScore);
            MainMenu.add(jmiExit);
            jmb.add(MainMenu);
            jmiStart.addActionListener(this);
            jmiHighScore.addActionListener(this);
            jmiExit.addActionListener(this);
            f.setJMenuBar(jmb);
            f.setVisible(true);
            //addKeyListener((KeyListener) this);
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
        public void actionPerformed(ActionEvent ae) {
            String comStr = ae.getActionCommand();
        }
    }
}
