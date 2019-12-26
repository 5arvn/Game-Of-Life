/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

/**
 *
 * @author varun
 */
public class Colony {

    protected Array2D current;
    protected Array2D nextprime;
    protected int size;
    protected String name;
    protected int GN;
    private boolean isSilent;

    public Colony(int size, String name) {
        current = new Array2D(size + 2, size + 2, true);
        nextprime = new Array2D(size + 2, size + 2, true);
        this.size = size;
        this.name = name;
        isSilent = false;
        GN = 0;
    }


    public int getColonySize() {
        return size;
    }

    public int GenerationNumber() {
        return GN;
    }

    public String getName() {
        return name;
    }

    public void resetColony() {
        current = new Array2D(size + 2, size + 2, true);
        GN = 0;
    }

    public void setCellAlive(int row, int col) {
            current.set(row + 1, col+1, 1);
        
    }


    public void setCellDead(int row, int col) {
            current.set(row+1, col+1 , 0);
    }


    public final boolean isCellAlive(int row, int col) {
        if (current.get(row +1, col+1) == 1) {
            return true;
        } else {
            return false;
        }
    }
    public int numberAlive(){
        int alivecount = 0;
        for(int r = 0; r< size + 2; r++){
           for(int c =0; c<size + 2; c++){
               if(isCellAlive(r, c)){
                   alivecount++;
                }
               
            }
        }
        return alivecount;
    }
    
   private Array2D layover(Array2D a, Array2D d){
       for(int r = 0; r< size + 2; r++){
           for(int c =0; c<size + 2; c++){
               int s = d.get(r, c);
              a.set(r, c, s);
           }
       }
       return a;
   }

    public void evolve() {
        layover(nextprime, current);
        for (int r = 1; r < size + 1; r++) {
            for (int c = 1; c <size + 1; c++) {
                setThem(current, nextprime, r, c);
            }
        }
        layover(current, nextprime);
        
        GN++;
    }
    private void setThem(Array2D a, Array2D b, int row, int col) {
        int alivecount = Alivecounting (a, row, col);
        if (a.get(row, col) == 1) {
            if (alivecount > 3) {
                b.set(row, col, 0);
                
            } else if (alivecount < 2) {
                b.set(row, col, 0);
                
            }
        } else if (a.get(row, col) == 0) {
            if (alivecount == 3) {
                b.set(row, col, 1);
                
            }
        }

    }
    
    private int Alivecounting(Array2D a, int row, int col){
        int above = row - 1;
        int below = row + 1;
        int left = col - 1;
        int right = col + 1;
        return  a.get(above, left) + 
                a.get(above, col) +
                a.get(above, right) +
                a.get(row, left) +
                a.get(row, right) +
                a.get(below, left) + 
                a.get(below, col) + 
                a.get(below, right);   
    }
    
    public boolean setSilent(){
    isSilent = !isSilent;
    return true;
    }


    @Override
    public String toString() {
        String s = "";
        s += "G" + GenerationNumber() + "\n";
        for (int r = 0; r < size; r++) {
            int row = r;
            for (int c = 0; c < size; c++) {
                int col = c;
                if (col != size - 1) {
                    if (isCellAlive(row, col)) {
                        s += " * ";
                    } else {
                        s += " _ ";
                    }
                } else if (isCellAlive(row, col)) {
                    s += " * " + "\n";
                } else {
                    s += " _ " + "\n";
                }
            }
        }

        return s;

    }
}
