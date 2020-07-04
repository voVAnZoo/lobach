import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    JFrame myFrame;
    Timer timer;

    public static void main(String[] args) {
        Main m = new Main();
        m.init();
        m.timer.start();
    }

    public void init(){
        myFrame = new JFrame("Lobach");
        timer = new Timer(Data.friq, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
