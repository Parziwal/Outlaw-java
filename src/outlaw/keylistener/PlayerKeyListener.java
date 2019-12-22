package outlaw.keylistener;

import outlaw.enumeration.ControlKey;
import outlaw.gameobject.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/** PlayerKeyListener osztály:
 * A játékosok megadott billentyűkkel való irányítását végző osztály. Az objektumok fájlba írása
 *  miatt megvalósítja a Serializable interfészt.
 */
public class PlayerKeyListener extends KeyAdapter implements Serializable {
    private Player player; /**< Az a játékos, aminek az irányításáért felelős. */
    private Set<Integer> pressed = new HashSet<Integer>(); /**< A lenyomott billentyűket tároló halmaz. */
    private HashMap<ControlKey, Integer> keys = new HashMap<ControlKey, Integer>(); /**< A megfelelő ControlKey értékekhez tartozó billentyűkódokat tárolja. */
    private Set<Integer> blockedKeys = new HashSet<Integer>(); /**< A blokkolt billentyűket tároló halmaz. */

    /** Konstruktor:
     * A megadott játékos irányításához szükséges billentyűk beállítása.
     * @param player - Player
     * @param moveUpKey - Integer
     * @param moveDownKey - Integer
     * @param moveLeftKey - Integer
     * @param moveRightKey - Integer
     * @param fireKey - Integer
     */
    public PlayerKeyListener(Player player, Integer moveUpKey, Integer moveDownKey, Integer moveLeftKey, Integer moveRightKey, Integer fireKey) {
        this.player = player;
        keys.put(ControlKey.UP, moveUpKey);
        keys.put(ControlKey.DOWN, moveDownKey);
        keys.put(ControlKey.LEFT, moveLeftKey);
        keys.put(ControlKey.RIGHT, moveRightKey);
        keys.put(ControlKey.FIRE, fireKey);
    }

    private int bulletVecY; /**< A lövedék Y irányú vektora. */

    /** A lenyomott billentyűk detektálás, és ennek megfelelően a játékos mozgási és
     * lövési irányának beállítása.
     * @param e - KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Integer keyCode =  e.getKeyCode();
        pressed.add(keyCode);

        if (pressed.contains(keys.get(ControlKey.UP))) {
            bulletVecY = -1;
        } else if (pressed.contains(keys.get(ControlKey.DOWN))) {
            bulletVecY = 1;
        } else {
            bulletVecY = 0;
        }
        if(pressed.contains(keys.get(ControlKey.FIRE)) && player.isAlive()) {
            player.setImageFire(bulletVecY);
            player.setVectorX(0);
            player.setVectorY(0);
        } else if(!blockedKeys.contains(keyCode)) {
            if (keyCode.equals(keys.get(ControlKey.UP))) {
                player.setVectorY(-1);
            } else if (keyCode.equals(keys.get(ControlKey.DOWN))) {
                player.setVectorY(1);
            } else if (keyCode.equals(keys.get(ControlKey.LEFT))) {
                player.setVectorX(-1);
            } else if (keyCode.equals(keys.get(ControlKey.RIGHT))) {
                player.setVectorX(1);
            }
        }
    }

    /** Az elengedett billentyűk deketálása, és ennek megfelelően a játékos mozgási irányának
     * 0-ba állítása, és a lövedék kilövése.
     * @param e - KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        Integer keyCode = e.getKeyCode();
        pressed.remove(keyCode);
        if(keyCode.equals(keys.get(ControlKey.UP)) || keyCode.equals(keys.get(ControlKey.DOWN))) {
            player.setVectorY(0);
            bulletVecY = 0;
            if(pressed.contains(keys.get(ControlKey.FIRE))) {
                player.setImageFire(0);
            }
        } else if(keyCode.equals(keys.get(ControlKey.LEFT)) || keyCode.equals(keys.get(ControlKey.RIGHT))) {
            player.setVectorX(0);
        } else if(keyCode.equals(keys.get(ControlKey.FIRE)) && player.isAlive()) {
            player.fire(bulletVecY*2);
            player.setImageStand();
        }
    }

    /** A block paraméter értékétől függően a paraméterben megadott billentyű blokkolása, vagy engedélyzése.
     * @param key - ControlKey
     * @param block - boolean
     */
    public void blockMovementKey(ControlKey key, boolean block) {
        if(!block) {
            blockedKeys.remove(keys.get(key));
        } else if(!blockedKeys.contains(keys.get(key))) {
            blockedKeys.add(keys.get(key));
            if(key.equals(ControlKey.UP) && player.getVectorY() < 0) {
                player.setVectorY(0);
            } else if (key.equals(ControlKey.DOWN) && player.getVectorY() > 0) {
                player.setVectorY(0);
            }else if(key.equals(ControlKey.LEFT) && player.getVectorX() < 0) {
                player.setVectorX(0);
            }else if (key.equals(ControlKey.RIGHT) && player.getVectorX() > 0) {
                player.setVectorX(0);
            }
        }
    }
}
