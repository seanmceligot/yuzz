package org.yuzz.functor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.yuzz.functor.Procedure.Proc1;
import org.yuzz.functor.Tuples.Tuple2;



public class Functions {
	public static <A,B> List<Tuple2<A,B>> zip(List<A> list1, List<B> list2) {
		ArrayList<Tuple2<A,B>> zip = new ArrayList<Tuple2<A,B>>(list1.size());
		int min = Math.min(list1.size(), list2.size());
		for(int i = 0; i < min; i++) {
			zip.add(new Tuple2<A,B>(list1.get(i), list2.get(i)));
		}
		return zip;
	}
	public static List<String> sequenceString(int start, int end) {
		return map(new Operators.ToString<Integer>(), sequence(start, end));
	}
	public static List<Integer> sequence(int end) {
		return sequence(0, end-1);
	}
	public static List<Integer> sequence(int start, int end) {
		assert( !(start>end) );
		List<Integer> sa = new ArrayList<Integer>(end-start);
		int current = start;
		while(current <= end) {
			sa.add(current);
			current = current+1;
		}
		return sa;
	}
	public static <T> List<T> tail(List<T> l) {
		return l.subList(1, l.size());
	}
	public static <T> T head(List<T> l) {
		return l.get(0);
	}
	public static <T> T head(T[] a) {
		return a[0];
	}
	public static <A,R> List<R> map(Fun.F<A,R> fun, A... list) {
    return map(fun, Arrays.asList(list), new LinkedList<R>());
  }
	public static <A,R> List<R> map(Fun.F<A,R> fun, List<A> list) {
		return map(fun, list, new LinkedList<R>());
	}
	/**
	 * @see http://en.wikipedia.org/wiki/Map_%28higher-order_function%29
	 * @see http://en.wikipedia.org/wiki/Tail_recursion
	 */ 
	public static <A,R> List<R> map(Fun.F<A,R> fun, List<A> list, List<R> out) {
		if (list.size() == 0) {
			return out;
		}
		out.add(fun.f(head(list)));
		return map(fun, tail(list), out);
	}
  public static <A,R> List<R> map(Fun.F<A,R> fun, Iterator<A> it, List<R> out) {
    if (!it.hasNext()) {
      return out;
    }
    out.add(fun.f(it.next()));
    return map(fun, it, out);
  }
		/**
		 * foldr
	 * http://en.wikipedia.org/wiki/Fold_(higher-order_function)
	 * @param fun
	 * @param item
	 * @param list
	 * @param <A>
	 * @return
	 */
	public static <A,B> B foldr(
	    Fun.F2<A, B, B> fun, // function taking an A, and a B, and returning a B 
	    B item, // initial value
	    List<A> list // the list
	    ) { 
		if (list.size() == 0) {
			return item;
		}
		return foldr(
		    fun, 
		    fun.f( Functions.head(list), item),
		    Functions.tail(list)
		    );
	}
	public static <A,B> A reduce(
	    Fun.F2<A, B, A> fun, // function taking an A, and a B, and returning a B 
	    A item, // initial value
	    List<B> list // the list
	    ) { 
      return foldl(fun, item, list);
      
  }
	/**
   * foldl
 * http://en.wikipedia.org/wiki/Fold_(higher-order_function)
 * @param fun
 * @param item
 * @param list
 * @param <A>
 * @return
 */
public static <A,B> A foldl(
    Fun.F2<A, B, A> fun, // function taking an A, and a B, and returning a B 
    A item, // initial value
    List<B> list // the list
    ) { 
    if (list.size() == 0) {
      return item;
    }
    return foldl(
      fun, 
      fun.f( item, Functions.head(list) ),
      Functions.tail(list)
      );
}
  public static <A,B> B reduce(
      Fun.F2<B, A, B> fun, // function taking an A, and a B, and returning a B 
      B item, // initial value
      Iterator<A> it // the list
      ) {
    
    if (!it.hasNext()) {
      return item;
    }
    return reduce(
        fun, 
        fun.f( item, it.next()),
        it);
  }
  public static <A, B> B reduce(Fun.F2<B,A, B> fun, B item, A... is) {
    return foldl(fun, item, Arrays.asList(is));
  }
	/*public static <A> A reduce(Fun.F2<A, A, A> fun, A... is) {
	  return reduce(fun, Arrays.asList(is));
	}*/
	/**
	 * foldr1
	 * @param <A>
	 * @param fun
	 * @param list
	 * @return
	 */
	public static <A> A reduce(Fun.F2<A, A, A> fun, List<A> list) {
		return reduce(fun, head(list), tail(list));
		
	}
	public static <T> void each(Proc1<T> proc, List<T> list) {
		for (T item : list) {
			proc.f(item);
		}
		
	}
	public static <T> Tuple2<List<T>, List<T>> partition(Iterable<T> ts, Fun.F<T, Boolean> f) {
	  List<T> is = new LinkedList<T>();
	  List<T> isnot = new LinkedList<T>();
	  for (T t : ts) {
	    if (f.f(t)) {
	      is.add(t);
	    } else {
	      isnot.add(t);
	    }
    }
	  return new Tuple2<List<T>, List<T>> (is, isnot);
	  
	}
	/*
	// http://en.wikipedia.org/wiki/Map_%28higher-order_function%29
	public static <A,R> List<R> map(F<R,A> fun, List<A> list) {
		List<R> mapped = new ArrayList<R>(list.size());
		for (A item : list) {
			mapped.add(fun.f(item));
		}
		return mapped;
	}*/
}
