'''
Exercise 4: Playing around with Extensions.

Python's dictionaries do not preserve the order of inserted data nor store the data sorted by the key. 
Write an extension for the dict class whose instances will keep the data sorted by their key value. 
Note that the order must be preserved also when new elements are added.
'''

class OrderedDict(dict):
    k_v = []
    
    def __isString(self, x):
        return "'"+x+"'" if type(x) == str else x

    def __str__(self):
        self.k_v = list(zip(self.keys(), self.values()))
        self.k_v.sort()
        r = ', '.join([i for i in list(map(lambda x: f'''{self.__isString(x[0])}:{self.__isString(x[1])}''', [el for el in self.k_v]))])
        return '{' + r + '}'

if __name__ == "__main__":
    od = OrderedDict({'5': 'five', '4': 'four'})
    print(od)
    od['3'] = 'three'
    od['2'] = 'two'
    print(od)
    