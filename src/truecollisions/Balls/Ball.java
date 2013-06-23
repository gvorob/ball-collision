/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Balls;

import truecollisions.Util.Misc;
import truecollisions.Util.Vector2;

/**
 *
 * @author George
 */
public class Ball {
    public Vector2 pos;
    public float radius;
    public Vector2 vel;
    public float mass, compressibility, friction;
    public Vector2 netForce;
    
    public Ball(Vector2 Pos, float Radius, Vector2 Vel, float Mass, float Comp, float Friction)
    {
        radius = Radius;
        pos = Pos; vel = Vel; mass = Mass;
        compressibility = Comp; friction = Friction;
        netForce = Vector2.Zero();
    }
    
    public void getForce(Vector2 f)
    {
        netForce.add(f);
    }
    
    public void step(float time)
    {
        netForce.vecMult(1 / mass * time);
        vel.add(netForce);
        pos.add(Vector2.vecMult(time, vel));
        netForce = Vector2.Zero();
    }
}
