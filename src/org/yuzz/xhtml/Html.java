package org.yuzz.xhtml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Html {
	public static final Html EMPTY = new Html();

	private HtmlElement[] els;

	public Html(HtmlElement... els) {
		super();
		this.els = els;
	}
	public void write(Writer out) throws IOException {
		for (HtmlElement el : els) {
			el.write(out);
		}
	}
	public void write(OutputStream out) throws IOException {
		OutputStreamWriter w = new OutputStreamWriter(out);
		write(w);
		w.flush();
	}
	public boolean isEmpty() {
		return els.length == 0;
	}
}
