import copy

def open_file():
    with open("dictionary.txt") as f:
        global set_w
        set_w = {l.strip().lower() for l in f.readlines() if len(l) > 3 and l.strip().isalpha()}
open_file()
offsets = [(1,0), (0,1), (-1,0), (0,-1)]

def dfs(i, j, l_words, car, visited):
    if not((0 <= i < 4) and (0 <= j < 4)) or tuple([i,j]) in visited: 
        return {car}

    visited.add(tuple([i,j]))
    res = set()
    for offset in offsets:
        res |= dfs(i+offset[0],
                   j+offset[1], 
                   l_words,
                   car+l_words[i][j],
                   copy.deepcopy(visited)) # visited | set(tuple([i,j]))
    return res
       
def ruzzles(words):
    l_words = [list(i) for i in words]
    
    res = set()
    for i in range(0, 4):
        for j in range(0, 4):
            res |= dfs(i, j, l_words, "", set())
    
    return sorted([i for i in res if i in set_w])

