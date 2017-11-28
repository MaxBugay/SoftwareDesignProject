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
        JFrame f= new JFrame("Geometry Wars"); //New Window
        PlayingField field = new PlayingField(); //New Panel
        f.setContentPane(field); //Put panel in window
        field.setSize(1920,1080); //Size of Window
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit
        field.setVisible(true); //Visible Window
        while(true) {
            
        }
    }
    public static class PlayingField extends JPanel implements ActionListener{ 
        public int xPos = 540, yPos = 960;
        
        PlayingField(){
            JFrame f = new JFrame("Geometry Wars");
            f.setSize(1920, 1080);

            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JMenuBar jmb = new JMenuBar();

            JMenu jmFile = new JMenu("Main Menu");
            JMenuItem jmiStart = new JMenuItem("Start Game");
            JMenuItem jmiHighScore = new JMenuItem("High Scores");
            JMenuItem jmiExit = new JMenuItem("Exit");
            jmFile.add(jmiStart);
            jmFile.add(jmiHighScore);
            jmFile.addSeparator();
            jmFile.add(jmiExit);
            jmb.add(jmFile);

            jmiStart.addActionListener(this);
            jmiHighScore.addActionListener(this);
            jmiExit.addActionListener(this);

            f.setJMenuBar(jmb);
            f.setVisible(true);
            
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.BLUE);
            
            Graphics2D sprites = (Graphics2D) g;
            sprites.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Image playerShip, triangleShip, pentagonShip, octagonShip;
            playerShip = new ImageIcon("PlayerShip.png").getImage();
            triangleShip = new ImageIcon("TriangleShip.png").getImage();
            pentagonShip = new ImageIcon("pentagonShip.png").getImage();
            octagonShip = new ImageIcon("octagonShip.png").getImage();
            
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
            System.out.println(comStr + " Selected");
            if (comStr.equals("Start Game")) {
                Thread thread = new Thread("Game thread") {
                public void run() {
                    
                    
                }
            };
                thread.start();
                repaint();
            }
            else if (comStr.equals("High Score")) {
                
            }
            else if (comStr.equals("Exit")) {
                System.exit(0);
            }
                
        }
    }
}
