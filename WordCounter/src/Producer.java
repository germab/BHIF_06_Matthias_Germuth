
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
public class Producer extends Thread{

    private final Queue<Book> fileQueue;

    public Producer(Queue fileQueue) {
        this.fileQueue = fileQueue;
    }
    
    @Override
    public void run() {
        File folder = new File(".");
        Book b = null;
        for (File f : folder.listFiles()) {
            String text = "";
            String line;
            try(BufferedReader br = new BufferedReader(new FileReader(f))){
                while((line=br.readLine())!=null)
                    text+=line;
                b = new Book(f.getName(),text);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            synchronized(fileQueue){
                try {
                    fileQueue.put(b);
                    fileQueue.notifyAll();
                } catch (FullException ex) {
                    try {
                        fileQueue.wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }
    
}
