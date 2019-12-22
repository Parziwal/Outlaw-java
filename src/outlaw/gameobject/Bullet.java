package outlaw.gameobject;

import outlaw.gameboard.GameBoard;

/** Bullet osztály:
 * A lövedéket reprezentáló mozgó objektum.
 */
public class Bullet extends MovingObject {

    private GameBoard gameBoard; /**< Az a játéktábla, amelyikhez a lövedék tartozik.  */
    private int relativePosX; /**< A lövedék relative X pozíciója a játékoshoz képest.  */
    private int relativePosY; /**< A lövedék relative Y pozíciója a játékoshoz képest.  */

    /** Kontruktor:
     * A lövedék relative pozíciójának, X irányú vektorának, és a hozzá tartozó játéktaáblájának beállítása.
     * Az X pozíció innentől fix, csak egy irányba lehet lőni.
     * A lövedék egy 1x1-es mátrix, vagyis egy "pixel".
     * @param relativePosX - int
     * @param relativePosY - int
     * @param vecX - int
     * @param gameBoard - GameBoard
     */
    public Bullet(int relativePosX, int relativePosY, int vecX, GameBoard gameBoard) {
        super(relativePosX, relativePosY, 1, 1, vecX, 0);
        this.relativePosX = relativePosX;
        this.relativePosY = relativePosY;
        this.gameBoard = gameBoard;
    }

    /** A lövedék léptetése és ütközések ellenőrzése, ha a lövedék aktív.
     */
    public void step() {
        if(getCell(0,0)) {
            if(gameBoard != null) {
                gameBoard.collisionDetection(this);
            }
            super.step();
        }
    }

    /** A paraméterként megadott objektummal való ütközés ellenőrzése.
     * Ha történt ütközés, akkor a megadott objektum hitBy metódusának meghívása.
     * @param gameObject - GameObject
     */
    public void collideWith(GameObject gameObject) {
        if(this.upCollide(gameObject) || this.downCollide(gameObject) || this.leftCollide(gameObject) || this.rightCollide(gameObject)) {
            gameObject.hitBy(this);
        }
    }

    /** Ha lövedék ütközik lövedékkel, akkor nem történik semmi.
     * @param bullet - Bullet
     */
    @Override
    public void hitBy(Bullet bullet) { }

    /** A lövedék aktiválása, és kilövése a megadott pozícióról + relative pozíció, a megadott Y irányba.
     * @param posX - int
     * @param posY - int
     * @param vecY - int
     */
    public void setActive(int posX, int posY, int vecY) {
        if (!getCell(0,0)) {
            super.setPositionX(posX + relativePosX);
            super.setPositionY(posY + relativePosY);
            super.setVectorY(vecY);
            super.setCell(0,0,true);
        }
    }

    /** A lövedék megsemmisítése, vagyis az objektum egyetlen cellájának false értékre állítása.
     */
    public void destroy(){
        super.setCell(0,0,false);
    }
}