/**
	* a Predicate is a functor (Object acting as a function) 
	* that returns a boolean (true or false)
	*/
package org.yuzz.functor;
public class Predicate {
	public static abstract class Pred0 {
		public abstract boolean test();
	}
	public static abstract class Pred1<A> {
		public abstract boolean test(A arg);
	}
	public static abstract class Pred2<A,B> {
		public abstract boolean test(A arg, B arg2);
	}
	public static abstract class Pred3<A,B,C> {
		public abstract boolean test(A arg, B arg2, C arg3);
	}
}
