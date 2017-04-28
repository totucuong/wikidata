package de.fu.info.wikisocial.wikidata.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by totucuong-standard on 1/13/17.
 * This class model a temporal edge, i.e., an edge with an associated timestamp
 */
public class TemporalEdge implements Comparable<TemporalEdge> {
    private String src;
    private String tgt;
    private int itemCount;
    private int propCount;
    private String context;


    public TemporalEdge(String src, String tgt, LocalDate timestamp) {
        this.src = src;
        this.tgt = tgt;
        this.timestamp = timestamp;
        this.itemCount = 0;
        this.propCount = 0;
        this.context = "";
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

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public void setPropCount(int propCount) {
        this.propCount = propCount;
    }

    private LocalDate timestamp;

    public int getItemCount() {
        return itemCount;
    }

    public int getPropCount() {
        return propCount;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
