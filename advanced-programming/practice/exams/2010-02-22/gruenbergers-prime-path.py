def is_prime(n):
    return [i for i in range(1, n//2 + 1) if n%i == 0] == [1]

def gen_odd_numers():
        i = 3
        while i < 10001:
            yield i
            i += 2

def gruenbergersPrimePath():
    # +1 = right
    # -1 = left
    # 0 = streight
    def choice(n):
        return ((is_prime(n) and (n-1) % 6 == 0) and +1) or \
               ((is_prime(n) and (n+1) % 6 == 0) and -1) or \
                                                      0

    add = lambda x, y: (x[0]+y[0], x[1]+y[1])
    directions = [(1,0),(0,-1),(-1,0),(0,1)]
    dir = 0
    last = (0,0)
    res = {}

    [((dir := (dir + choice(i)) % 4) or True) and
     (((res.get(last) != None) and (res.update({last: res.get(last)+[i]}) or True)) or \
      (res.update({last: [i]})) or True) and \
     ((last := add(last, directions[dir])) or True)
    for i in gen_odd_numers()]

    # for i in gen_odd_numers():
    #     dir = (dir + choice(i)) % 4
    #     if res.get(last) != None:
    #         res.update({last: res.get(last)+[i]}) 
    #     else:
    #         res.update({last: [i]})
    #     last = add(last, directions[dir])

    return {x: y for x, y in res.items() if len(y) > 1}

print(gruenbergersPrimePath())


















































# First implementation

# def gruenbergersPrimePath():
#     def gen_odd_numers():
#         i = 5
#         while i < 10001:
#             yield i
#             i += 2

#     # +1 = right
#     # -1 = left
#     # 0 = streight
#     def choice(n):
#         return ((is_prime(n) and (n-1) % 6 == 0) and +1) or \
#                ((is_prime(n) and (n+1) % 6 == 0) and -1) or \
#                                                       0

#     def generate_path():
#         g = gen_odd_numers()
#         update = 1 # x = 1, y = 0
#         last_move = 0
#         l = [[0,0], [0,1]]

#         for i in g:
#             if choice(i) != 0:
#                 last_move = choice(i)
#                 update = not update

#             x = l[-1].copy()
#             x[update] += last_move
#             l.append(x)

#         return l
    
#     def intersection():
#         path = generate_path()
#         memo = []
#         res = []
#         def inner():
#             for i in path:
#                 if i not in memo:
#                     memo.append(i)
#                 else:
#                     res.append(i)
#             print(all([True if path.count(i) > 1 else False for i in res]))
#             return res

#         return inner()

#     return intersection()
    
# print(gruenbergersPrimePath())
# gruenbergersPrimePath()