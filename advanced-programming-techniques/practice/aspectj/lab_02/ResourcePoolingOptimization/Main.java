public class Main {

  public static void main(String[] args) {
    RA ra1 = new RA(); RA ra2 = new RA();
    RB rb1 = new RB(); RB rb2 = new RB();
    RC rc1 = new RC(); RC rc2 = new RC();

    ra1.destroy();
    RB rb3 = new RB();

    rb2.destroy();
    rb2 = new RB();
    rc2.destroy();
    rb2.destroy();
    rb1.destroy();
    RA ra3 = new RA();
    rb1 = new RB();
  }
}
