import javax.swing.*;
import java.awt.*;

/**
 * Created by Gautier on 14/01/2015.
 */
public class moveThread extends Thread{

    private JPanel target;
    private boolean interrupted;

    public moveThread(JPanel _target) {
        this.target = _target;
        this.interrupted = false;
    }

    @Override
    public synchronized void run() {
        while (!this.interrupted) {
            try {
                this.target.setLocation(this.target.getX(), (this.target.getY()+1));
                this.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void interrupt(){
        this.interrupted = true;
    }
}
