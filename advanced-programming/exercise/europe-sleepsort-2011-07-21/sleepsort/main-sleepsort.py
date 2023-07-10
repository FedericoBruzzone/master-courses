from threading import Thread
from time import sleep 

res = []

def sleep_for(n):
    sleep(n)
    res.append(n)

def sleepsort(list_of_numbers):
    list_of_threads = []
    for number in list_of_numbers:
        number = number / 100
        list_of_threads.append(Thread(target=sleep_for, args=[number]))

    for thread in list_of_threads:
        thread.start()
    
    while any([i.is_alive() for i in list_of_threads]): pass

    print(res)

if __name__ == "__main__":
    sleepsort([7, 2 ,100, 1, 9, 45, 2, 33, 7, 77, 25])
    sleepsort([333, 222 ,112, 777, 901, 455, 256, 313, 125, 625, 825, 999, 316])
    sleepsort([1000, 10, 10.5, 100, 22, 77, 700, 3.145, 2000, 150, 35, 287, 4, 7, 777, 2525, 255, 256, 25])

