package outlaw.gameobject;

/** NormalWall osztály:
 * Egy olyan GameObject, ami egy sima falat reprezentál a játéktéren.
 */
public class NormalWall extends GameObject {

    /** Konstruktor:
     * A megadott szélességű, vastagságú és pozíziójú fal létrehozása.
     * A falhoz tartozó mártix minden cellájának true értékre való állítása.
     * @param posX - int
     * @param posY - int
     * @param width - int
     * @param heigth - int
     */
    public NormalWall(int posX, int posY, int width, int heigth) {
        super(posX, posY, width, heigth);
        for(int i = 0;i < super.getHeight();i++){
            for(int j = 0;j < super.getWidth();j++){
                super.setCell(i, j, true);
            }
        }
    }

    /** Lövedékkel való ütközéskor a lövedék megsemmisítése.
     * @param bullet - Bullet
     */
    @Override
    public void hitBy(Bullet bullet) {
        bullet.destroy();
    }
}
