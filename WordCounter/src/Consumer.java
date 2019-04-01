
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
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
public class Consumer extends Thread{

    private final Queue<Book> fileQueue;

    public Consumer(Queue fileQueue) {
        this.fileQueue = fileQueue;
    }
    
    @Override
    public void run() {
        while(true){
            Book b = null;
            synchronized(fileQueue){
                try {
                    b = fileQueue.get();
                    fileQueue.notifyAll();
                } catch (EmptyException ex) {
                    try {
                        fileQueue.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
                    continue;
                }
            }
            HashMap<String,Integer> map = b.countWords();
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./Files/OutputFiles"+b.getFileName()+"-Output.txt")))){
                for (String key: map.keySet()) {
                    bw.write(key+": "+map.get(key));
                    bw.newLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
}
