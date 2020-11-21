/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.oktpp.tuskoktpp.entity;

/**
 *
 * @author danil
 */
public class TapeTaskCount {
    Typetask tapetask;
    int count;
    
    public TapeTaskCount(Typetask tapetask, int count){
        this.tapetask =tapetask;
        this.count=count;
    }
    
    public Typetask getTapetask() {
        return tapetask;
    }

    public void setTapetask(Typetask tapetask) {
        this.tapetask = tapetask;
    }
        
    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
