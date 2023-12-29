package swing;

import java.awt.Graphics;
import java.awt.Image;

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
			imageIcon = new ImageIcon(BackgroundPanel.class.getResource(imagePath));
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