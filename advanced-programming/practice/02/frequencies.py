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

print(freqs("test.txt", 100))
