import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;




public class Fenetre extends JFrame { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel img = new JLabel();
	private Image image;
	Fenetre(){
		final JPanel contentPane;
		setTitle("Image"); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setBounds(0, 0, 1200, 750); 
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btn_upload = new JButton("Upload");
		
		btn_upload.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{	
					
					String load=upload();
					if(load!="aucun fichier selectionné"){
						PanelImage PanelImage = null;
						try {
							PanelImage = new PanelImage(load);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						PanelImage.setBounds(100, 10, 1000 , 650);
						PanelImage.setLayout(null);
						PanelImage.setBackground(Color.WHITE);
						PanelImage.setBorder(BorderFactory.createLineBorder(Color.black));

						contentPane.add(PanelImage);
						repaint();
				}
				}
			});	
		
		 
		btn_upload.setBounds(50,670, 125, 30);
		 contentPane.add(btn_upload);
	}
	private String upload() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        int returnVal = chooser.showOpenDialog(getContentPane());
        String chemin = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File ficChoisi = chooser.getSelectedFile();
            chemin = ficChoisi.getAbsolutePath();
            System.out.println("Chargement du fichier : " + ficChoisi.getAbsolutePath());
   
            }
        if (chemin == null)
        	return "aucun fichier selectionné";
        else
        	return chemin;
	}

}
