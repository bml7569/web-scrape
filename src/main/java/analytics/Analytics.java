package analytics;

import java.util.Hashtable;

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
    public Hashtable<String, Double> wordFrequency(){
        if (memoizedOperations.containsKey("word.frequency")) return (Hashtable<String, Double>) memoizedOperations.get("word.frequency");
        int total = this.totalWords();
        Hashtable<String, Integer> counts = this.countWords();
        Hashtable<String, Double> frequency = new Hashtable<String, Double>();
        for (String key : counts.keySet()){
            int count = counts.get(key);
            double freq = count / (total * 1.0);
            frequency.put(key, freq);
        }
        memoizedOperations.put("word.frequency", frequency);
        return frequency;
    }
}

