/**
 * a Function is a functor (Object acting as a function) 
 * that returns a value
 */
package org.yuzz.functor;
public class Function {
	public abstract static class Fun0<R> {
		public abstract R apply();
	}

	public abstract static class Fun1<R,A> {
		public abstract R apply(A a);
	}
	
	public abstract static class Fun2<R,A,B> {
		public Fun1 bind(final A a) {
			final Fun2 fun2 = this;
			return new Fun1() {
				@Override
				public Object apply(Object b) {
					return fun2.apply(a, b);
				}
			};
		}
		public abstract R apply(A a, B b);
	}
	public abstract static class Fun3<R,A,B,C> {
		public abstract R apply(A a, B b, C c);
	}
}
