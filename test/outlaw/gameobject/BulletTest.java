package outlaw.gameobject;

import org.junit.Before;
import org.junit.Test;
import outlaw.gameobject.Bullet;
import outlaw.gameobject.HorizontalBouncingWall;
import outlaw.gameobject.NormalWall;

import static org.junit.Assert.*;

public class BulletTest {
    Bullet bullet;

    @Before
    public void setUp() {
        bullet = new Bullet(0,0,-2,null);
    }

    @Test
    public void step() {
        bullet.setActive(0,0, 3);
        bullet.step();
        assertEquals(-2, bullet.getPositionX());
        assertEquals(3, bullet.getPositionY());
    }

    @Test
    public void collideWithNormalWall() {
        NormalWall wall = new NormalWall(0,0, 1, 10);
        bullet.setActive(10,0, 0);
        bullet.collideWith(wall);
        assertTrue(bullet.getCell(0, 0));
        bullet.step();
        bullet.collideWith(wall);
        assertFalse(bullet.getCell(0, 0));
    }

    @Test
    public void collideWithHorizontalBouncingWall() {
        HorizontalBouncingWall wall = new HorizontalBouncingWall(0,0, 10);
        bullet.setActive(10,10, -1);
        bullet.collideWith(wall);
        assertTrue(bullet.getCell(0, 0));
        bullet.step();
        bullet.collideWith(wall);
        assertEquals(1, bullet.getVectorY());
    }

    @Test
    public void collideWithCactus() {
        Cactus cactus = new Cactus(0, 0, 20);
        bullet.setActive(10,0, 0);
        bullet.collideWith(cactus);
        assertTrue(bullet.getCell(0, 0));
        bullet.step();
        bullet.collideWith(cactus);
        assertFalse(bullet.getCell(0, 0));
    }

    @Test
    public void setActive() {
        bullet.setActive(2,3, 1);
        assertEquals(2, bullet.getPositionX());
        assertEquals(3, bullet.getPositionY());
        assertEquals(1, bullet.getVectorY());
        assertTrue(bullet.getCell(0,0));
    }

    @Test
    public void destroy() {
        bullet.setActive(0,0,0);
        bullet.destroy();
        assertFalse(bullet.getCell(0, 0));
    }
}