package org.yuzz.xml;
import static org.yuzz.xml.NodeStatics.nlist;

import java.util.List;

import org.yuzz.functor.Functions;
import org.yuzz.functor.Procedure;
import org.yuzz.functor.TreeOperations;
import org.yuzz.functor.Fun.F2;
import org.yuzz.xml.Node.NodeList;

public class NodeFunctions {
	public static Node findById(Node node, String id) {
		return (Node)TreeOperations.findFirst(node, new NodeOperators.AttributeTest("id", id));
	}
	public static Node findByTagName(Node node, String tagName) {
		return (Node)TreeOperations.findFirst(node, new NodeOperators.TagNameTest(tagName));
	}
	
	public static NodeList join(List<Node> list) {
		final NodeList n = nlist();
		Functions.each(new Procedure.Proc1<Node>() {
			public void f(Node arg) {
				n.add(arg);
			}
		}, list);
		return n;
	}
}
