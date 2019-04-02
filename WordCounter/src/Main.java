
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthias
 */
public class Main {
    
    public static void main(String[] args) {
        Queue fileQueue = new Queue();
        
        Producer prod = new Producer(fileQueue);
        Consumer con1 = new Consumer(fileQueue);
        Consumer con2 = new Consumer(fileQueue);
        
        prod.start();
        con1.start();
        con2.start();
        
        try {
            prod.join();
            con1.join();
            con2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
