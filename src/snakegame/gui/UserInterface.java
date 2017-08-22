package snakegame.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import snakegame.game.SnakeGame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private SnakeGame game;
    private int sideLength;
    private DrawingBoard drawingBoard;
    private KeyboardListener keyboardListener;

    public UserInterface(SnakeGame game, int sideLength) {
        this.game = game;
        this.sideLength = sideLength;
    }

    @Override
    public void run() {
        frame = new JFrame("Worm Game");
        int width = (game.getWidth() + 1) * sideLength + 10;
        int height = (game.getHeight() + 2) * sideLength + 10;

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        // Create drawing board first which then is added into container-object.
        drawingBoard = new DrawingBoard(game, sideLength);
        container.add(drawingBoard);
        // After this, create keyboard listener which is added into frame-object
        keyboardListener = new KeyboardListener(game.getWorm());
        frame.addKeyListener(keyboardListener);
    }

    public Updatable getUpdatable() {
        return drawingBoard;
    }

    public JFrame getFrame() {
        return frame;
    }
}
