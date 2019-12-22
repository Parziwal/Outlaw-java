package outlaw.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/** Menu osztály:
 * A frame-re helyezhető menük osztálya.
 */
public class Menu extends JPanel {
    /** A panelen lávő gombok értékeinek beállítását végzi.
     * @param button - JButton
     * @param name - String
     * @param width - int
     * @param height - int
     * @param actionListener - ActionListener
     */
    public void setButton(JButton button, String name, int width, int height, ActionListener actionListener) {
        try {
            BufferedImage img = ImageIO.read(new File( "images/buttons/" + name + "_button.png"));
            img = scale(img, width, height);
            button.setIcon(new ImageIcon(img));
            img = ImageIO.read(new File("images/buttons/" + name + "_button_pressed.png"));
            img = scale(img, width, height);
            button.setPressedIcon(new ImageIcon(img));
            img = ImageIO.read(new File("images/buttons/" + name + "_button_rollover.png"));
            img = scale(img, width, height);
            button.setRolloverIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        button.setSize(width,height);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setActionCommand(name);
        button.addActionListener(actionListener);
    }


    /** Egy kép átméretezése a paraméterként megadott méretre.
     * @param imageToScale - BufferedImage
     * @param width - int
     * @param height - int
     * @return BufferedImage
     */
    public BufferedImage scale(BufferedImage imageToScale, int width, int height) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(width, height, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, width, height, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }
}
