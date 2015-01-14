import javax.swing.*;
import java.awt.*;

/**
 * Created by Gautier on 14/01/2015.
 */
public class moveThread extends Thread{

    private JPanel target;

    public moveThread(JPanel _target) {
        this.target = _target;
    }

    @Override
    public synchronized void run() {
        for (int i = 1; i <= 100; i++) {
            try {
                this.target.setLocation(this.target.getX(), (this.target.getY()+1));
                this.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
