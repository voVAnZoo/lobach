package core;

import javax.swing.*;
import java.awt.*;
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

        Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
        myFrame.setBounds(0,0, sSize.width, sSize.height);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myFrame.setVisible(true);

        timer = new Timer(Data.friq, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
