/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package truecollisions.Util;

import java.util.ArrayList;
import java.util.ListIterator;


/**
 *
 * @author George
 */
public class Timer 
{
    
    int interval;
    ArrayList<TimerListener> listeners;
    long lastTime;
    boolean running;
    
    public Timer(int interv)
    {
        interval = interv;        
        listeners = new ArrayList<TimerListener>();
        running = false;
    }
    
    public void start()
    {
        running = true;
        lastTime=System.currentTimeMillis();
        while (running)
        {
            if(System.currentTimeMillis()-lastTime > interval)
            {
                update();
                lastTime+=interval;
            }
        }
    }
    
    public void stop()
    {
        running = false;
    }
    
    public void addListener(TimerListener tl){listeners.add(tl);}
    
    private void update()
    {
         ListIterator<TimerListener> i = listeners.listIterator();
         while(i.hasNext())
         {
            i.next().timerEvent();
        }
    }

    public float tickTime() {
        return (float)interval / 1000;
    }
    
}
