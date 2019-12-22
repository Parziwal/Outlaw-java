package outlaw;

import outlaw.menu.GameMenu;
import outlaw.menu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Outlaw osztály:
 * A program megjelenítéséért felelős ablak.
 */
public class Outlaw extends JFrame implements ActionListener {
    private GameMenu gameMenu; /**< A játékot tartalmazó menü. */
    private MainMenu mainMenu; /**< A program főmenűje. */
    private CardLayout card = new CardLayout(); /**< CardLayout biztosítja a menük közötti váltást. */
    private Container container; /**< A JFramehez tartozó tároló, ami a komponenseket tartalmazza. */

    /** A program ablakának inicializálása a megadott méreteknek megfelelően.
     * Egy mainMenu és egy gameMenu panel létrehozása és hozzáadása a JFrame tárolójához.
     * @param width - int
     * @param height - int
     */
    public Outlaw(int width, int height) {
        super("Outlaw");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setResizable(false);
        container = getContentPane();
        container.setLayout(card);

        mainMenu = new MainMenu(width, height, this);
        gameMenu = new GameMenu(width, height, this);

        container.add("main", mainMenu);
        container.add("game", gameMenu);

        setVisible(true);
    }

    /** A programban lévő gombokjoz tartozó események detektálásáért felelős.
     * A Play esemény hatására létrehoz egy új játékot, átvált a játék menüre és elindítja a játékot.
     * A Load esemény hatására, ha volt elmentett játék betölti.
     * Az Exit esemény hatására kilép a játékból.
     * A Back esemény hatására visszalép a fő menübe.
     * A Reset esemény hatására újraindítja a játékot.
     * A Save esemény hatására elmenti a játékot.
     * @param e - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("play")) {
            gameMenu.newGame();
            card.show(container, "game");
            gameMenu.setFocusable(true);
            gameMenu.requestFocusInWindow();
            gameMenu.startGame();
        } else if(e.getActionCommand().equals("load")) {
            if(gameMenu.loadGame()) {
                card.show(container, "game");
                gameMenu.setFocusable(true);
                gameMenu.requestFocusInWindow();
                gameMenu.startGame();
            }
        } else if(e.getActionCommand().equals("exit")) {
            System.exit(0);
        } else if(e.getActionCommand().equals("back")) {
            gameMenu.stopGame();
            card.show(container, "main");
        } else if(e.getActionCommand().equals("reset")) {
            gameMenu.stopGame();
            gameMenu.newGame();
            gameMenu.setFocusable(true);
            gameMenu.requestFocusInWindow();
            gameMenu.startGame();
        } else if(e.getActionCommand().equals("save")) {
            gameMenu.saveGame();
            gameMenu.setFocusable(true);
            gameMenu.requestFocusInWindow();
        }
    }
}