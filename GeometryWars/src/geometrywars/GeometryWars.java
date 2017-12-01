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
import javax.swing.*;

public class GeometryWars extends JApplet {
    public static class PlayingField extends JPanel implements ActionListener{ 
        public int xPos = 540, yPos = 540;
        private static final int PREF_W = 1080, PREF_H = 1080, SHIP_W = 15;;
        private JMenuBar jmb;
        private JMenu jmMainMenu;
        private JMenuItem jmiStart;
        private JMenuItem jmiHighScore;
        private JMenuItem jmiExit;
        private Font font = new Font("Arial", Font.ITALIC, 30);
        
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
            for (int i = 0; i < 10; i++) {
                rand1 = (int)(Math.random() * (1080));
                rand2 = (int)(Math.random() * (1080));
                g.setColor(Color.yellow);
                g.drawOval(rand1, rand2, SHIP_W, SHIP_W);
                g.fillOval(rand1, rand2, SHIP_W, SHIP_W);
                
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

                    xPos = newX;
                    yPos = newY;
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
    }
    public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    PlayingField.createField();
                }
            });
            Enemy1 triangle = new Enemy1();
            
        }
}
