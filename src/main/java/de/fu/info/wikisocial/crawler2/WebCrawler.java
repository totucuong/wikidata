package de.fu.info.wikisocial.crawler2;

import java.io.IOException;

/**
 * Created by totucuong on 3/15/16.
 * Copied from PS-KB: de.fu.agdb.crawler2
 */
public interface WebCrawler {
    /**
     * start crawling
     */
    void start();

    /**
     * end crawling
     */
    void end();

    /**
     * Save crawled result to <tt>pathfile</tt>  file.
     * @param pathfile path to <tt>pathfile</tt> file.
     */
    void saveToFile(String pathfile) throws IOException;
}
