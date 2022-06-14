package de.shao.visualisierung;

import javax.swing.*;
import java.awt.*;

public class Hauptfenster extends JFrame {

    private int frameWidth = 1920 ;
    private int frameHeight = 1080;

    public Hauptfenster(){
        setSize(new Dimension(frameWidth,frameHeight));
        setUndecorated(true);
        setLocationRelativeTo(null);
        setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
        add(new Inhalt());
        setVisible(true);
    }
}
