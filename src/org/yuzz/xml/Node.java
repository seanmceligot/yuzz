package org.yuzz.xml;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;
import org.yuzz.functor.TreeNode;
import org.yuzz.functor.Fun.F;
/*
public class Node extends Node<Node> {
	
}
*/
public class Node<ChildNode extends Node> extends F<ChildNode, Node> implements TreeNode<ChildNode> {
	public static final Node[] EMPTY_LIST = new Node[0];
	 
	protected final LinkedList<ChildNode> _childElements;
	protected final TreeMap<String,String> _attributes;
	private boolean _allowEmptyTags = true;
	protected final String _tag;
	
	public Node (String tag) {
		_tag = tag;
		_childElements = new LinkedList<ChildNode>();
		_attributes = new TreeMap<String,String>();
	}
	public Node (String tag, Attribute[] atts, ChildNode... children) {
		this(tag);
		addAttributes(atts);
		add(children);
	}
	public Node (String tag, ChildNode... children) {
		this(tag);
		add(children);
	}
	public Node (String tag, Attribute... atts) {
		this(tag);
		addAttributes(atts);
	}
	public Node disableEmptyTags() {
		_allowEmptyTags = false;
		return this;
	}
	/**
	 * checkes for allowed tags using annotations
	 * @param node
	 */
	public void check(ChildNode node) {
		Class<? extends Object> myclass = getClass();
		AllowedTags rq = myclass.getAnnotation(AllowedTags.class);
		if (rq !=null) {
			String[] allowed = rq.value();
			for (String tag : allowed) {
				if (tag.equalsIgnoreCase(node._tag)) {
					return;
				}
			}
			throw new NodeValidationException(node+" NOT ALLOWED ON "+getClass().getName()+" allowes: "+Arrays.asList(allowed));
		}
	}

	public LinkedList<ChildNode> toLinkedList(ChildNode... children) {
		LinkedList<ChildNode> list = new LinkedList<ChildNode>();
		if (children != null) {
			for (ChildNode node : children) {
				check(node);
				list.add(node);
			}
		}
		return list;
	}
	public TreeMap<String,String> toTreeMap(Attribute... atts) {
		TreeMap<String,String> map = new TreeMap<String, String>();
		if (atts != null) {
			for (Attribute attribute : atts) {
				map.put(attribute.getKey(), attribute.getVal());
			}
		}
		return map;
	}
	public void addAttributes(Attribute ... atts) {
		for (Attribute attribute : atts) {
			add(attribute);
		}
	}

	public void set(String attname, String value) {
		_attributes.put(attname, value);
	}
	public String get(String attname) {
		return _attributes.get(attname);
	}
	public Node add(final ChildNode cn) {
		if (cn instanceof Attribute) {
			Attribute attribute = (Attribute)cn;
			_attributes.put(attribute.getKey(), attribute.getVal());
		} else {
			check(cn);
			_childElements.add((ChildNode)cn);
		}
		
		return this;
	}	
	public Node add(final Attribute at) {
			_attributes.put(at.getKey(), at.getVal());
			return this;
	}	
	public void add(final ChildNode... ee) {
		for (ChildNode node : ee) {
			add(node);
		}
	}
	
	public String getTag() {
		return _tag;
	}
	public String toString() {
		return "Node("+_tag+")";
	}
	public String writeString()  {
		StringWriter writer = new StringWriter();
		try {
			write(writer);
		} catch (IOException e) {	}
		return writer.toString();
	}

	public void start(Writer out) throws IOException {
		if (_tag != null) {
			out.write("<" + _tag);
			Set<Entry<String, String>> aset = _attributes.entrySet();
			for (Entry<String, String> entry : aset) {
				out.write(" "+entry.getKey()+"=\""+entry.getValue()+"\"");
			}
			if ( (_allowEmptyTags) && (_childElements.isEmpty()) ) {
				out.write("/>");
			} else {
				out.write(">");
			}
		}
	}
	public List<ChildNode> getChildren() {
		return _childElements;
	}

	public void end(Writer out) throws IOException {
		if (_tag != null) {
			if ( (_allowEmptyTags) && (_childElements.isEmpty()) ) {
			} else {
				endTag(out);
			}
			out.write('\n');
		}
	}
	public void endTag(Writer out) throws IOException {
		out.write("</" + _tag + ">");
	}

	public void write(Writer out) throws IOException {
		start(out);
		if (_childElements != null) {
			for (ChildNode el : _childElements) {
				el.write(out);
			}
		}
		end(out);
	}

