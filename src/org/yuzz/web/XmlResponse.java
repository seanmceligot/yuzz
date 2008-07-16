package org.yuzz.web;
import java.io.PrintStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yuzz.xml.Xhtml.DocType;

import static org.yuzz.xml.NodeStatics.*;
import static org.yuzz.xml.Xhtml.*;
import static org.yuzz.functor.Function.*;

public class XmlResponse implements Response {
	private XmlNoderCreator _creator;
	public XmlResponse(XmlNoderCreator creator) {
		_creator = creator;
	}
	public void send(HttpServletRequest req, HttpServletResponse res) throws SystemException {
		ServletOutputStream out;
		try {

			XmlNoder noder = _creator.create(req, res);
			out = res.getOutputStream();
			out.println("<?xml version=\"1.0\"?>");
			out.println();
			noder.node().write(new PrintStream(out));
		} catch (Throwable e) {
			throw new SystemException(e);
		}
	}
}
