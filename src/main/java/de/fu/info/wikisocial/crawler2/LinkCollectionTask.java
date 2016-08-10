package de.fu.info.wikisocial.crawler2;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Logger;

/**
 * Created by totucuong on 3/16/16.
 * The following source code was adapted using code from PS-KB: de.fu.agdb.crawler2
 */
public class LinkCollectionTask extends RecursiveTask<ArrayList<URL>> {
    private final static int timeOut = 4000;

    protected final static Logger LOG = Logger.getLogger(LinkCollectionTask.class.getName());

    private final Element element;

    public LinkCollectionTask(Element element) {
        this.element = element;
    }

    @Override
    protected ArrayList<URL> compute() {
        ArrayList<URL> urls = new ArrayList<>();
        Elements links = element.select("a[href]");
        for (Element l : links) {
            try {
                urls.add(new URL(l.attr("abs:href")));
            } catch (MalformedURLException e){
                // do nothing
            }
        }
        return urls;
    }
}
