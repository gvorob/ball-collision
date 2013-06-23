/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions;

import truecollisions.Balls.BallHandler;
import truecollisions.Util.ImageLoader;
import truecollisions.Util.Keyboard;
import truecollisions.Util.Misc;
import truecollisions.Util.Mouse;
import truecollisions.Util.Timer;
import truecollisions.Util.Screen;
import truecollisions.Util.TimerListener;

/**
 *
 * @author George
 */
public class TrueCollisions implements TimerListener {
    public static TrueCollisions tc;
    public static void main(String[] args) {
        tc = new TrueCollisions();
    }
    
    public Screen screen;
    public Timer timer;
    public Keyboard keyboard;
    public Mouse mouse;
    public ImageLoader imageLoad;
    public Toolbelt tools;
    public BallHandler caddy;
    
    public TrueCollisions()
    {
        screen = new Screen(800, 600);
        caddy = new BallHandler(0,0,800, 600 - 64);
        timer = new Timer(10);
        keyboard = new Keyboard();
        mouse = new Mouse();
        imageLoad = new ImageLoader();
        tools = new Toolbelt(imageLoad, mouse, keyboard, caddy, 800, 600);
        screen.c.addMouseListener(mouse);
        screen.c.addMouseMotionListener(mouse);
        screen.c.addKeyListener(keyboard);
        timer.addListener(this);
        timer.start();
    }
    
    public void timerEvent()
    {
        for(int i = 1; i <= 1000; i++){
        caddy.step(timer.tickTime() / 1000);}
        screen.clear();
        caddy.draw(screen.buffer);
        tools.draw(screen.buffer);
        screen.flushBuffer();
    }
}
