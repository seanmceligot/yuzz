/**
	* a Procedure is a functor (Object acting as a function) 
	* that does not return a value
	*/
package org.yuzz.functor;

public class Procedure {
	public static abstract class Proc0 {
		public abstract void f();
	}
	public static abstract class Proc1<A> {
		public abstract void f(A arg);
	}
	public static abstract class Proc2<A,B> {
		public abstract void f(A arg, B arg2);
	}
	public static abstract class Proc3<A,B,C> {
		public abstract void f(A arg, B arg2, C arg3);
	}
}
