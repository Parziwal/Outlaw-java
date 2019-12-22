package outlaw.gameobject;

/** HorizontalBouncingWall osztály:
 * Egy olyan GameObject, ami egy horizontális falat reprezentál, amelyről a lövedékek visszapattannak.
 */
public class HorizontalBouncingWall extends GameObject{

    /** Konstruktor:
     * A megadott pozíciójú és szélességű fal létrehozása.
     * @param posX - int
     * @param posY - int
     * @param width - int
     */
    public HorizontalBouncingWall(int posX, int posY, int width) {
        super(posX, posY, width, 1);
        for(int i = 0;i < super.getHeight();i++){
            for(int j = 0;j < super.getWidth();j++){
                super.setCell(i, j, true);
            }
        }
    }

    /** A lövedékkel való ütközéskor a lövedék Y irányú vektorának negálása.
     * @param bullet - Bullet
     */
    @Override
    public void hitBy(Bullet bullet) {
        bullet.setVectorY(-bullet.getVectorY());
    }
}
