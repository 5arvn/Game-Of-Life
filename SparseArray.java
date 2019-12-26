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
public class SparseArray implements ArrayInterface {
    protected HashMap<Integer, Integer> notarray;
    protected int values[];
    
    public SparseArray (int size){
       values = new int[size];
       notarray = new HashMap<>(values.length);    
    }
    @Override
     public void set(int index, int value){
         notarray.put(index, value);
     }
     
     @Override
    public int get(int index){
        if(notarray.containsKey(index)){
            return notarray.get(index);
        } else{
            return 0;
        }
            
    }
     @Override
    public int length(){
        return values.length;
    }
    public int numberElements(){
        return notarray.size();
    }
}
