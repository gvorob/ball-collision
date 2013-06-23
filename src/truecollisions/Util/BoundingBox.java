/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Util;

import java.awt.Point;

/**
 *
 * @author George
 */
public class BoundingBox {
    
    public Vector2 pos, size;
    
    public BoundingBox(Vector2 Pos, Vector2 Size)
    {
        pos = Pos;
        size = Size;
    }
    
    public Vector2 getPointNearest(Vector2 target)
    {
        if(target.x<getLeft())
        {
            if(target.y<getTop())
                return getTL();
            if(target.y>getBottom())
                return getBL();
            return new Vector2(getLeft(),target.y);
        }
        if(target.x>getRight())
        {
            if(target.y<getTop())
                return getTR();
            if(target.y>getBottom())
                return getBR();
            return new Vector2(getRight(),target.y);
        }
        if(target.y<getTop())
            return new Vector2(target.x,getTop());
        if(target.y>getBottom())
            return new Vector2(target.x,getBottom());
        return target.clone();
    }
    
    public Vector2 getSize()
    {
        return size;
    }
    
    public boolean isInTile(Point p)
    {
        return ((pos.x-p.x)<1)&&((p.x - pos.x)<size.x)&&((pos.y-p.y)<1)&&((p.y - pos.y)<size.y);
    }
    
    public boolean isIntersecting(BoundingBox bounds)
    {
        return 
                getLeft()>bounds.getRight()||
                bounds.getLeft()>getRight()||
                getTop()>bounds.getBottom()||
                bounds.getTop()>getBottom();
    }
    
    public boolean isPointInside(Point p)
    {
        return
                (p.x > getLeft() && p.x < getRight()) &&
                (p.y > getTop() && p.y < getBottom());
    }
    
    public float distanceTo(Vector2 pos){return Vector2.vecSubt(pos, getPointNearest(pos)).length();}
    public float getTop(){return pos.y;}
    public float getBottom(){return pos.y+size.y;}
    public float getLeft(){return pos.x;}
    public float getRight(){return pos.x+size.x;}
    public Vector2 getTL(){return pos;}
    public Vector2 getTR(){return new Vector2(getRight(),getTop());}
    public Vector2 getBL(){return new Vector2(getLeft(),getBottom());}
    public Vector2 getBR(){return new Vector2(getRight(),getBottom());}
    public Vector2 getCenter(){return Vector2.vecAdd(pos,Vector2.vecMult(.5f,size));}
}
