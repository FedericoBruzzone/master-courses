from sudoku import *

if __name__ == '__main__':
    S = sudoku()

    for i in range(1, 101):
        print(f'{next(S)}')