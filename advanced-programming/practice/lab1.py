from antialiasing import *

if __name__ == "__main__":
    l0 = list()
    l1 = list()
    l0.append(1)
    l0.append(2)
    l0.append(3)
    print(l0)
    print(".----.")
    l1 = l0
    print(".----.")
    print(l1)
    l1[0] = 'a'
    print(l0)
    print(l1)
    another_list = list()
    another_list = l1
    l1.append("O")
    print(l0)
    print(l1)
    print(another_list)
    l2 = list()
    l2.append(l0)
    l2.append(l1)
    l2.append("M")
    l2.append(another_list)
    print(l2)
    l3 = list()
    l3 = l2
    print(l3)
    l3[0][1] = 3.14
    del l3[3][2]
    print(l0)
    print(l1)
    print(l2)
    print(l3)