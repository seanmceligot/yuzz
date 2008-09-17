package test.yuzz;

import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.alist;
import static org.yuzz.xml.NodeStatics.body;
import static org.yuzz.xml.NodeStatics.div;
import static org.yuzz.xml.NodeStatics.htmlXhtml1999;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;
import static org.yuzz.xml.NodeStatics.table;
import static org.yuzz.xml.NodeStatics.td;
import static org.yuzz.xml.NodeStatics.tr;
import static org.yuzz.xml.NodeStatics.xhtml11Strict;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.yuzz.functor.Fun;
import org.yuzz.functor.Functions;
import org.yuzz.tools.Render;
import org.yuzz.xml.AllowedTags;
import org.yuzz.xml.Node;
import org.yuzz.xml.NodeStatics;
import org.yuzz.xml.Xhtml.Body;
import org.yuzz.xml.Xhtml.DocType;
import org.yuzz.xml.Xhtml.Head;
import org.yuzz.xml.Xhtml.HtmlTag;
import org.yuzz.xml.Xhtml.Table;

import fj.F;
import fj.F2;

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
	public void testParser() throws IOException {
		Table table = buildTable();
		Render.browse("firefox", table);
		System.out.println(table.writeString());
	}
	@Test
	public void testSerialize() throws FileNotFoundException, IOException, ClassNotFoundException {
		 ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
	        "Node.ser"));
			Table table = buildTable();
		Node n = htmlXhtml1999( body(
				  div(a("id","emptydiv")), table));
		n.writeExternal(o);
		o.close();
			
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("Node.ser"));
		Node n2 = Node.readExternal(in);
		n2.write(System.out);
		System.out.println("1\n"+n.writeString()+"\n2\n"+n2.writeString());
		Assert.assertEquals(n.writeString(), n2.writeString());
	}
  private Table buildTable() {
    Table table = table( 
          a("border","1"),
    	    tr( 
    	     td( alist(a("align", "left")), 
    	      n("font", a("size", "+1"), 
    	       n("strong", t("Method")))) 
    	       ));
    F2<Table, String, Table> fun = new F2<Table, String, Table>() {
      public Table f(Table table, String str) {
        table.add(tr(td(t(str))));
        return table;
      }
      
    };
    List<String> methods = Functions.map(new F<Method,String>() {
      public String f(Method m) {
        return m.toString();
      }
      
    } , NodeStatics.class.getMethods());
    Functions.reduce(fun , table, methods );
    return table;
  }
}
