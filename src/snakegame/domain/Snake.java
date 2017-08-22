/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.domain;

import java.util.List;
import java.util.ArrayList;
import snakegame.Direction;

/**
 *
 * @author Anthony Catalano imagesdecieve@gmail.com
 */
public class Snake {

    private int x, y, length;
    private Direction direction;
    private List<Piece> listOfPieces;
    private boolean grow = false;

    public Snake(int originalX, int originalY, Direction originalDirection) {
        x = originalX;
        y = originalY;
        direction = originalDirection;
        length = 1;
        listOfPieces = new ArrayList<Piece>();
        listOfPieces.add(new Piece(x, y));
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLength() {
        return length;
    }

    public List getPieces() {
        return listOfPieces;
    }

    public void move() {
        if (length < 3) {
            grow();
        }
        Direction currentDirection = getDirection();
        Piece head = getHead();
        int x = head.x;
        int y = head.y;
        if (currentDirection == Direction.DOWN) {
            y += 1;
        } else if (currentDirection == Direction.LEFT) {
            x -= 1;
        } else if (currentDirection == Direction.RIGHT) {
            x += 1;
        } else if (currentDirection == Direction.UP) {
            y -= 1;
        }
        listOfPieces.add(new Piece(x, y));
        if (grow == false) {
            listOfPieces.remove(0);
        } else {
            length++;
            grow = false;
        }

    }

    public void grow() {
        grow = true;
    }

    public Piece getHead() {
        return listOfPieces.get(listOfPieces.size() - 1);
    }

    public boolean runsInto(Piece piece) {
        for (Piece p : listOfPieces) {
            if (p.getX() == piece.x && p.getY() == piece.y) {
                return true;
            }
        }
        return false;
    }

    public boolean runsIntoItself() {
        for (Piece piece : listOfPieces) {
            Piece head = getHead();
            if ((piece.getX() == getHead().x && piece.getY() == getHead().y) && !piece.equals(head)) {
                return true;
            }
        }
        return false;
    }
}
