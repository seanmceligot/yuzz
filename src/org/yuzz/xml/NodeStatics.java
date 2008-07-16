package org.yuzz.xml;
import org.yuzz.xml.Node.Attribute;
import org.yuzz.xml.Xhtml.DocType;
import org.yuzz.xml.Xhtml.Head;
import org.yuzz.xml.Xhtml.HtmlChildren;
import org.yuzz.xml.Xhtml.HtmlTag;
import org.yuzz.xml.Xhtml.TdOrTh;
import org.yuzz.xml.Xhtml.TextArea;
import org.yuzz.xml.Xhtml.Tr;

/**
 * 
 * @author MceligSP
 * @see test.common.NodeTest
 */
public class NodeStatics {

	public static Node n(final String tag, Node.Attribute att) {
		return new Node(tag, alist(att),  Node.EMPTY_LIST);
	}
	public static Node n(final String tag, final Node... ee) {
		return new Node(tag, ee);
	}
	public static Node n(final String tag, Node.Attribute[] atts, final Node... ee) {
		return new Node(tag, atts, ee);
	}
	public static Node n(final String tag, Node.Attribute[] atts) {
		return new Node(tag, atts, Node.EMPTY_LIST);
	}

	public static Node.Attribute a(String k, String v) {
		return new Node.Attribute(k,v);
	}

	public static Node.Text t(String t) {
		return new Node.Text(t);
	}

	public static Node.CData cdata(String t) {
		return new Node.CData(t);
	}

	public static Node.NodeList nlist(Node... nodes) {
		return new Node.NodeList(nodes);
	}
	public static Node.ScriptSrc scriptsrc(String src) {
		return new Node.ScriptSrc(src);
	}
	public static Node.Script javascript(String script) {
		return new Node.Script(script); 
	}
	public static Node.Attribute[] alist(Node.Attribute... atts) {
		return atts;
	}
	public static Xhtml.Table table(Tr... children) {
		return new Xhtml.Table(Node.Attribute.EMPTY_LIST, children);
	}
	public static Xhtml.Table table(Node.Attribute[] atts, Tr... children) {
		return new Xhtml.Table(atts, children);
	}
	public static Xhtml.Table table(Node.Attribute att, Tr... children) {
		return new Xhtml.Table(alist(att), children);
	}
	public static Xhtml.Tr tr(TdOrTh... children) {
		return new Xhtml.Tr(Node.Attribute.EMPTY_LIST, children);
	}
	public static Xhtml.Td td(Node... children) {
		return new Xhtml.Td(children);
	}
	public static Xhtml.Th th(Node... children) {
		return new Xhtml.Th(children);
	}
	public static Xhtml.Tr tr(Node.Attribute[] atts,TdOrTh... children) {
		return new Xhtml.Tr(atts, children);
	}
	public static Xhtml.Td td(Node.Attribute[] atts,Node... children) {
		return new Xhtml.Td(atts, children);
	}
	public static Xhtml.Th th(Node.Attribute[] atts,Node... children) {
		return new Xhtml.Th(atts, children);
	}
	public static Xhtml.Tr tr(Node.Attribute att, TdOrTh... children) {
		return new Xhtml.Tr(alist(att), children);
	}
	public static Node div(Node.Attribute att) {
		return new Node("div", alist(att),  Node.EMPTY_LIST).disableEmptyTags();
	}
	public static Node div(final Node... ee) {
		return new Node("div", ee).disableEmptyTags();
	}
	public static Node div(Node.Attribute[] atts, final Node... ee) {
		return new Node("div", atts, ee).disableEmptyTags();
	}
	public static Node div(Node.Attribute[] atts) {
		return new Node("div", atts, Node.EMPTY_LIST).disableEmptyTags();
	}
	public static Node div() {
		return new Node("div", Attribute.EMPTY_LIST, Node.EMPTY_LIST).disableEmptyTags();
	}
	/**
	 * @return XHTML 1.1 Strict DocType
	 */
	public static DocType xhtml11Strict() {
		DocType dt = new DocType(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"",
				"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
		return dt;
	}
	/**
	 * 
	 * @param child nodes
	 * @return <html> with namespace 1999 xhtml for XHTML 1.1 Strict 
	 */
	public static HtmlTag htmlXhtml1999(HtmlChildren... nn) {
		return new HtmlTag( alist(a("xmlns", "http://www.w3.org/1999/xhtml")) , nn) ;
	}
	public static Head head(Node... nn) {
		return new Head(nn);
	}
	public static Node title(String text) {
		return n("title", t(text));
	}
	public static Xhtml.Body body(Node... nn) {
		return new Xhtml.Body(nn);
	}
	public static TextArea textarea(int rows, int cols, String text, Node.Attribute... atts) {
		return new TextArea(rows, cols, text, atts);
	}
}
