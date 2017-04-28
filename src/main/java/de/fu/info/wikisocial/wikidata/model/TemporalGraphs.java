package de.fu.info.wikisocial.wikidata.model;

import de.fu.info.wikisocial.graph.Graph;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

/**
 * Created by totucuong-standard on 1/13/17.
 * This is a collection of weekly graphs. Typical methods is
 * get_graph(int week_index)
 * number_of_weeks()
 */
public class TemporalGraphs {

    private ArrayList<TemporalEdge> edges;

    private ArrayList<ArrayList<TemporalEdge>> weekly_graphs;

    private String filePath;

    public TemporalGraphs(String filePath) {
        this.filePath = filePath;
    }

    /**
     * read in graphs
     */
    public void read() {
        weekly_graphs = new ArrayList<>();
        edges = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            String cur;
            while  ((cur = in.readLine()) != null) {
                String[] piece = cur.split(";");
                TemporalEdge e = new TemporalEdge(piece[0], piece[1], LocalDate.parse(piece[2], DateTimeFormatter.ISO_DATE));
                e.setItemCount(Integer.parseInt(piece[3]));
                e.setPropCount(Integer.parseInt(piece[4]));
                e.setContext(piece[5]);
                edges.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * split big graph into weekly graphs
     * @return weekly graphs
     */
    public void split_weekly() {
        ArrayList<TemporalEdge> cur_week_edges = new ArrayList<>();
        LocalDate next = edges.get(0).getTimestamp().plusWeeks(1);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getTimestamp().isAfter(next)) {
                // split current week graph off
                this.weekly_graphs.add(cur_week_edges);
                // move on to next week
                next = next.plusWeeks(1);
                // create new weekly graph
                cur_week_edges = new ArrayList<>();
                cur_week_edges.add(edges.get(i));
            } else
                cur_week_edges.add(edges.get(i));
        }
    }

    /**
     *
     * This method saves the graph on weekly basis.
     *
     */
    public void save() {
        for (int i = 0; i < weekly_graphs.size(); i++) {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "_" + i))) {
                weekly_graphs.get(i).forEach(e -> {
                    try {
                        out.write(e.getSrc());
                        out.write(";");
                        out.write(e.getTgt());
                        out.write(";");
                        out.write(e.getTimestamp().format(DateTimeFormatter.ISO_DATE));
                        out.write(";");
                        out.write(Integer.toString(e.getItemCount()));
                        out.write(";");
                        out.write(Integer.toString(e.getPropCount()));
                        out.write(";");
                        out.write(e.getContext());
                        out.write("\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
            } catch (IOException ioe) {
                System.err.println("Something wrong happened while writing to disc");
                ioe.printStackTrace();
            }
        }
    }
}
