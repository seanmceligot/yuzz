package org.yuzz.xhtml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class HtmlTag implements HtmlElement {
	protected final String _tag;
	protected final IHtmlAttr[] _attrs;
	protected final HtmlElement[] _content;
	
	private static final Set<String> validHtmlITags = mkValidHtmlITags();

	HtmlTag(String t, IHtmlAttr[] as, HtmlElement... cs) {
		_tag =t; _attrs=as;
		_content = cs;
	}
	public void write(OutputStream out) throws IOException {
		write(new OutputStreamWriter(out));
	}
	public void write(Writer out) throws IOException {
		out.write('<');
		out.write(_tag);
		out.write(' ');
		if (( _attrs.length == 0 ) && _content.length==0 && (isEmptyAllowed(_tag))) {
			out.write("/>");		 
		} else {
			for (IHtmlAttr at : _attrs) {
				out.write(at.key());
				out.write("=\"");
				out.write(at.val().toString());
				out.write("\" ");
			}
			out.write('>');
			for (HtmlElement el : _content) {
				el.write(out);
			}
			out.write("</");
			out.write(_tag);
			out.write('>');
		}
	}
	private static Set<String> mkValidHtmlITags() {
		String[] _validHtmlITags = new String[] { "area", "base",
				"basefont", "br", "col", "frame", "hr", "img", "input", "isindex",
				"link", "meta", "param" };
		return new CopyOnWriteArraySet<String>(Arrays.asList(_validHtmlITags));
	}
	
	private boolean isEmptyAllowed(String tag) {
		return validHtmlITags.contains(tag);
	}
}
