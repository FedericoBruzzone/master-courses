public aspect ColorControl {

  pointcut CCClientCflow(ColorControllingClient client):
    cflow(call(* *(..)) && target(client));

  pointcut make(): call(FigureElement Figure.make*(..));

  after (ColorControllingClient c) returning (FigureElement fe): make() && CCClientCflow(c) {
    fe.setColor(c.colotFor(fe));
  }

}
