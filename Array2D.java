/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

import java.util.HashMap;

/**
 *
 * @author varun
 */
public class Array2D  {
    protected ArrayInterface ai;
    protected int numberRows;
    protected int numberCols;
    protected boolean isSparse;
    protected HashMap<Integer, Integer> sparse;
    public Array2D(int numberRows, int numberCols, boolean isSparse){
        this.numberRows = numberRows;
        this.numberCols = numberCols;
        this.isSparse = isSparse;
        
        int size = numberRows * numberCols;
        if(isSparse){
            ai = new SparseArray(size);
        } else {
            ai = new FlexiArray(size);
        }
                
    }
    private int index(int row, int col){
       if(isSparse){
           return (row - 1) * numberCols + col;
       } else {
        return   (row) * numberCols + col;
       }
    }
    
    public void set(int r, int c, int value){
        if(r < numberRows && c < numberCols){
            ai.set(index(r, c), value);
            
        } else {
           System.out.println("Array2D index out of range r: "+ r +" col: "+ c);
           System.out.println("MaxRows: " + numberRows + " MaxCols: " + numberCols);
           System.exit(0);
        }
    }
    public int get(int r, int c){
       if(r < numberRows && c < numberCols){
        return ai.get(index(r, c));
       } else{
           return 0;
       }
    }
    public int numberRows(){
        return numberRows;
    }
    public int numberCols(){
        return numberCols;
    }
}
