package outlaw.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** MainMenu osztály:
 * A program főmenüjét reprezentáló osztály.
 */
public class MainMenu extends Menu {

    /** A megadott méretnek megfelelő panel létrehozása. A panelen lévő gombok beállítása,
     * paraméterként kapott actionListener hozzárendelése, majd hozzáadása a panelhez.
     * @param width - int
     * @param height - int
     * @param actionListener - ActionListener
     */
    public MainMenu(int width, int height, ActionListener actionListener) {
        setLayout(null);
        setPreferredSize(new Dimension(width, height));
        repaint();

        int buttonWidth = 181;
        int buttonHeight = 56;

        JButton playButton = new JButton();
        setButton(playButton, "play", buttonWidth, buttonHeight, actionListener);
        playButton.setLocation(width / 2 - playButton.getSize().width / 2, 200);

        JButton loadButton = new JButton();
        setButton(loadButton, "load", buttonWidth, buttonHeight, actionListener);
        loadButton.setLocation(width / 2 - loadButton.getSize().width / 2, 220 + buttonHeight);

        JButton exitButton = new JButton();
        setButton(exitButton, "exit", buttonWidth, buttonHeight, actionListener);
        exitButton.setLocation(width / 2 - exitButton.getSize().width / 2, 240 + 2 * buttonHeight);

        add(playButton);
        add(loadButton);
        add(exitButton);
    }

    /** A főmenü hátterének kirajzolása.
     * @param g - Graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            BufferedImage img = ImageIO.read(new File( "images/main_menu.png"));
            g.drawImage(img, 0,0, 750, 550, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
