
with open("dict.txt") as f:
	words=[word[:-1] for word in f.readlines()]

def aux(start, end, path, paths):
	if start == end:
		paths.append(path)
		return

	for word in [w for w in words if sum(a!=b for a,b in zip(start,w)) == 1]:
		if not word in path:
			aux(word, end, path + [word], paths)

def chain(start, end):
	paths = []
	aux(start, end, [start], paths)
	res = ""
	for s in sorted(paths):
		res += s.__str__() + "\n"
	return res
