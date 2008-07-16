package rss;

import org.yuzz.xml.Node;
import org.yuzz.xml.Xhtml;
import org.yuzz.xml.Node.Attribute;
import org.yuzz.xml.Node.Text;

import static org.yuzz.xml.NodeStatics.*;

public class Rss {
	public static abstract class RssItemChildren<T extends Node> extends Node<T> {
		public RssItemChildren(String tag, T... ee) {
			super(tag, ee);
		}
	};

	public static class RssItem extends Node<RssItemChildren> {
		public RssItem(RssItemChildren... ee) {
			super("item", ee);
			add(a("rdf:about", "http://xml.com/pub/2000/08/09/xslt/xslt.html"));
		}
	};
	public static class RssTitle extends RssItemChildren<Text> {
		protected RssTitle(String title) {
				super("title", t(title));
			};
		}
	public static class RssDescription extends RssItemChildren<Text> {
		protected RssDescription(String text) {
				super("description", t(text));
			};
		}
	public static class RssLink extends RssItemChildren<Text> {
		protected RssLink(String text) {
				super("link", t(text));
			};
		}
	public static RssItem item(String title, String link, String description) {
		return new RssItem(new RssTitle(title), new RssLink(link), new RssDescription(description));
	}
	public static Node rss(RssItem...items) {
		Node rdf = n("rdf:RDF", a("xmlns:rdf","http://www.w3.org/1999/02/22-rdf-syntax-ns#"),
				a("xmlns", "http://purl.org/rss/1.0/"));
		rdf.add(channel());
		for (RssItem rssItem : items) {
			rdf.add(rssItem);
		}
		return rdf;
	}
	public static Node channel() {
	  return n("channel", a("rdf:about", "http://www.xml.com/xml/news.rss"), 
	  	    n("title", t("XML.com")), 
	  	    n("link", t("http://xml.com/pub")), 
	  	    n("description", t("XML.com features a rich mix of information and services \n     for the XML community.")), 
	  	    n("image", a("rdf:resource", "http://xml.com/universal/images/xml_tiny.gif")), 
	  	    n("items", 
	  	     n("rdf:seq",  
	  	    		 n("rdf:li", a("rdf:resource", "http://xml.com/pub/2000/08/09/xslt/xslt.html")), 
	  	    		 n("rdf:li", a("rdf:resource", "http://xml.com/pub/2000/08/09/rdfdb/index.html")))), 
	  	   n("textinput", a("rdf:resource", "http://search.xml.com")))
	  	;
}
}
