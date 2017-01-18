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
        weekly_graphs = new ArrayList<>();
        edges = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            String cur;
            while  ((cur = in.readLine()) != null) {
                String[] piece = cur.split(",");
                edges.add(new TemporalEdge(piece[0], piece[1], LocalDate.parse(piece[2], DateTimeFormatter.ofPattern("d[d] MMMM yyyy"))));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ArrayList<TemporalEdge>> split_weekly() {
        ArrayList<TemporalEdge> cur_week_edges = new ArrayList<>();
        LocalDate next = edges.get(0).getTimestamp().plusWeeks(1);
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).getTimestamp().isEqual(next)) {
                weekly_graphs.add(cur_week_edges);
                cur_week_edges = new ArrayList<>();
                next = next.plusWeeks(1);
            } else
                cur_week_edges.add(edges.get(i));
        }
        return weekly_graphs;
    }

    /**
     *
     * This method saves the graph on weekly basis.
     *
     */
    public void save() {
        int count = 0;
        weekly_graphs.forEach(g -> {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(filePath + "_" + count))) {
                g.forEach(e -> {
                    try {
                        out.write(e.getSrc());
                        out.write(",");
                        out.write(e.getTgt());
                        out.write(",");
                        out.write(e.getTimestamp().format(DateTimeFormatter.ISO_DATE));
                        out.write("\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }
}
