package org.yuzz.web;
import org.yuzz.xml.Node;

public interface Noder</*Res, */NodeType extends Node> {
	public NodeType node(/*Res res*/) throws Throwable;

}
