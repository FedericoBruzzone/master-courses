.PHONY: help run clean

help:
	@echo "Usage:"
	@echo "    make run    =>  compile and run the program"
	@echo "    make clean  =>  remove .class"

run:
	@javac *.java
	@java -Djava.system.class.loader=DecryptClassLoader Main

clean:
	rm -f *.class
