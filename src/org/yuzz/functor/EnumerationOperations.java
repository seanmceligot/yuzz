package org.yuzz.functor;
import java.util.*;

public class EnumerationOperations {

	public static <T> T findFirst(Enumeration<T> en, Predicate.Pred1 test) {
		while(en.hasMoreElements()) {
			T obj = en.nextElement();
			if (test.test(obj)) {
				return obj;
			}
		}
		return null;
	}
	public static <T> List<T> collect(Enumeration<T> en, Predicate.Pred1<T>  test) {
		LinkedList<T> list = new LinkedList<T>();
		return collect(en, list, test);
	}
	public static <T> List<T> collect(Enumeration<T> en, List<T> list, Predicate.Pred1  test) {
		while(en.hasMoreElements()) {
			T obj = en.nextElement();
			if (test.test(obj)) {
				list.add(obj);
			}
		}
		return list;
	}
	public static void forEach(Enumeration en, Procedure.Proc0 procedure) {
		while(en.hasMoreElements()) {
			procedure.call();
		}
	}
	public static <T> void forEach(Enumeration<T> en, Procedure.Proc1<T> procedure) {
		while(en.hasMoreElements()) {
			T obj = en.nextElement();
			procedure.call(obj);
		}
	}
	public static <ET,PT> void forEach(Enumeration<ET> en, PT parameter, Procedure.Proc2<ET,PT> procedure) {
		while(en.hasMoreElements()) {
			ET obj = en.nextElement();
			procedure.call(obj, parameter);
		}
	}
}
