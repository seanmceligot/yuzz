package test.functor;

import static org.junit.Assert.fail;
import static org.yuzz.functor.Functions.map;
import static org.yuzz.functor.Functions.reduce;
import static org.yuzz.functor.Functions.sequence;
import static org.yuzz.functor.Functions.zip;

import java.util.Arrays;
import java.util.List;



import org.junit.Test;
import org.yuzz.functor.Functions;
import org.yuzz.functor.Operators;
import org.yuzz.functor.Function.Fun1;
import org.yuzz.functor.Operators.Concat;

public class FunctionsTest {

	private final List<Integer> zeroNine = sequence(10);
	private final List<String> stringList = Arrays.asList(new String[]{"one", "two", "three"});

	@Test
	public void testZip() {
		List a = Arrays.asList(new String[] {"a","b","c"});
		List b = Arrays.asList(new String[] {"1","2","3"});
		List c = zip(a, b);
		System.out.println(c);
		
	}
	public static Fun1 concat(String prefix) {
		return new Concat().bind(prefix);
	}
	public static String concat(String...ss) {
		return reduce(new Concat(), Arrays.asList(ss));
	}
	@Test
	public void testConcat() {
		String a = concat("one", "two", "three");
		System.out.println("concat: "+a);
	}
	@Test
	public void testJoin() {
		String a = Functions.reduce(Operators.mkJoin(":"), stringList);
		System.out.println("join: "+a);
	}	
	@Test
	public void testSandwich() {
		List<String> list = map(Operators.mkSandwitch("<td>","</td") , stringList);
		System.out.println("SurroundWith: "+list);
	}
	@Test
	public void testSequence() {
		System.out.println("sequence: "+zeroNine);
		
	}

	@Test
	public void testTail() {
		System.out.println("tail: "+zeroNine);
	}

	@Test
	public void testHeadTArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testHeadListOfT() {
		Integer integer = Functions.head(zeroNine);
		System.out.println("head: "+integer);
	}

	@Test
	public void testMap() {
		List<Integer> tens = Functions.map(new Fun1<Integer,Integer>() {
			public Integer apply(Integer a) {
				return a.intValue()*10;
			}
		}, Functions.sequence(1,3));
		System.out.println(tens);
	}

	@Test
	public void testFold() {
		fail("Not yet implemented");
	}

}
