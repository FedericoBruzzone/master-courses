import os
import random

# THE FUCK DO I KNOW

def memory_consumption(func):
	def wrapper(*args, **kwargs):
		res = func(*args, **kwargs)

		with open(f"/proc/{os.getpid()}/status") as status:
			for line in status.read().split("\n"):
				if "VmRSS" in line or "VmSize" in line:
					print(line)

		return res
	return wrapper

N = 15

def free_row(queens, r):
	return queens == [] or not any(q[0] == r for q in queens)

def free_col(queens, c):
	return queens == [] or not any(q[1] == c for q in queens)

def free_diag(queens, r, c):
	diagonals = [(h, k) for h in range(N) for k in range(N) if abs(r - h) == abs(c - k)]
	return queens == [] or not any(q == (h, k) for q in queens for h, k in diagonals)

def is_free(queens, r, c):
	return free_row(queens, r) and free_col(queens, c) and free_diag(queens, r, c)

@memory_consumption
def place_queens(queens, col = 0):
	if col >= N:
		return True

	for row in range(N):
		if is_free(queens, row, col):
			queens.append((row, col))

			if place_queens(queens, col + 1):
				return True

			queens.pop()

	return False

if __name__ == "__main__":
	place_queens([])