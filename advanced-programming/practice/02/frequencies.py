# Exercise 2: Frequencies.
# Let's write a module (a pool of functions) that given a quite large text (over than 2000 words) counts how frequent each word occurs in the text. In particular the module should provide the function freqs that given a filename and a number would return a list of words (with their frequencies) that occur more than the given number; the list is sorted by frequency with the higher first.
#The text is read from a file and it is a real text with punctuation (i.e., commas, semicolons, ...) that shouldn't be counted.
#Note that words that differ only for the case should be considered the same.



def freqs(filename, numebr):
    words = {}
    with open(filename, encoding = 'utf-8') as f:
        for word in f.readline().lower().split():
            #...

print(freqs("test.txt", 10))
