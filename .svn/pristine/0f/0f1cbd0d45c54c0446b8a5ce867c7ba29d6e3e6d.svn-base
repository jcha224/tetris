/**
 * TCSS 305 - SPRING 2016
 * Assignment 6 - Tetris
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.Timer;


import model.Board;
import model.Point;
import model.TetrisPiece;

/**
 * This class contains the implementation of the tetris GUI.
 * @author John Chang
 * @version 24 May 2016
 */
public class TetrisGUI {

    /** The preferred window size. */
    private static final int WIDTH = 300;
    
    /** The preferred winder size. */
    private static final int LENGTH = 600;

    /** The instructions panel dimensions. */
    private static final int PANEL_SIZE = 200;
    
    /** Where the board begins. */
    private static final int FIVE = 5;
    
    /** The timer delay. */
    private static final int TIMER_DELAY = 1000;

    /** The next piece width. */
    private static final int NEXT_PIECE_WIDTH = 55;

    /** The next piece height. */
    private static final int NEXT_PIECE_HEIGHT = 70;

    /** The I piece width. */
    private static final int I_PIECE_WIDTH = 45;

    /** The I piece height. */
    private static final int I_PIECE_HEIGHT = 60;
    
    /** The timer level adjustment. */
    private static final int TIMER_LEVEL = 100;

    /** The width of large size. */
    private static final int FIFTEEN = 15;

    /** The length of large size. */
    private static final int THIRTY = 30;
    
    /** Timer for the Tetris. */
    private final Timer myTimer;
    
    /** Board object. */
    private Board myBoard;
    
    /** Frame that contains entire tetris game. */
    private final JFrame myFrame;
    
    /** Tetris board text. */
    private String[] myBlocks;
  
    /** The next tetris piece. */
    private TetrisPiece myNextPiece;
    
    /** The score description of current game. */
    private final JLabel myScore;
    
    /** The total score. */
    private int myTotalScore;
    
    /** The game active status. */
    private boolean myStatus;
    
    /** The score level. */
    private int myLevel;
    
    /** The pause status. */
    private boolean myPause;
    
    /** The new game menu item. */
    private JMenuItem myNewGame;
    
    /** The end game menu item. */
    private JMenuItem myEndGame;
    
    /** The original board size. */
    private JRadioButtonMenuItem myOrig;
    
    /** The large board size. */
    private JRadioButtonMenuItem myBig;

    
    /**
     * Constructor of the TetrisGUI.
     */
    public TetrisGUI() {
        myFrame = new JFrame("Tetris");
        myTimer = new Timer(TIMER_DELAY, new TimerActionListener());
        myBoard = new Board();
        myBlocks = new String[0];
        myNextPiece = TetrisPiece.getRandomPiece();
        myScore = new JLabel();
        myLevel = 0;
        setup();
    }
    
    /** 
     * Helper method to instantiate fields.
     */
    private void setup() {
        myOrig = new JRadioButtonMenuItem("10 X 20", true);
        myBig  = new JRadioButtonMenuItem("15 X 30", false);
        
        myTotalScore = 0;
        myStatus = false;
        myPause = false;
        myNewGame = new JMenuItem("New Game");
        myEndGame = new JMenuItem("End Game");
    }
    
    /**
     * Begins the Tetris GUI.
     */
    public void start() {
        final TetrisPanel gamePanel = new TetrisPanel();
        myFrame.setLayout(new BorderLayout());
        myFrame.setJMenuBar(menuBar());
        gamePanel.setBackground(Color.WHITE);
        
        gamePanel.setPreferredSize(new Dimension(WIDTH + 1, LENGTH));
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setScore(0);
        
        final JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        final InfoPanel nextPiece = new InfoPanel();
        final JPanel instruct = new JPanel();
        final JPanel scorePanel = new JPanel();
        
        setPanel(scorePanel);
        setPanel(nextPiece);
        setPanel(instruct);
        
        scorePanel.add(myScore);
        instruct.add(getInstructions());
        rightPanel.add(nextPiece);
        rightPanel.add(scorePanel);
        rightPanel.add(instruct);
        
        myFrame.add(rightPanel, BorderLayout.EAST);
        myFrame.add(gamePanel, BorderLayout.WEST);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setVisible(true);    
    }
    
