/**
 * 
 */
package org.yuzz.xml;

import org.yuzz.functor.Predicate;
import org.yuzz.functor.TreeNode;
import org.yuzz.functor.Predicate.Pred1;


public class IsType extends Predicate.Pred1<TreeNode> {
	private final String _tag;
	public IsType(String tag) {
		_tag = tag;
	}
	public boolean test(TreeNode parameter) {
		return _tag.equals( ((Node)parameter).getTag());
	}
}