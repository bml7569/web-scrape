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

    public HashMap<String, Integer> countWords(){
        if (memoizedOperations.containsKey("count.words")) return (HashMap<String, Integer>) memoizedOperations.get("count.words");
        HashMap<String, Integer> h = new HashMap<String, Integer>();
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

    public HashMap<String, Double> wordFrequency(){
        if (memoizedOperations.containsKey("word.frequency")) return (HashMap<String, Double>) memoizedOperations.get("word.frequency");
        int total = this.totalWords();
        HashMap<String, Integer> counts = this.countWords();
        HashMap<String, Double> frequency = new HashMap<String, Double>();
        for (String key : counts.keySet()){
            int count = counts.get(key);
            double freq = count / (total * 1.0);
            frequency.put(key, freq);
        }
        memoizedOperations.put("word.frequency", frequency);
        return frequency;
    }

    public static HashMap<String, Integer> sortWords(HashMap<String, Integer> hm){

        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
