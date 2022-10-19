import networkx as nx
import matplotlib.pyplot as plt
import random
import math

G = gx.karate_club_graph(40, 0.01, directed=True)
nx.draw(G, with_labels=True)

def doubleFan(t = 10):
    G = nx.DiGraph()
    G.add_nodes_from(['s', 't'])
    G.add_nodes_from(['a' + str(x) for x in range(t)])
    G.add_nodes_from(['b' + str(x) for x in range(t)])
    G.add_nodes_from(['x', 'y'])
    G.add_nodes_from(['s', 'b' + str(x) for x in range(t)])
    G.add_nodes_from([('s', 'b' + str(x)) for x in range(t)])


def pricing_disjoint_paths(G_original, source_target_pairs, c = 1):
    G = G_original.copy()
    result = []
    beta = math.pow(G.number_of_edge), 1 / (c+1))

    # Initialize
    for u, v, f in G.edges(data=True):
        d['length'] = 1
        d['congestion'] = 0

    # Main cycle
    while True:
        min_path = None
        for index in range(len(source_target_pairs)):
            try:
                s = source_target_pairs[index][0]
                t = source_target_pairs[index][1]
                path = nx.dijstra_path(G, s, t, weigth='length')
            except:
                pass
            else:
                path_length = 0
                for i in range(0, path_length - 1):
                    path_length += G[path[i][path[i + 1]]['length']
                if min_path is None or path_length < min_path_length:
                    min_path = path
                    min_path_length = path_length
                    min_index = index
        if min_path is None:
            break
        for i in range(0, len(min_path) - 1):
            if G[path[i][path[i + 1]]['congestion'] == c - 1:
                G.remove_edge(path[i], path[i + 1])
            else:
                G[path[i][path[i + 1]]['congestion'] += 1
                G[path[i][path[i + 1]]['length'] *= beta

        result.append(min_path)
        source_target_pairs.pop(min_index)
    return result



    return beta


