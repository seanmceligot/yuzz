package org.yuzz.xhtml;

import static org.yuzz.xhtml.Strict.*;

import java.io.IOException;

import org.junit.Test;
import org.yuzz.xhtml.Strict.Element_html;

public class StrictTest {
@Test
public void test() throws IOException {
	Element_html html = html( 
				head(title()),
				body());
	html.write(System.out);
}
}
