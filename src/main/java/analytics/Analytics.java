package analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class Analytics {
    private String[] words;
    private Hashtable<String, Object> memoizedOperations;
    public Analytics(String t){
        memoizedOperations = new Hashtable<String, Object>();
        this.words = t.split(" ");
    }

    public int totalWords(){
        return this.words.length;
    }

    public Hashtable<String, Integer> countWords(){
        if (memoizedOperations.containsKey("count.words")) return (Hashtable<String, Integer>) memoizedOperations.get("count.words");
        Hashtable<String, Integer> h = new Hashtable<String, Integer>();
        for(String w: this.words){
            if (h.containsKey(w)){
                int i = h.get(w) +1;
                h.put(w, i);
            }else{
                h.put(w, 1);
            }
        }
        memoizedOperations.put("count.words", h);
        return h;
    }

    //true for descending, false for ascending
    public Hashtable<String, Integer> sort(Hashtable<String, Integer> h, boolean order){
        List<Integer> list = new ArrayList<Integer>(h.values());
        if(order){
            Collections.sort(list, Collections.reverseOrder());
        }else{
            Collections.sort(list);
        }
    }



    public String mostCommon(){
        Hashtable<String, Integer> h = countWords();

    }

}

