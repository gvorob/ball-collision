/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Balls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import truecollisions.Util.Misc;
import truecollisions.Util.Vector2;

/**
 *
 * @author George
 */
public class BallHandler {
    private int minx, miny, maxx, maxy;
    private ArrayList<Ball> balls;
    private ArrayList<Ball> tempBalls;
    private final float gravity = 120f;
    
    public BallHandler(int x1, int x2, int y1, int y2)
    {
        minx = x1; maxx = x2;
        miny = y1; maxy = y2;
        balls = new ArrayList();
        tempBalls = new ArrayList();
    }
    
    public void step(float time)
    {
        for (Ball i : tempBalls)
        {
            balls.add(i);
        }
        tempBalls = new ArrayList();
        Vector2 g = new Vector2(0, gravity);
        for (Ball i : balls)
        {
            for (Ball j : balls)
            {
                if((i.radius + j.radius) * 2 > Vector2.vecSubt(i.pos, j.pos).taxiDist() && !i.equals(j))
                {
                    Vector2 v = Vector2.vecSubt(i.pos,j.pos);
                    float sumr = i.radius+j.radius;
                    float sumc = i.compressibility+j.compressibility;
                    if(v.length() < sumr)
                    {
                        v = Vector2.vecMult(500 / (v.length() / sumr) / sumc, v.normal());
                        i.getForce(v);
                        j.getForce(Vector2.vecMult(-1, v));
                    }
                }
            }
            i.getForce(Vector2.vecMult(i.mass, g));
            if(Math.abs(i.pos.y - maxy) < i.radius)
            {
                i.getForce(new Vector2(0,500 / i.radius / i.compressibility * -1 * (Math.abs(i.pos.y - maxy))));
                Vector2 v = new Vector2(0,(i.pos.y + i.radius - maxy));
            }
            i.step(time);
        }
    }
    
    public void draw(BufferedImage b)
    {
        Graphics g = b.getGraphics();
        g.setColor(Color.red);
        for (Ball i : balls)
        {
            g.drawOval((int)(i.pos.x - (0.5 * i.radius)), (int)(i.pos.y - (0.5 * i.radius)), (int)(2 * i.radius), (int)(2 * i.radius));
        }
    }
    
    public void addBall (Ball b)
    {
        tempBalls.add(b);
    }
    
    public void removeBall (Ball b)
    {
        balls.remove(b);
    }
}
