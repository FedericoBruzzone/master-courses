from antialiasing import *

if __name__ == "__main__":
    l0 = list()
    l1 = list()
    l0.append(1)
    l0.append(2)
    l0.append(3)
    print(f"l0 :- {l0}")
    l1 = l0
    print(f"l1 :- {l1}")
    l1[0] = 'a'
    print(f"l0 :- {l0}")
    print(f"l1 :- {l1}")
    another_list = list()
    another_list = l1
    l1.append('g')
    print(f"l0 :- {l0}")
    print(f"l1 :- {l1}")
    print(f"another_list :- {another_list}")
    l2 = list()
    l2.append(l0)
    l2.append(l1)
    l2.append('e')
    l2.append(another_list)
    print(f"l2 :- {l2}")
    l3 = list()
    l3 = l2
    print(f"l3 :- {l3}")
    l3[0][1] = 3.14
    del l3[3][2]
    print(f"l0 :- {l0}")
    print(f"l1 :- {l1}")
    print(f"another_list :- {another_list}")
    print(f"l2 :- {l2}")
    print(f"l3 :- {l3}")

