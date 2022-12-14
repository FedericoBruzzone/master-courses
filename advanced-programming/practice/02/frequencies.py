print('''%%%%%%%%%%%%%%%%%%  2  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 2: Frequencies.

# Let's write a module (a pool of functions) that given a quite large text (over than 2000 words) 
# counts how frequent each word occurs in the text. In particular the module should provide 
# the function freqs that given a filename and a number would return a list of words (with their frequencies) 
# that occur more than the given number; the list is sorted by frequency with the higher first.

# The text is read from a file and it is a real text with punctuation (i.e., commas, semicolons, ...) that shouldn't be counted.
# Note that words that differ only for the case should be considered the same.

# First implementation
import  re
import functools

class Dict():
    def __init__(self):
        self.dictionary = dict()

    def __call__(self, selff, value):
        self.dictionary[value] = self.dictionary[value] + 1 if value in self.dictionary else 1
        return self

def freqs(filename, number):
    return [key for (key, value) in functools.reduce(Dict(), list(filter(lambda x: x != "", re.sub(r"[\W]", " ", open(filename).read().lower()).split(" ")))).dictionary.items() if value > number]
    # return [key for (key, value) in functools.reduce(Dict(), re.sub(r"[\W]", " ", open(filename).read().lower()).split(")).dictionary.items() if value > number]

print(freqs("test.txt", 100))

# Second implementation
def freqs_s(filename, number):
    dictionary = dict()

    with open(filename, encoding="utf-8") as f:
        words_list = re.sub(r"[\W]", " ", open(filename).read().lower()).split()

        while words_list:
            dictionary[words_list[0]] = words_list.count(words_list[0])
            words_list = list(filter(lambda element: element != words_list[0], words_list))

        return [k for (k, v) in dictionary.items() if v > number]


print(freqs_s("test.txt", 100))
