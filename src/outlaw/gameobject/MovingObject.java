package outlaw.gameobject;

import java.awt.*;

/** MovingObject abstract osztály:
 * A GameObject osztályból leszármazó, mozgó objektumokat reprezentáló osztály.
 */
public abstract class MovingObject extends GameObject {
    private int vectorX; /**< Az objektum X irányú vektora. */
    private int vectorY; /**< Az objektum Y irényú vektora. */

    /** 1. Konstruktor:
     *  A megadott méretű, pozíciójú, és irányú objektum létrehozása.
     * @param posX - int
     * @param posY - int
     * @param width - int
     * @param heigth - int
     * @param vecX - int
     * @param vecY - int
     */
    public MovingObject(int posX, int posY, int width, int heigth, int vecX, int vecY) {
        super(posX, posY, width, heigth);
        vectorX = vecX;
        vectorY = vecY;
    }

    /** 2. Konstruktor:
     * A megadott mátrixnak megfelelő kinézetű, pozíciójú és irányú objektum létrehozása.
     * @param posX - int
     * @param posY - int
     * @param matrix - boolean[][]
     * @param vecX - int
     * @param vecY - int
     */
    public MovingObject(int posX, int posY, boolean[][] matrix, int vecX, int vecY) {
        super(posX, posY, matrix);
        vectorX = vecX;
        vectorY = vecY;
    }

    /** A objketum a mozgatása a vektoroknak megfelelően.
     */
    public void step() {
        super.setPositionX(getPositionX() + vectorX);
        super.setPositionY(getPositionY() + vectorY);
    }

    /** Ütközés felülről:
     * Ellenőrzi, hogy az objektum felülről ütközött-e egy másik objektummal.
     * Ha igen, akkor a visszatérési érték true, egyébként false.
     * @param gameObject - GameObject
     * @return boolean
     */
    public boolean upCollide(GameObject gameObject) {
        int thisPosX = super.getPositionX();
        for(int i = 0;i < super.getWidth();i++) {
            int thisPosY = super.getPositionY();
            for(int j = 0;j < super.getHeight();j++) {
                Rectangle thisPiece = new Rectangle(thisPosX, thisPosY, unit, unit);
                if (super.getCell(j, i)) {
                    int otherPosY = gameObject.getPositionY();
                    for(int k = 0;k < gameObject.getHeight();k++) {
                        int otherPosX = gameObject.getPositionX();
                        for(int l = 0;l < gameObject.getWidth();l++) {
                            Rectangle otherPiece = new Rectangle(otherPosX + 1, otherPosY + 2, unit - 2, unit - 2);
                            if(gameObject.getCell(k, l) && thisPiece.intersects(otherPiece)) {
                                return true;
                            }
                            otherPosX += unit;
                        }
                        otherPosY += unit;
                    }
                    break;
                }
                thisPosY += unit;
            }
            thisPosX += unit;
        }
        return false;
    }

    /** Ütközés alulról:
     * Ellenőrzi, hogy az objektum alulról ütközött-e egy másik objektummal.
     * Ha igen, akkor a visszatérési érték true, egyébként false.
     * @param gameObject - Gameobject
     * @return boolean
     */
    public boolean downCollide(GameObject gameObject) {
        int thisPosX = super.getPositionX();
        for(int i = 0;i < super.getWidth();i++) {
            int thisPosY = super.getPositionY() + (super.getHeight() - 1) * unit;
            for(int j = super.getHeight() - 1;j >= 0;j--) {
                Rectangle thisPiece = new Rectangle(thisPosX, thisPosY, unit, unit);
                if (super.getCell(j, i)) {
                    int otherPosY = gameObject.getPositionY();
                    for(int k = 0;k < gameObject.getHeight();k++) {
                        int otherPosX = gameObject.getPositionX();
                        for(int l = 0;l < gameObject.getWidth();l++) {
                            Rectangle otherPiece = new Rectangle(otherPosX + 1, otherPosY - 2, unit - 2, unit - 2);
                            if(gameObject.getCell(k, l) && thisPiece.intersects(otherPiece)) {
                                return true;
                            }
                            otherPosX += unit;
                        }
                        otherPosY += unit;
                    }
                    break;
                }
                thisPosY -= unit;
            }
            thisPosX += unit;
        }
        return false;
    }

