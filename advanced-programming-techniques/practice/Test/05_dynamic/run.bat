javac -d bin -cp javassist.jar *.java
jar -cfm MyAgent.jar MANIFEST.MF bin/MyAgent.class
java -ea -javaagent:MyAgent.jar -cp bin App 3