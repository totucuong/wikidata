package de.fu.info.wikisocial.wikidata.extractor;

/**
 * Created by totucuong-standard on 9/22/16.
 */
public class WTPNetworkExtractor {
//    private BufferedWriter w;
//
//    /**
//     *
//     * @param writer a BufferedWriter to write the graph to
//     */
//    public WTPNetworkExtractor(BufferedWriter writer) {
//        this.w = writer;
//    }
//
//    /**
//     *
//     * @param threads list of discussion threads
//     */
//    public void extract(ArrayList<TalkPageExtractor> pages) throws IOException{
//        for (TalkPageExtractor p : pages) {
//            for (Thread t : p.get_threads())
//                extract_edges(t);
//        }
//
//    }
//
//    /**
//     * Extract graph edges for the a within page network
//     * @param t a discussion thread
//     */
//    private void extract_edges(Thread t) throws IOException {
//        extract_edges(t.getReplies());
//    }
//
//    /**
//     * Extract edges from replies to a messenger
//     * @param replies replies to him/her
//     * Here replies are jsoup's elements which are a tree of children elements. Each reply is organized as follows.
//     *                  <dd>
//     *                      <dl>
//     *                          ...
//     *                          <dd></dd>
//     *                          <dd></dd>
//     *                          ...
//     *                      </dl>
//     *                  </dd>
//     *  Here each <dd></dd> can contains other replies which is grouped using <dl></dl>, and so on recursively.
//     *
//     */
//    private void extract_edges(List<Reply> replies) throws IOException {
//        for (Reply r : replies)
//            extract_edges(r);
//    }
//
//    private void extract_edges(Reply r) throws IOException {
//        String poster = r.getPoster();
//        String replier = Extractor.extract_user(extract_reply_msg(r.getBody()));
//        w.write(replier + " " + poster);
//    }
//
//    private String extract_reply_msg(Element body) {
//        List<Node> nodes = new ArrayList<>();
//        for (Node child : body.childNodes()) {
//            if (child instanceof Element)
//                break;
//            else
//                nodes.add(child);
//
//        }
//        StringBuilder builder = new StringBuilder();
//        for (Node node : nodes)
//            builder.append(node.toString());
//        return builder.toString();
//    }

}
