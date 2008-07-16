package org.yuzz.functor;
public class IsEqual<T> extends Predicate.Pred1<T> {
	private final T _obj;
	public IsEqual(T obj) {
		_obj = obj;
	}
	public boolean test(T other) {
		//org.apache.log4j.Category.getInstance(getClass()).debug(_obj+" == "+other+"? "+other.equals(_obj));
		return other.equals(_obj);
	}
}
