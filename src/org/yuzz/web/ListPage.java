package org.yuzz.web;
import static org.yuzz.xml.NodeStatics.body;
import static org.yuzz.xml.NodeStatics.head;
import static org.yuzz.xml.NodeStatics.htmlXhtml1999;
import static org.yuzz.xml.NodeStatics.title;

import java.awt.print.Book;

import org.snuvy.Dbm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.xml.Xhtml.HtmlTag;

public class ListPage implements Page {

	public HtmlTag node(Dbm dbm) throws Throwable {
		return htmlXhtml1999(
				head(title("List")),
				body(new ListView(null).node())
				);
	}

	@Override
	public HtmlTag process(HttpServletRequest req, HttpServletResponse res) throws Throwable {
	  	Dbm dbm = new Dbm();
	  	dbm.open();
	  	try {
	  		return node(dbm);
	  	} finally {
	  		dbm.close();
	  	}

	}
}
