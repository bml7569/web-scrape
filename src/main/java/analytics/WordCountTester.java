package analytics;

import scraping.Scraper;

import java.util.Hashtable;

public class WordCountTester {
    public static void main(String[] args){
        String test_url = "https://www.nytimes.com/2020/09/26/us/coronavirus-vermont-transplants.html";
        Scraper scraper = new Scraper(test_url);
        Analytics analytics = new Analytics(scraper.getArticle());
        System.out.println("Word Frequencies: ");
        Hashtable<String, Integer> table = analytics.countWords();
        for (String key : table.keySet()){
            System.out.println("Word: " + key + ", Count: " + table.get(key));
        }
    }
}
