AJC = ajc -1.9
AJ = aj

INTROSPECTION = Introspection.class
SUBINTROSPECTION = SubIntrospection.class

#package: compile_run

#all: package

introspection: $(INTROSPECTION)
subintrospection: $(SUBINTROSPECTION) 

Introspection.class: Introspection.aj 
	$(AJC) Introspection.aj *.java

SubIntrospection.class: SubIntrospection.aj 
	$(AJC) Introspection.aj 
	$(AJC) SubIntrospection.aj *.java

run:
	$(AJ) Main

compile_run:
	$(AJC) Introspection.aj *.java
	$(AJ) Main

clean:
	rm *.class
