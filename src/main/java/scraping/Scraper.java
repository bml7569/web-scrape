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
        try {
            this.url = url;
            this.content = read();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getArticle(){
        Element article = content.select("article").first();
        Elements ele = article.select("p").remove();
        for (Element e : ele){
            System.out.println(e.text());
        }
        System.out.println(clean(article.text()));
        return null;
    }
    private String clean(String input){
        return input.replaceAll("[^a-zA-Z0-9]", "");
    }
    public String getRawHTML() {
        return content.html();
    }

    public String getAsText() {
        return content.wholeText();
    }

    private Document read() throws IOException {
        return Jsoup.connect(this.url).get();
    }
}
