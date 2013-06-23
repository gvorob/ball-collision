/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author George
 */
public class ImageLoader implements ImageObserver{
    
    private class pair
    {
        public String f;
        public Image i;
        public pair(String F, Image I){f = F; i = I;}
    }
    
    public Image[] Images;
    public ArrayList<pair> loadedImages;
    
    public ImageLoader()
    {
        loadedImages = new ArrayList<pair>();
        
    }    
    public BufferedImage loadImage(File f, int x, int y, int w, int h)
    {
        String file = f.getAbsolutePath();
        Misc.prln("File: " + file + "\nArray length was: " + loadedImages.size());
        ListIterator<pair> i = loadedImages.listIterator();
        while(i.hasNext())
        {
            if(i.next().f.equals(file))
            {
                Misc.prln("Old image loaded");
                return drawImage(loadedImages.get(i.previousIndex()).i, x, y, w, h);
            }
        }
        loadedImages.add(new pair(file, Toolkit.getDefaultToolkit().createImage(file)));
        Misc.prln("New Image loaded");
        return drawImage(loadedImages.get(loadedImages.size() - 1).i, x, y, w, h);
    }

    private BufferedImage drawImage(Image image, int x, int y, int w, int h)
    {
        BufferedImage b = new BufferedImage(w,h,BufferedImage.TYPE_4BYTE_ABGR);        
        Graphics g = b.getGraphics();
        g.setColor(Color.black);
        g.fillRect(1,1,14,14);
        g.drawImage(image, 0, 0, w, h, x, y, x+w, y+h,this);
        return b;
    }
    
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
    
}