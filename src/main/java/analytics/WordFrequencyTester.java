package analytics;

import scraping.Scraper;

import java.util.Hashtable;

public class WordFrequencyTester {
    public static void main(String[] args){
        String test_url = "https://www.nytimes.com/2020/09/26/us/coronavirus-vermont-transplants.html";
        Scraper scraper = new Scraper(test_url);
        Analytics analytics = new Analytics(scraper.getArticle());
        System.out.println("Total Words: " + analytics.totalWords());
        System.out.println("Word Frequencies: ");
        Hashtable<String, Double> table = analytics.wordFrequency();
        for (String key : table.keySet()){
            System.out.println("Word: " + key + ", Frequency: " + table.get(key));
        }
    }
}
