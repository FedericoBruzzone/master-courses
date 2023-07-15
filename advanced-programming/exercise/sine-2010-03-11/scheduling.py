
class a_scheduling():
    def schedule(p):
        pass

class fifo(a_scheduling):
    def schedule(p):
        p.run()
        # wait for p.how_long
        return False

class round_robin(a_scheduling):
    global QUANT
    QUANT = 2

    def schedule(p):
        p.run()
        p.how_long -= QUANT
        if p.how_long > 0:
            return True
        return False


class process():
    def __init__(self, name, arrival, how_long=1):
        self.name = name
        self.arrival = arrival
        self.how_long = how_long

    def run(self):
        print(self.name, self.arrival, self.how_long)

    def __repr__(self):
        return self.name  + " " + str(self.arrival) + " " + str(self.how_long)
        
class scheduler():
    def __init__(self, p, t):
        self.p = sorted(p, key=lambda x: x.arrival)
        self.t = t

    def scheduling(self):
        while(self.p != []):
            i = self.p[0]
            update = self.t.schedule(i)
            if update:
                self.p.remove(i)
                self.p.append(i)
            else:
                self.p.remove(i)
            print(self.p)
            


if __name__ == "__main__":
    pl = [process("one", 10), process("two", 3, 5), process("three", 15), process("four", 30, 5), process("five", 10), process("six", 6, 10), process("seven", 10), process("eight", 25, 5)]
    print("fifo scheduling")
    s = scheduler(pl, fifo)
    s.scheduling()
    print("round-robin scheduling")
    s = scheduler(pl, round_robin)
    s.scheduling()