public class MyStringBuilder {
    
    public StringBuilder append(char[] str) throws NullPointerException {
        System.out.print("Sono il figlio, ma non dovrei esserlo");
        //return super.append(str);
        throw new NullPointerException();
    }

    public void newMethod() {System.out.println("new method");}
}
