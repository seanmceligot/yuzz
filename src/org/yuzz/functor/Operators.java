package org.yuzz.functor;
import org.yuzz.functor.Function.Fun1;
import org.yuzz.functor.Function.Fun2;
import org.yuzz.functor.Tuples.Tuple2;

public class Operators {

	public static class Concat extends Fun2<String,String,String> {
		@Override
		public String apply(String a, String b) {
			return a+b;
		}
	}
	public static Fun2 mkConcat() {
		return new Concat();
	}

	public static Fun1 mkConcat(String bind) {
		return new Concat().bind(bind);
	}

	
	public static class Join extends Fun2<String,String,String> {
		private final String _delim;
		public Join(String delim) {
			_delim = delim;
		}
		public String apply(String a, String b) {
			return a+_delim+b;
		}
	}

	public class MakeTuple <A,B>  extends Fun2<Tuple2<A,B>, A,B>{
		@Override
		public Tuple2<A, B> apply(A a, B b) {
			// TODO Auto-generated method stub
			return new Tuple2<A,B>(a,b);
		}
	}

	public static class SurroundWith extends Fun1<String,String> {
		private final String _pre;
		private final String _post;
		public SurroundWith(String pre, String post) {
			_pre = pre;
			_post = post;
		}
		public String apply(String middle) {
			return _pre+middle+_post;
		}
	}

	public static class ToString<T> extends Fun1<String, T> {
		@Override
		public String apply(T a) {
			return a.toString();
		}
	}

	public static Join mkJoin(String delim) {
		return new Join(delim);
	}

	public static SurroundWith mkSandwitch(String pre, String post) {
		return new SurroundWith(pre,post);
	}

	public static ToString mkToString() {
		return new ToString();
	}

}