    /**
     * This method draws the tetris game board.
     * @param theG2d the graphics of the panel
     */
    private void drawBoard(final Graphics2D theG2d) {
        theG2d.setStroke(new BasicStroke(2));
        int y = 0;
        for (int i = FIVE; i < myBlocks.length - 1; i++) {
            int x = 0;
            final String temp = myBlocks[i];
            for (int j = 1; j < temp.length() - 1; j++) {
                final Shape block = new Rectangle2D.Double(x * 1.0, y * 1.0, 
                                        WIDTH / myBoard.getWidth(), 
                                        LENGTH / myBoard.getHeight());
                theG2d.setPaint(Color.BLACK);
                theG2d.draw(block);
                theG2d.setPaint(getColor(temp.charAt(j)));
                theG2d.fill(block);
                x += WIDTH / (int) myBoard.getWidth();
            }
            y += LENGTH / (int) myBoard.getHeight();
        }
    }
    
    /**
     * Writes instructions on the panel.
     * @return the instructions label
     */
    private JLabel getInstructions() {
        final JLabel label = new JLabel("<html>Left Arrow = move left <br>"
                        + "Right Arrow = move right <br>"
                        + "Down Arrow = move down <br>"
                        + "Up Arrow = rotate clockwise <br>"
                        + "z = rotate counter clockwise <br>"
                        + "p = pause <br>"
                        + "Space = drop<html>");
        return label;
    }
    
    /**
     * Writes score panel. 
     * @param theScore the number of lines cleared
     */
    private void setScore(final int theScore) {
        if (theScore == FIVE - 1) {
            myTotalScore += 2 * theScore * TIMER_LEVEL;
        } else {
            myTotalScore += theScore * TIMER_LEVEL;
        }
        myLevel += theScore;
        setTimer(true);
        myScore.setText("<html>Score = " + myTotalScore + " <br><br><br> "
                        + "Total number of lines = " + myLevel + " <br><br><br>"
                        + "Total Levels = " + (myLevel / FIVE) + "<br><br><br>"
                        + "Five clears for each level..<br>" 
                        + "Number of clears = " + (myLevel % FIVE) + "<br>"
                        + "<html>");
    }
    
    /**
     * Modifies timer for levels.
     * @param theGame used to restart timer
     */
    private void setTimer(final boolean theGame) {
        final int temp = myTimer.getDelay();
        if (temp > FIVE * 2) {
            if (temp > TIMER_LEVEL) {
                myTimer.setDelay(temp - TIMER_LEVEL / 2);
            } else {
                myTimer.setDelay(temp - FIVE);
            }
        }
        if (!theGame) { 
            myTimer.setDelay(TIMER_DELAY);
        }
    }
    
