from alternade import *

if __name__ == "__main__":
  for i in range(2,5):
    tot = 0
    var_format = "«{0}» is an alternade for "+ \
        ("«{{{}}}», "*(i-1)).format(*range(1,i))+"and «{{{}}}»".format(i)
    for w, a in alternade_generator("dictionary.txt", i):
       tot+=1
       print(var_format.format(w,*a))
    print("There are {0} alternade of lenght {1} in this dictionary\n"
                 .format(tot, i))

