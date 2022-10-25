print('''%%%%%%%%%%%%%%%%%%  3  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 3: Temperature Conversion System

# Beyond the well-known Celsius and Fahrenheit, there are other six temperature scales: Kelvin, Rankine, Delisle, Newton, Réaumur, and Rømer (Look at http://en.wikipedia.org/wiki/Comparison_of_temperature_scales to read about them).
# 1. Write a function (table) that given a pure number returns a conversion table for it (as a string) among any of the 8 temperature scales (remember that functions are objects as well).
# 2. Write a function (toAll) that given a temperature in a specified scale returns a string for all the corresponding temperatures in the other scales, the result must be sorted on the temperatures and the scale must be specified.

def fromC(x): return x
def toC(x): return x
def fromDe(x): return 100-x*2/3
def toDe(x): return (100-x)*3/2
def fromF(x): return (x-32)*5/9
def toF(x): return x*9/5+32
def fromK(x): return x-273.15
def toK(x): return x+273.15
def fromN(x): return x*100/33
def toN(x): return x*33/100
def fromR(x): return (x-491.67)*5/9
def toR(x): return x*9/5+491.67
def fromRe(x): return x*5/4
def toRe(x): return x*4/5
def fromRo(x): return (x-7.5)*40/21
def toRo(x): return x*21/40+7.5

toT = {'F': toF, 'K': toK, 'R': toR, 'De': toDe, 'N': toN, 'Re': toRe, 'Ro': toRo, 'C': toC}
fromT = {'F': fromF, 'K': fromK, 'R': fromR, 'De': fromDe, 'N': fromN, 'Re': fromRe, 'Ro': fromRo, 'C': fromC}

def fromTtoAll(n, t):
	celsius = fromT[t](n)
	return {scale: convert(celsius) for scale, convert in toT.items()}

# 1
def table(n):
	all_values = [[convert \
		       for scale, convert in sorted(fromTtoAll(n, t).items(), key=lambda x: x[0])] \
		       for t in sorted(toT.keys())]
	res = "    "+(8*"{: ^6} ")+"\n"+(8*("{:<3}"+(8*"{: 6.1f} ")+"\n"))
	output = temp = sorted(toT.keys())
	for i in range(8): output += [temp[i]] + all_values[i]

	return res.format(*output)

print(table(25))

# 2
def toAll(n, t):
	all_values = fromTtoAll(n, t)
	del all_values[t]

	res=(7*"{{{}[1]:.1f}}◦{{{}[0]}} ").format(*[e for l in zip(range(7),range(7)) for e in l])
	output = sorted(all_values.items(),key=lambda x:x[1])
	return res.format(*output)

print(toAll(25, 'F'))