	public void write(PrintStream out) throws IOException {
		PrintWriter writer = new PrintWriter(out);
		write(writer);
		writer.flush();
	}

	public static final class NoChildren extends Node<Node> {
	
		public static final NoChildren[] EMPTY_LIST = new NoChildren[0];
		@SuppressWarnings("unused")
		private NoChildren() {
			super("", Node.Attribute.EMPTY_LIST, EMPTY_LIST);
		}
	};
	public static class Attribute extends Node<NoChildren> {
		public static final Attribute[] EMPTY_LIST = new Attribute[0];
		private String key;
		private String val;
		
		public Attribute(String k, String v) {
			super(k,EMPTY_LIST, NoChildren.EMPTY_LIST);
			key=k;
			val=v;
		}
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
	}
	public static class Text extends Node<NoChildren> {
		private final String _text;
		@Override
		public void write(Writer out) throws IOException {
			out.write(_text);
		}

		protected Text(String text) {
			super("", Attribute.EMPTY_LIST, NoChildren.EMPTY_LIST);
			_text = text==null?"":StringEscapeUtils.escapeXml(text);
		}
		/**
		 * Takes a String and returns a Text
		 * @return
		 */
		public static F<String, Text> mkText() {
			return new F<String,Text>() {
				public Text f(String text) {
					return new Text(text);
				}
			};
		};
	}
	public static class UnencodedText extends Node<NoChildren> {
		private final String _text;
		@Override
		public void write(Writer out) throws IOException {
			out.write(_text);
		}

		protected UnencodedText(String text) {
			super("", Attribute.EMPTY_LIST, NoChildren.EMPTY_LIST);
			_text =text;
		}
	}
	public static class ScriptSrc extends Node<UnencodedText> {
		protected ScriptSrc(String src) {
			super("script", Node.Attribute.EMPTY_LIST, new UnencodedText[0]);
			disableEmptyTags();
			set("src", src);
		}
	}
	public static class Script extends Node<UnencodedText> {
		protected Script(String script) {
			super("script", Node.Attribute.EMPTY_LIST, new UnencodedText[0]);
			add(new UnencodedText(script));
			set("type", "text/javascript");
		}
	}
	public static class CData extends Node<NoChildren> {
		@Override
		public void write(Writer out) throws IOException {
			out.write(_tag);
		}
		protected CData(String text) {
			super("<![CDATA["+text+"]]>");
		}
	}
	public static class NodeList extends Node<Node> {
		@Override
    public void start(Writer out) throws IOException {}
    public void end(Writer out) throws IOException {}
		protected NodeList(final Node... nodes) {
			super("", nodes);
		}
	}
	public boolean addAll(Collection<? extends ChildNode> arg0) {
		for (ChildNode cn : arg0) {
			add(cn);
		}
		return false;
	}
	public boolean remove(Object arg0) {
		return _childElements.remove(arg0);
	}
	@Override
	public Node f(ChildNode a) {
		add(a);
		return this;
	}
	public F<Node,Node> mkNode() {
		return new F<Node,Node>() {
			public Node f(Node a) {
				return new Node(_tag, new Node[]{a});
			}
		};
	};
	public F<Node[],Node> mkNodeA() {
		return new F<Node[],Node>() {
			public Node f(Node[] a) {
				return new Node(_tag, a);
			}
		};
	}
	public static Node readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		String tag = (String)in.readObject();
		boolean allowEmptyTags = in.readBoolean();
		Attribute[] atts;
		{
			int c = in.readInt();
			atts = new Attribute[c];
			for(int i = 0; i < c; i++ ) {
				String key = (String)in.readObject();
				String value = (String)in.readObject();
				atts[i] = new Attribute(key, value);
			}
		}
		
		Node n;
		//if (tag.length() > 0) {
			n = new Node(tag, atts);
		//} else {
		//	n = new Text()
		//}
		n._allowEmptyTags = allowEmptyTags; 
		{
			int c = in.readInt();
			for (int i = 0; i < c; i++) {
				Node child = Node.readExternal(in);
				n.add(child);
			}
		}
		return n;
	}
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(_tag);
		out.writeBoolean(_allowEmptyTags);
		out.writeInt(_attributes.size());
		for (Entry<String, String> at : _attributes.entrySet()) {
			out.writeObject(at.getKey());
			out.writeObject(at.getValue());
		}
		out.writeInt(_childElements.size());
		for (ChildNode child : _childElements) {
			child.writeExternal(out);
		}
	};
	
}
