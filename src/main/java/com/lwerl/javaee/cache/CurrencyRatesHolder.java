package com.lwerl.javaee.cache;


import javax.inject.Singleton;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;


@Singleton
public class CurrencyRatesHolder {


    private static final String SERVICE_URL = "http://www.cbr.ru/scripts/XML_daily.asp";
    private volatile Date updatedDate;
    private volatile String raw;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock read = lock.readLock();
    private Lock write = lock.readLock();

    public Date getUpdatedDate() {
        return updatedDate;
    }

    private void setRaw(String raw) {
        this.raw = raw;
    }

    public void updateRates() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(SERVICE_URL).openStream()))) {
            write.lock();
            raw = br.lines().collect(Collectors.joining("\n"));
            updatedDate = new Date();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            write.unlock();
        }
    }

    public String getHtmlView() {
        StringWriter result = new StringWriter();
        try {
            read.lock();
            StreamSource xslSource = new StreamSource(getClass().getClassLoader().getResourceAsStream("/xslt/currency.xsl"));
            StreamSource source = new StreamSource(new StringReader(raw));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(xslSource);
            transformer.transform(source, new StreamResult(result));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            read.unlock();
        }
        return result.toString();
    }
}
