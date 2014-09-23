package com.loganElliott.imagecircle;
import java.awt.*;

import javax.swing.*;

/**
 * Author: Logan Elliott
 * Program: Image Circle Calculator and diagram
 * Date: 6/11/2013
 * Version: 1.5
 * 
 * Additonal Info:
 *  4" = 101.6
 *  5" = 127mm
 **/

@SuppressWarnings("serial")
public class ImageCircleJFrame extends JFrame {
    
    public ImageCircleJFrame(String title, int x, int y, int width, int height) {
    	JPanel imageCircleJPanel = new ImageCirleJPanel();
    	JFrame imageCircleJFrame = new JFrame();
    	// Set the title, top left location, and close operation for the frame
        
        imageCircleJFrame.setTitle(title);
        imageCircleJFrame.setLocation(x, y);
        imageCircleJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the size of the content pane of the window, resize and validate the
        // window to suit, obtain keyboard focus, and then make the window visible
        imageCircleJFrame .setPreferredSize(new Dimension(width, height));
        imageCircleJFrame.setLayout(new BorderLayout());
        imageCircleJFrame.add(imageCircleJPanel, BorderLayout.CENTER);
        imageCircleJFrame.pack();
        imageCircleJFrame.setVisible(true);
        imageCircleJFrame.setResizable(false);
        
        

    }
}

class run {
	public static final int JFRAME_AREA_WIDTH = 1024;
    public static final int JFRAME_AREA_HEIGHT = 1024;
    
    public static void main(String[] args) {    	
    	@SuppressWarnings("unused")
		ImageCircleJFrame gui = new ImageCircleJFrame("Image Circle Calculator",0,0,JFRAME_AREA_WIDTH,JFRAME_AREA_HEIGHT);
    }
     	
    	
}