    /**
     * Sets the panel dimensions and border.
     * @param thePanel the panel that is changed
     */
    private void setPanel(final JPanel thePanel) {
        thePanel.setBackground(Color.WHITE);
        thePanel.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        thePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    /**
     * This method creates the next tetris piece panel.
     * @param theG2D the graphics of the panel
     */
    private void drawNextPiece(final Graphics2D theG2D) {
        final Point[] next = myNextPiece.getPoints();
        for (int i = 0; i < next.length; i++) {
            int tempX = NEXT_PIECE_WIDTH;
            int tempY = NEXT_PIECE_HEIGHT;
            int y = next[i].y();
            if (y == 2) {
                y = 0;
            } 
            if (myNextPiece.getBlock().getChar() == 'I') {
                y = y + 1;
                tempX = I_PIECE_WIDTH;
                tempY = I_PIECE_HEIGHT;
            } else if (myNextPiece.getBlock().getChar() == '0') {
                tempX = I_PIECE_WIDTH;
            }
            final Shape block = new Rectangle2D.Double(tempX + next[i].x() * 30.0, 
                                           tempY + y * 30.0, 30.0, 30.0);
            theG2D.draw(block);
        }
    }
    
    /** 
     * Creates the menu bar.
     * @return the menu bar
     */
    private JMenuBar menuBar() {
        final JMenuBar menu = new JMenuBar();
        final JMenu file = new JMenu("File");
        final JMenu score = new JMenu("Score");
        final JMenuItem scoreScreen = new JMenuItem("Scoring");
        final JMenu size = new JMenu("Size");
        
        myOrig.addActionListener(sizeOptions(true));
        myBig.addActionListener(sizeOptions(false));
        
        size.add(myOrig);
        size.add(myBig);
        
        myNewGame.addActionListener(gameStart(true));
        myEndGame.addActionListener(gameStart(false));
        scoreScreen.addActionListener(scoreWindow());
        score.add(scoreScreen);
        file.add(myNewGame);
        file.add(myEndGame);
        myEndGame.setEnabled(false);
        
        menu.add(file);
        menu.add(size);
        menu.add(score);
        
        return menu;
    }
    
    /** 
     * The radio buttons for grid size.
     * @param theSize false for large true for original
     * @return the radio button
     */
    private ActionListener sizeOptions(final boolean theSize) {
        final ActionListener temp = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (theSize) {
                    myOrig.setSelected(true);
                    myBig.setSelected(false);
                    myBoard.deleteObservers();
                    myBoard = new Board();
                } else { 
                    myBig.setSelected(true);
                    myOrig.setSelected(false);
                    myBoard.deleteObservers();
                    myBoard = new Board(FIFTEEN, THIRTY);
                }
            }
        };
        return temp;
    }
    
    /**
     * Action listener opens window for the scoring menu item.
     * @return the action listener
     */
    private ActionListener scoreWindow() {
        return new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, "The total score is incremented "
                                + "by 100 for each line cleared and 800 if you clear "
                                + "4 lines in a row.\n" 
                                + "In order to reach the next level you must clear"
                                + "5 lines.\nThe speed of the falling tetris pieces"
                                + " gradually get faster with each level. \n"
                                + "The game ends when a tetris piece crosses the top"
                                + " of the tetris board.",
                                "Scoring Details", 
                                JOptionPane.PLAIN_MESSAGE, null);
            }
        };
    }

    /**
     * Action listener for new and end game menu items.
     * @param theStatus differentiate new and end game
     * @return the action listener
     */
    private ActionListener gameStart(final boolean theStatus) {
        final ActionListener temp = new ActionListener() {
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                if (theStatus) {
                    myStatus = true;
                    myBoard.addObserver(gameStatus());
                    myFrame.addKeyListener(new TetrisKeyListener());
                    myBoard.newGame();
                    setTimer(false);
                    myTimer.start();
                    myEndGame.setEnabled(true);
                    myNewGame.setEnabled(false);
                    sizeStatus(false);
                } else {
                    openGameOver();
                }
            }
        };
        return temp;
    }
    
    /** 
     * Size button set-enabled method. 
     * @param theBool the button status
     */
    private void sizeStatus(final boolean theBool) {
        myOrig.setEnabled(theBool);
        myBig.setEnabled(theBool);
    }
    /**
     * Pauses and unpauses the game.
     * @param theStatus true for pause false for unpause
     */
    private void pause(final boolean theStatus) {
        if (myStatus) {
            if (theStatus) {
                myTimer.stop();
                myBoard.deleteObservers();
                myPause = true;
            } else {
                myTimer.start();
                myBoard.addObserver(gameStatus());
                myPause = false;
            }
        }
    }
    
    /**
     * Pop up window for when the game ends.
     */
    private void openGameOver() {
        sizeStatus(true);
        myTimer.stop();
        myStatus = false;
        myEndGame.setEnabled(false);
        myNewGame.setEnabled(true);
        myFrame.removeKeyListener(myFrame.getKeyListeners()[0]);
        myBoard.deleteObservers();
        JOptionPane.showMessageDialog(null, "TCSS 305 Tetris" 
                        + "\n Spring 2016 \n John Chang", "GAME OVER", 
                        JOptionPane.PLAIN_MESSAGE, 
                        new ImageIcon("C:\\Users\\John\\305\\username-tetris"
                                      + "\\images\\gameover.gif"));
    }
    
    /**
     * This method returns the color of each block.
     * @param theChar the block type identifier
     * @return the block color
     */
    private Color getColor(final char theChar) {
        Color result = null;
        if (theChar == 'I') {
            result = Color.YELLOW;
        } else if (theChar == 'J') {
            result = Color.CYAN;
        } else if (theChar == 'L') {
            result = Color.GREEN;
        } else if (theChar == '0') {
            result = Color.BLUE;
        } else if (theChar == 'S') {
            result = Color.ORANGE;
        } else if (theChar == 'T') {
            result = Color.PINK;
        } else if (theChar == 'Z') {
            result = Color.RED;
        } else {
            result = Color.WHITE;
        }
        return result;
    }
    
    /** 
     * The observer views the tetris board.
     * @return the observer
     */
    private Observer gameStatus() {
        final Observer temp = new Observer() {
            
            @Override
            public void update(final Observable theBoard, final Object theBlocks) {
                //System.out.println(theBoard);
                if (theBlocks instanceof String) {
                    myBlocks = theBlocks.toString().split("\n");
                    myFrame.repaint();
                } else if (theBlocks instanceof TetrisPiece) {
                    
                    myNextPiece = (TetrisPiece) theBlocks;
                } else if (theBlocks instanceof Boolean) {
                        openGameOver();
                } else if (theBlocks instanceof Integer[]) {
                    setScore(((Integer[]) theBlocks).length);
                }
            }
        };
        return temp;
    }
    
    /**
     * The speed of the board is dependent on this timer.
     * @author John Chang
     */
    private class TimerActionListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();
        }
    }
    
    /**
     * The panel the tetris board is painted on.
     * @author John Chang
     */
    private class TetrisPanel extends JPanel {
        /** Generated serial version ID. */
        private static final long serialVersionUID = 691058644342210451L;

        @Override 
        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            drawBoard(g2d);
        }
    }
    
    /**
     * The panel that contains the tetris information. 
     * @author John Chang
     */
    private class InfoPanel extends JPanel {
        /** Generated serial version ID. */
        private static final long serialVersionUID = -4052125310931666480L;
       
        @Override
        public void paintComponent(final Graphics theGraphics) {
            super.paintComponent(theGraphics);
            final Graphics2D g2d = (Graphics2D) theGraphics;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                 RenderingHints.VALUE_ANTIALIAS_ON);
            drawNextPiece(g2d);
        }
    }
    
    /**
     * Key listener moves tetris piece.
     * @author John Chang
     */
    private class TetrisKeyListener extends KeyAdapter {
       
        @Override
        public void keyPressed(final KeyEvent theEvent) {          
            if (!myPause && myStatus) {
                switch (theEvent.getKeyCode()) {
                    case KeyEvent.VK_Z: myBoard.rotateCCW();
                    break;
                    case KeyEvent.VK_DOWN: myBoard.down();
                    break;
                    case KeyEvent.VK_LEFT: myBoard.left();
                    break;
                    case KeyEvent.VK_RIGHT: myBoard.right();
                    break;
                    case KeyEvent.VK_SPACE: myBoard.drop();
                    break;
                    case KeyEvent.VK_UP: myBoard.rotateCW();
                    break;
                    default: break;
                }
            }
            pauseHelp(theEvent);
        }
        
        /**
         * Keyevent helper method that adds pause option.
         * @param theEvent the key event
         */
        private void pauseHelp(final KeyEvent theEvent) {
            if (theEvent.getKeyCode() == KeyEvent.VK_P) {
                pause(!myPause);
            }
        }
    }
}
