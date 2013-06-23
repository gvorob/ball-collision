/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import truecollisions.Toolbelt;
import truecollisions.Util.ImageLoader;
import truecollisions.Util.Misc;

/**
 *
 * @author George
 */
public class PointerTool extends Tool {
    
    
    public PointerTool(ImageLoader im, Toolbelt t)
    {
        super(im, t);
        toolTip = "Basic Pointer";
    }
    
    public void loadImage(ImageLoader im)
    {
        hotX = hotY = 8;
        icon = im.loadImage(new File("images\\tools.png"), 0, 0, 16, 16);
        Graphics g = icon.getGraphics();
        g.setColor(Color.red);
        g.drawRect(0, 0, 15, 15);
        cursor = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);//im.loadImage(new File("images\\tools.png"), 0, 16, 16, 16);
        g = cursor.getGraphics();
        g.setColor(Color.black);
        g.drawOval(0,0,15,15);
    }
    
    public void draw(BufferedImage b)
    {
        super.drawCursor(b);
    }
    
}
