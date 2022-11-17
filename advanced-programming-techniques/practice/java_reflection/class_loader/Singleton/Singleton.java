public class Singleton {
   static private boolean runOnce = false;
   public Singleton() {
     if (runOnce) 
       throw new IllegalStateException("[ERROR] re-instantiation of «Singleton»!!!");
     runOnce = true;
   }
}
