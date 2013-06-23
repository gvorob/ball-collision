/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import truecollisions.Balls.BallHandler;
import truecollisions.Tools.*;
import truecollisions.Util.BoundingBox;
import truecollisions.Util.ImageLoader;
import truecollisions.Util.KeyEventListener;
import truecollisions.Util.Keyboard;
import truecollisions.Util.Misc;
import truecollisions.Util.Mouse;
import truecollisions.Util.MouseEventListener;
import truecollisions.Util.Vector2;

/**
 *
 * @author George
 */
public class Toolbelt implements KeyEventListener, MouseEventListener{
    
    public ArrayList<Tool> tools;
    public Tool currTool, lastMousedTool;
    public BallHandler balls;
    public BoundingBox bounds;
    public final int iconWidth = 32, margin = 8;
    
    public Toolbelt(ImageLoader im, Mouse m, Keyboard k, BallHandler b, int width, int height)
    {
        m.addListener(this);
        k.addListener(this);
        balls = b;
        tools = new ArrayList<Tool>(0);
        loadTools(im);
        bounds = new BoundingBox(new Vector2(0, height - 64), new Vector2(width,64));
    }
    
    public void draw(BufferedImage b)
    {
        BufferedImage icon = new BufferedImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);
        if(currTool != null){currTool.drawIcon(icon);}
        Graphics g = b.getGraphics();
        g.setColor(Color.gray);
        g.fillRect(0, b.getHeight() - 64, b.getWidth(), 64);
        //g.drawImage(icon, margin, b.getHeight() - 32 - (iconWidth / 2), iconWidth, iconWidth, null);
        int i = 0;
        for(Tool t : tools)
        {
            t.drawIcon(icon);
            if(t.equals(currTool))
            {
                g.setColor(Color.white);
                g.fillRect(
                        i * (margin + iconWidth) + margin - margin / 4,
                        b.getHeight() - 32 - (iconWidth / 2) - margin / 4,
                        iconWidth + margin / 2,
                        iconWidth + margin / 2);
            }
            if(t.equals(lastMousedTool))
            {
                g.setColor(Color.yellow);
                g.fillRect(
                        i * (margin + iconWidth) + margin - margin / 4,
                        b.getHeight() - 32 - (iconWidth / 2) - margin / 4,
                        iconWidth + margin / 2,
                        iconWidth + margin / 2);
            }
            g.drawImage(icon, margin + i * (margin + iconWidth), b.getHeight() - 32 - (iconWidth / 2), iconWidth, iconWidth, null);
            i++;
        }
        if(currTool != null){currTool.draw(b);}
    }
    
    public boolean KeyChange(int index, boolean down)
    {
        if(currTool == null) return true;
        return currTool.KeyChange(index, down);
    }
    public boolean mouseClicked(int x, int y, boolean  left, boolean down)
    {
        if((bounds.isPointInside(new Point(x,y)) && down) || currTool == null)
        {
            if(lastMousedTool != null)
            {
                currTool = lastMousedTool;
            }
            return true;
        }
        else{
        return currTool.mouseClicked(x, y, left, down);}
    }
    public boolean mouseMoved(int oldX, int oldY, int x, int y, boolean left, boolean right)
    {
        if(bounds.isPointInside(new Point(x,y)))
        {
            BoundingBox b = new BoundingBox(
                    new Vector2(margin, bounds.getTop() + 32 - iconWidth / 2),
                    new Vector2(iconWidth, iconWidth));
            lastMousedTool = null;
            for(Tool t : tools)
            {
                if(b.isPointInside(new Point(x,y)))
                {
                    lastMousedTool = t;
                }
                b.pos.x += iconWidth + margin;
            }
        }
        if (currTool  == null) return true;
        return currTool.mouseMoved(oldX, oldY, x, y, left, right);
    }
    
    private void loadTools(ImageLoader im) {
        tools.add(new BallTool(im, this));
        tools.add(new PointerTool(im, this));
        currTool = tools.get(0);
    }
}
