/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

import java.util.*;
import java.awt.*;

/**
 *
 * @author varun
 */
public class parallelEvolveTask extends TimerTask {
    private final ColonyFrame frame;
    private int delay = 0;
    private Timer timer;
    private int NumberRuns = 0;
    private boolean isSilent = false;
    public parallelEvolveTask(ColonyFrame cf){
        frame = cf;
    }
    public void setSilent(boolean silent)
    {
        isSilent=silent;
    }
    public void setDelay(int delay){
        this.delay = delay;
    }
    
    public int getDelay(){
        return delay;
    }
    @Override
    public void run(){
        frame.evolveFrame();
        if(!isSilent){
          frame.repaint();
        }
        if(frame.getGenNum() == NumberRuns){
            this.cancel();
            frame.repaint();
        }
        //System.out.println("run= " + frame.getGenNum());
    }
     
    public void moveIt(int NumberRuns, int d){
        this.NumberRuns = NumberRuns;
        this.delay = d;
        this.timer = new Timer();
        if (d < 1){
            this.delay = 1;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(this, delay, delay);
        //if(!isSilent){
        //   frame.repaint();
        //}
    }
}
