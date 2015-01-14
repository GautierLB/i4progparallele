import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;


public class PanelImage extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;
	PanelImage(Image img) throws IOException{
		 // image = javax.imageio.ImageIO.read (new File(img));
		this.image=img;
		  repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawImage(image,0, 0, this);
		
		
	}
}
