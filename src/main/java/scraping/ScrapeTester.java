package scraping;

public class ScrapeTester {
    public static void main(String[] args){
        Scraper scraper = new Scraper("https://www.nytimes.com/2020/09/26/us/politics/biden-supreme-court-confirmation.html");
        scraper.getArticle();
    }
}
