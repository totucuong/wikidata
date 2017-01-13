package de.fu.info.wikisocial.wikidata.model;

import java.util.Date;

/**
 * Created by totucuong-standard on 1/13/17.
 * This class model a temporal edge, i.e., an edge with an associated timestamp
 */
public class TemporalEdge implements Comparable<TemporalEdge> {
    private String src;
    private String tgt;
    private Date timestamp;


    public TemporalEdge(String src, String tgt, Date timestamp) {
        this.src = src;
        this.tgt = tgt;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(TemporalEdge other) {
        if (this.timestamp.equals(other.timestamp))
            return 0;
        else
            return this.timestamp.before(other.timestamp) ? -1 : 1;
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

    public Date getTimestamp() {
        return timestamp;
    }
}
