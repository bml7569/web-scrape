package analytics;

import java.util.*;


public class Analytics {
    private String[] words;
    private HashMap<String, Object> memoizedOperations;
    public Analytics(String t){
        memoizedOperations = new HashMap<String, Object>();
        this.words = t.split(" ");
    }

    public int totalWords(){
        return this.words.length;
    }
    public int characterCount(){
        int total = 0;
        for (String word : words){
            total += word.length();
        }
        total += totalWords();
        return total;
    }

    public HashMap<String, Double> countWords(){
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
}

