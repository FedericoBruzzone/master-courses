print('''%%%%%%%%%%%%%%%%%%  4  %%%%%%%%%%%%%%%%%%%%''')

# Exercise 4: Matrix Calculi

# A matrix can be represented as a list of lists (rows and columns).
# 1. Use the comprehensions to define a function (identity) that returns the identity matrix (the one with all 0s but the 1s on the diagonal) of given size.
# 2. Use the comprehensions to define a function (square) that returns a square matrix filled with the first n*n integers with n given as an argument.
# 3. Write the function transpose to transpose a generic matrix independently of the size and content.
# 4. Write a function to multiply two matrices non necessarily square matrices.

rows = lambda l, n, m: [[l[col + row * m] for col in range(0, m)] for row in range(0,n)]

# 1
indentity = lambda n: rows([1 if row==col else 0 for col in range(n) for row in range(n)], n, n)
print(indentity(4))

# 2
square= lambda n: rows([row + col * n for row in range(1,n+1) for col in range(0,n)], n, n)
print(square(4))

# 3
transpose = lambda matrix: rows([matrix[row][col] for row in range(len(matrix)) for col in range(len(matrix[0]))], len(matrix[0]), len(matrix))
print(transpose(square(4)))

# 4
element = lambda matrix1, matrix2, m1, m2: sum(matrix1[m1][i]+matrix2[i][m2] for i in range(len(matrix1[0])))
multiply = lambda matrix1, matrix2: rows([element(matrix1, matrix2, m1, m2) \
	for m1 in range(len(matrix1)) for m2 in range(len(matrix2[0]))], len(matrix1), len(matrix2[0]))

print(multiply(square(4), square(5)))
