class editor(object):
    def __init__(self):
        self.text = [] 
        self.c = -1 
    
    def __move_c(self):
        find = False
        prev = self.c
        for el in self.text[self.c:]:
            if el.isascii():
                find = True
                break
            self.c += 1
        if not find:
            if prev >= 0:
                self.c = prev - 1

    def x(self):
        try:
            del self.text[self.c]
        except Exception as e:
            pass 
        self.__move_c()

    def dw(self):
        for el in self.text[self.c:]:
            if el == " " or len(self.text) == self.c:
                break
            del self.text[self.c]

        self.__move_c()
            
    def i(self, ch):
        self.c += 1
        self.text.insert(self.c, ch)

    def iw(self, t):
        t = t + " "
        prev = self.text[self.c+1:]
        res = []
        for i in self.text[:self.c+1]:
            res.append(i)
        res.extend(t)
        for i in prev:
            res.append(i)
        self.text = res
        self.c += len(t)

    def h(self, n=1):
        i = 0
        while self.c >= 0 and i < n:
            self.c -= 1
            i += 1
    
    def l(self, n=1):
        i = 0
        while self.c <= len(self.text) and i < n:
            self.c += 1
            i += 1

    def __str__(self):
        return "".join(self.text)
