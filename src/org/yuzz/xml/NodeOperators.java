package org.yuzz.xml;
import static org.yuzz.xml.NodeStatics.n;

import org.yuzz.functor.TreeNode;
import org.yuzz.functor.Function.Fun1;
import org.yuzz.functor.Function.Fun2;
import org.yuzz.functor.Predicate.Pred1;
import org.yuzz.functor.Procedure.Proc2;

public class NodeOperators {

	public static final class AddNodes<Root extends Node, Child extends Node> extends Fun2<Root, Root, Child> {
		@Override
		public Root apply(Root root, Child child) {
			root.add(child);
			return root;
		}
	}
	public static final class AddNodesProc<Root extends Node, Child extends Node> extends Proc2<Root, Child> {
		@Override
		public void call(Root root, Child child) {
			root.add(child);
		}
	}

	public static class AttributeTest extends Pred1<TreeNode> {
		private final String _attributeName;
		private final String _value;
		public AttributeTest(String attribute, String value) {
			_attributeName = attribute;
			_value = value;
		}
		@Override
		public boolean test(TreeNode tn) {
			Node n = (Node)tn;
			return _value.equalsIgnoreCase(n.get(_attributeName));
		}
	}
	public static class TagNameTest extends Pred1<TreeNode> {
		private final String _tagName;
		public TagNameTest(String tagName) {
			_tagName = tagName;
		}
		@Override
		public boolean test(TreeNode tn) {
			Node n = (Node)tn;
			return _tagName.equalsIgnoreCase(n.getTag());
		}
	}
	public static class MkNode extends Fun1<Node,String> {
		@Override
		public Node apply(String tag) {
			return n(tag);
		}
	}

}
