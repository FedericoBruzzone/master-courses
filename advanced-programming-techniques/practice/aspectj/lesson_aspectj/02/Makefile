AJC = ajc -1.9
AJ = aj

MYASPECT =MyAspect2.class

package: $(MYASPECT)

all: package

MyAspect2.class: MyAspect2.aj
	$(AJC) MyAspect2.aj *.java

MyAspect1.class: MyAspect1.aj
	$(AJC) MyAspect1.aj *.java

run:
	$(AJ) Main

clean:
	rm *.class