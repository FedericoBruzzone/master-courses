from swype import *

if __name__ == "__main__":
    test_cases = \
            ["heqerqllo",                  # hello
             "qwertuihgfcvjk",             # quick
             "wertyuioiuytrtghjklkjhgfd"   # world
             ]
    
    for test in test_cases:
        print(get_suggestion(test))
