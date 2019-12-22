package outlaw.gameobject;

/** Cactus osztály:
 * A játékban a kaktusz reprezentáló objektum.
 */
public class Cactus extends GameObject{
    /** Konstruktor:
     * A megadott magasságú és pozíziójú kaktusz létrehozása.
     * @param posX - int
     * @param posY - int
     * @param heigth - int
     */
    public Cactus(int posX, int posY, int heigth) {
        super(posX, posY, 7, heigth);
        for(int i = 0;i < super.getHeight();i++){
            super.setCell(i, 0, i < super.getHeight() / 2);
            super.setCell(i, 3, true);
            super.setCell(i, 6,  super.getHeight() / 10 < i && i < 2 * super.getHeight() / 3);
        }
        super.setCell(super.getHeight() / 2 - 1, 1, true);
        super.setCell(super.getHeight() / 2 - 1, 2, true);
        super.setCell( 2 * super.getHeight() / 3 - 1, 4, true);
        super.setCell( 2 * super.getHeight() / 3 - 1, 5, true);
    }

    /** Lövedékkel való ütközéskor a lövedék megsemmisítése.
     * @param bullet - Bullet
     */
    @Override
    public void hitBy(Bullet bullet) {
        bullet.destroy();
    }
}
