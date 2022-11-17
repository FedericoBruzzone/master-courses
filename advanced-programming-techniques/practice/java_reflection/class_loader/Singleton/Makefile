JAVAC = javac

package: SimpleClassLoader.class SingletonViolationTest.class Singleton.class 

all: package

clean: 
	rm testclasses/*.class
	rm *.class

SimpleClassLoader.class: SimpleClassLoader.java
	$(JAVAC) SimpleClassLoader.java

SingletonViolationTest.class: SingletonViolationTest.java
	$(JAVAC) SingletonViolationTest.java

Singleton.class: Singleton.java
	$(JAVAC) Singleton.java
	mv Singleton.class testclasses/
