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
public class GrowingArray extends FlexiArray {
   protected int[] newarray;

    
    public GrowingArray(int size) {
       super(size);
       this.size = size;
        myarray = new int [size];
       
    }
    
    @Override
    public void set(int index, int value){
      
        if(index < myarray.length){
            super.set(index, value);
        } else {
            newarray = new int[ (int) Math.round((double)index * 1.2)];
            copy(newarray); 
            newarray[index] = value;
        }
      
          
      }
    private void copy(int[] newarray){
       System.arraycopy(myarray, 0, newarray, 0, myarray.length);
        myarray = newarray;
    }
    
    @Override
    public int get(int index){
        if(index < myarray.length){
        return super.get(index);
        }
        return newarray[index];
    }
    
     @Override
    public int length() {
        return newarray.length;
    }
}
