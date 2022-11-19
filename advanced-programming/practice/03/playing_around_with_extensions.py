'''
Exercise 4: Playing around with Extensions.

Python's dictionaries do not preserve the order of inserted data nor store the data sorted by the key. 
Write an extension for the dict class whose instances will keep the data sorted by their key value. 
Note that the order must be preserved also when new elements are added.
'''

class OrderedDict(dict):
    __K_V = []

    def __init__(self, args):
        [self.__insert_key(k) for k in args]
        super().__init__(args)

    __is_str = lambda self, s: "'"+s+"'" if type(s) == str else s
    __insert_key = lambda self, __key: (len(self.__K_V) == 0 and (self.__K_V.append(__key) or True)) or \
                                       (self.__K_V.insert([i for i, e in enumerate(self.__K_V, start=0) if str(__key) < str(e)][0], __key))
    
    def __setitem__(self, __key, __value):
        self.__insert_key(__key)
        return super().__setitem__(__key, __value)

    def __str__(self):
        return '{' + ', '.join([i for i in list(map(lambda k : f'''{self.__is_str(k)}:{self.__is_str(self[k])}''', [k for k in self.__K_V]))]) + '}'

if __name__ == "__main__":
    od = OrderedDict({'5': 'five', '4': 'four'})
    print(od)
    od['3'] = 'three'
    od['2'] = 'two'
    od[1] = 'one'
    print(od)

# class OrderedDict(dict):
#     k_v = []
    
#     def __isString(self, x):
#         return "'"+x+"'" if type(x) == str else x

#     def __str__(self):
#         self.k_v = list(zip(self.keys(), self.values()))
#         self.k_v.sort()
#         r = ', '.join([i for i in list(map(lambda x: f'''{self.__isString(x[0])}:{self.__isString(x[1])}''', [el for el in self.k_v]))])
#         return '{' + r + '}'