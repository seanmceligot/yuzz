package org.yuzz.xhtml;

import java.io.IOException;
import java.io.Writer;

class HtmlString implements HtmlElement {
	public final String str;
	HtmlString(String s) { str = s;}

	public void write(Writer out) throws IOException {
		out.write(str);
	}
}

