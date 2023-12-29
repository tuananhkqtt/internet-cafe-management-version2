package swing;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton{
	
	public IconButton() {
		super.setBorderPainted(false);
		super.setSize(10, 10);
	}
	
	@Override
	public void setIcon(Icon icon) {
		super.setIcon(resizeIcon(icon));
		super.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	private Icon resizeIcon(Icon icon) {
		setMargin(new Insets(0, 0, 0, 0));
		
		int width, height;
		
		double ratio = (double) icon.getIconWidth() / icon.getIconHeight();
		// set icon width = button width
		width = this.getWidth();
		// change icon height so that ratio aren't changed
		height = (int) (width/ratio);
		// if icon's dimension is smaller than button's dimension (icon's width = button's width, icon's height < button's height)
		if(height < this.getHeight()) {
			// increase icon's height = button's height
			height = this.getHeight();
			// ensure ratio aren't changed
			width = (int) (height/ratio);
		}
		ImageIcon imageIcon = (ImageIcon) icon;
		// start implement the function to resize image according to the width and height dimensions we have just calculated
		Image image = imageIcon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		// set image of imageIcon again
		imageIcon.setImage(image);
		
		return imageIcon;
	}
}
