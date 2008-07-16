package rss;

import org.yuzz.xml.Node;

public class RssItem extends Node {
	private String _title;
	private String _link;
	private String _description;
	
	public RssItem(String title, String link, String description) {
		super("item");
		_title = title;
		_link = link;
		_description = description;
	}
	
	
	
}
