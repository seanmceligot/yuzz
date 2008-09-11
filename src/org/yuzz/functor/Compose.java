/**
 * 
 */
package org.yuzz.functor;


public class Compose<A,B,C> extends Fun.F< A, C> {
  private final Fun.F<B, C> _f1;
  private final Fun.F<A,B> _f2;
  public Compose(
    Fun.F<B, C> f1, 
    Fun.F<A,B> f2
    ) {
    _f1 = f1;
    _f2 = f2;
  }
  public C f(A a) {
    B b = _f2.f(a);
    C c = _f1.f(b);
    return c;
  }
}