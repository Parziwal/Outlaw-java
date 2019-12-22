package outlaw.gameboard;

import outlaw.gameobject.Player;
import outlaw.gameobject.*;
import outlaw.sprite.LeftPlayerSprite;
import outlaw.sprite.RightPlayerSprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;

/** GameBoard osztály:
 * A játéktábla, amely inicializálja, beállítja és tárolja a rajta elhelyezkedő objektumokat, és kezeli a
 * közöttük lévő kapcsolatokat. Az objektumok fájlba írás miatt megvalósítja a Serializable interfészt.
 */
public class GameBoard implements Serializable {
    private Player leftPlayer; /**< A játéktáblán lévő bal oldali játékos. */
    private Player rightPlayer; /**< A játéktáblán lévő jobb oldali játékos. */
    private ArrayList<GameObject> barriers = new ArrayList<>(); /**< A játéktéren lévő akadályokat tartalmazó lista. */
    private DrawGame drawGame; /**< A játéktábla képernyőre rajzolásáért felelős. */


    /** A megadott méretű pálya létrehozása. A játékosok, az azokhoz tartozó töltények, a játéktáblán
     * lévő falak létrehozása. A játéktábla kirajzolásáért felelős drawGame beállítása.
     * @param width - int
     * @param height - int
     */
    public GameBoard(int width, int height) {
        Bullet leftPlayerBullet = new Bullet(6 * GameObject.unit,5 * GameObject.unit,3,this);
        leftPlayer = new Player(width / 4 - GameObject.unit * 4,height / 3, KeyEvent.VK_E, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_F, KeyEvent.VK_Q,
                    new LeftPlayerSprite(),  leftPlayerBullet, this);
        leftPlayer.setColor(Color.RED);

        Bullet rightPlayerBullet = new Bullet(GameObject.unit,5 * GameObject.unit,-3,this);
        rightPlayer = new Player(3 * width / 4 - GameObject.unit * 4,height / 3, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_M,
                new RightPlayerSprite(), rightPlayerBullet, this);
        rightPlayer.setColor(Color.BLUE);

        int boardWidth = width / GameObject.unit - 4;
        int boardHeight = (3 * height / 4) / GameObject.unit;
        NormalWall leftWall = new NormalWall(GameObject.unit,0, 1, boardHeight);
        NormalWall rightWall = new NormalWall((boardWidth + 1) * GameObject.unit,0,1, boardHeight);
        HorizontalBouncingWall topWall = new HorizontalBouncingWall(GameObject.unit,0, boardWidth);
        HorizontalBouncingWall bottomWall = new HorizontalBouncingWall(GameObject.unit,boardHeight * GameObject.unit, boardWidth + 1);
        int cactusHeight = boardHeight / 2;
        Cactus cactus = new Cactus(boardWidth / 2 * GameObject.unit - 2 * GameObject.unit,(boardHeight - cactusHeight + 1) / 2 * GameObject.unit,  cactusHeight);
        cactus.setColor(new Color(0, 100, 0));

        barriers.add(topWall);
        barriers.add(bottomWall);
        barriers.add(leftWall);
        barriers.add(rightWall);
        barriers.add(cactus);

        drawGame = new DrawGame(width, height, leftPlayer, rightPlayer);
        drawGame.addGameObject(leftPlayerBullet);
        drawGame.addGameObject(rightPlayerBullet);

        for(int i = 0; i < barriers.size(); i++) {
            drawGame.addGameObject(barriers.get(i));
        }
    }

    /** A játék elindítása. Mindkét játékos és a kirajzolásért felelős drawGame külön szálon futtatása.
     */
    public void start() {
        Thread t1 = new Thread(leftPlayer);
        Thread t2 = new Thread(rightPlayer);
        Thread t3 = new Thread(drawGame);
        t1.start();t2.start();t3.start();
    }

    /** A játék befejezése. A játékosok és a kirajzolásért felelős példány szálának megállítása.
     */
    public void  stop() {
        leftPlayer.stop();
        rightPlayer.stop();
        drawGame.stop();
    }

    /** Ellenőrzi, hogy a megadott játékos a játéktáblán ütközik-e valamelyik fallal.
     * @param player - Player
     */
    public void collisionDetection(Player player) {
        for(GameObject wall : barriers) {
            player.collideWith(wall);
        }
    }

    /** Ellenőrzi, hogy a megadott lövedék a játéktáblán ütközik-e valamelyik fallal vagy játékossal.
     * @param bullet - Bullet
     */
    public void collisionDetection(Bullet bullet) {
        for(GameObject wall : barriers) {
            bullet.collideWith(wall);
        }
        bullet.collideWith(leftPlayer);
        bullet.collideWith(rightPlayer);
    }

    /** A játék vége. A paraméterben megadott játékos veszített. A nyertes játékos számára
     * a drawGame segítségével egy jelvény kirajzolása.
     * @param player - Player
     */
    public void gameOver(Player player) {
        if(leftPlayer != player) {
            drawGame.drawBadge(leftPlayer.getPositionX() + 3 * GameObject.unit, leftPlayer.getPositionY() + 5 * GameObject.unit);
        } else {
            drawGame.drawBadge(rightPlayer.getPositionX() + 3 * GameObject.unit, rightPlayer.getPositionY() + 5 * GameObject.unit);
        }
    }

    /** A kirajzolásért felelős drawGame lekérdezése.
     * @return DrawGame
     */
    public DrawGame getBoard() {
        return drawGame;
    }

    /** A játékosok keylistener-eivel tér vissza.
     * @return KeyListener[]
     */
    public KeyListener[] getKeyListeners() {
        return new KeyListener[]{leftPlayer.getKeyListener(), rightPlayer.getKeyListener()};
    }
}
