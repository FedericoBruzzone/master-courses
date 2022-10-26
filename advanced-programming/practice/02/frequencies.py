# Exercise 2: Frequencies.
# Let's write a module (a pool of functions) that given a quite large text (over than 2000 words) counts how frequent each word occurs in the text. In particular the module should provide the function freqs that given a filename and a number would return a list of words (with their frequencies) that occur more than the given number; the list is sorted by frequency with the higher first.
#The text is read from a file and it is a real text with punctuation (i.e., commas, semicolons, ...) that shouldn't be counted.
#Note that words that differ only for the case should be considered the same.

class Dict():
    def __init__(self):
        self.dictionary = dict()

    def __call__(self, selff, value):
        self.dictionary[value] = self.dictionary[value] + 1 if value in self.dictionary else 1
        return self

import  re
import functools

def freqs(filename, number):
    return [key for (key, value) in functools.reduce(Dict(), list(filter(lambda x: x != "", re.sub(r"[\W]", " ", open(filename).read().lower()).split(" ")))).dictionary.items() if value > number]

    # return [key for (key, value) in functools.reduce(Dict(), re.sub(r"[\W]", " ", open(filename).read().lower()).split(")).dictionary.items() if value > number]

print(freqs("test.txt", 100))


#def freqsWhithoutLib (filename, number):
    #elementi = {}
    #for line in fileinput.input(files=(filename),encoding="utf-8"):
        #for parola in line.lower().split():
            #elementi[parola] = 1 if parola not in elementi else elementi[parola]+1
    #return [print(tupla) for tupla in elementi.items() if tupla[1]>number]


def freqs(filename, number):
    dictionary = dict()

    with open(filename, encoding="utf-8") as f:
        words_list = re.sub(r"[\W]", " ", open(filename).read().lower()).split()

        while words_list:
            occ_element = words_list.count(words_list[0])
            dictionary[words_list[0]] = occ_element
            words_list = list(filter(lambda element: element != words_list[0], words_list))

        return [k for (k, v) in dictionary.items() if v > number]


print(freqs("test.txt", 100))
