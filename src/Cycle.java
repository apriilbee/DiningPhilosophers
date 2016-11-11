
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author testuser
 */
public class Cycle {
    static Philosopher[] ph;
    static Fork[] forks;
    static int limit = 0;
    
    public static void main(String[] args) throws InterruptedException {
        int total, hungry;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Total Philosophers: ");
        total = sc.nextInt();
        System.out.print("Hungry Philosophers: ");
        hungry = sc.nextInt();
        
        //initialize forks and philosophers
        ph = new Philosopher[total];
        forks = new Fork[total];
        
        for(int i=0; i<total; i++){
            Fork tmp = new Fork(i);
            forks[i] = tmp;
        }
        for(int i=0; i<total; i++){
            Philosopher p = new Philosopher(i, forks[i],  (i-1 >= 0) ? forks[i-1] : forks[total-1]);
            ph[i] = p;
        }
        //----------------
        
        //initialize Hungry philosophers
        for(int i=0; i<hungry; i++){
            System.out.print("Position for hungry philosopher " + (i+1) + ": ");
            ph[sc.nextInt()].setHungry();
        }
        //
        sc.nextLine();
        System.out.print("A. One at a time\nB. Two at a time\nEnter letter: ");
        String ch = sc.nextLine().toUpperCase();
        if (ch.equals("A")) 
            limit = 1;
        else 
            limit = 2;
       
        simulate(); 
            
    }


    static int eating = 0;
    private static void simulate() throws InterruptedException {
        while(hasHungry()){
            for(int i=0; i<ph.length; i++){
                if(eating==limit && ph[i].hungry){
                    System.out.println("Philosopher " + ph[i].id + " is waiting.");
                }
                else{
                    if(ph[i].hungry){
                        if(ph[i].left.isUsed || ph[i].right.isUsed){
                            System.out.println("Philosopher " + ph[i].id + " is waiting.");
                        }
                        else{
                            ph[i].eat();
                            if(ph[i].eating)
                                eating++;
                        }
                    }
                    else{
                        ph[i].think();
                    }
                }
                if(i == ph.length - 1){
                    for(int j=0; j<ph.length; j++){
                        if(ph[j].eating){
                            ph[j].done();
                            eating--;
                        }
                    }
                }
            }
            System.out.println("--------------");
        }
    }

    private static boolean hasHungry() {
        for(int i=0; i<ph.length; i++){
            if(ph[i].hungry == true)
                return true;
        }
        return false;
    }
    
    private static void print() {
        for(int i=0; i<ph.length; i++){
            System.out.println("Philosopher " + ph[i].id + " Left: " + ph[i].left.id + " Right: " + ph[i].right.id);
            System.out.println("Philosopher " + ph[i].id + " Status: " + ph[i].hungry);
        }
    }
}
