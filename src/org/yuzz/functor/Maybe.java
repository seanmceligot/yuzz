package org.yuzz.functor;

public abstract class Maybe<T> {
  public abstract boolean isJust();
  public abstract boolean isNoting();
  public static class Nothing<T> extends Maybe<T> {

    @Override
    public boolean isJust() {
      return false;
    }

    @Override
    public boolean isNoting() {
      return true;
    }
  }
  public static class Just<T> extends Maybe<T> {
    private final T _t;

    public Just(T t) {
      _t = t;
    }

    @Override
    public boolean isJust() {
      return true;
    }

    @Override
    public boolean isNoting() {
      return false;
    }
    public T fromJust() {
      return _t;
    }
  }
  
}
