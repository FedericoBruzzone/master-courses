// javac -cp '..\javassist.jar' *.java
// jar -m .\MANIFEST.mf -f Premain.jar -c .\Premain.class

// java -cp '.;..\javassist.jar' Main

// java -D'java.system.class.loader'=MyClassLoader -cp '.;..\javassist.jar' MyLoader
// java -D'java.system.class.loader'=MyClassLoader -cp '.;..\javassist.jar' Main

// java -javaagent:Premain.jar -cp '.;..\javassist.jar' MyLoader

// java -javaagent:Premain.jar -D'java.system.class.loader'=MyClassLoader -cp '.;..\javassist.jar' MyLoader

public class Main {
    public static void main(String[] args) {
        String s = new String();
    }
}

// NEW TEST
// javac -cp /usr/share/java/javassist.jar *.java
// jar -m MANIFEST.txt -f Premain.jar -c Premain.class
// java -javaagent:Premain.jar -cp .:/usr/share/java/javassist.jar MyLoader