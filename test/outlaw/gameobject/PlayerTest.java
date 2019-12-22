package outlaw.gameobject;

import org.junit.Before;
import org.junit.Test;
import outlaw.gameobject.Player;
import outlaw.gameobject.Bullet;
import outlaw.gameobject.GameObject;
import outlaw.sprite.LeftPlayerSprite;
import outlaw.gameobject.NormalWall;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;
    LeftPlayerSprite sprite;
    Bullet bullet;

    @Before
    public void setUp() {
        bullet = new Bullet(0,0,-2,null);
        sprite = new LeftPlayerSprite();
        player = new Player(0,0, KeyEvent.VK_E, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_F, KeyEvent.VK_Q,
                sprite,  bullet, null);
    }

    @Test
    public void step() {
        player.setVectorX(2);
        player.step();
        assertEquals(2, player.getPositionX());
        assertEquals(0, player.getPositionY());
        player.setVectorY(2);
        player.step();
        assertEquals(4, player.getPositionX());
        assertEquals(2, player.getPositionY());
    }

    @Test
    public void upCollide() {
        NormalWall wall = new NormalWall(0,0, 10, 1);
        player.setPositionY(10);
        assertFalse(player.upCollide(wall));
        KeyListener keyListener = player.getKeyListener();
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_E));
        player.step();
        assertTrue(player.upCollide(wall));
    }

    @Test
    public void downCollide() {
        NormalWall wall = new NormalWall(0, 122, 10, 1);
        assertFalse(player.downCollide(wall));
        KeyListener keyListener = player.getKeyListener();
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_D));
        player.step();
        assertTrue(player.downCollide(wall));
    }

    @Test
    public void leftCollide() {
        NormalWall wall = new NormalWall(0,0, 1, 10);
        player.setPositionX(10);
        assertFalse(player.leftCollide(wall));
        KeyListener keyListener = player.getKeyListener();
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_S));
        player.step();
        assertTrue(player.leftCollide(wall));
    }

    @Test
    public void rightCollide() {
        NormalWall wall = new NormalWall(80,0, 1, 10);
        assertFalse(player.rightCollide(wall));
        KeyListener keyListener = player.getKeyListener();
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_F));
        player.step();
        assertTrue(player.rightCollide(wall));
    }

    @Test
    public void hitBy() {
        Bullet bullet = new Bullet(0,0,2,null);
        player.hitBy(bullet);
        assertEquals(9, player.getLives());
    }

    @Test
    public void setImageFire() {
        player.setImageFire(-1);
        boolean[][] matrix = sprite.getFireUp();
        for(int i = 0;i < matrix.length || i < player.getHeight();i++) {
            for(int j = 0;j < matrix[i].length || j < player.getWidth();j++) {
                assertEquals(matrix[i][j], player.getCell(i, j));
            }
        }

        player.setImageFire(0);
        matrix = sprite.getFireStraight();
        for(int i = 0;i < matrix.length || i < player.getHeight();i++) {
            for(int j = 0;j < matrix[i].length || j < player.getWidth();j++) {
                assertEquals(matrix[i][j], player.getCell(i, j));
            }
        }

        player.setImageFire(1);
        matrix = sprite.getFireDown();
        for(int i = 0;i < matrix.length || i < player.getHeight();i++) {
            for(int j = 0;j < matrix[i].length || j < player.getWidth();j++) {
                assertEquals(matrix[i][j], player.getCell(i, j));
            }
        }
    }

    @Test
    public void setImageStand() {
        player.setImageStand();
        boolean[][] matrix = sprite.getStand();
        for(int i = 0;i < matrix.length || i < player.getHeight();i++) {
            for(int j = 0;j < matrix[i].length || j < player.getWidth();j++) {
                assertEquals(matrix[i][j], player.getCell(i, j));
            }
        }
    }

    @Test
    public void setImageDead() {
        player.setImageDead();
        boolean[][] matrix = sprite.getDead();
        for(int i = 0;i < matrix.length || i < player.getHeight();i++) {
            for(int j = 0;j < matrix[i].length || j < player.getWidth();j++) {
                assertEquals(matrix[i][j], player.getCell(i, j));
            }
        }
    }

    @Test
    public void fire() {
        player.fire(-1);
        player.step();
        assertTrue(bullet.getCell(0,0));
        assertEquals(-2, bullet.getPositionX());
        assertEquals(-1, bullet.getPositionY());
    }

    @Test
    public void blockKeyWhenCollide() {
        NormalWall wall = new NormalWall(0,0, 10, 1);
        player.setPositionY(10);
        KeyListener keyListener = player.getKeyListener();
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_E));
        player.step();
        player.collideWith(wall);
        keyListener.keyPressed(new KeyEvent(new Component() {}, 0, 0l, 0, KeyEvent.VK_E));
        assertEquals(9, player.getPositionY());
    }
}