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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GeometryWars extends JApplet {
    public static class PlayingField extends JPanel implements ActionListener { 
        public int xPos = 540, yPos = 540;
        int i;
        private int[] enemyX = new int[10];
        private int[] enemyY = new int[10];
        private static final int PREF_W = 1080, PREF_H = 1080, SHIP_W = 15;;
        private Enemy1[] triangle = new Enemy1[10];
        private JMenuBar jmb;
        private JMenu jmMainMenu;
        private JMenuItem jmiStart;
        private JMenuItem jmiHighScore;
        private JMenuItem jmiExit;
        private Font font = new Font("Arial", Font.ITALIC, 30);
        private boolean secondWave = false;
        private boolean setSpawns = true;
        PlayingField(){
            jmb = new JMenuBar();
            jmMainMenu = new JMenu("Main Menu");
            jmiStart = new JMenuItem("Start Game");
            jmiHighScore = new JMenuItem("High Scores");
            jmiExit = new JMenuItem("Exit");
            
            jmMainMenu.add(jmiStart);
            jmMainMenu.add(jmiHighScore);
            jmMainMenu.addSeparator();
            jmMainMenu.add(jmiExit);
            jmb.add(jmMainMenu);

            jmiStart.addActionListener(this);
            jmiHighScore.addActionListener(this);
            jmiExit.addActionListener(this);
            
        }
        
        public JMenuBar getJMB() {
            return jmb;
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(PREF_W, PREF_H);
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.BLUE);
            if (setSpawns) {
                for (int x = 0; x < 10; x++) {
                    enemyX[x] = (int)(Math.random() * (1080));
                    enemyY[x] = (int)(Math.random() * (1080));
                }
                setSpawns = false;
            }
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(font);
            Image playerShip, triangleShip, pentagonShip, octagonShip;
            playerShip = new ImageIcon("PlayerShip.png").getImage();
            triangleShip = new ImageIcon("TriangleShip.png").getImage();
            pentagonShip = new ImageIcon("PentagonShip.png").getImage();
            octagonShip = new ImageIcon("OctagonShip.png").getImage();
            g.drawString("Test", 100, 200);
            g.drawImage(playerShip, SHIP_W, SHIP_W, this);
            g.setColor(Color.CYAN);
            g.drawOval(xPos, yPos, SHIP_W, SHIP_W);
            g.fillOval(xPos, yPos, SHIP_W, SHIP_W);
            int rand1, rand2;
            g.setColor(Color.yellow);
            for (int j = 0; j < 5; j++) {
                g.drawOval(enemyX[j], enemyY[j], SHIP_W, SHIP_W);
                    g.fillOval(enemyX[j], enemyY[j], SHIP_W, SHIP_W);
            }
            
            if (secondWave) {
                g.setColor(Color.GREEN);
                for (int i = 5; i < 10; i++) {
                    g.drawOval(enemyX[i], enemyY[i], SHIP_W, SHIP_W);
                    g.fillOval(enemyX[i], enemyY[i], SHIP_W, SHIP_W);
                }
                
            }
            
            addKeyBinding();
        }
        
        private void addKeyBinding() {
            int condition = WHEN_IN_FOCUSED_WINDOW;
            InputMap inputMap = getInputMap(condition);
            ActionMap actionMap = getActionMap();

            for (final KeyboardInput dir : KeyboardInput.values()) {
                KeyStroke keyStroke = KeyStroke.getKeyStroke(dir.getKeyCode(), 0);
                inputMap.put(keyStroke, dir.toString());
                actionMap.put(dir.toString(), new AbstractAction() {

                @Override
                    public void actionPerformed(ActionEvent evt) {
                    int newX = xPos + dir.getxTrans();
                    int newY = yPos + dir.getyTrans();
                    newX = Math.min(newX, PREF_W - 2 * SHIP_W);
                    newX = Math.max(newX, SHIP_W);
                    newY = Math.min(newY, PREF_H - 2 * SHIP_W);
                    newY = Math.max(newY, SHIP_W);
                    for (int j = 0; j < 10; j++) {
                        triangle[j] = new Enemy1(enemyX[j], enemyY[j]);
                    }
                    
                    for (int i = 0; i < 10; i++) {
                        if (xPos >= enemyX[i]) {
                            enemyX[i] += 3;
                            triangle[i].setxPosition(enemyX[i]);
                        }
                        else {
                            enemyX[i] -= 3;
                            triangle[i].setxPosition(enemyX[i]);
                        }
                        if (yPos >= enemyY[i]) {
                            enemyY[i] += 3;
                            triangle[i].setyPosition(enemyY[i]);
                        }
                        else {
                            enemyY[i] -= 3;
                            triangle[i].setyPosition(enemyY[i]);
                        }
                    }
                    
                    xPos = newX;
                    yPos = newY;
                    for (int i = 0; i < 10; i++) {
                        if (xPos >= enemyX[i] - 3 && xPos <= enemyX[i] + 3) 
                            if (yPos >= enemyY[i] - 3 && yPos <= enemyY[i] + 3) {
                                System.out.println("you died kid");
                                secondWave = true;
                            }
                    }
                    repaint();
                    }
                });
            }
        }
        enum KeyboardInput {
            
            W(KeyEvent.VK_W, 0, -5), S(KeyEvent.VK_S, 0, 5), 
            A(KeyEvent.VK_A, -5, 0), D(KeyEvent.VK_D, 5, 0);
            
            //UP(KeyEvent.VK_UP, 0, -5), DOWN(KeyEvent.VK_DOWN, 0, 5), 
            //LEFT(KeyEvent.VK_LEFT, -5, 0), RIGHT(KeyEvent.VK_RIGHT, 5, 0);

            private int keyCode;
            private int xTrans;
            private int yTrans;

            private KeyboardInput(int keyCode, int xTrans, int yTrans) {
                this.keyCode = keyCode;
                this.xTrans = xTrans;
                this.yTrans = yTrans;
            }   

            public int getKeyCode() {
                return keyCode;
            }

            public int getxTrans() {
                return xTrans;
            }

            public int getyTrans() {
                return yTrans;
            }
        }
        
        private static void createField() {
            PlayingField field = new PlayingField();
            JFrame frame = new JFrame("Geometry Wars");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(field);
            frame.setJMenuBar(field.getJMB());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            String comStr = ae.getActionCommand();
            System.out.println(comStr + " Selected");
            switch (comStr) {
                case "Start Game":
                    Thread thread = new Thread("Game thread") {
                        public void run() {
                            repaint();
                        }
                    };
                    thread.start();
                    break;
                case "High Score":
                    break;
                case "Exit":
                    System.exit(0);
                default:
                    break;
            }
        }
        /*public void enemy() {
            Thread t = new Thread(this);
            t.start();
            Thread w = new Thread(this);
            w.start();
        }
        @Override
        public void run() {
            Enemy1 triangle = new Enemy1(enemy1X, enemy1Y);
                   while (triangle.getHP() > 0) {
                       if (triangle.getxPosition() > xPos){
                       enemy1X += 5;
                       triangle.setxPosition(enemy1X);
                   }
                   else {
                       enemy1X -= 5;
                       triangle.setxPosition(enemy1X);
                   }
                   if (triangle.getyPosition() > yPos) {
                       enemy1Y += 5;
                       triangle.setyPosition(enemy1Y);
                   }
                   else {
                       enemy1Y -= 5;
                       triangle.setyPosition(enemy1Y);
                   }
                   repaint();
                       //System.out.println(this.getxPos());
                }
        }*/
    }
    
    
    
    public static void main(String[] args) {
        PlayingField g = new PlayingField();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    g.createField();
                }
            });
            //g.enemy();            
        }
}
