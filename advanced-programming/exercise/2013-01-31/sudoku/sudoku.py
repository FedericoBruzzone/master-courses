import itertools

def check_row(s):
    ssum = set()
    for i in range(len(s)):
        ssum = set()
        for j in range(len(s)):
            ssum |= {s[i][j]}
        if sum(ssum) != 10:
            return False
        
def test_columns(s):
    for j in range(0,4):
        if len({s[i][j] for i in range(0,4)}) < 4:
            return False
    return True

def test_squares(s):
    for offx in [0,2]:
        for offy in [0,2]:
            if len({s[x+offx][y+offy] for x in range(0,2) for y in range(0,2)})< 4:
                return False
    return True

def is_valid(s):
    return test_columns(s) and test_squares(s)

def sudoku():
    s = list(itertools.product(itertools.permutations(range(1, 5)),itertools.permutations(range(1, 5)),itertools.permutations(range(1, 5)),itertools.permutations(range(1, 5))))
    while True:
        for i in s:
            if is_valid(i):
                yield i
