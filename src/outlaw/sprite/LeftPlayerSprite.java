package outlaw.sprite;

/** LeftPlayer Sprite osztály:
 * A bal oldali játékos kinézeteit tároló osztály.
 */
public class LeftPlayerSprite implements PlayerSprite {
    private static final boolean[][] leftPlayerStand = {
            {false, false, false, true, true, false, false, false},
            {false, false, true, true, true, true, true, false},
            {false, false, false, true, true, true, false, false},
            {false, false, false, true, true, false, false, false},
            {false, true, true, true, true, true, true, false},
            {true, false, false, true, true, false, false, true},
            {true, false, false, true, true, false, false, true},
            {true, false, false, true, true, false, false, true},
            {false, true, false, true, true, false, true, false},
            {false, false, true, true, true, true, false, false},
            {false, true, true, false, false, true, true, false},
            {true, true, false, false, false, false, true, true}
    }; /**< A bal oldai álló játékos képe. */

    private static final boolean[][] leftPlayerDead = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, false, true, true, false, false, false, false},
            {false, true, true, true, true, true, false, false},
            {false, false, true, true, true, false, false, false},
            {false, false, true, true, false, false, false, false},
            {false, true, true, true, false, false, false, false},
            {true, false, true, true, false, false, false, false},
            {true, false, true, true, false, false, false, true},
            {false, true, true, true, true, true, true, true}
    }; /**< A bal oldai lelőtt játékos képe. */

    private static final boolean[][] leftPlayerFireStraight = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {true, true, true, true, true, false, false, false},
            {false, true, true, true, false, false, false, false},
            {false, true, true, false, false, true, true, true},
            {false, true, true, true, true, true, false, false},
            {false, true, true, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {false, true, true, true, true, false, false, false},
            {false, false, true, false, true, false, false, false},
            {true, true, true, false, true, true, false, false}
    }; /**< A bal oldai egyenesen lövő játékos képe. */

    private static final boolean[][] leftPlayerFireUp = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {true, true, true, true, true, false, false, false},
            {false, true, true, true, false, false, false, true},
            {false, true, true, false, false, true, true, false},
            {false, true, true, true, true, true, false, false},
            {false, true, true, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {false, true, true, true, true, false, false, false},
            {false, false, true, false, true, false, false, false},
            {true, true, true, false, true, true, false, false}
    }; /**< A bal oldai felfele lövő játékos képe. */

    private static final boolean[][] leftPlayerFireDown = {
            {false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {true, true, true, true, true, false, false, false},
            {false, true, true, true, false, false, false, false},
            {false, true, true, false, false, true, true, false},
            {false, true, true, true, true, true, false, true},
            {false, true, true, false, false, false, false, false},
            {false, true, true, false, false, false, false, false},
            {false, true, true, true, true, false, false, false},
            {false, false, true, false, true, false, false, false},
            {true, true, true, false, true, true, false, false}
    }; /**< A bal oldai lefele lövő játékos képe. */

    /** A bal oldali álló játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getStand() {
        return leftPlayerStand;
    }

    /** A bal oldali lelőtt játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getDead() {
        return leftPlayerDead;
    }

    /** Az bal odali egyenesen lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireStraight() {
        return leftPlayerFireStraight;
    }

    /** A bal oldali felfele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireUp() {
        return leftPlayerFireUp;
    }

    /** A bal oldali lefele lövő játékos képének visszaadása.
     * @return boolean[][]
     */
    @Override
    public boolean[][] getFireDown() {
        return leftPlayerFireDown;
    }
}
