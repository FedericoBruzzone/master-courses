import unittest
import itertools
import functools
from ruzzle import * 

inputs = [["walk", "moon", "hate", "rope"], 
          ["abbr", "evia", "tion", "alba"]] 
          # ["abse", "imtn", "nded", "ssen"], 
          # ["reca", "rwar", "aazp", "syon"], 
          # ["abst", "oime", "uesl", "snsp"], 
          # ["essx", "ndet", "sigh", "raen"], 
          # ["poap", "lkjh", "aswe", "jhnh"]] 
open_file()
offsets = [(1,0), (0,1), (-1,0), (0,-1)]

class MyTest(unittest.TestCase):
    def __init__(self, *args, **kwargs):
        self.res = [ruzzles(i) for i in inputs]
        super(MyTest, self).__init__(*args, **kwargs)

    # def setUp(self):
    #     self.res = [ruzzles(i) for i in inputs]
    
    def test_alphabetically_sorted(self):
        for i in self.res:
            self.assertEqual(sorted(i), i)

    def test_english_words(self):
        for i in self.res:
            for j in i:
                self.assertTrue(j in set_w)

    def test_shorter_longer(self):
        for i in self.res:
            for j in i:
                self.assertTrue(3 <= len(j) < 16)

    def test_hidden(self):
        def check(word, matrix, i, j):
            tmp = False
            if word == "":
                return True 
            if ((0<=i<4) and (0<=j<4)) and word[0] != matrix[i][j]:
                for offi, offj in offsets:
                    tmp |= check(word[1:], matrix, i+offi, j+offj)
            return tmp 

        def is_hidden(list_word, index):
            matrix = [list(i) for i in inputs[index]]
            all_perm = list(itertools.product(range(4), repeat=2))

            for word in list_word:
                for (i, j) in all_perm:
                    if check(word, matrix, i, j):
                        return True
            return False 

        for index, list_word in enumerate(self.res):
            self.assertTrue(is_hidden(list_word, index))

if __name__ == "__main__":
    unittest.main()


