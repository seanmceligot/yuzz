package org.yuzz.xml;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.head;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.scriptsrc;
import static org.yuzz.xml.NodeStatics.title;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.yuzz.xml.Xhtml.Head;

public class Test {
	/**
	 * To use add the line render(node) to the end us your test. 
	 * You should comment out the render line when you are done.
	 * @param node
	 */
	public static void render(Node node, File file, String[] jsFiles, String[] cssfiles) {
		try {
			Node header;
			FileWriter fout;
			fout = new FileWriter(file);
			try {
				// add header if it's missing so javascript and css show up
				Node head = NodeFunctions.findByTagName(node, "head");
				if (head == null) {
					header = head( 
							   title("Yuzz Test"), 
							   n("meta", a("content", "text/html; charset=UTF-8"), a("http-equiv", "Content-Type")));
						for (String css : cssfiles) {
							header.add(n("link", a("href", css), a("rel", "stylesheet"), a("type", "text/css")));
						}
						for (String js : jsFiles) {
							header.add(scriptsrc(js));
						}
				  node = NodeStatics.nlist(header, node);
				}
				node.write(fout);
			} finally {
				fout.close();
			}
			new ProcessBuilder("cmd", "/c", "start", file.getPath()).start();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
