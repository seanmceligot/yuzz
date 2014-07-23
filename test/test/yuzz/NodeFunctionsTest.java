package test.yuzz;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;
import static org.yuzz.xml.NodeStatics.table;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.yuzz.functor.Functions;
import org.yuzz.functor.Operators;
import org.yuzz.functor.Fun.F;
import org.yuzz.functor.Fun.F2;
import org.yuzz.functor.Tuples.Tuple2;
import org.yuzz.tools.Render;
import org.yuzz.xml.Node;
import org.yuzz.xml.NodeOperators;
import org.yuzz.xml.Xhtml.Table;


public class NodeFunctionsTest {

  public Node<Node> f(Node<Node> n, Tuple2<String,String> t) {
		n.add(n("td", a("class",t._1()), t(t._2())));
		return n;
	}
	@Test
	public void testTable() throws IOException {
		String[][] strings = new String[][]{
		    {"A", "B", "C", "D"},
		    {"1","2", "3","4"},
		    {"5","6", "7","8"},
		    };
    F2<Table, String[], Table> fun = new NodeOperators.StringGridToTable();
    Table table = Functions.reduce(fun, table(), strings);
    Render.browse("firefox", table);
    System.out.println(table.writeString());
	}

	@Test
	public void functorTest() {
		String[] ss = {"Office Name", "DSN", "Commercial", "Hours"};
		List<Integer> sequence = Functions.sequence(4);
		System.out.println("sequence: "+sequence);
		
		List<String> list = Functions.map(new Operators.ToString<Integer>(), sequence);
		list = Functions.map(Operators.mkConcat("col"), list);
		List<Tuple2<String, String>> cols = Functions.zip(Arrays.asList(ss), list);
		System.out.println("cols: "+cols);
		//Node tr = Functions.reduce( fun, n("tr"), cols);
		//System.out.println(n("table",tr).writeString());
			//return tableHeader(cols, fun, node);
	}
}
