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
public class FlexiArray implements ArrayInterface {
    protected int myarray[];
    protected int size;
    protected int index;
    protected int value;
    public FlexiArray(int size){
        this.size = size;
        myarray = new int [size];
    }

    @Override
    public void set(int index, int value) {
        myarray[index] = value;
       
    }

    
    @Override
    public int get(int index) {
        return myarray[index];
    }

    @Override
    public int length() {
        return size;
    }
    
}
