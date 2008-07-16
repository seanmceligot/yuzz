package org.yuzz.xml;
import java.io.IOException;
import java.io.Writer;

import org.yuzz.xml.Node.Attribute;
import static org.yuzz.xml.NodeStatics.*;

public class Xhtml<ChildNode extends Node> extends Node<ChildNode> {
	
	public Xhtml(String tag, Attribute... atts) {
		super(tag, atts);
	}
	public Xhtml(String tag, Attribute[] atts, ChildNode... children) {
		super(tag, atts, children);
	}
	public Xhtml(String tag, ChildNode... children) {
		super(tag, children);
	}
	public Xhtml(String tag) {
		super(tag);
	}
	public final String getClassNames() {
		return super.get("class");
	}
	public final void setClassNames(String clas) {
		super.set("class", clas);
	}
	public final String getId() {
		return super.get("id");
	}
	public final void setId(String id) {
		super.set("id", id);
	}
	public final String getStyle() {
		return super.get("style");
	}
	public final void setStyle(String style) {
		super.set("style", style);
	}
	public final String getTitle() {
		return super.get("title");
	}
	public final void setTitle(String title) {
		super.set("title", title);
	}
	public void set(String attname, String value) {
		super.set(attname, value);
	}
	public String get(String attname) {
		return super.get(attname);
	}
	/*
	public Node findById(String id) {
		return TreeOperations.findFirst(this, new IsId(id));
	}*/
	public static abstract class TdOrTh extends Node<Node> {
		public TdOrTh(String tag, Node... ee) {
			super(tag, ee);
		}
		public TdOrTh(String tag, Attribute[] atts, Node... ee) {
			super(tag, atts, ee);
		}
	};
	public static abstract class HtmlChildren extends Node<Node> {
		public HtmlChildren(String tag, Node... ee) {
			super(tag, ee);
		}
		public HtmlChildren(String tag, Attribute[] atts, Node... ee) {
			super(tag, atts, ee);
		}
	};
	public static class Td extends TdOrTh {
		public Td(Node... ee) {
			super("td", ee);
		}
		public Td(Attribute[] atts, Node... ee) {
			super("td", atts, ee);
		}
	};
	public static class Th extends TdOrTh {
		public Th(Node... ee) {
			super("th", ee);
		}
		public Th(Attribute[] atts, Node... ee) {
			super("th", atts, ee);
		}
	};
	public static class Tr extends Xhtml<TdOrTh> {
		public Tr(Attribute[] atts, TdOrTh... ee) {
			super("tr", atts, ee);
		}
		
	}
	public static class Table extends Node<Tr> {
		public Table(Attribute[] atts, Tr... children) {
			super("table", atts, children);
		}
	
	}
	
		public static class Title extends Xhtml<Text> {
		protected Title(String title) {
				super("title", t(title));
			};
		}

		public static class H1 extends Xhtml<Text> {
		protected H1(Text... ee) {
				super("h1", ee);
			};
		}
		public static class H2 extends Xhtml<Text> {
		protected H2(String text) {
				super("h2", t(text));
			};
		}
		public static class H3 extends Xhtml<Text> {
		protected H3(String text) {
				super("h3", t(text));
			};
		}

		public static class P extends Xhtml<Node> {
		protected P(Node... ee) {
				super("p", ee);
			};
		}

		public static class Form extends Xhtml<Node> {
		protected Form(Node... ee) {
				super("form", ee);
			};
		}


		public static class Input extends Node {
		protected Input(Node... ee) {
				super("input", ee);
			};
		}
		public static class Document extends Node {
			public Document(Node... ee) {
				super("document", ee);
			}
		}
		@AllowedTags({"head","body"})
		public static class HtmlTag extends Xhtml<HtmlChildren> {
			public HtmlTag(Attribute[] atts, HtmlChildren... nn) {
				super("html", atts, nn);
			}
		}
		@AllowedTags({"title","script","meta","style", "link"})
		public static class Head extends HtmlChildren {
			public Head(Node... ee) {
				super("head", Node.Attribute.EMPTY_LIST, ee);
			}
			public Head(Attribute[] atts, Node... ee) {
				super("head", atts, ee);
			}
		};
		public static class Body extends HtmlChildren {
			public Body(Node... ee) {
				super("body", ee);
			}
			public Body(Attribute[] atts, Node... ee) {
				super("body", atts, ee);
			}
		};
		
		@AllowedTags({"html"})
		public static class DocType extends Node<HtmlTag> {
			private final String _doctype;
			private final String _dtd;
			
			public DocType(String doctype, String dtd) {
				super("DOCTYPE");
				_doctype = doctype;
				_dtd = dtd;
			}
			public DocType(String doctype, String dtd, HtmlTag html) {
				this(doctype, dtd);
				add(html);
			}
			@Override
			public void start(Writer out) throws IOException {
				out.write(_doctype);
				out.write('\n');
				out.write(_dtd);
				out.write('\n');
			}
			@Override
			public void end(Writer out) throws IOException {
				//super.end(out);
			}
		}
		public static class TextArea extends Node<UnencodedText> {
			public TextArea(int rows, int cols, String text, Attribute... atts) {
				super("textarea", atts, new UnencodedText[0]);
				disableEmptyTags();
				set("rows", Integer.toString(rows));
				set("cols", Integer.toString(cols));
				add(new UnencodedText(text));
			}
		}
		public static class HtmlId extends Node.Attribute {
			public HtmlId(String id) {
				super("id", id);
			}
		}
}
