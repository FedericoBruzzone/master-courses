import math

class circle():
    def __init__(self, ray):
        if ray <= 0:
            raise ValueError("{0} is unadmissible size for a circle's ray".format(ray))
        self._ray = ray
    def calculate_area(self):
        return self._ray**2*math.pi
    def calculate_perimeter(self):
        return 2*self._ray*math.pi
    def __lt__(self, other):
        return self.lessthan(other)
    def lessthan(other): pass
    def __str__(self):
        return "I'm a circle of ray {0} and my area is {1:.2f}".format(self._ray, self.calculate_area())

