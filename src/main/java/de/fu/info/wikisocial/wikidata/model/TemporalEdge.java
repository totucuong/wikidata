package de.fu.info.wikisocial.wikidata.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by totucuong-standard on 1/13/17.
 * This class model a temporal edge, i.e., an edge with an associated timestamp
 */
public class TemporalEdge implements Comparable<TemporalEdge> {
    private String src;
    private String tgt;
    private Collection<String> items;
    private Collection<String> props;
    private String context;


    public TemporalEdge(String src, String tgt, LocalDate timestamp) {
        this.src = src;
        this.tgt = tgt;
        this.timestamp = timestamp;
        this.context = "";
        this.items = new ArrayList<>();
        this.props = new ArrayList<>();
    }

    @Override
    public int compareTo(TemporalEdge other) {
        if (this.timestamp.equals(other.timestamp))
            return 0;
        else
            return this.timestamp.isBefore(other.timestamp) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "TemporalEdge{" +
                "src='" + src + '\'' +
                ", tgt='" + tgt + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getSrc() {
        return src;
    }

    public String getTgt() {
        return tgt;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }


    private LocalDate timestamp;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setItems(Collection<String> items) {
        this.items = items;
    }

    public void setProps(Collection<String> props) {
        this.props = props;
    }

    public Collection<String> getItems() {
        return items;
    }

    public Collection<String> getProps() {
        return props;
    }
}
