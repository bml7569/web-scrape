package analytics;

import java.util.*;


public class Analytics {
    private String[] words;
    private HashMap<String, Object> memoizedOperations;
    public Analytics(String t){
        memoizedOperations = new HashMap<String, Object>();
        this.words = (t != null) ? t.split(" ") : null;
    }
    public int totalWords(){
        if (sourceIsNull()) return -1;
        return this.words.length;
    }
    public int characterCount(){
        if (sourceIsNull()) return -1;
        int total = 0;
        for (String word : words){
            total += word.length();
        }
        total += totalWords();
        return total;
    }

    public HashMap<String, Double> countWords(){
        if (sourceIsNull()) return null;
        if (memoizedOperations.containsKey("count.words")) return (HashMap<String, Double>) memoizedOperations.get("count.words");
        HashMap<String, Double> h = new HashMap<String, Double>();
        for(String w: this.words){
            if (h.containsKey(w)){
                double i = h.get(w) +1;
                h.put(w, i);
            }else{
                h.put(w, 1.0);
            }
        }
        memoizedOperations.put("count.words", h);
        return h;
    }

    public HashMap<String, Double> wordFrequency(){
        if (sourceIsNull()) return null;
        if (memoizedOperations.containsKey("word.frequency")) return (HashMap<String, Double>) memoizedOperations.get("word.frequency");
        int total = this.totalWords();
        HashMap<String, Double> counts = this.countWords();
        HashMap<String, Double> frequency = new HashMap<String, Double>();
        for (String key : counts.keySet()){
            double count = counts.get(key);
            double freq = count / total;
            frequency.put(key, freq);
        }
        memoizedOperations.put("word.frequency", frequency);
        return sort(frequency, true);
    }
    private boolean sourceIsNull(){
        return this.words == null;
    }
    public static HashMap<String, Double> sort(HashMap<String, Double> hm, boolean ascending){
        List<Map.Entry<String, Double> > list = new LinkedList<>(hm.entrySet());
        list.sort(Map.Entry.comparingByValue());
        if (ascending) Collections.reverse(list);
        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public String mostCommon(){
        HashMap<String, Double> counts = this.countWords();
        HashMap<String, Double> sorted = sort(counts, false);
        Map.Entry<String,Double> entry = sorted.entrySet().iterator().next();
        return entry.getKey();
    }
}

