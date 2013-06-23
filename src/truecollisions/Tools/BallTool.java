/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import truecollisions.Balls.Ball;
import truecollisions.Toolbelt;
import truecollisions.Util.ImageLoader;
import truecollisions.Util.Misc;
import truecollisions.Util.Vector2;

/**
 *
 * @author George
 */
public class BallTool extends Tool {
    
    private int ox, oy;
    private final float radius = 15;
    private final float mass = 1;
    private final float Comp = 0.1f;
    private final float friction = 0.1f;
    
    public BallTool(ImageLoader im, Toolbelt t)
    {
        super(im, t);
        ox = oy = -1;
        toolTip = "Launches a ball";
    }
    
    public void loadImage(ImageLoader im)
    {
        hotX = hotY = 8;
        icon = im.loadImage(new File("images\\tools.png"), 16, 0, 16, 16);
        Graphics g = icon.getGraphics();
        g.setColor(Color.red);
        g.drawRect(0, 0, 15, 15);
        cursor = im.loadImage(new File("images\\tools.png"), 16, 16, 16, 16);
        g = cursor.getGraphics();
        g.setColor(Color.gray);
        g.drawLine(0,8,16,8);
        g.drawLine(8, 0, 8, 16);
    }
    
    public boolean mouseClicked(int X, int Y, boolean  left, boolean down)
    {
        if(left)
        {
            if(down)
            {
                ox = X;
                oy = Y;
            }
            else
            {
                tools.balls.addBall(new Ball(
                        new Vector2(ox - radius,oy - radius),
                        radius,
                        Vector2.vecMult(5, new Vector2(ox - x, oy - y)),
                        mass,
                        Comp,
                        friction)
                        );
                ox = oy = -1;
            }
        }
        return true;
    }
    
    public void draw(BufferedImage b)
    {
        Graphics g  = b.getGraphics();
        g.setColor(Color.black);
        g.drawOval(x - 5, y- 5, 10, 10);
        g.drawImage(icon, x, y, null);
        if(ox != -1)
        {
            g.drawLine(x, y, ox, oy);
        }
    }
    
}
