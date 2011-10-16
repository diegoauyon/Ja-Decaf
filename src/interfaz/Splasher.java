package interfaz;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
 * @(#)Splasher.java  2.0  January 31, 2004
 *
 * Copyright (c) 2003-2004 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * This software is in the public domain.
 */

import javax.imageio.ImageIO;

/**
 *
 * @author  werni
 */
public class Splasher {
    /**
     * Shows the splash screen, launches the application and then disposes
     * the splash screen.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	BufferedImage img = null;
    	try {
    	    img = ImageIO.read(new File("./libs/data/logo.png"));
    	} catch (IOException e) {
    	}
        SplashWindow.splash(img);      
        SplashWindow.invokeMain("interfaz.IDecaf", args);
        SplashWindow.disposeSplash();
    }
    
}
