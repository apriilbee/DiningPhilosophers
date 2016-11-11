
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author testuser
 */
public class Philosopher {
    int id;
    boolean hungry = false;
    boolean eating = false;
    Fork right, left;
    
    Philosopher(int id, Fork left, Fork right){
        this.id = id;
        this.left = left;
        this.right = right;
    }
    
    public void setHungry(){
        hungry = true;
    }
    
    public void start() throws InterruptedException{
        if(hungry)
            eat();
        else
            think();
    }
    
    public boolean requestFork(Fork f){
        if(f.isUsed){
           
            return false;
        }
        f.pickup(this);
        return true;
    }
    
    public void releaseFork(Fork f){
        f.release();
    }
   
    public void eat() {
        boolean request_left = requestFork(left);
        boolean request_right = requestFork(right);
        
//        if(!request_left)
//            System.out.println("\tLeft fork is used by " + left.usedBy.id );
//        
//        if(!request_right)
//            System.out.println("\tRight fork is used by " + right.usedBy.id );
        
        if(request_right && request_left){
            eating = true;
            System.out.println("Philosopher " + id + " is now eating.");
        }
    }
    
    public void done(){
        releaseFork(left);
        releaseFork(right);
        System.out.println("Philosopher " + id + " is done eating.");
        eating = false;
        hungry = false;
    }
    
    public void think(){
        
    }
}
