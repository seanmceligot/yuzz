package org.yuzz.functor;
import java.util.List;

public interface TreeNode <T extends TreeNode>  {
	public List<T> getChildren();
	//public TreeNode getParent();
}
