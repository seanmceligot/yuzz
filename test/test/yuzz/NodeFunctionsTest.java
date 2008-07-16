package test.yuzz;

import static org.yuzz.functor.Functions.map;
import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;

import java.util.Arrays;
import java.util.List;

import org.yuzz.functor.Function;
import org.yuzz.functor.Functions;
import org.yuzz.functor.Operators;
import org.yuzz.functor.Function.Fun2;
import org.yuzz.functor.Tuples.Tuple2;

import org.junit.Test;
import org.yuzz.xml.Node;
import org.yuzz.xml.NodeFunctions;
import org.yuzz.xml.Node.NodeList;


public class NodeFunctionsTest {
	public Node apply(Node n, Tuple2<String,String> t) {
		n.add(n("td", a("class",t._1()), t(t._2())));
		return n;
	}
	public List map2(Function.Fun1 f, List list) {
		return list;
	}
	@Test
	public void testTable() {
		List list = Arrays.asList(new String[]{"Office Name", "DSN", "Commercial", "Hours"});
		System.out.println("testTable: "+list);
		list = map(t("").mkText(), list);
		System.out.println("testTable: "+list);
		list = map(n("td").mkNode(), list);
		System.out.println("testTable: "+list);
		NodeList n = NodeFunctions.join(list);
		System.out.println("testTable: "+n.writeString());
		
	}

	@Test
	public void functorTest() {
		Fun2<Node, Node, Tuple2<String,String>> fun = new Fun2<Node, Node, Tuple2<String,String>>() {
			@Override
			public Node apply(Node n, Tuple2<String,String> t) {
				n.add(n("td", a("class",t._1()), t(t._2())));
				return n;
			}
		};
		String[] ss = {"Office Name", "DSN", "Commercial", "Hours"};
		List<Integer> sequence = Functions.sequence(4);
		System.out.println("sequence: "+sequence);
		
		List<String> list = Functions.map(Operators.mkToString(), sequence);
		list = Functions.map(Operators.mkConcat("col"), list);
		List<Tuple2<String, String>> cols = Functions.zip(Arrays.asList(ss), list);
		System.out.println("cols: "+cols);
		Node tr = NodeFunctions.reduce( fun, n("tr"), cols);
		System.out.println(n("table",tr).writeString());
			//return tableHeader(cols, fun, node);
	}
}
