package test.yuzz;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.alist;
import static org.yuzz.xml.NodeStatics.div;
import static org.yuzz.xml.NodeStatics.htmlXhtml1999;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;
import static org.yuzz.xml.NodeStatics.table;
import static org.yuzz.xml.NodeStatics.td;
import static org.yuzz.xml.NodeStatics.tr;
import static org.yuzz.xml.NodeStatics.xhtml11Strict;

import org.junit.Assert;
import org.junit.Test;
import org.yuzz.xml.AllowedTags;
import org.yuzz.xml.Node;
import org.yuzz.xml.Xhtml.Body;
import org.yuzz.xml.Xhtml.DocType;
import org.yuzz.xml.Xhtml.Head;
import org.yuzz.xml.Xhtml.HtmlTag;
import org.yuzz.xml.Xhtml.Table;

public class NodeTest {
	@Test
	public void testDocType() {
		AllowedTags annotation = DocType.class.getAnnotation(AllowedTags.class);
		System.out.println("annotation: "+annotation);
		DocType docType = xhtml11Strict();
		annotation = docType.getClass().getAnnotation(AllowedTags.class);
		System.out.println("annotation: "+annotation);
		HtmlTag html = htmlXhtml1999();
		docType.add(html);
		Head head = new Head();
		html.add(head);
		html.add(new Body());
		try {
			head.add(n("title"));
			Assert.fail("should have throw Exception adding titile");
		} catch(RuntimeException ex) {
			System.out.println("GOOD: "+ex.getMessage());
		}
	}
	@Test
	public void testParser() {
		Table table = table( 
						    tr( 
						     td( alist(a("align", "left")), 
						      n("font", a("size", "+1"), 
						       n("strong", t("Filename")))), 
						     td( alist(a("align", "center")), 
						      n("font", a("size", "+1"), 
						       n("strong", t("Size")))), 
						     td( alist(a("align", "right")), 
						      n("font", a("size", "+1"), 
						       n("strong", t("Last Modified"))))));
		Node n = n("document", 
				 n("html", 
				  n("body", 
						  div(a("id","emptydiv")), table)))
				;
		// this next line should NOT compile 
		//table.add(n("div"));
		// this next line should NOT compile
		//table.add(td());
		// this next line SHOULD compile
		table.add(tr());
		
		
		System.out.println(n.writeString());
	}
}
