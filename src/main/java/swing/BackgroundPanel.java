package swing;

import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	private Image image;

	/**
	 * Create the panel.
	 */
	public BackgroundPanel(String imagePath) {
		new JPanel();
		ImageIcon imageIcon = null;
		try {
			if (getClass().getResource(imagePath) != null) {
				imageIcon = new ImageIcon(ImageIO.read(getClass().getResource(imagePath)));
			} else {
			    System.out.println(imagePath);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		image = imageIcon.getImage();
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }

}