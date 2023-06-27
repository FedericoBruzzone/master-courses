WORDS = [word for word in open('dictionary.txt').read().lower().splitlines() if word.isalpha()]

def is_correct(my_word, match):
    try:
        for i in match:
            my_word = my_word[my_word.index(i)+1:]
        return True
    except:
        return False

def get_suggestion(my_word):
    all_match = list(filter(lambda word: (my_word[0] == word[0]) and (my_word[-1] == word[-1]), WORDS)) 
    # print(all_match)
    return [match for match in all_match if is_correct(my_word, match)]

