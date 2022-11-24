 print('''%%%%%%%%%%%%%%%%%%  2  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 2: Alkaline Earth Metals.

# Assign a list that contains the atomic numbers and the names of the six alkaline earth metals—barium (56), beryllium (4), calcium (20), magnesium (12), radium (88), and strontium (38)—to a variable called alkaline_earth_metals.
# 1. Write a one-liner to return the highest atomic number in alkaline_earth_metals.
# 2. Using one of the list methods, sort alkaline_earth_metals in ascending order (from the lightest to the heaviest).
# 3. Transform the alkaline_earth_metals into a dictionary using the name of the metals as the dictionary’s key.
# 4. Create a second dictionary containing the noble gases—helium (2), neon (10), argon (18), krypton (36), xenon (54), and radon (86)—and store it in the variable noble_gases.
# 5. Merge the two dictionaries and print the result as couples (name, atomic number) sorted in ascending order on the element names. Note that Python’s dictionaries do not preserve the insertion order neither it is sorted in some way.

# 1
alkaline_earth_metals = [('barium',56), ('beryllium', 4), ('calcium', 20), ('magnesium', 12), ('radium', 88), ('strontium', 38)]

print(sorted(alkaline_earth_metals, key = lambda x:x[1])[-1][1])

# 2
print(sorted(alkaline_earth_metals, key = lambda x:x[1]))


#3
alkaline_earth_metals = dict(alkaline_earth_metals)
print(alkaline_earth_metals)
type(alkaline_earth_metals)

# 4
noble_gases = {'helium': 2, 'neon': 10, 'argon': 18, 'krypton': 36, 'xenon': 54, 'radon': 86}

# 5
#merge_dict = alkaline_earth_metals | noble_gases
merge_dict = {**alkaline_earth_metals, **noble_gases}
print(merge_dict)
