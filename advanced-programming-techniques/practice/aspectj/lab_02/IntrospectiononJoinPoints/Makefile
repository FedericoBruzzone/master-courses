AJC = ajc -1.9
AJ = aj

INTROSPECTION = Introspection.class

package: compile_run

all: package

compile: $(INTROSPECTION)

Introspection.class: Introspection.aj 
	$(AJC) Introspection.aj *.java

run:
	$(AJ) Main

compile_run:
	$(AJC) Introspection.aj *.java
	$(AJ) Main

clean:
	rm *.class
