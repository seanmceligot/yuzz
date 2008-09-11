package org.yuzz.functor;
import org.yuzz.functor.Fun.F;
import org.yuzz.functor.Fun.F2;
import org.yuzz.functor.Tuples.Tuple2;

public class Operators {

	public static class Concat extends F2<String,String,String> {
		@Override
		public String f(String a, String b) {
			return a+b;
		}
	}
	public static F2<String,String,String> mkConcat() {
		return new Concat();
	}

	public static F<String,String> mkConcat(String bind) {
		return new Concat().bind(bind);
	}
	
	public static class Join extends F2<String,String,String> {
		private final String _delim;
		public Join(String delim) {
			_delim = delim;
		}
		public String f(String a, String b) {
		  if (b == null) {
		    b = "";
		  }
			return a+_delim+b;
		}
	}

	public static class MakeTuple <A,B>  extends F2<A,B, Tuple2<A,B>>{
		@Override
		public Tuple2<A, B> f(A a, B b) {
			return new Tuple2<A,B>(a,b);
		}
	}

	public static class SurroundWith extends F<String,String> {
		private final String _pre;
		private final String _post;
		public SurroundWith(String pre, String post) {
			_pre = pre;
			_post = post;
		}
		public String f(String middle) {
			return _pre+middle+_post;
		}
	}

	public static class ToString<T> extends F<T, String> {
		@Override
		public String f(T a) {
			return a.toString();
		}
	}
}
