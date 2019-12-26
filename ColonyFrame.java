/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class ColonyFrame extends JFrame {
    Colony colony;
    protected int genNum = 0;
    int cellHeight;
    int cellWidth;
    public Colony getColony()
    {
        return colony;
    }
    public ColonyFrame(Colony colony){
        super();
        
        this.colony = colony;
        
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }
    });
        setTitle(colony.getName());
        setSize(400, 400);
        setVisible(true);
    }
    private void setupFrame(Graphics gr)
    {
        //pack();
        
        int sWidth  = (int)this.getContentPane().getSize().getWidth()  
                - (getInsets().left+getInsets().right);
        int sHeight = (int)this.getContentPane().getSize().getHeight() 
                - (getInsets().top+getInsets().bottom);
        
        int box = Math.min(sWidth,sHeight);
        
        gr.clearRect(0,0, (int)this.getContentPane().getSize().getWidth(), 
                (int)this.getContentPane().getSize().getHeight());
        cellHeight=box/colony.getColonySize();
        cellWidth =box/colony.getColonySize();
        
    }
    public int getGenNum(){
        return genNum;
    }
    public void evolveFrame(){
        colony.evolve();
        genNum++;
        
        
    }
    
    @Override
    public void paint(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;
        String col;
        Color color;
        setupFrame(g);
       for (int y = 0; y < colony.getColonySize(); y++) {
            for (int x = 0; x < colony.getColonySize(); x++) {
                if (colony.isCellAlive(x, y)) {
                    color = Color.GREEN;
                    col = "green";
                } else {
                    color = Color.GRAY;
                    col = "gray";
                }
                g.setColor(Color.BLACK);
                g.drawRect(y * cellWidth+10, x * cellHeight+cellHeight, 
                        cellWidth,  cellHeight);
                g.setColor(color);
                g.fillRect(y * cellWidth+10, x * cellHeight+cellHeight, 
                        cellWidth,  cellHeight);
                
            }
        }
    }
}
    

    
