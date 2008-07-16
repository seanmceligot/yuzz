package org.yuzz.web;

import org.yuzz.xml.Node;
import org.yuzz.xml.Xhtml.HtmlTag;

public interface HtmlTaglet <T extends Node>{
	public T tag() throws Throwable;
}
