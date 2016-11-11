/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author testuser
 */
public class Fork {
    int id;
    Philosopher usedBy;
    boolean isUsed = false;
    
    Fork(int id){
        this.id = id;
    }
    
    public void pickup(Philosopher p){
        usedBy = p;
        isUsed = true;
    }
    
    public void release(){
        usedBy = null;
        isUsed = false;
    }
}
