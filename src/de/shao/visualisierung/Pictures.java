package de.shao.visualisierung;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public enum Pictures {

    AUTOMAT("images/automat.png"),
    HASEN("images/runner.png"),
    SLIDER("images/slider.png"),
    BACKGROUND("images/musicbackground.png");

    private final boolean DEBUG = true;

    private String path;
    Map<String, Image> imageMap = new HashMap<>();

    Pictures(String path) {
        this.path = path;
    }

    public Image image() {
        if (imageMap.containsKey(path)) {
            return imageMap.get(path);
        } else {
            BufferedImage image;
            try {
                if (DEBUG){
                    image = ImageIO.read(new File("resources/" + path));
                }else{
                    image = ImageIO.read(getClass().getResource("/"+path));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return image;
        }
    }
}
