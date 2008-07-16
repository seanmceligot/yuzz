package org.yuzz.functor;
import java.util.LinkedList;
import java.util.List;

public final class TreeOperations {
/**
		* search up the parent nodes until the test is true
		* The current node is tested
	public static TreeNode searchUp(TreeNode node, Predicate.Pred1 test) {
		if (test.test(node)) {
			return node;
		}
		TreeNode next = node.getParent();
		if(next != null) {
			return searchUp(next, test);
		}
		return null;
	}
		*/
	public static TreeNode findFirst(TreeNode node, Predicate.Pred1<TreeNode> test) {
		if (test.test(node)) {
			return node;
		}
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			TreeNode found = findFirst(next, test);
			if (found != null) {
				return found;
			}
		}
		return null;
	}
	/**
		* collect all nodes where test returns true to List<TreeNode>
		*/
	public static List<TreeNode> collect(TreeNode node, Predicate.Pred1<TreeNode> test) {
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		return collect(node, list, test);
	}
	/**
		* collect all nodes where test returns true to List<TreeNode>
		*/
	public static List<TreeNode> collect(TreeNode node, List<TreeNode> list, Predicate.Pred1<TreeNode> test) {
		if (test.test(node)) {
			list.add(node);
		}
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			collect(next, list, test);
		}
		return list;
	}
	public static void forEach(TreeNode node, Procedure.Proc0 procedure) {
		procedure.call();
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			forEach(next, procedure);
		}
	}
	public static void forEach(TreeNode node, Procedure.Proc1 <TreeNode> procedure) {
		procedure.call(node);
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			forEach(next, procedure);
		}
	}
	public static <T> void forEach (TreeNode node, T parameter, Procedure.Proc2<TreeNode,T> procedure) {
		procedure.call(node, parameter);
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			forEach(next, parameter, procedure);
		}
	}
	public static void forEach(TreeNode node, Procedure.Proc2<TreeNode,Integer> fn) {
		forEach(node, fn, 0);
	}
	private static void forEach(TreeNode node, Procedure.Proc2<TreeNode,Integer> fn, int depth) {
		fn.call(node, depth);
		depth++;
		List<TreeNode> it = node.getChildren();
		for (TreeNode next : it) {
			forEach(next, fn, depth);
		}
	}
}
