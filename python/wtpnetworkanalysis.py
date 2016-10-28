# construct graph from file wtpnetwork.csv

#compute weight for each edge
f = open("../data/wtpnetwork.csv", 'r')
edge_to_weight = {}
for line in f:
    key = line.rstrip()
    if key in edge_to_weight:
        edge_to_weight[key] += 1
    else:
        edge_to_weight[key] = 1

print edge_to_weight['Jianhui67,Jasper_Deng']

# create weighted directed graph
import networkx as nx
G=nx.DiGraph()
for edge in edge_to_weight.iteritems():
   v = edge[0].split(",")
   G.add_edge(v[0], v[1], weight=edge[1])

# Basic statistics
print "number of nodes: " + str(G.number_of_nodes())
print "number of edges: " + str(G.number_of_edges())

# Distance statistics

G2=nx.dodecahedral_graph()
nx.draw(G2,pos=nx.spring_layout(G2)) # use spring layout

import panda as pd
import numpy as np
import matplotlib as plt

ts = pd.Series(np.random.randn(1000), index=pd.date_range('1/1/2000', periods=1000))



ts = ts.cumsum()

ts.plot()