package org.yuzz.tools;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.head;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.scriptsrc;
import static org.yuzz.xml.NodeStatics.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.yuzz.xml.Node;
import org.yuzz.xml.NodeFunctions;
import org.yuzz.xml.Xhtml.Head;

public class Render {
  public static void windowsBrowser(String browserPath, Node node) throws IOException {
    File htmlFile = render(node);
    new ProcessBuilder("cmd", "/c", "start", htmlFile.getCanonicalPath()).start();
  }
  public static void browse(String browserPath, Node node) throws IOException {
    File htmlFile = render(node);
    new ProcessBuilder(browserPath, htmlFile.getCanonicalPath()).start();
  }
	/**
	 * To use add the line render(node) to the end us your test. 
	 * You should comment out the render line when you are done.
	 * @param node
	 */
  public static File render(Node node) {
    File htmlFile = new File("tmp.html");
    render(node, htmlFile, new String[0], new String[0]);
    return htmlFile;
  }
	public static void render(Node node, File file, String[] jsFiles, String[] cssfiles) {
		try {
			FileWriter fout;
			fout = new FileWriter(file);
			try {
        if (!node.getTag().equalsIgnoreCase("html")) {
				// add header if it's missing so javascript and css show up
				Head header = head( 
						   title("Yuzz Test"), 
						   n("meta", a("content", "text/html; charset=UTF-8"), a("http-equiv", "Content-Type")));
					for (String css : cssfiles) {
						header.add(n("link", a("href", css), a("rel", "stylesheet"), a("type", "text/css")));
					}
					for (String js : jsFiles) {
						header.add(scriptsrc(js));
					}
				  node = htmlXhtml1999(header, body(node));
				}
				node.write(fout);
			} finally {
				fout.close();
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
