package de.fu.info.wikisocial.crawler2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by totucuong on 3/15/16.
 */
public abstract class WebCrawlerImpl implements WebCrawler {

    protected final static Logger LOG = Logger.getLogger(WebCrawler.class.getName());

    private long startTime;

    public void start() {
        LOG.log(Level.INFO, "start crawling ...");
        startTime = System.currentTimeMillis();
    }

    public void end() {
        LOG.log(Level.INFO, "finish crawling after " + (System.currentTimeMillis() - startTime)/1000 + " seconds");
    }

    public abstract void saveToFile(String pathfile) throws IOException;
}
