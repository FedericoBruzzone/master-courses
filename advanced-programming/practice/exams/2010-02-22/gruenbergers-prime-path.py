def is_prime(n):
    return [i for i in range(1, n//2 + 1) if n%i == 0] == [1]

def gruenbergersPrimePath():
    def gen_odd_numers():
        i = 5
        while i < 10:
            yield i
            i += 2

    # +1 = right
    # -1 = left
    # 0 = streight
    def choice(n):
        return ((is_prime(n) and (n-1) % 6 == 0) and +1) or \
               ((is_prime(n) and (n+1) % 6 == 0) and -1) or \
                                                      0

    def intersection(list):
        pass

    
    g = gen_odd_numers()
    update = 0 # x = 1, y = 0
    last_move = 0
    l = [[0,0], [0,1]]

    for i in g:
        if choice(i) != 0:
            last_move = choice(i)
        else:
            update = not update

        x = l[-1]
        x[update] += last_move
        l.append(x)

    return l

print(gruenbergersPrimePath())