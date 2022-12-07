# Compile time with only javassist

## My Class

* Create a Patch class with only main method to modify your .class file
    * Create ClassPool
    * Extract from ClassPool your class
    * . . .

### Command line

```Java
javac -cp /usr/share/java/javassist.jar *.java
java -cp .:/usr/share/java/javassist.jar Patch // Generate a .class file modified
java Main
```

## Java Class

* Create a Patch class with only main method to modify a system .class file
    * Create ClassPool
    * Extract from ClassPool a system class
    * . . .

### Command line

```Java
javac -cp /usr/share/java/javassist.jar *.java
java -cp .:/usr/share/java/javassist.jar PatchStringBuilder // Generate a .class file modified
jar -f StringBuilder.jar -c java/lang/StringBuilder.class 
java --patch-module java.base=StringBuilder.jar MyClass
```

# Load time with javassist and Loader+Translator

* IMPORTANT: In jvassist.Translator pass only our written class!

## My Class

* Create a MyTranslator class that implements `javassist.Translator` 
    * @Override 
        * `public void start(ClassPool p) {}`
        * `public void onLoad(ClassPool p, String cn) {}`
* Create a RunMyTranslator class 
```Java
MyTranslator myTranslator = new MyTranslator();
ClassPool cp = ClassPool.getDefault();
Loader loader = new Loader(cp);
loader.addTranslator(cp, myTranslator);
loader.run("Main", args);
```

### Command line

```Java
javac -cp /usr/share/java/javassist.jar *.java
java -cp .:/usr/share/java/javassist.jar RunTranslator
```

## No Java class

# Load time with javassist and premain+ClassFileTransformer

* IMPORTANT: In jvassist.Translator pass only our written class!

## My Class

* Create a MyTransformer class that implements `java.lang.instrument.ClassFileTransformer`
    * @Override 
        ```Java
        public byte[] transform(ClassLoader loader, 
                                String className, 
                                Class<?> classBeingRedefined, 
                                ProtectionDomain protectionDomain, 
                                byte[] classfileBuffer) throws IllegalClassFormatException {}
        ```
* Create Premain class with premain method
    ```Java
    public static void premain(String agentArgs, Instrumentation instrumentation) {
            System.out.println("----- Premain::premain -----");
            MyTransformer myTransformer = new MyTransformer();
            instrumentation.addTransformer(myTransformer);
        }
    ```

* Create **MANIFEST.MF** 
    * Look at [java.lang.Instrument](https://docs.oracle.com/en/java/javase/11/docs/api/java.instrument/java/lang/instrument/package-summary.html) (Section _Manifest Attributes_)
```
Manifest-Version: 1.0
Premain-Class: Premain
```

### Command line

```Java
javac -cp /usr/share/java/javassist.jar *.java
jar -m MANIFEST.MF -f Premain.jar -c Premain.class
java -javaagent:Premain.jar[=args] -cp .:/usr/share/java/javassist.jar Main
```

## No Java Class

# Load time with javassist and java.lang.ClassLoader 

## My Class

* Create a MyClassLoader class that extends `java.lang.ClassLoader`
    * Create `public MyClassLoader(ClassLoader parent) { super(parent); }`
    * @Override
        ```Java
        public Class<?> loadClass(String name) throws ClassNotFoundException {}
        public Class<?> findClass(String name) throws ClassNotFoundException {}
        ```
        It is important to remember to break delegation model in `loadClass()` method. You can do it with a simple if statement checking if a `name` does not start with `java.`.

### Command line

```java
javac -cp /usr/share/java/javassist.jar *.java
java -Djava.system.class.loader=MyClassLoader -cp .:/usr/share/java/javassist.jar Main
```

## No Java Class

# Useful class

```Java
public class Main {
    public static void main(String[] args) {
        A a = new A();
        a.a();
        StringBuilder sb = new StringBuilder();
        sb.append(new char[]{});
    }
}
```

```Java
public class A {
    public void a() {
        System.out.println("A::a");
    }
}
```