package com.lwerl.javaee.cache;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lWeRl on 28.01.2018.
 */
@Singleton
public class NewsHolder {

    private static final String SERVICE_URL = "https://www.rbc.ru/";
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock read = lock.readLock();
    private Lock write = lock.readLock();
    // Не совсем уверен как сделать чтобы содержание листа было всегда актуально для многопоточки?
    private volatile List<News> newsList = new ArrayList<>();

    public void updateNews() {
        try {
            Document serviceDocument = Jsoup.connect(SERVICE_URL).get();
            Element newsWrapper = serviceDocument.selectFirst("div.main__row");
            Element mainNews = newsWrapper.selectFirst(".main__col-main__inner a");
            Elements otherNews = newsWrapper.select(".main-feed .main-feed__item a");
            write.lock();
            newsList.clear();
            newsList.add(new News(mainNews.selectFirst("span span").text(), mainNews.attr("href")));
            otherNews.forEach(newsElement -> newsList.add(new News(newsElement.selectFirst("span span").text(), newsElement.attr("href"))));
            write.unlock();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getHtmlView() {
        StringBuilder stringBuilder = new StringBuilder("<div class=\"news-wrapper\">");

        read.lock();
        newsList.forEach(news -> {
            stringBuilder.append("<div class=\"news\"><a target=\"_blank\" href=\"").append(news.getHref()).append("\">").append(news.getArticle()).append("</a></div>");
        });
        read.unlock();
        stringBuilder.append("</div>");
        return stringBuilder.toString();
    }

    public static class News {
        private String article;
        private String href;

        News(String article, String href) {
            this.article = article;
            this.href = href;
        }

        String getArticle() {
            return article;
        }

        String getHref() {
            return href;
        }
    }
}
