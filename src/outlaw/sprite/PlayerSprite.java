package outlaw.sprite;

import java.io.Serializable;

/** PlayerSprite interfész:
 * A játékosok kinézeteit reprezetáló interfész.
 */
public interface PlayerSprite extends Serializable {
    /** Az álló játékos képének visszaadása.
     * @return boolean[][]
     */
    boolean[][] getStand();
    /** Lelőtt játékos képének visszaadása.
     * @return boolean[][]
     */
    boolean[][] getDead();
    /** Az egyenesen lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    boolean[][] getFireStraight();
    /** A felfele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    boolean[][] getFireUp();
    /** A lefele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    boolean[][] getFireDown();
}
