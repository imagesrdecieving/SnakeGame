package snakegame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import snakegame.Direction;
import snakegame.domain.Apple;
import snakegame.domain.Piece;
import snakegame.domain.Snake;
import snakegame.gui.Updatable;

public class SnakeGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Snake worm;
    private Apple apple;
    private int x = 0, y = 0;
    private Random r = new Random();

    public SnakeGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

        worm = new Snake(width / 2, height / 2, Direction.DOWN);

        apple = createNewApple();

    }

    private Apple createNewApple() {
        x = r.nextInt(width);
        y = r.nextInt(height);
        this.apple = new Apple(x, y);
        if (appleCheck(apple)) {
            createNewApple();
        }
        return this.apple;
    }

    public Apple getApple() {
        if (appleCheck(apple)) {
            createNewApple();
        }
        return apple;
    }

    private boolean appleCheck(Piece apple) {
        return (worm.runsInto(apple) || (apple.getX() >= width) || (apple.getY() >= height));
    }

    public Snake getWorm() {
        return this.worm;
    }

    public void setWorm(Snake worm) {
        this.worm = worm;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (continues) {
            worm.move();
            if (worm.runsInto(apple)) {
                worm.grow();
                apple = createNewApple();
            }
            if ((worm.runsIntoItself()) || (worm.getHead().getX() == width) || (worm.getHead().getY() == height) || (worm.getHead().getY() == 0) || (worm.getHead().getX() == 0)) {
                continues = false;
            }
            updatable.update();
            setDelay(1000 / worm.getLength());
            return;
        }

    }

}
