/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Tools;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import truecollisions.Toolbelt;
import truecollisions.Util.ImageLoader;

/**
 *
 * @author George
 */
public class Tool {
    protected BufferedImage icon;
    protected String toolTip;
    protected int x, y;
    protected int hotX, hotY;
    protected BufferedImage cursor;
    protected Toolbelt tools;
    
    public Point getHotSpot(){return new Point(hotX, hotY);}
    
    public Tool(ImageLoader im, Toolbelt t)
    {
        loadImage(im);
        tools = t;
    }
    
    public boolean KeyChange(int index, boolean down)
    {
        return false;
    }
    
    public boolean mouseClicked(int X, int Y, boolean  left, boolean down)
    {
        return false;
    }
    
    public boolean mouseMoved(int oldX, int oldY, int X, int Y, boolean left, boolean right)
    {
        x = X; y = Y;
        return false;
    }
    
    public void loadImage(ImageLoader im){}
    
    public void drawIcon(BufferedImage b)
    {
        Graphics g = b.getGraphics();
        g.drawImage(icon, 0, 0, b.getWidth(), b.getHeight(), null);
    }
    
    public void draw(BufferedImage b)
    {}
    
    public String getText()
    {
        return toolTip;
    }

    protected void drawCursor(BufferedImage b) {
        b.getGraphics().drawImage(cursor, x - hotX, y - hotY, null);
    }
}
