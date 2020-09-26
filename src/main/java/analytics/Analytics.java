package analytics;

import java.util.Hashtable;

public class Analytics {
    private String[] words;

    public Analytics(String t){
        this.words = t.split(" ");
    }

    public int totalWords(){
        return this.words.length;
    }

    public Hashtable countWords(){
        Hashtable h = new Hashtable();
        for(String w: this.words){
            if w
        }
    }
}

