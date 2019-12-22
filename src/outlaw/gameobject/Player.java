package outlaw.gameobject;

import outlaw.enumeration.ControlKey;
import outlaw.gameboard.GameBoard;
import outlaw.keylistener.PlayerKeyListener;
import outlaw.sprite.PlayerSprite;

import java.awt.event.KeyListener;

/** Player osztály:
 * A játéktáblán a játékost reprezentáló mozgó objektum. A Runnable interfész implemetálásával
 * lehetővé teszi a játékos külön szálon való futtatását.
 */
public class Player extends MovingObject implements Runnable {
    private PlayerSprite sprite; /**< A játékos kinézeteit tartalmazza. */
    private Bullet bullet; /**< A játékoshoz tartozó lövedék. */
    private PlayerKeyListener keyListener; /** A karakter irányításához szükséges keylistener. */
    private GameBoard gameboard; /**< A játéktábla, amihez a játékos tartozik. */
    private int lives = 10; /**< A játékos életeinek a száma. */
    private boolean dead = false; /**< Azt tárolja, hogy a játékos éppen lelőtt állapotba van-e. */
    private volatile boolean stopSignal = false; /**< A játékoshoz tartozó futó szál befejezésének jelzése. */

    /** A játékos inicializálása: a kezdő pozíció beállítása, a karakter irányításához szükséges
     *  billentyűk megadása, a játékos kinézeteinek megadása, a lövedék beállítása, és a megadott
     *  játéktáblához való hozzárendelés.
     * @param startPosX - int
     * @param startPosY - int
     * @param moveUpKey - Integer
     * @param moveDownKey - Integer
     * @param moveLeftKey - Integer
     * @param moveRightKey - Integer
     * @param fireKey - Integer
     * @param sprite - PlayerSprite
     * @param bullet - Bullet
     * @param gameboard - GameBoard
     */
    public Player(int startPosX, int startPosY, Integer moveUpKey, Integer moveDownKey, Integer moveLeftKey, Integer moveRightKey, Integer fireKey,
                  PlayerSprite sprite, Bullet bullet, GameBoard gameboard) {
        super(startPosX, startPosY, sprite.getStand(), 0,0);
        keyListener = new PlayerKeyListener(this, moveUpKey, moveDownKey, moveLeftKey, moveRightKey, fireKey);
        this.sprite = sprite;
        this.bullet = bullet;
        this.gameboard = gameboard;
    }

    /** A léptetés előtt az ütközés ellenőrzése, majd a játékoshoz tartozó
     *  lövedék, és ha a játékos nem halott, akkor a játékos léptetése.
     */
    public void step() {
        if(gameboard != null) {
            gameboard.collisionDetection(this);
        }
        if(bullet != null) {
            bullet.step();
        }
        if(!dead) {
            super.step();
        }
    }

    /** A játékos a paraméterként megadott Y irányba kilövi a hozzá tartozó lövedéket.
     * @param vecY - int
     */
    public void fire(int vecY) {
        bullet.setActive(super.getPositionX(), super.getPositionY(), vecY);
    }

    /** A paraméterként megadott objektummal való ütközés ellenőrzése. Az ütközés
     * irányának megfelelően a karakter mozgásának blokkolása.
     * @param gameObject - GameObject
     */
    public void collideWith(GameObject gameObject) {
        if(super.upCollide(gameObject)) {
            keyListener.blockMovementKey(ControlKey.UP, true);
        } else {
            keyListener.blockMovementKey(ControlKey.UP, false);
        }
        if(super.downCollide(gameObject)) {
            keyListener.blockMovementKey(ControlKey.DOWN, true);
        } else {
            keyListener.blockMovementKey(ControlKey.DOWN, false);
        }
        if(super.leftCollide(gameObject)) {
            keyListener.blockMovementKey(ControlKey.LEFT, true);
        } else {
            keyListener.blockMovementKey(ControlKey.LEFT, false);
        }
        if(super.rightCollide(gameObject)) {
            keyListener.blockMovementKey(ControlKey.RIGHT, true);
        } else {
            keyListener.blockMovementKey(ControlKey.RIGHT, false);
        }
    }

    /** Az ellenfél lövedékével való ütközéskor az ellenfél tölténye megsemmisül, és az
     * eltalált játékos egy életet veszít. Ha a játékos elvesztette az összes életét, akkor
     * meghívja a gameboard gameover metódusát. Emellett beállítja a játékosnak a lelőtt kinézetet
     * 1 mp-re. Erre az időre a játékot is megállítja.
     * @param bullet - Bullet
     */
    public void hitBy(Bullet bullet) {
        if(this.bullet != bullet) {
            bullet.destroy();
            lives--;
            if(lives == 0 && gameboard != null) {
                gameboard.gameOver(this);
            }

            dead = true;
            this.setImageDead();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dead = false;
            this.setImageStand();
        }
    }

    /** A játékoshoz tartozó keylistener lekérdezése.
     * @return Keylistener
     */
    public KeyListener getKeyListener() {
        return keyListener;
    }

    /** A játékos megmaradt életeinek a számát adja vissza.
     * @return int
     */
    public int getLives() {
        return lives;
    }

    /** Megmondja, hogy a játékos életben van-e.
     * @return boolean
     */
    public boolean isAlive() {
        return !dead;
    }

    /** A lövő játékos kinézet beállítása. A paramétertől függően ez lehet egyenesen, le vagy felfele lövő játékos kinézet.
     * @param direction - int
     */
    public void setImageFire(int direction) {
        if(direction == 0) {
            super.setMatrix(sprite.getFireStraight());
        } else  if(direction == -1) {
            super.setMatrix(sprite.getFireUp());
        } else if(direction == 1) {
            super.setMatrix(sprite.getFireDown());
        }
    }

    /** Az álló játékos kinézet beállítása.
     */
    public void setImageStand() {
        super.setMatrix(sprite.getStand());
    }

    /** A lelőtt játékos kinézet beállítása.
     */
    public void setImageDead() {
        super.setMatrix(sprite.getDead());
    }

    /** A Runnable interfészhez tartozó metódus. A játékos külön szálon való futtatását szolgálja.
     */
    @Override
    public void run() {
        while (lives != 0 && !stopSignal) {
            step();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /** A játékoshoz tartozó futó szál befejezése.
     */
    public void stop() {
        stopSignal = true;
    }
}
