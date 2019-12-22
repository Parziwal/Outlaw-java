package outlaw.sprite;

/** RightPlayerSprite osztály:
 * A jobb oldali játékos kinézeteit tároló osztály.
 */
public class RightPlayerSprite implements PlayerSprite {
    private static final boolean[][] rightPlayerStand = {
            {false, false, false, true, true, false, false, false},
            {false, true, true, true, true, true, false, false},
            {false, false, true, true, true, false, false, false},
            {false, false, false, true, true, false, false, false},
            {false, true, true, true, true, true, true, false},
            {true, false, false, true, true, false, false, true},
            {true, false, false, true, true, false, false, true},
            {true, false, false, true, true, false, false, true},
            {false, true, false, true, true, false, true, false},
            {false, false, true, true, true, true, false, false},
            {false, true, true, false, false, true, true, false},
            {true, true, false, false, false, false, true, true}
    }; /**< A jobb oldali álló játékos képe. */

    private static final boolean[][] rightPlayerDead = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, true, true, false, false},
            {false, false, true, true, true, true, true, false},
            {false, false, false, true, true, true, false, false},
            {false, false, false, false, true, true, false, false},
            {false, false, false, false, true, true, true, false},
            {false, false, false, false, true, true, false, true},
            {true, false, false, false, true, true, false, true},
            {true, true, true, true, true, true, true, false}
    }; /**< A jobb oldali lelőtt játékos képe. */

    private static final boolean[][] rightPlayerFireStraight = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, true},
            {false, false, false, false, true, true, true, false},
            {true, true, true, false, false, true, true, false},
            {false, false, true, true, true, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, false},
            {false, false, false, true, false, true, false, false},
            {false, false, true, true, false, true, true, true}
    }; /**< A jobb oldali egyenesen lövő játékos képe. */

    private static final boolean[][] rightPlayerFireUp = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, true},
            {true, false, false, false, true, true, true, false},
            {false, true, true, false, false, true, true, false},
            {false, false, true, true, true, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, false},
            {false, false, false, true, false, true, false, false},
            {false, false, true, true, false, true, true, true}
    }; /**< A jobb oldali felfele lövő játékos képe. */

    private static final boolean[][] rightPlayerFireDown = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, true},
            {false, false, false, false, true, true, true, false},
            {false, true, true, false, false, true, true, false},
            {true, false, true, true, true, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, false, false, true, true, false},
            {false, false, false, true, true, true, true, false},
            {false, false, false, true, false, true, false, false},
            {false, false, true, true, false, true, true, true}
    }; /**< A jobb oldali lefele lövő játékos képe. */

    /** A jobb oldali álló játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getStand() {
        return rightPlayerStand;
    }

    /** A jobb oldali lelőtt játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getDead() {
        return rightPlayerDead;
    }

    /** Az jobb odali egyenesen lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireStraight() {
        return rightPlayerFireStraight;
    }

    /** A jobb oldali felfele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireUp() {
        return rightPlayerFireUp;
    }

    /** A jobb oldali lefele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireDown() {
        return rightPlayerFireDown;
    }
}