    /** Ütközés balodalról:
     * Ellenőrzi, hogy az objektum balodalról ütközött-e egy másik objektummal.
     * Ha igen, akkor a visszatérési érték true, egyébként false.
     * @param gameObject - Gameobject
     * @return boolean
     */
    public boolean leftCollide(GameObject gameObject) {
        int thisPosY = super.getPositionY();
        for(int i = 0;i < super.getHeight();i++) {
            int thisPosX = super.getPositionX();
            for(int j = 0;j < super.getWidth();j++) {
                Rectangle thisPiece = new Rectangle(thisPosX, thisPosY, unit, unit);
                if (super.getCell(i, j)) {
                    int otherPosY = gameObject.getPositionY();
                    for(int k = 0;k < gameObject.getHeight();k++) {
                        int otherPosX = gameObject.getPositionX();
                        for(int l = 0;l < gameObject.getWidth();l++) {
                            Rectangle otherPiece = new Rectangle(otherPosX + 2, otherPosY + 1, unit - 2, unit - 2);
                            if(gameObject.getCell(k, l) && thisPiece.intersects(otherPiece)) {
                                return true;
                            }
                            otherPosX += unit;
                        }
                        otherPosY += unit;
                    }
                    break;
                }
                thisPosX += unit;
            }
            thisPosY += unit;
        }
        return false;
    }

    /** Ütközés jobbodalról:
     * Ellenőrzi, hogy az objektum jobbodalról ütközött-e egy másik objektummal.
     * Ha igen, akkor a visszatérési érték true, egyébként false.
     * @param gameObject - Gameobject
     * @return boolean
     */
    public boolean rightCollide(GameObject gameObject) {
        int thisPosY = super.getPositionY();
        for(int i = 0;i < super.getHeight();i++) {
            int thisPosX = super.getPositionX() + (super.getWidth() - 1) * unit;
            for(int j = super.getWidth() - 1;j >= 0;j--) {
                Rectangle thisPiece = new Rectangle(thisPosX, thisPosY, unit, unit);
                if (super.getCell(i, j)) {
                    int otherPosY = gameObject.getPositionY();
                    for(int k = 0;k < gameObject.getHeight();k++) {
                        int otherPosX = gameObject.getPositionX();
                        for(int l = 0;l < gameObject.getWidth();l++) {
                            Rectangle otherPiece = new Rectangle(otherPosX, otherPosY + 1, unit - 2, unit - 2);
                            if(gameObject.getCell(k, l) && thisPiece.intersects(otherPiece)) {
                                return true;
                            }
                            otherPosX += unit;
                        }
                        otherPosY += unit;
                    }
                    break;
                }
                thisPosX -= unit;
            }
            thisPosY += unit;
        }
        return false;
    }

    /** A paraméterként megadott objektummal való ütközés végrehajtandó művelet.
     * @param gameObject - GameObject
     */
    public abstract void collideWith(GameObject gameObject);

    /** Az X irányú vektor lekérdezése.
     * @return int
     */
    public int getVectorX() {
        return vectorX;
    }

    /** Az Y irányú vektor lekérdezése.
     * @return int
     */
    public int getVectorY() {
        return vectorY;
    }

    /** Az X irányú vektor módosítása.
     */
    public void setVectorX(int vectorX) {
        this.vectorX = vectorX;
    }

    /** Az Y irányú vektor módosítása.
     */
    public void setVectorY(int vectorY) {
        this.vectorY = vectorY;
    }
}
