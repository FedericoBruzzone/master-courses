from memory_usage import *

NUMBERS = [number for number in open("../t9's texts.txt").read().splitlines()] 
DICT = set([number for number in open("../dictionary.txt").read().splitlines()]) 
DICT_FREC = set([number for number in open("../dict-frequency.txt").read().splitlines()])
number_to_letter = {
    "2": ["a","b","c"],
    "3": ["d","e","f"],
    "4": ["g","h","i"],
    "5": ["j","k","l"],
    "6": ["m","n","o"],
    "7": ["p","q","r","s"],
    "8": ["t","u","v"],
    "9": ["w","x","y","z"]
}

def all_comb_of(word):
    letters = [number_to_letter[n] for n in word]
    def inner(letters, i=0):
        if i == len(letters) - 1:
            return letters[i]
        else:
            return [a + b for a in letters[i] for b in inner(letters, i+1)]
    return inner(letters, 0)

def print_correctly(x):
    def inner(x, i=0):
        if i == len(x)-1:
            return x[i]
        else:
            return [a + " " + j for a in x[i] for j in inner(x, i+1)]
    return inner(x, 0)
    

@memory_usage
def iterate():
    for n in NUMBERS[:1]:
        words = n.split()
        all_comb = list(filter(None, [list(filter(lambda x: x in DICT, x)) for x in [all_comb_of(word) for word in words]]))
        print(print_correctly(all_comb)) 
    
        

iterate()
# print(DICT_FREC)
