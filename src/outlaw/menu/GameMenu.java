package outlaw.menu;

import outlaw.gameboard.GameBoard;
import outlaw.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.*;

/** GameMenu osztály:
 * A játéktáblát és a navigáló, játék állapotát visszaállító, elmentő gombokat tartalmazó és kezelő menü.
 */
public class GameMenu extends Menu {
    private GameBoard gameBoard; /**< A játéktáblát tartamazó változó. */
    private JPanel gameBoardPanel = new JPanel(); /**< A menü azon része, ami játékot jeleníti meg. */

    /** A megadott méretnek megfelelő panel létrehozása. A panelen lévő gombok beállítása,
     * és a paraméterként kapott actionListener hozzárendelése a gombokhoz.
     * @param width - int
     * @param height - int
     * @param actionListener - ActionListener
     */
    public GameMenu(int width, int height, ActionListener actionListener) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        gameBoardPanel.setPreferredSize(new Dimension(width, height - 50));
        add(gameBoardPanel);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int buttonWidth = 121;
        int buttonHeight = 37;

        JButton backButton = new JButton();
        setButton(backButton, "back", buttonWidth, buttonHeight, actionListener);

        JButton resetButton = new JButton();
        setButton(resetButton, "reset", buttonWidth, buttonHeight, actionListener);

        JButton saveButton = new JButton();
        setButton(saveButton, "save", buttonWidth, buttonHeight, actionListener);

        buttons.add(backButton);
        buttons.add(resetButton);
        buttons.add(saveButton);

        add(buttons);
    }

    /** A játék elindítása.
     */
    public void startGame() {
        if(gameBoard != null) {
            gameBoard.start();
        }
    }

    /** A játék befejezése.
     */
    public void stopGame() {
        if(gameBoard != null) {
            gameBoard.stop();
        }
    }

    /** Új játék létrehozása, és hozzáadása a menühöz.
     */
    public void newGame() {
        clearGameBoard();

        gameBoard = new GameBoard(gameBoardPanel.getPreferredSize().width,  gameBoardPanel.getPreferredSize().height);
        gameBoardPanel.add(gameBoard.getBoard());
        KeyListener[] keyListeners = gameBoard.getKeyListeners();
        for(int i = 0;i < keyListeners.length;i++) {
            addKeyListener(keyListeners[i]);
        }
    }

    /** A játék aktuális állapotának elmentése fájlba serializálásal.
     */
    public void saveGame() {
        if(gameBoard == null) {
            JOptionPane.showMessageDialog(null, "Game is ended can't save.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            FileOutputStream file = new FileOutputStream("save.txt");
            ObjectOutputStream os = new ObjectOutputStream(file);
            os.writeObject(gameBoard);
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyListener[] keyListeners = gameBoard.getKeyListeners();
        for(int i = 0;i < keyListeners.length;i++) {
            addKeyListener(keyListeners[i]);
        }
    }

    /** A játék betöltése fájlból serializálásal. Ha sikeres volt a beolvasás a visszatérési érték true.
     * @return boolean
     */
    public boolean loadGame() {
        clearGameBoard();

        if(!(new File("save.txt")).exists()) {
            JOptionPane.showMessageDialog(null, "Saved game not found.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        try {
            FileInputStream file = new FileInputStream("save.txt");
            ObjectInputStream is = new ObjectInputStream(file);
            gameBoard = (GameBoard)is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        gameBoardPanel.add(gameBoard.getBoard());
        KeyListener[] keyListeners = gameBoard.getKeyListeners();
        for(int i = 0;i < keyListeners.length;i++) {
            addKeyListener(keyListeners[i]);
        }

        return true;
    }

    /** A játéktábla eltávolítása a panelről, és a játéktábla törlése.
     */
    private void clearGameBoard() {
        if(gameBoard != null) {
            gameBoardPanel.remove(gameBoard.getBoard());
            KeyListener[] keyListeners = getKeyListeners();
            for(int i = 0;i < keyListeners.length;i++) {
                removeKeyListener(keyListeners[i]);
            }
            gameBoardPanel.revalidate();
            gameBoard = null;
        }
    }
}
