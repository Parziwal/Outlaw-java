package outlaw.gameboard;

import outlaw.gameobject.Player;
import outlaw.gameobject.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/** DrawGame osztály:
 * A játéktábla kirajzolásáért felelős osztály. Az objektumok fájlba írása
 *  miatt megvalósítja a Serializable interfészt. A külön szálon való futtatás
 *  miatt megvalósítja a Runnable interfészt.
 */
public class DrawGame extends JPanel implements Serializable, Runnable {
    private ArrayList<GameObject> gameObjects = new ArrayList<>(); /** A kirajzolandő objektumok listája. */
    private int width; /** A rajzterület szélessége. */
    private int scoreBoardHeight; /** Az eredményjelző magassága. */
    private Player leftPlayer; /**< A bal oldali játékos. */
    private Player rightPlayer; /**< A jobb oldali játékos. */
    private BufferedImage badge; /**< A jelvény képe, amit a győztes játékos kap meg. */
    private int badgePosX; /**< A jelvény X pozíciója. */
    private int badgePosY; /**< A jelvény Y pozíciója. */
    private volatile boolean stopSignal = false; /**< A játéktábla kirajzolásához tartozó futó szál befejezésének jelzése. */

    /** A rajzterület méretének beállítsa és az attribútumainak inicializálása.
     * @param width - int
     * @param height - int
     * @param leftPlayer - Player
     * @param rightPlayer - Player
     */
    public DrawGame(int width, int height, Player leftPlayer, Player rightPlayer) {
        setPreferredSize(new Dimension(width,height));
        this.width = width;
        scoreBoardHeight = height / 8;
        this.leftPlayer = leftPlayer;
        this.rightPlayer = rightPlayer;
    }

    /** Kirajzolandó objektum hozzáadása.
     * @param g - GameObject
     */
    public void addGameObject(GameObject g){
        gameObjects.add(g);
    }

    /** A megadott helyre a megadott forrású kép kirajzolása.
     * @param g - Graphics
     * @param imageSrc - String
     * @param posX - int
     */
    private void drawImage(Graphics g, String imageSrc, int posX) {
        try {
            BufferedImage img = ImageIO.read(new File(imageSrc));
            int imgSize = 9 * scoreBoardHeight / 12;
            g.drawImage(img, posX, scoreBoardHeight / 2 - imgSize / 2, imgSize, imgSize, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** A jelvény pozíciójának megadása, és a jelvény képének beolvasása.
     * @param badgePosX - int
     * @param badgePosY - int
     */
    public void drawBadge(int badgePosX, int badgePosY) {
        this.badgePosX = badgePosX;
        this.badgePosY = badgePosY;
        try {
            badge = ImageIO.read(new File("images/badge.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** A paraméterként átadott objektum kirajzolása.
     * @param g - Graphics
     * @param object - Gameobject
     */
    private void drawGameObject(Graphics g, GameObject object) {
        for (int j = 0; j < object.getHeight(); j++) {
            for (int k = 0; k < object.getWidth(); k++) {
                if (object.getCell(j, k)) {
                    g.setColor(object.getColor());
                    g.fillRect(object.getPositionX() + k * GameObject.unit, scoreBoardHeight + object.getPositionY() + j * GameObject.unit, GameObject.unit, GameObject.unit);
                }
            }
        }
    }

    /** A pontszámok, a jelvény és a játéktáblán lévő objektumok kirajzolása.
     * @param g - Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawImage(g, "images/numbers/" + leftPlayer.getLives() + ".png", width / 4);
        this.drawImage(g, "images/numbers/" + rightPlayer.getLives() + ".png", 3 * width / 4);

        for(int i = 0;i < gameObjects.size();i++) {
            drawGameObject(g, gameObjects.get(i));
        }
        drawGameObject(g, leftPlayer);
        drawGameObject(g, rightPlayer);

        if(badge != null) {
            g.drawImage(badge, badgePosX, scoreBoardHeight + badgePosY, badge.getWidth(), badge.getHeight(), null);
        }
    }

    /** A Runnable interfészhez tartozó metódus. A játéktábla kirajzolásának külön szálon való futtatását szolgálja.
     */
    @Override
    public void run() {
        while(leftPlayer.getLives() != 0 && rightPlayer.getLives() != 0 && !stopSignal) {
            repaint();
        }
    }

    /** A játéktábla kirajzolásához tartozó futó szál befejezése.
     */
    public void stop() {
        stopSignal = true;
    }
}
