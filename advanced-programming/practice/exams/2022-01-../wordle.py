def position(word, secret):
    yellow_letter = set(word).intersection(set(secret))
    green_letter = [ch if ch == secret[i] else " " for i, ch in enumerate(word)]
    black_letter = set(word) - set(secret)
    return list(yellow_letter), green_letter, list(black_letter)

def matching(list_words, word, secret):
    yellow, green, black = position(word, secret)
    # print(yellow, green, black)
    green = list(filter(lambda x: all([green[i] == x[i] or green[i] == " " for i in range(len(green))]), list_words))
    # print(green)
    black_green = list(filter(lambda x: len(set(x).intersection(set(black))) == 0, green))
    # print(black_green)
    final = list(filter(lambda x: len(set(x).intersection(set(yellow))) != 0 , black_green)) if (len(yellow) > 0) else black_green
    # print(final)
    return final


if name == "main":
    with open("Esami\words.txt", encoding='utf-8') as f:
        words = f.read().split("\n")

        def rec(lista, i=1):
            if len(lista) == 1 : return lista[0]
            word = lista[0]
            lista = matching(lista, lista[0], "zesty")
            if word in lista: lista.remove(word)
            return rec(lista, i+1)

        print(rec(words))
       
