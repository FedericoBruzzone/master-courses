AJC = ajc -1.9
AJ = aj

package: JavaFile.class

first: package FirstAj.class

all: package

JavaFile.class: *.java
	$(AJC) *.java

FirstAj.class: ExceptionLogging.aj
	$(AJC) ExceptionLogging.aj

clean:
	rm *.class