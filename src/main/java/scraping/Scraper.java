package scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

public class Scraper {
    private String url;
    private Document content;

    public Scraper(String url) {
        this.url = checkURL(url);
    }
    private String checkURL(String url){
        try{
            return new URL(url).toString();
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public boolean goodURL(){
        return url != null;
    }
    public String getArticle(){
        Elements articles = content.select("article");
        if (articles != null && articles.size() >= 1){
            Element article = articles.first();
            article.select("a").remove();
            Elements paragraphs = article.select("p").remove();
            return reconstruct(paragraphs);
        } else {
            return null;
        }

    }
    private String clean(String input){
        return input.replaceAll("[^a-zA-Z\\s]", "");
    }
    public String getRawHTML() {
        return content.html();
    }

    public String getAsText() {
        return content.wholeText();
    }

    public void read() throws IOException {
        this.content = Jsoup.connect(this.url).get();
    }
    private String reconstruct(Elements paragraphs){
        StringBuilder builder = new StringBuilder();
        for (Element paragraph : paragraphs){
            builder.append(clean(paragraph.text().toLowerCase()) + " ");
        }
        return builder.toString();

    }
}
