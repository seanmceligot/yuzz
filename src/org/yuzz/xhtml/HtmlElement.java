package org.yuzz.xhtml;

import java.io.IOException;
import java.io.Writer;

public interface HtmlElement {
	public void write(Writer out) throws IOException;
}

