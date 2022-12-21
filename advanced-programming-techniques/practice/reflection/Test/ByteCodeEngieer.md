# Compile time

* Create main to modify a class file

```
javac -cp /usr/share/java/javassist.jar *.java
java -cp .:/usr/share/java/javassist.jar Patch
java Main
```

* if it is a java class
```
javac -cp /usr/share/java/javassist.jar *.java
java -cp .:/usr/share/java/javassist.jar Patch
java Main
```
# 