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
	public PanelImage img;

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
        JButton btn_launch = new JButton("Lancer");
        btn_launch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Animation_launched(evt);
            }
        });
		btn_upload.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{	
					
					String load=upload();
					if(load!="aucun fichier selectionn�"){
						img = null;
						try {
                            img = new PanelImage(load);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        img.setBounds(100, 10, 1000 , 650);
                        img.setLayout(null);
                        img.setBackground(Color.WHITE);
                        img.setBorder(BorderFactory.createLineBorder(Color.black));
						contentPane.add(img);
						repaint();
				}
				}
			});	

		btn_upload.setBounds(50,670, 125, 30);
        btn_launch.setBounds(100,670, 125, 30);
		contentPane.add(btn_upload);
        contentPane.add(btn_launch);
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
        if (chemin == null) {
            return "aucun fichier selectionn�";
        }
        else {
            return chemin;
        }
	}

    private void Animation_launched (java.awt.event.MouseEvent evt){
        moveThread move = new moveThread((JPanel)img);
        move.start();
    }

}
