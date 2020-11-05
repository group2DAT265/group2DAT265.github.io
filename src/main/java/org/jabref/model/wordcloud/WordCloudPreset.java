package org.jabref.model.wordcloud;

import com.kennycason.kumo.font.FontWeight;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.font.KumoFont;

import java.awt.*;

// This class holds the preferences from the user to be used when generating a word cloud

public class WordCloudPreset {

    private KumoFont font; // The font of the word cloud
    private String shape; // The shape; Rectangle or Circle
    private ColorPalette colors; // The colors used in the word cloud for the text
    private Color background; // The background color, currently only white or black

    // Dummy constructor used when downloading or not
    public WordCloudPreset(){
    }

    public WordCloudPreset(String font, String shape, String color, String background) {
        this.font = new KumoFont(font, FontWeight.PLAIN);
        this.shape = shape;
        switch(color){
            case "Blue color palette":  this.colors = new ColorPalette(new Color(3,37,76), new Color(17,103,177), new Color(24, 123, 205), new Color(42, 157, 244));
                                        break;
            case "Red color palette":   this.colors = new ColorPalette(new Color(167,0,0), new Color(255,0,0), new Color(255,82,82), new Color(255,123,123), new Color(255,186,186));
                                        break;
            case "Green color palette": this.colors = new ColorPalette(new Color(30, 86, 49), new Color(76, 154, 42), new Color(118, 186, 27), new Color(104, 187, 89), new Color(172, 223, 135));
                                        break;
            default:                    this.colors = new ColorPalette(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE);
                                        break;
        }
        if(background == "White"){
            this.background = Color.WHITE;
        }else{
            this.background = Color.BLACK;
        }

    }

    public KumoFont getFont() {
        return font;
    }

    public String getShape() {
        return shape;
    }

    public ColorPalette getColors() {
        return colors;
    }

    public Color getBackground(){
        return background;
    }




}
