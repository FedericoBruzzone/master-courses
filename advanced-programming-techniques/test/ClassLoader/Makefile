JAVAC = javac

package: MainTestClassLoader.class MyClassLoader.class TestClass.class 

all: package

clean: 
	rm *.class

MainTestClassLoader.class: MainTestClassLoader.java
	$(JAVAC) MainTestClassLoader.java

MyClassLoader.class: MyClassLoader.java
	$(JAVAC) MyClassLoader.java

TestClass.class: TestClass.java
	$(JAVAC) TestClass.java
