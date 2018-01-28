package com.lwerl.javaee.servlet.listener;

import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.cache.NewsHolder;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by lWeRl on 25.01.2018.
 */

@WebListener
public class OnStartServletContextListener implements ServletContextListener {

    @Inject
    private CurrencyRatesHolder rateInfo;
    @Inject
    private NewsHolder newsHolder;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler.scheduleAtFixedRate(() -> rateInfo.updateRates(), 0, 5, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(() -> newsHolder.updateNews(), 0, 5, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdown();
    }
}
