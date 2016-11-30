import networkx as nx
import numpy as np
import matplotlib.pyplot as plt


def createGraph(pathtographfile):
    f = open(pathtographfile, 'r')
    edge_to_weight = {}
    for line in f:
        key = line.rstrip()
        if key in edge_to_weight:
            edge_to_weight[key] += 1
        else:
            edge_to_weight[key] = 1
    G = nx.DiGraph()
    for (e, w) in edge_to_weight.items():
        v = e.split(",")
        G.add_edge(v[0], v[1], weight=w)
    return G


def computeAndPlotDegreeStats(G):
    in_degrees = []
    count = 0
    for indeg in G.in_degree_iter():
        in_degrees.append(indeg[1])
        if (indeg[1] >= 1):
            count += 1

    print("mean in-degree", np.mean(in_degrees), "sd = ", np.std(in_degrees))
    print("max in-degree = ", np.max(in_degrees))
    print("w. in-degree >= 1 = ", count)

    out_degrees = []
    count = 0
    for outdeg in G.out_degree_iter():
        out_degrees.append(outdeg[1])
        if (outdeg[1] >= 1):
            count += 1
    print("mean out-degree", np.mean(out_degrees), " sd =", np.std(out_degrees))
    print("max out-degree = ", np.max(out_degrees))
    print("w. out-degree >= 1 = ", count)

    import matplotlib.pyplot as plt
    fig = plt.figure(figsize=(12, 6))
    idplot = fig.add_subplot(121)
    idplot.hist(in_degrees, bins=50)
    idplot.set_title("in-degree distribution")
    odplot = fig.add_subplot(122)
    odplot.hist(out_degrees, bins=50)
    odplot.set_title("out-degree distribution")
    plt.show()


def boxPlotDegreeDist(G):
    in_degrees = []
    for indeg in G.in_degree_iter():
        in_degrees.append(indeg[1])
    print("Avg. indegree ", np.mean(in_degrees))
    print("Std. indegree ", np.std(in_degrees))
    out_degrees = []
    for outdeg in G.out_degree_iter():
        out_degrees.append(outdeg[1])
    print("Avg. outdegree", np.mean(out_degrees))
    print("Std. outdegree", np.std(out_degrees))
    data = [in_degrees, out_degrees]
    plt.figure()
    plt.boxplot(data, labels=["in-degree", "out-degree"])
    plt.savefig("/Users/totucuong-standard/Projects/wikisocial-paper/images/boxplot_degree")
    plt.show()


def drawGraph(G, name):
    nx.draw(G, node_size=10)
    plt.savefig(name)
    plt.show()


def analyzeComponents(G):
    UG = G.to_undirected()
    largest_cc = max(nx.connected_components(UG), key=len)
    print("number of components:", nx.number_connected_components(UG))
    print("number of nodes in large connected component", len(largest_cc))
    print("size of giant component compared to original graph", len(largest_cc) / G.number_of_nodes() * 100)
    print("size of giant component", len(largest_cc))