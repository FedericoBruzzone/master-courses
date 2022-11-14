def position(word, secret):
    yellow_letter = list(set(word).intersection(set(secret)))
    green_letter = [ch if ch == secret[i] else ' ' for i, ch in enumerate(word)]
    black_letter = set(word) - set(secret)
    return yellow_letter, green_letter, black_letter

def matching(list_words, word, secret):
    yellow, green, black = position(word, secret)
    green = list(filter(lambda x: all([green[i] == x[i] or green[i] == ' ' for i in range(len(green))]), list_words))
    print(green)
    print(black)
    black_green = set(green).difference(list(filter(lambda x: set(x).intersection(set(black)), green)))
    print(black_green)

def main(file):
    with open(file, encoding='utf-8') as f:
        list = f.read().split("\n")
    matching(list, "about", "admit")

#position("foods", "seeds")
main("words.txt") 
        
