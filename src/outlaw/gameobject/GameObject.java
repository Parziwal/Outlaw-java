package outlaw.gameobject;

import java.awt.*;
import java.io.Serializable;

/** Gamobject abstract osztály:
 * A játéktáblán elhelyezhető objetumok ősosztálya. Az objektumok fájlba írása
 *  miatt megvalósítja a Serializable interfészt.
 */
public abstract class GameObject implements Serializable {
    public static final int unit = 10; /**< Az objektum egy egységének mérete pixelben.  */
    private int positionX; /**< Az objektum X pozíciója. */
    private int positionY; /**< Az objektum Y pozíciója. */
    private boolean[][] matrix; /**< Az objektum 2d-s pixeles kinézete. */
    private Color color = Color.black; /**< Az objektum színe (alapból fekete). */

    /** 1. Konstruktor:
     * A megadott méretű és pozíciójú objektum létrehozása.
     * @param posX - int, az objektum x pozíciója pixelben értendő
     * @param posY - int, az objektum y pozíciója pixelben értendő
     * @param width - int, az objektum szélessége a unit-ban értendő
     * @param heigth - int, az objektum magassága a unit-ban értendő
     */
    public GameObject(int posX, int posY, int width, int heigth) {
        matrix = new boolean[heigth][width];
        positionX = posX;
        positionY = posY;
    }

    /** 2. Konstruktor:
     *  A megadott mátrixnak megfelelő kinézetű és pozíciójú objektum létrehozása.
     * @param posX - int
     * @param posY - int
     * @param matrix - boolean[][]
     */
    public GameObject(int posX, int posY, boolean[][] matrix) {
        this.matrix = matrix;
        positionX = posX;
        positionY = posY;
    }

    /** A mátrix magasságának lekérdezése.
     * @return int
     */
    public int getHeight() { return matrix.length; }

    /** A mátrix szélességének lekérdezése.
     * @return int
     */
    public int getWidth() {
        return matrix[0].length;
    }

    /** Cella értékének lekérdezése:
     * Megadja a mátrix y. sorának az x. oszlopában lévő cella értékét.
     * @param y - int
     * @param x - int
     * @return boolean
     */
    public boolean getCell(int y, int x){
        return matrix[y][x];
    }

    /** Cella módosítása:
     * A mátrix y. sorának az x. oszlopában lévő cella értékét beállítja a value értékére.
     * @param y - int
     * @param x - int
     * @param value - boolean
     */
    public void setCell(int y, int x, boolean value){
        if(y >= matrix.length && x >= matrix[0].length) {
            return;
        }
        matrix[y][x] = value;
    }


    /** Mátrix módosítása:
     * A mátrix értékének felülírása egy másik mátrix-al.
     * @param matrix - boolean[][]
     */
    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    /** Az objektum X pozíciójának lekérdezése.
     * @return int
     */
    public int getPositionX(){
        return positionX;
    }


    /** Az objektum Y pozíciójának lekérdezése.
     * @return int
     */
    public int getPositionY(){
        return positionY;
    }


    /** Az objektum X pozíciójának módosítása.
     * @param x - int
     */
    public void setPositionX(int x){ this.positionX = x; }

    /** Az objektum Y pozíciójának módosítása.
     * @param y - int
     */
    public void setPositionY(int y){
        this.positionY = y;
    }

    /** Az objektum színének lekérdezése.
     * @return Color
     */
    public Color getColor() {
        return color;
    }

    /** Az objektum színének módosítása.
     * @param color - Color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /** A lövedékkel való ütközés implemetálása a nem abstract leszármazotakban.
     * @param bullet - Bullet
     */
    public abstract void hitBy(Bullet bullet);
}
