import graphanalysis as ga

G = ga.createGraph("../data/itpnetwork.csv")
print("Number of edges: ", G.number_of_edges())