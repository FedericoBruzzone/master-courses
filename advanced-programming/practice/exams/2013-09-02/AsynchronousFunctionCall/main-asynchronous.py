from asynchronous import *

if __name__ == "__main__":
	import time

	@asynchronous
	def long_process(num):
		time.sleep(10)
		return num * num
	
	result = long_process.start(12)
	
	for i in range(20):
		time.sleep(1)
	
		if result.is_done():
			print("[{1}]: result {0}".format(result.get_result(), i))
		else: print("[{0}]: not ready yet".format(i))
	
	result2 = long_process.start(13)
	
	result3 = long_process.start(3)
	

	try:
		print("result2 {0}".format(result2.get_result()))
	except asynchronous.NotYetDoneException as ex:
		print(ex.message)

	time.sleep(4)

	try:
		print("result3 {0}".format(result3.get_result()))
	except asynchronous.NotYetDoneException as ex:
		print(ex.message)
