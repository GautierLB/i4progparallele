import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Fenetre extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ImageSplit imgsplit = null;
    private ArrayList<moveThread> moveThreads;

    Fenetre() {
        final JPanel contentPane;
        final JPanel affichage;
        moveThreads = new ArrayList<moveThread>();
        setTitle("Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1200, 750);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        affichage = new JPanel();
        //affichage.setBorder(new EmptyBorder(5, 5, 5, 5));
        affichage.setBounds(100, 10, 1000, 650);

        //affichage.setBorder(BorderFactory.createLineBorder(Color.black));
        affichage.setLayout(null);
        contentPane.add(affichage);
        JButton btn_upload = new JButton("Upload");
        JButton btn_launch = new JButton("Lancer");
        btn_launch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (imgsplit != null) {
                    if (moveThreads.size() == 0) {
                        Animation_launched(evt);
                    } else if (moveThreads.get(0).getInterrupted() == true) {
                        for (int i = 0; i < moveThreads.size(); i++) {
                            synchronized (moveThreads.get(i)){
                                moveThreads.get(i).setInterrupted(false);
                                moveThreads.get(i).notify();
                            }
                        }
                    }
                }
            }
        });

        JButton btn_pause = new JButton("Pause");
        btn_pause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Animation_paused(evt);
            }
        });

        btn_upload.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                String load = upload();
                if (load != "aucun fichier selectionn�") {
                    if (imgsplit != null) {
                        for (int i = 0; i < imgsplit.images.length; i++) {
                            contentPane.remove(imgsplit.images[i]);
                        }
                    }
                    try {
                        imgsplit = new ImageSplit(load, affichage);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    repaint();
                }
            }
        });

        btn_upload.setBounds(50, 670, 125, 30);
        btn_launch.setBounds(400, 670, 125, 30);
        btn_pause.setBounds(700, 670, 125, 30);
        contentPane.add(btn_upload, 0);
        contentPane.add(btn_launch, 0);
        contentPane.add(btn_pause, 0);
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
            return "aucun fichier selectionn?";
        } else {
            return chemin;
        }
    }

    private void Animation_launched(java.awt.event.MouseEvent evt) {
        boolean direction = true;
        for (int i = 0; i < 3; i++) {
            moveThread move;

            if ((direction == true)) {
                move = new moveThread((JPanel) imgsplit.images[i], (JPanel) imgsplit.images[i + 3], true);
                direction = false;
            } else {
                move = new moveThread((JPanel) imgsplit.images[i], (JPanel) imgsplit.images[i + 3], false);
                direction = true;
            }
            if (i == 2) {
                direction = true;
            }

            moveThreads.add(move);
            move.start();
        }
        System.out.println();
    }

    private synchronized void Animation_paused(java.awt.event.MouseEvent evt){
        for (moveThread thread : moveThreads) {
            thread.setInterrupted(true);
        }

    }

}
