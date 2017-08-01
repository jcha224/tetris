/**
 * TCSS 305 - SPRING 2016
 * Assignment 6 - Tetris
 */
package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Runs Tetris by starting the TetrisGUI.
 * 
 * @author John Chang
 * @version 24 May 2016
 */
public final class TetrisMain {

    /**
     * The class constructor.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }
    
    /**
     * Sets look and feel and calls TetrisGUI.
     * @param theArgs command line
     */
    public static void main(final String[] theArgs) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
      /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
      
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI().start();
            }
        });
    }
}
