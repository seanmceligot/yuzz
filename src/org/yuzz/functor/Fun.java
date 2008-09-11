/**
 * a Function is a functor (Object acting as a function) 
 * that returns a value
 */
package org.yuzz.functor;
public class Fun {
	public abstract static class Fun0<R> {
		public abstract R f();
	}

	public abstract static class F<A,R> {
		public abstract R f(A a);
	}
	
	public abstract static class F2<A,B,R> {
		public F<B,R> bind(final A a) {
			final F2<A,B,R> F2 = this;
			return new F<B,R>() {
				@Override
				public R f(B b) {
					return F2.f(a, b);
				}
			};
		}
		public abstract R f(A a, B b);
	}
	public abstract static class F3<A,B,C,R> {
		public abstract R f(A a, B b, C c);
	}
}
