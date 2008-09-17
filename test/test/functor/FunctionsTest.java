package test.functor;

import static org.yuzz.functor.Functions.sequence;
import static org.yuzz.functor.Functions.zip;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.yuzz.functor.Functions;
import org.yuzz.functor.Operators;
import org.yuzz.functor.Operators.Concat;
import org.yuzz.functor.Operators.Join;
import org.yuzz.functor.Tuples.Tuple2;

import fj.F;
import fj.Function;

public class FunctionsTest {

	private static final String[] ONETWOTHREE = new String[]{"one", "two", "three"};
  private final List<Integer> zeroNine = sequence(10);
	private final List<String> stringList = Arrays.asList(ONETWOTHREE);

	@Test
	public void testZip() {
		List<String> a = Arrays.asList(new String[] {"a","b","c"});
		List<String> b = Arrays.asList(new String[] {"1","2","3"});
		List<Tuple2<String,String>> c = zip(a, b);
		Assert.assertEquals("[(a,1), (b,2), (c,3)]", c.toString());
	}
	public static F<String,String> concat(String prefix) {
		return Function.curry(new Concat(), prefix);
	}
	public static String concat(String...ss) {
		return Functions.foldl(new Concat(), "", Arrays.asList(ss));
	}

  @Test
  public void testFoldl() {
    {
      List<String> ss = Arrays.asList(ONETWOTHREE);
      String foldl = Functions.foldl(new Join(":"), "zero", ss);
      Assert.assertEquals("zero:one:two:three", foldl);
    }
    {
      List<String> ss = Arrays.asList(ONETWOTHREE);
      String foldl = Functions.reduce(new Join(":"), "zero", ss.iterator());
      Assert.assertEquals("zero:one:two:three", foldl);
    }
    {
      List<String> ss = Arrays.asList(ONETWOTHREE);
      String foldl = Functions.reduce(new Join(":"), ss);
      Assert.assertEquals("one:two:three", foldl);
    }
  }
	@Test
	public void testConcat() {
		String a = concat("one", "two", "three");
		Assert.assertEquals("onetwothree", a);
	}
	@Test
	public void testJoin() {
		String a = Functions.reduce(new Operators.Join(":"), stringList);
		Assert.assertEquals("one:two:three", a);
	}	
	@Test
	public void testSandwich() {
		//List<String> list = map(new Operators.SurroundWith("<td>","</td") , stringList);
		//Assert.assertEquals("[<td>one</td, <td>two</td, <td>three</td]", list.toString());
	}
	@Test
	public void testSequence() {
	  Assert.assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", zeroNine.toString());
	}

	@Test
	public void testTail() {
	  List<Integer> tail = Functions.tail(zeroNine);
    Assert.assertEquals("[1, 2, 3, 4, 5, 6, 7, 8, 9]", tail.toString());
	}

	@Test
	public void testHeadListOfT() {
		Integer integer = Functions.head(zeroNine);
    Assert.assertEquals(new Integer(0), integer);
	}
	@Test
	public void testMap() {
		List<Integer> tens = Functions.map(new F<Integer,Integer>() {
			public Integer f(Integer a) {
				return a.intValue()*10;
			}
		}, Functions.sequence(1,3));
		Assert.assertEquals("[10, 20, 30]", tens.toString());
		
	}

}
