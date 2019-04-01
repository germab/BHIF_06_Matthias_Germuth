
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matthias
 */
public class Book {
    private String fileName;
    private String text;
    private HashMap<String,Integer> wordCount = new HashMap();

    public Book(String fileName, String text) {
        this.fileName = fileName;
        this.text = text;
    }
    
    public HashMap<String,Integer> countWords(){
        text.replaceAll("[^a-zA-Z0-9 ]", "");
        String[] parts = text.split(" ");
        for (String part : parts) {
            if(!wordCount.containsKey(part)){
                wordCount.put(part,1);
                continue;
            }
            wordCount.put(part,wordCount.get(part)+1);
        }
        return wordCount;
    }

    public String getFileName() {
        return fileName;
    }

    public String getText() {
        return text;
    }
    
}
