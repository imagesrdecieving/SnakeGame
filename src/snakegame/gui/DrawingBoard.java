/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;
import javax.swing.JPanel;
import snakegame.domain.Piece;
import snakegame.game.SnakeGame;

/**
 *
 * @author Anthony Catalano imagesdecieve@gmail.com
 */
public class DrawingBoard extends JPanel implements Updatable{
    SnakeGame game;
    int pieceLength;

    public DrawingBoard(SnakeGame game, int pieceLength) {
        this.game = game;
        this.pieceLength = pieceLength;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.setColor(Color.BLACK);
        for (Iterator it = game.getWorm().getPieces().iterator(); it.hasNext();) {
            Object wormSegment = it.next();
            Piece piece = (Piece) wormSegment;
            g.fill3DRect(piece.getX() * pieceLength, piece.getY() * pieceLength, pieceLength, pieceLength, true);
        }
        g.setColor(Color.RED);
        g.fillOval(game.getApple().getX() * pieceLength, game.getApple().getY() * pieceLength, pieceLength, pieceLength);
    }

    @Override
    public void update() {
        this.repaint();
    }
    
}
