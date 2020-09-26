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

    public Hashtable<String, Integer> countWords(){
        Hashtable<String, Integer> h = new Hashtable<String, Integer>();
        for(String w: this.words){
            if (h.containsKey(w)){
                int i = h.get(w) +1;
                h.put(w, i);
            }else{
                h.put(w, 1);
            }
        }
        return h;
    }
}

