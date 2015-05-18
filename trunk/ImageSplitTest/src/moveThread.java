import javax.swing.*;

/**
 * Created by Gautier on 14/01/2015.
 */
public class moveThread extends Thread {

    private JPanel target;
    private JPanel target2;
    private boolean interrupted;
    private boolean up;

    public moveThread(JPanel _target, JPanel _target2, boolean up) {
        this.target = _target;
        this.target2 = _target2;
        this.interrupted = false;
        this.up = up;
    }

    @Override
    public synchronized void run() {
            while (true) {
                try {
                    if (up == true) {
                        this.target.setLocation(this.target.getX(), (this.target.getY() + 1));
                        this.target2.setLocation(this.target2.getX(), (this.target2.getY() + 1));
                        if (this.target.getY() > this.target.getHeight()) {
                            this.target.setLocation(this.target.getX(), -this.target.getHeight());
                        }
                        if (this.target2.getY() > this.target2.getHeight()) {
                            this.target2.setLocation(this.target2.getX(), -this.target2.getHeight());
                        } else {
                            this.target.setLocation(this.target.getX(), (this.target.getY() - 1));
                            this.target2.setLocation(this.target2.getX(), (this.target2.getY() - 1));
                            if (this.target.getY() < -this.target.getHeight()) {
                                this.target.setLocation(this.target.getX(), this.target.getHeight());
                            }
                        }
                      if (this.target2.getY() < -this.target2.getHeight()) {
                            this.target2.setLocation(this.target2.getX(), this.target2.getHeight());
                        }
                    }
                    this.sleep(10);
                    synchronized (this) {
                        while (this.interrupted)
                            wait();
                    }
                }
                 catch (InterruptedException e) {

                 }
        }

    }

    public void setInterrupted(boolean value) {
        this.interrupted = value;
    }
    public boolean getInterrupted() {
        return this.interrupted;
    }
}
