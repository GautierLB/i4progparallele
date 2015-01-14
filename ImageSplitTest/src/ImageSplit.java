import javax.imageio.ImageIO;  
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;  
  
public class ImageSplit {  

	public JPanel affichage;
	public PanelImage images[] = new PanelImage[3];
    public ImageSplit(String path,JPanel affichage) throws IOException {  
    	this.affichage=affichage;
        File file = new File(path); // I have bear.jpg in my working directory  
        FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = resize(ImageIO.read(fis), 1000, 650); //reading the image file  
  
        int rows = 1; //You should decide the values for rows and cols variables  
        int cols = 3;  
        int chunks = rows * cols;  
        
        int chunkWidth = image.getWidth() / cols; // determines the chunk width and height  
        int chunkHeight = image.getHeight() / rows;  
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks]; //Image array to hold image chunks  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //Initialize the image array with image chunks  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
  
                // draws the image chunk  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x, chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
        System.out.println("Splitting done");  
  
        //writing mini images into image files  
        for (int i = 0; i < imgs.length; i++) {  
        	
        	PanelImage imagetemp =new PanelImage(imgs[i]); 
        	imagetemp.setBounds(i*333, 0, 333 , 650);
        	imagetemp.setLayout(null);
        	imagetemp.setBackground(Color.WHITE);
        	imagetemp.setBorder(BorderFactory.createLineBorder(Color.black));

        	affichage.add(imagetemp,-1);
        	images[i]=imagetemp;
        	
        }  
        /*PanelImage imagetemp =new PanelImage(image); 
    	imagetemp.setBounds(100, 10, 1000 , 650);
    	imagetemp.setLayout(null);
    	imagetemp.setBackground(Color.WHITE);
    	imagetemp.setBorder(BorderFactory.createLineBorder(Color.black));

    	ContentPane.add(imagetemp);*/
        System.out.println("Mini images created");  
    }  
    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage i = new BufferedImage(width, height, image.getType());
        Graphics2D g = i.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        g.dispose();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     
        return i;
    }
}  