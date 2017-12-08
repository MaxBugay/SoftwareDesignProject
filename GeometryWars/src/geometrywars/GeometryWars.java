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
    public static class PlayingField extends JPanel implements ActionListener, Runnable { 
        public int xPos = 540, yPos = 540, xBullet, yBullet;
        boolean firingUp = false, firingDown = false, firingLeft = false, firingRight = false;;
        private int[] enemyX = new int[10];
        private int[] enemyY = new int[10];
        private static final int PREF_W = 1080, PREF_H = 1080, SHIP_W = 40, BULLET_W = 20;
        private Enemy1[] triangle = new Enemy1[5];
        private Enemy2[] square = new Enemy2[5];
        private Enemy3[] pentagon = new Enemy3[5];
        private JMenuBar jmb;
        private JMenu jmMainMenu;
        private JMenuItem jmiStart;
        private JMenuItem jmiHighScore;
        private JMenuItem jmiExit;
        private Font font = new Font("Arial", Font.ITALIC, 30);
        private boolean secondWave = false;
        private boolean setSpawns = true;
        private int[] checkShot = new int [4];
        private int[] startShotX = new int[4];
        private int[] startShotY = new int[4];
        private boolean activeBullet = false;
        private int score = 0, spawn = -1;
        private boolean secondStart = false;
        private boolean gameOver = false;
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

        public int getxPos() {
            return xPos;
        }

        public int getyPos() {
            return yPos;
        }

        public void setxPos(int xPos) {
            this.xPos = xPos;
        }

        public void setyPos(int yPos) {
            this.yPos = yPos;
        }

        public void fireUp() {
            this.firingUp = true;
        }

        public void fireDown() {
            this.firingDown = true;
        }

        public void fireLeft() {
            this.firingLeft = true;
        }

        public void fireRight() {
            this.firingRight = true;
        }
        
        public void moveRight() {
            this.xPos = xPos + 10;
        }

        public void moveUp() {
            this.yPos = yPos + 10;
        }
        
        public void moveLeft() {
            this.xPos = xPos - 10;
        }

        public void moveDown() {
            this.yPos = yPos - 10;
        }
        
        
        public JMenuBar getJMB() {
            return jmb;
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(PREF_W, PREF_H);
        }
        
        public void fireRate(boolean isFiring) 
        {
            for (int i = 0; i < 5; i++)
            {
                repaint();
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.DARK_GRAY);
            if (setSpawns) {
                for (int x = 0; x < 10; x++) {
                    enemyX[x] = (int)(Math.random() * (1080));
                    enemyY[x] = (int)(Math.random() * (1080));
                }
                setSpawns = false;
                for (int j = 0; j < 5; j++) {
                    triangle[j] = new Enemy1(enemyX[j], enemyY[j]);
                    if (j < 4)
                        checkShot[j] = 0;
                }
                Thread t = new Thread(this);
                t.start();
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
            //g2.drawString("Test", 100, 200);
            g2.drawImage(playerShip, xPos, yPos, SHIP_W, SHIP_W, this);
            g2.drawImage(triangleShip, xPos, yPos, SHIP_W, SHIP_W, this);
            g2.setColor(Color.CYAN);
            g2.drawOval(xPos - 10, yPos - 10, SHIP_W, SHIP_W);
            g2.fillOval(xPos - 10, yPos - 10, SHIP_W, SHIP_W);
            String scoreS = Integer.toString(score);
            g2.drawString(scoreS, 100, 200);
            g2.setColor(Color.yellow);
            for (int j = 0; j < 5; j++) {
                if (triangle[j].getHP() == 1){
                    g.drawOval(enemyX[j], enemyY[j], SHIP_W/2, SHIP_W/2);
                    g.fillOval(enemyX[j], enemyY[j], SHIP_W/2, SHIP_W/2);
                }
                else {
                    triangle[j].setxPosition((int)(Math.random() * (-1080)));
                    triangle[j].setyPosition((int)(Math.random() * (-1080)));
                    enemyX[j] = triangle[j].getxPosition();
                    enemyY[j] = triangle[j].getyPosition();
                }
                
                //g2.drawImage(triangleShip, xPos, yPos, SHIP_W, SHIP_W, this);
            }
            if (gameOver) {
                g2.setColor(Color.magenta);
                g2.drawString("LOL YOU DIED", 500, 500);
            }
            int scoreCheck = score;
            if (scoreCheck >= 50) {
                for (int i = 0; i < 5; i++) {
                    triangle[i].onCreate();
                }
                scoreCheck = 0;
                secondWave = true;
                spawn++;
            }
            
            if (spawn == 0) {
                for (int i  = 0; i < 5; i++, spawn++) {
                    square[i] = new Enemy2(enemyX[i+5], enemyY[i+5]);
                }
            }
            
            
            if (secondWave) {
                g.setColor(Color.GREEN);
                int j = 5;
                for (int i = 0; i < 5; i++) {
                    if (square[i].getHP() == 1) {
                    g.drawOval(enemyX[j], enemyY[j], SHIP_W, SHIP_W);
                    g.fillOval(enemyX[j], enemyY[j], SHIP_W, SHIP_W);
                    }
                    else {
                        triangle[j].setxPosition((int)(Math.random() * (-1080)));
                        triangle[j].setyPosition((int)(Math.random() * (-1080)));
                        enemyX[j] = triangle[j].getxPosition();
                        enemyY[j] = triangle[j].getyPosition();
                    }
                    j++;
                }
            }
            if (firingUp)
            {
                if (checkShot[0] == 0) {
                    startShotX[0] = xPos;
                    startShotY[0] = yPos;
                }
                xBullet = xPos + 20;
                yBullet = yPos + 20;
                if (activeBullet)
                    g.setColor(Color.RED);
                else 
                    g.setColor(Color.YELLOW);
                    g.drawOval(startShotX[0],startShotY[0] - checkShot[0],BULLET_W, BULLET_W);
                    g.fillOval(startShotX[0],startShotY[0] - checkShot[0],BULLET_W, BULLET_W);
                checkShot[0] += 10;
                if (checkShot[0] == 100) {
                    checkShot[0] = 0;
                    firingUp = false;
                    startShotX[0] = -500;
                    startShotY[0] = -500;
                }
            }
            if (firingDown)
            {
                if (checkShot[1] == 0) {
                    startShotX[1] = xPos;
                    startShotY[1] = yPos;
                }
                xBullet = xPos + 20;
                yBullet = yPos + 20;
                if (activeBullet)
                    g.setColor(Color.RED);
                else 
                    g.setColor(Color.yellow);

                    g.drawOval(startShotX[1],startShotY[1] + checkShot[1],BULLET_W, BULLET_W);
                    g.fillOval(startShotX[1],startShotY[1] + checkShot[1],BULLET_W, BULLET_W);

                checkShot[1] += 10;
                if (checkShot[1] == 100) {
                    checkShot[1] = 0;
                    firingDown = false;
                    startShotX[1] = -500;
                    startShotY[1] = -500;
                }
            }
            if (firingLeft)
            {
                if (checkShot[2] == 0) {
                    startShotX[2] = xPos;
                    startShotY[2] = yPos;
                }
                xBullet = xPos + 20;
                yBullet = yPos + 20;
                if (activeBullet)
                    g.setColor(Color.RED);
                else 
                    g.setColor(Color.yellow);
                    g.drawOval(startShotX[2] - checkShot[2],startShotY[2],BULLET_W, BULLET_W);
                    g.fillOval(startShotX[2] - checkShot[2],startShotY[2],BULLET_W, BULLET_W);
                checkShot[2] += 10;
                if (checkShot[2] == 100) {
                    checkShot[2] = 0;
                    firingLeft = false;
                    startShotX[2] = -500;
                    startShotY[2] = -500;
                }
            }
            if (firingRight)
            {
                if (checkShot[3] == 0) {
                    startShotX[3] = xPos;
                    startShotY[3] = yPos;
                }
                xBullet = xPos + 20;
                yBullet = yPos + 20;
                if (activeBullet)
                    g.setColor(Color.RED);
                else 
                    g.setColor(Color.yellow);
                    g.drawOval(startShotX[3] + checkShot[3],startShotY[3],BULLET_W, BULLET_W);
                    g.fillOval(startShotX[3] + checkShot[3], startShotY[3],BULLET_W, BULLET_W);
                checkShot[3] += 10;
                if (checkShot[3] == 100) {
                    checkShot[3] = 0;
                    firingRight = false;
                    startShotX[3] = -500;
                    startShotY[3] = -500;
                }
            }
            addKeyBinding();
            addKeyBinding2();
        }
        
        @Override
        public void run() {
                while (1 > 0) {
                   for (int i = 0; i < 5; i++) {
                        if (xPos >= enemyX[i]) {
                            enemyX[i] += 5;
                            triangle[i].setxPosition(enemyX[i]);
                        }
                        else {
                            enemyX[i] -= 5;
                            triangle[i].setxPosition(enemyX[i]);
                        }
                        if (yPos >= enemyY[i]) {
                            enemyY[i] += 5;
                            triangle[i].setyPosition(enemyY[i]);
                        }
                        else {
                            enemyY[i] -= 5;
                            triangle[i].setyPosition(enemyY[i]);
                        }
                       try {
                           Thread.sleep(15);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(GeometryWars.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       for (int k = 0; k < 5; k++) {
                            if (startShotX[0] >= enemyX[k] - 10 && startShotX[0] <= enemyX[k] + 10) {
                                if (startShotY[0] - checkShot[0] >= enemyY[k] - 10 && startShotY[0] - checkShot[0] <= enemyY[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotX[1] >= enemyX[k] - 10 && startShotX[1] <= enemyX[k] + 10) {
                                if (startShotY[1] + checkShot[1] >= enemyY[k] - 10 && startShotY[1] + checkShot[1] <= enemyY[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotY[2] >= enemyY[k] - 10 && startShotY[2] <= enemyY[k] + 10) {
                                if (startShotX[2] - checkShot[2] >= enemyX[k] - 10 && startShotX[2] - checkShot[2] <= enemyX[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotY[3] >= enemyY[k] - 10 && startShotY[3] <= enemyY[k] + 10) {
                                if (startShotX[3] + checkShot[3] >= enemyX[k] - 10 && startShotX[3] + checkShot[3] <= enemyX[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                        }
                        repaint();
                    }
                }
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
                    activeBullet = false;
                    
                    
                    xPos = newX;
                    yPos = newY;
                    for (int i = 0; i < 5; i++) {
                        if (xPos >= enemyX[i] - 10 && xPos <= enemyX[i] + 10) 
                            if (yPos >= enemyY[i] - 10 & yPos <= enemyY[i] + 10) {
                                gameOver = true;
                                System.out.println("meme");
                            }
                    }
                    repaint();
                    }
                });
            }
        }
        enum KeyboardInput {
            
            W(KeyEvent.VK_W, 0, -10), S(KeyEvent.VK_S, 0, 10), 
            A(KeyEvent.VK_A, -10, 0), D(KeyEvent.VK_D, 10, 0);

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
        
        private void addKeyBinding2() {
            int condition = WHEN_IN_FOCUSED_WINDOW;
            InputMap inputMap = getInputMap(condition);
            ActionMap actionMap = getActionMap();

            for (final KeyboardInput2 dir : KeyboardInput2.values()) {
                KeyStroke keyStroke = KeyStroke.getKeyStroke(dir.getKeyCode(), 0);
                inputMap.put(keyStroke, dir.toString());
                actionMap.put(dir.toString(), new AbstractAction() {

                @Override
                    public void actionPerformed(ActionEvent evt) {
                        activeBullet = true;

                        for (int k = 0; k < 5; k++) {
                            if (startShotX[0] >= enemyX[k] - 10 && startShotX[0] <= enemyX[k] + 10) {
                                if (startShotY[0] - checkShot[0] >= enemyY[k] - 10 && startShotY[0] - checkShot[0] <= enemyY[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotX[1] >= enemyX[k] - 10 && startShotX[1] <= enemyX[k] + 10) {
                                if (startShotY[1] + checkShot[1] >= enemyY[k] - 10 && startShotY[1] + checkShot[1] <= enemyY[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotY[2] >= enemyY[k] - 10 && startShotY[2] <= enemyY[k] + 10) {
                                if (startShotX[2] - checkShot[2] >= enemyX[k] - 10 && startShotX[2] - checkShot[2] <= enemyX[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                            if (startShotY[3] >= enemyY[k] - 10 && startShotY[3] <= enemyY[k] + 10) {
                                if (startShotX[3] + checkShot[3] >= enemyX[k] - 10 && startShotX[3] + checkShot[3] <= enemyX[k] + 10) {
                                    triangle[k].onHit();
                                    score += 10;
                                }
                            }
                        }
                    
                        int key = dir.getKeyCode();

                        if (key == KeyEvent.VK_DOWN) {
                            firingDown = true;
                        }
                        if (key == KeyEvent.VK_UP) {
                            firingUp = true;
                        }
                        if (key == KeyEvent.VK_RIGHT) {
                            firingRight = true;
                        }
                        if (key == KeyEvent.VK_LEFT) {
                            firingLeft = true;
                        }
        
                        repaint();
                    }
                });
            }
        }
        enum KeyboardInput2 {
            
            UP(KeyEvent.VK_UP, 0, -10), DOWN(KeyEvent.VK_DOWN, 0, 10), 
            LEFT(KeyEvent.VK_LEFT, -10, 0), RIGHT(KeyEvent.VK_RIGHT, 10, 0);

            private int keyCode;
            private int xTrans;
            private int yTrans;

            private KeyboardInput2(int keyCode, int xTrans, int yTrans) {
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
