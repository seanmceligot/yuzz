package org.yuzz.xhtml;

public class HtmlAttr<V> implements IHtmlAttr<V> {
	final String key;
	final V val;
	
	public HtmlAttr(String a, V b) {
		key = a;
		val = b;
	}
	/* (non-Javadoc)
	 * @see org.yuzz.xhtml.IAttribute#key()
	 */
	public String key() {return key;}
	/* (non-Javadoc)
	 * @see org.yuzz.xhtml.IAttribute#val()
	 */
	public V val() {return val;}
}
