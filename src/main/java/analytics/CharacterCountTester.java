package analytics;

import scraping.Scraper;

import java.util.Hashtable;

public class CharacterCountTester {
    public static void main(String[] args){
        String test_url = "https://www.nytimes.com/2020/09/26/us/coronavirus-vermont-transplants.html";
        Scraper scraper = new Scraper(test_url);
        Analytics analytics = new Analytics(scraper.getArticle());
        System.out.println("Character Count: " + analytics.characterCount());
    }
}
