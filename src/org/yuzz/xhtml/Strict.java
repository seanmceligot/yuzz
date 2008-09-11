package org.yuzz.xhtml;


public class Strict {
	public static HtmlString str(String s) {
		return new HtmlString(s);
	}
	/*private static Html tag(String tag, IHtmlAttr atts, HtmlElement... els) {
		return new Html(new HtmlTag(tag,atts,els));
	}
	private static Html tag(String tag, IHtmlAttr[] atts) {
		return new Html(new HtmlTag(tag,atts,Html.EMPTY));
	}*/
	public static IHtmlAttr strAttr(String n, String v) {
		return new HtmlAttr(n,v);
	}
	public static IHtmlAttr[] aa(IHtmlAttr... atts) {
		return atts;
	}
	public static Html hh(HtmlElement... els) {
		return new Html(els);
	}
	
	//// html ////
	interface ContentOf_html extends HtmlElement  {}
	public static Element_html html(ContentOf_html... els) {return new Element_html(ATTRIBUTE_OF_html, els);}
	public static Element_html html(AttributeOf_html[] atts, ContentOf_html... els) {return new Element_html(atts, els);}
	private static final AttributeOf_html[] ATTRIBUTE_OF_html = new AttributeOf_html[0];
	interface AttributeOf_html extends IHtmlAttr  {}
	public static class Element_html extends HtmlTag  {
		Element_html(AttributeOf_html[] as, ContentOf_html...cs) {
			super("html", as, cs);
	}}


	//// head ////
	interface ContentOf_head extends HtmlElement  {}
	public static Element_head head(ContentOf_head... els) {return new Element_head(ATTRIBUTE_OF_head, els);}
	public static Element_head head(AttributeOf_head[] atts, ContentOf_head... els) {return new Element_head(atts, els);}
	private static final AttributeOf_head[] ATTRIBUTE_OF_head = new AttributeOf_head[0];
	interface AttributeOf_head extends IHtmlAttr  {}
	public static class Element_head extends HtmlTag implements ContentOf_html {
		Element_head(AttributeOf_head[] as, ContentOf_head...cs) {
			super("head", as, cs);
	}}


	//// title ////
	interface ContentOf_title extends HtmlElement  {}
	public static Element_title title(ContentOf_title... els) {return new Element_title(ATTRIBUTE_OF_title, els);}
	public static Element_title title(AttributeOf_title[] atts, ContentOf_title... els) {return new Element_title(atts, els);}
	private static final AttributeOf_title[] ATTRIBUTE_OF_title = new AttributeOf_title[0];
	interface AttributeOf_title extends IHtmlAttr  {}
	public static class Element_title extends HtmlTag implements ContentOf_head {
		Element_title(AttributeOf_title[] as, ContentOf_title...cs) {
			super("title", as, cs);
	}}


	//// base ////
	interface ContentOf_base extends HtmlElement  {}
	public static Element_base base(ContentOf_base... els) {return new Element_base(ATTRIBUTE_OF_base, els);}
	public static Element_base base(AttributeOf_base[] atts, ContentOf_base... els) {return new Element_base(atts, els);}
	private static final AttributeOf_base[] ATTRIBUTE_OF_base = new AttributeOf_base[0];
	interface AttributeOf_base extends IHtmlAttr  {}
	public static class Element_base extends HtmlTag implements ContentOf_head {
		Element_base(AttributeOf_base[] as, ContentOf_base...cs) {
			super("base", as, cs);
	}}


	//// meta ////
	interface ContentOf_meta extends HtmlElement  {}
	public static Element_meta meta(ContentOf_meta... els) {return new Element_meta(ATTRIBUTE_OF_meta, els);}
	public static Element_meta meta(AttributeOf_meta[] atts, ContentOf_meta... els) {return new Element_meta(atts, els);}
	private static final AttributeOf_meta[] ATTRIBUTE_OF_meta = new AttributeOf_meta[0];
	interface AttributeOf_meta extends IHtmlAttr  {}
	public static class Element_meta extends HtmlTag  {
		Element_meta(AttributeOf_meta[] as, ContentOf_meta...cs) {
			super("meta", as, cs);
	}}


	//// link ////
	interface ContentOf_link extends HtmlElement  {}
	public static Element_link link(ContentOf_link... els) {return new Element_link(ATTRIBUTE_OF_link, els);}
	public static Element_link link(AttributeOf_link[] atts, ContentOf_link... els) {return new Element_link(atts, els);}
	private static final AttributeOf_link[] ATTRIBUTE_OF_link = new AttributeOf_link[0];
	interface AttributeOf_link extends IHtmlAttr  {}
	public static class Element_link extends HtmlTag  {
		Element_link(AttributeOf_link[] as, ContentOf_link...cs) {
			super("link", as, cs);
	}}


	//// style ////
	interface ContentOf_style extends HtmlElement  {}
	public static Element_style style(ContentOf_style... els) {return new Element_style(ATTRIBUTE_OF_style, els);}
	public static Element_style style(AttributeOf_style[] atts, ContentOf_style... els) {return new Element_style(atts, els);}
	private static final AttributeOf_style[] ATTRIBUTE_OF_style = new AttributeOf_style[0];
	interface AttributeOf_style extends IHtmlAttr  {}
	public static class Element_style extends HtmlTag  {
		Element_style(AttributeOf_style[] as, ContentOf_style...cs) {
			super("style", as, cs);
	}}


	//// script ////
	interface ContentOf_script extends HtmlElement  {}
	public static Element_script script(ContentOf_script... els) {return new Element_script(ATTRIBUTE_OF_script, els);}
	public static Element_script script(AttributeOf_script[] atts, ContentOf_script... els) {return new Element_script(atts, els);}
	private static final AttributeOf_script[] ATTRIBUTE_OF_script = new AttributeOf_script[0];
	interface AttributeOf_script extends IHtmlAttr  {}
	public static class Element_script extends HtmlTag  {
		Element_script(AttributeOf_script[] as, ContentOf_script...cs) {
			super("script", as, cs);
	}}


	//// noscript ////
	interface ContentOf_noscript extends HtmlElement  {}
	public static Element_noscript noscript(ContentOf_noscript... els) {return new Element_noscript(ATTRIBUTE_OF_noscript, els);}
	public static Element_noscript noscript(AttributeOf_noscript[] atts, ContentOf_noscript... els) {return new Element_noscript(atts, els);}
	private static final AttributeOf_noscript[] ATTRIBUTE_OF_noscript = new AttributeOf_noscript[0];
	interface AttributeOf_noscript extends IHtmlAttr  {}
	public static class Element_noscript extends HtmlTag  {
		Element_noscript(AttributeOf_noscript[] as, ContentOf_noscript...cs) {
			super("noscript", as, cs);
	}}


	//// body ////
	interface ContentOf_body extends HtmlElement  {}
	public static Element_body body(ContentOf_body... els) {return new Element_body(ATTRIBUTE_OF_body, els);}
	public static Element_body body(AttributeOf_body[] atts, ContentOf_body... els) {return new Element_body(atts, els);}
	private static final AttributeOf_body[] ATTRIBUTE_OF_body = new AttributeOf_body[0];
	interface AttributeOf_body extends IHtmlAttr  {}
	public static class Element_body extends HtmlTag implements ContentOf_html {
		Element_body(AttributeOf_body[] as, ContentOf_body...cs) {
			super("body", as, cs);
	}}


	//// div ////
	interface ContentOf_div extends HtmlElement  {}
	public static Element_div div(ContentOf_div... els) {return new Element_div(ATTRIBUTE_OF_div, els);}
	public static Element_div div(AttributeOf_div[] atts, ContentOf_div... els) {return new Element_div(atts, els);}
	private static final AttributeOf_div[] ATTRIBUTE_OF_div = new AttributeOf_div[0];
	interface AttributeOf_div extends IHtmlAttr  {}
	public static class Element_div extends HtmlTag  {
		Element_div(AttributeOf_div[] as, ContentOf_div...cs) {
			super("div", as, cs);
	}}


	//// p ////
	interface ContentOf_p extends HtmlElement  {}
	public static Element_p p(ContentOf_p... els) {return new Element_p(ATTRIBUTE_OF_p, els);}
	public static Element_p p(AttributeOf_p[] atts, ContentOf_p... els) {return new Element_p(atts, els);}
	private static final AttributeOf_p[] ATTRIBUTE_OF_p = new AttributeOf_p[0];
	interface AttributeOf_p extends IHtmlAttr  {}
	public static class Element_p extends HtmlTag  {
		Element_p(AttributeOf_p[] as, ContentOf_p...cs) {
			super("p", as, cs);
	}}


	//// h1 ////
	interface ContentOf_h1 extends HtmlElement  {}
	public static Element_h1 h1(ContentOf_h1... els) {return new Element_h1(ATTRIBUTE_OF_h1, els);}
	public static Element_h1 h1(AttributeOf_h1[] atts, ContentOf_h1... els) {return new Element_h1(atts, els);}
	private static final AttributeOf_h1[] ATTRIBUTE_OF_h1 = new AttributeOf_h1[0];
	interface AttributeOf_h1 extends IHtmlAttr  {}
	public static class Element_h1 extends HtmlTag  {
		Element_h1(AttributeOf_h1[] as, ContentOf_h1...cs) {
			super("h1", as, cs);
	}}


	//// h2 ////
	interface ContentOf_h2 extends HtmlElement  {}
	public static Element_h2 h2(ContentOf_h2... els) {return new Element_h2(ATTRIBUTE_OF_h2, els);}
	public static Element_h2 h2(AttributeOf_h2[] atts, ContentOf_h2... els) {return new Element_h2(atts, els);}
	private static final AttributeOf_h2[] ATTRIBUTE_OF_h2 = new AttributeOf_h2[0];
	interface AttributeOf_h2 extends IHtmlAttr  {}
	public static class Element_h2 extends HtmlTag  {
		Element_h2(AttributeOf_h2[] as, ContentOf_h2...cs) {
			super("h2", as, cs);
	}}


	//// h3 ////
	interface ContentOf_h3 extends HtmlElement  {}
	public static Element_h3 h3(ContentOf_h3... els) {return new Element_h3(ATTRIBUTE_OF_h3, els);}
	public static Element_h3 h3(AttributeOf_h3[] atts, ContentOf_h3... els) {return new Element_h3(atts, els);}
	private static final AttributeOf_h3[] ATTRIBUTE_OF_h3 = new AttributeOf_h3[0];
	interface AttributeOf_h3 extends IHtmlAttr  {}
	public static class Element_h3 extends HtmlTag  {
		Element_h3(AttributeOf_h3[] as, ContentOf_h3...cs) {
			super("h3", as, cs);
	}}


	//// h4 ////
	interface ContentOf_h4 extends HtmlElement  {}
	public static Element_h4 h4(ContentOf_h4... els) {return new Element_h4(ATTRIBUTE_OF_h4, els);}
	public static Element_h4 h4(AttributeOf_h4[] atts, ContentOf_h4... els) {return new Element_h4(atts, els);}
	private static final AttributeOf_h4[] ATTRIBUTE_OF_h4 = new AttributeOf_h4[0];
	interface AttributeOf_h4 extends IHtmlAttr  {}
	public static class Element_h4 extends HtmlTag  {
		Element_h4(AttributeOf_h4[] as, ContentOf_h4...cs) {
			super("h4", as, cs);
	}}


	//// h5 ////
	interface ContentOf_h5 extends HtmlElement  {}
	public static Element_h5 h5(ContentOf_h5... els) {return new Element_h5(ATTRIBUTE_OF_h5, els);}
	public static Element_h5 h5(AttributeOf_h5[] atts, ContentOf_h5... els) {return new Element_h5(atts, els);}
	private static final AttributeOf_h5[] ATTRIBUTE_OF_h5 = new AttributeOf_h5[0];
	interface AttributeOf_h5 extends IHtmlAttr  {}
	public static class Element_h5 extends HtmlTag  {
		Element_h5(AttributeOf_h5[] as, ContentOf_h5...cs) {
			super("h5", as, cs);
	}}


	//// h6 ////
	interface ContentOf_h6 extends HtmlElement  {}
	public static Element_h6 h6(ContentOf_h6... els) {return new Element_h6(ATTRIBUTE_OF_h6, els);}
	public static Element_h6 h6(AttributeOf_h6[] atts, ContentOf_h6... els) {return new Element_h6(atts, els);}
	private static final AttributeOf_h6[] ATTRIBUTE_OF_h6 = new AttributeOf_h6[0];
	interface AttributeOf_h6 extends IHtmlAttr  {}
	public static class Element_h6 extends HtmlTag  {
		Element_h6(AttributeOf_h6[] as, ContentOf_h6...cs) {
			super("h6", as, cs);
	}}


	//// ul ////
	interface ContentOf_ul extends HtmlElement  {}
	public static Element_ul ul(ContentOf_ul... els) {return new Element_ul(ATTRIBUTE_OF_ul, els);}
	public static Element_ul ul(AttributeOf_ul[] atts, ContentOf_ul... els) {return new Element_ul(atts, els);}
	private static final AttributeOf_ul[] ATTRIBUTE_OF_ul = new AttributeOf_ul[0];
	interface AttributeOf_ul extends IHtmlAttr  {}
	public static class Element_ul extends HtmlTag  {
		Element_ul(AttributeOf_ul[] as, ContentOf_ul...cs) {
			super("ul", as, cs);
	}}


	//// ol ////
	interface ContentOf_ol extends HtmlElement  {}
	public static Element_ol ol(ContentOf_ol... els) {return new Element_ol(ATTRIBUTE_OF_ol, els);}
	public static Element_ol ol(AttributeOf_ol[] atts, ContentOf_ol... els) {return new Element_ol(atts, els);}
	private static final AttributeOf_ol[] ATTRIBUTE_OF_ol = new AttributeOf_ol[0];
	interface AttributeOf_ol extends IHtmlAttr  {}
	public static class Element_ol extends HtmlTag  {
		Element_ol(AttributeOf_ol[] as, ContentOf_ol...cs) {
			super("ol", as, cs);
	}}


	//// li ////
	interface ContentOf_li extends HtmlElement  {}
	public static Element_li li(ContentOf_li... els) {return new Element_li(ATTRIBUTE_OF_li, els);}
	public static Element_li li(AttributeOf_li[] atts, ContentOf_li... els) {return new Element_li(atts, els);}
	private static final AttributeOf_li[] ATTRIBUTE_OF_li = new AttributeOf_li[0];
	interface AttributeOf_li extends IHtmlAttr  {}
	public static class Element_li extends HtmlTag implements ContentOf_ul, ContentOf_ol {
		Element_li(AttributeOf_li[] as, ContentOf_li...cs) {
			super("li", as, cs);
	}}


	//// dl ////
	interface ContentOf_dl extends HtmlElement  {}
	public static Element_dl dl(ContentOf_dl... els) {return new Element_dl(ATTRIBUTE_OF_dl, els);}
	public static Element_dl dl(AttributeOf_dl[] atts, ContentOf_dl... els) {return new Element_dl(atts, els);}
	private static final AttributeOf_dl[] ATTRIBUTE_OF_dl = new AttributeOf_dl[0];
	interface AttributeOf_dl extends IHtmlAttr  {}
	public static class Element_dl extends HtmlTag  {
		Element_dl(AttributeOf_dl[] as, ContentOf_dl...cs) {
			super("dl", as, cs);
	}}


	//// dt ////
	interface ContentOf_dt extends HtmlElement  {}
	public static Element_dt dt(ContentOf_dt... els) {return new Element_dt(ATTRIBUTE_OF_dt, els);}
	public static Element_dt dt(AttributeOf_dt[] atts, ContentOf_dt... els) {return new Element_dt(atts, els);}
	private static final AttributeOf_dt[] ATTRIBUTE_OF_dt = new AttributeOf_dt[0];
	interface AttributeOf_dt extends IHtmlAttr  {}
	public static class Element_dt extends HtmlTag implements ContentOf_dl {
		Element_dt(AttributeOf_dt[] as, ContentOf_dt...cs) {
			super("dt", as, cs);
	}}


	//// dd ////
	interface ContentOf_dd extends HtmlElement  {}
	public static Element_dd dd(ContentOf_dd... els) {return new Element_dd(ATTRIBUTE_OF_dd, els);}
	public static Element_dd dd(AttributeOf_dd[] atts, ContentOf_dd... els) {return new Element_dd(atts, els);}
	private static final AttributeOf_dd[] ATTRIBUTE_OF_dd = new AttributeOf_dd[0];
	interface AttributeOf_dd extends IHtmlAttr  {}
	public static class Element_dd extends HtmlTag  {
		Element_dd(AttributeOf_dd[] as, ContentOf_dd...cs) {
			super("dd", as, cs);
	}}


	//// address ////
	interface ContentOf_address extends HtmlElement  {}
	public static Element_address address(ContentOf_address... els) {return new Element_address(ATTRIBUTE_OF_address, els);}
	public static Element_address address(AttributeOf_address[] atts, ContentOf_address... els) {return new Element_address(atts, els);}
	private static final AttributeOf_address[] ATTRIBUTE_OF_address = new AttributeOf_address[0];
	interface AttributeOf_address extends IHtmlAttr  {}
	public static class Element_address extends HtmlTag  {
		Element_address(AttributeOf_address[] as, ContentOf_address...cs) {
			super("address", as, cs);
	}}


	//// hr ////
	interface ContentOf_hr extends HtmlElement  {}
	public static Element_hr hr(ContentOf_hr... els) {return new Element_hr(ATTRIBUTE_OF_hr, els);}
	public static Element_hr hr(AttributeOf_hr[] atts, ContentOf_hr... els) {return new Element_hr(atts, els);}
	private static final AttributeOf_hr[] ATTRIBUTE_OF_hr = new AttributeOf_hr[0];
	interface AttributeOf_hr extends IHtmlAttr  {}
	public static class Element_hr extends HtmlTag  {
		Element_hr(AttributeOf_hr[] as, ContentOf_hr...cs) {
			super("hr", as, cs);
	}}


	//// pre ////
	interface ContentOf_pre extends HtmlElement  {}
	public static Element_pre pre(ContentOf_pre... els) {return new Element_pre(ATTRIBUTE_OF_pre, els);}
	public static Element_pre pre(AttributeOf_pre[] atts, ContentOf_pre... els) {return new Element_pre(atts, els);}
	private static final AttributeOf_pre[] ATTRIBUTE_OF_pre = new AttributeOf_pre[0];
	interface AttributeOf_pre extends IHtmlAttr  {}
	public static class Element_pre extends HtmlTag  {
		Element_pre(AttributeOf_pre[] as, ContentOf_pre...cs) {
			super("pre", as, cs);
	}}


	//// blockquote ////
	interface ContentOf_blockquote extends HtmlElement  {}
	public static Element_blockquote blockquote(ContentOf_blockquote... els) {return new Element_blockquote(ATTRIBUTE_OF_blockquote, els);}
	public static Element_blockquote blockquote(AttributeOf_blockquote[] atts, ContentOf_blockquote... els) {return new Element_blockquote(atts, els);}
	private static final AttributeOf_blockquote[] ATTRIBUTE_OF_blockquote = new AttributeOf_blockquote[0];
	interface AttributeOf_blockquote extends IHtmlAttr  {}
	public static class Element_blockquote extends HtmlTag  {
		Element_blockquote(AttributeOf_blockquote[] as, ContentOf_blockquote...cs) {
			super("blockquote", as, cs);
	}}


	//// ins ////
	interface ContentOf_ins extends HtmlElement  {}
	public static Element_ins ins(ContentOf_ins... els) {return new Element_ins(ATTRIBUTE_OF_ins, els);}
	public static Element_ins ins(AttributeOf_ins[] atts, ContentOf_ins... els) {return new Element_ins(atts, els);}
	private static final AttributeOf_ins[] ATTRIBUTE_OF_ins = new AttributeOf_ins[0];
	interface AttributeOf_ins extends IHtmlAttr  {}
	public static class Element_ins extends HtmlTag  {
		Element_ins(AttributeOf_ins[] as, ContentOf_ins...cs) {
			super("ins", as, cs);
	}}


	//// del ////
	interface ContentOf_del extends HtmlElement  {}
	public static Element_del del(ContentOf_del... els) {return new Element_del(ATTRIBUTE_OF_del, els);}
	public static Element_del del(AttributeOf_del[] atts, ContentOf_del... els) {return new Element_del(atts, els);}
	private static final AttributeOf_del[] ATTRIBUTE_OF_del = new AttributeOf_del[0];
	interface AttributeOf_del extends IHtmlAttr  {}
	public static class Element_del extends HtmlTag  {
		Element_del(AttributeOf_del[] as, ContentOf_del...cs) {
			super("del", as, cs);
	}}


	//// a ////
	interface ContentOf_a extends HtmlElement  {}
	public static Element_a a(ContentOf_a... els) {return new Element_a(ATTRIBUTE_OF_a, els);}
	public static Element_a a(AttributeOf_a[] atts, ContentOf_a... els) {return new Element_a(atts, els);}
	private static final AttributeOf_a[] ATTRIBUTE_OF_a = new AttributeOf_a[0];
	interface AttributeOf_a extends IHtmlAttr  {}
	public static class Element_a extends HtmlTag  {
		Element_a(AttributeOf_a[] as, ContentOf_a...cs) {
			super("a", as, cs);
	}}


	//// span ////
	interface ContentOf_span extends HtmlElement  {}
	public static Element_span span(ContentOf_span... els) {return new Element_span(ATTRIBUTE_OF_span, els);}
	public static Element_span span(AttributeOf_span[] atts, ContentOf_span... els) {return new Element_span(atts, els);}
	private static final AttributeOf_span[] ATTRIBUTE_OF_span = new AttributeOf_span[0];
	interface AttributeOf_span extends IHtmlAttr  {}
	public static class Element_span extends HtmlTag  {
		Element_span(AttributeOf_span[] as, ContentOf_span...cs) {
			super("span", as, cs);
	}}


	//// bdo ////
	interface ContentOf_bdo extends HtmlElement  {}
	public static Element_bdo bdo(ContentOf_bdo... els) {return new Element_bdo(ATTRIBUTE_OF_bdo, els);}
	public static Element_bdo bdo(AttributeOf_bdo[] atts, ContentOf_bdo... els) {return new Element_bdo(atts, els);}
	private static final AttributeOf_bdo[] ATTRIBUTE_OF_bdo = new AttributeOf_bdo[0];
	interface AttributeOf_bdo extends IHtmlAttr  {}
	public static class Element_bdo extends HtmlTag  {
		Element_bdo(AttributeOf_bdo[] as, ContentOf_bdo...cs) {
			super("bdo", as, cs);
	}}


	//// br ////
	interface ContentOf_br extends HtmlElement  {}
	public static Element_br br(ContentOf_br... els) {return new Element_br(ATTRIBUTE_OF_br, els);}
	public static Element_br br(AttributeOf_br[] atts, ContentOf_br... els) {return new Element_br(atts, els);}
	private static final AttributeOf_br[] ATTRIBUTE_OF_br = new AttributeOf_br[0];
	interface AttributeOf_br extends IHtmlAttr  {}
	public static class Element_br extends HtmlTag  {
		Element_br(AttributeOf_br[] as, ContentOf_br...cs) {
			super("br", as, cs);
	}}


	//// em ////
	interface ContentOf_em extends HtmlElement  {}
	public static Element_em em(ContentOf_em... els) {return new Element_em(ATTRIBUTE_OF_em, els);}
	public static Element_em em(AttributeOf_em[] atts, ContentOf_em... els) {return new Element_em(atts, els);}
	private static final AttributeOf_em[] ATTRIBUTE_OF_em = new AttributeOf_em[0];
	interface AttributeOf_em extends IHtmlAttr  {}
	public static class Element_em extends HtmlTag  {
		Element_em(AttributeOf_em[] as, ContentOf_em...cs) {
			super("em", as, cs);
	}}


	//// strong ////
	interface ContentOf_strong extends HtmlElement  {}
	public static Element_strong strong(ContentOf_strong... els) {return new Element_strong(ATTRIBUTE_OF_strong, els);}
	public static Element_strong strong(AttributeOf_strong[] atts, ContentOf_strong... els) {return new Element_strong(atts, els);}
	private static final AttributeOf_strong[] ATTRIBUTE_OF_strong = new AttributeOf_strong[0];
	interface AttributeOf_strong extends IHtmlAttr  {}
	public static class Element_strong extends HtmlTag  {
		Element_strong(AttributeOf_strong[] as, ContentOf_strong...cs) {
			super("strong", as, cs);
	}}


	//// dfn ////
	interface ContentOf_dfn extends HtmlElement  {}
	public static Element_dfn dfn(ContentOf_dfn... els) {return new Element_dfn(ATTRIBUTE_OF_dfn, els);}
	public static Element_dfn dfn(AttributeOf_dfn[] atts, ContentOf_dfn... els) {return new Element_dfn(atts, els);}
	private static final AttributeOf_dfn[] ATTRIBUTE_OF_dfn = new AttributeOf_dfn[0];
	interface AttributeOf_dfn extends IHtmlAttr  {}
	public static class Element_dfn extends HtmlTag  {
		Element_dfn(AttributeOf_dfn[] as, ContentOf_dfn...cs) {
			super("dfn", as, cs);
	}}


	//// code ////
	interface ContentOf_code extends HtmlElement  {}
	public static Element_code code(ContentOf_code... els) {return new Element_code(ATTRIBUTE_OF_code, els);}
	public static Element_code code(AttributeOf_code[] atts, ContentOf_code... els) {return new Element_code(atts, els);}
	private static final AttributeOf_code[] ATTRIBUTE_OF_code = new AttributeOf_code[0];
	interface AttributeOf_code extends IHtmlAttr  {}
	public static class Element_code extends HtmlTag  {
		Element_code(AttributeOf_code[] as, ContentOf_code...cs) {
			super("code", as, cs);
	}}


	//// samp ////
	interface ContentOf_samp extends HtmlElement  {}
	public static Element_samp samp(ContentOf_samp... els) {return new Element_samp(ATTRIBUTE_OF_samp, els);}
	public static Element_samp samp(AttributeOf_samp[] atts, ContentOf_samp... els) {return new Element_samp(atts, els);}
	private static final AttributeOf_samp[] ATTRIBUTE_OF_samp = new AttributeOf_samp[0];
	interface AttributeOf_samp extends IHtmlAttr  {}
	public static class Element_samp extends HtmlTag  {
		Element_samp(AttributeOf_samp[] as, ContentOf_samp...cs) {
			super("samp", as, cs);
	}}


	//// kbd ////
	interface ContentOf_kbd extends HtmlElement  {}
	public static Element_kbd kbd(ContentOf_kbd... els) {return new Element_kbd(ATTRIBUTE_OF_kbd, els);}
	public static Element_kbd kbd(AttributeOf_kbd[] atts, ContentOf_kbd... els) {return new Element_kbd(atts, els);}
	private static final AttributeOf_kbd[] ATTRIBUTE_OF_kbd = new AttributeOf_kbd[0];
	interface AttributeOf_kbd extends IHtmlAttr  {}
	public static class Element_kbd extends HtmlTag  {
		Element_kbd(AttributeOf_kbd[] as, ContentOf_kbd...cs) {
			super("kbd", as, cs);
	}}


	//// var ////
	interface ContentOf_var extends HtmlElement  {}
	public static Element_var var(ContentOf_var... els) {return new Element_var(ATTRIBUTE_OF_var, els);}
	public static Element_var var(AttributeOf_var[] atts, ContentOf_var... els) {return new Element_var(atts, els);}
	private static final AttributeOf_var[] ATTRIBUTE_OF_var = new AttributeOf_var[0];
	interface AttributeOf_var extends IHtmlAttr  {}
	public static class Element_var extends HtmlTag  {
		Element_var(AttributeOf_var[] as, ContentOf_var...cs) {
			super("var", as, cs);
	}}


	//// cite ////
	interface ContentOf_cite extends HtmlElement  {}
	public static Element_cite cite(ContentOf_cite... els) {return new Element_cite(ATTRIBUTE_OF_cite, els);}
	public static Element_cite cite(AttributeOf_cite[] atts, ContentOf_cite... els) {return new Element_cite(atts, els);}
	private static final AttributeOf_cite[] ATTRIBUTE_OF_cite = new AttributeOf_cite[0];
	interface AttributeOf_cite extends IHtmlAttr  {}
	public static class Element_cite extends HtmlTag  {
		Element_cite(AttributeOf_cite[] as, ContentOf_cite...cs) {
			super("cite", as, cs);
	}}


	//// abbr ////
	interface ContentOf_abbr extends HtmlElement  {}
	public static Element_abbr abbr(ContentOf_abbr... els) {return new Element_abbr(ATTRIBUTE_OF_abbr, els);}
	public static Element_abbr abbr(AttributeOf_abbr[] atts, ContentOf_abbr... els) {return new Element_abbr(atts, els);}
	private static final AttributeOf_abbr[] ATTRIBUTE_OF_abbr = new AttributeOf_abbr[0];
	interface AttributeOf_abbr extends IHtmlAttr  {}
	public static class Element_abbr extends HtmlTag  {
		Element_abbr(AttributeOf_abbr[] as, ContentOf_abbr...cs) {
			super("abbr", as, cs);
	}}


	//// acronym ////
	interface ContentOf_acronym extends HtmlElement  {}
	public static Element_acronym acronym(ContentOf_acronym... els) {return new Element_acronym(ATTRIBUTE_OF_acronym, els);}
	public static Element_acronym acronym(AttributeOf_acronym[] atts, ContentOf_acronym... els) {return new Element_acronym(atts, els);}
	private static final AttributeOf_acronym[] ATTRIBUTE_OF_acronym = new AttributeOf_acronym[0];
	interface AttributeOf_acronym extends IHtmlAttr  {}
	public static class Element_acronym extends HtmlTag  {
		Element_acronym(AttributeOf_acronym[] as, ContentOf_acronym...cs) {
			super("acronym", as, cs);
	}}


	//// q ////
	interface ContentOf_q extends HtmlElement  {}
	public static Element_q q(ContentOf_q... els) {return new Element_q(ATTRIBUTE_OF_q, els);}
	public static Element_q q(AttributeOf_q[] atts, ContentOf_q... els) {return new Element_q(atts, els);}
	private static final AttributeOf_q[] ATTRIBUTE_OF_q = new AttributeOf_q[0];
	interface AttributeOf_q extends IHtmlAttr  {}
	public static class Element_q extends HtmlTag  {
		Element_q(AttributeOf_q[] as, ContentOf_q...cs) {
			super("q", as, cs);
	}}


	//// sub ////
	interface ContentOf_sub extends HtmlElement  {}
	public static Element_sub sub(ContentOf_sub... els) {return new Element_sub(ATTRIBUTE_OF_sub, els);}
	public static Element_sub sub(AttributeOf_sub[] atts, ContentOf_sub... els) {return new Element_sub(atts, els);}
	private static final AttributeOf_sub[] ATTRIBUTE_OF_sub = new AttributeOf_sub[0];
	interface AttributeOf_sub extends IHtmlAttr  {}
	public static class Element_sub extends HtmlTag  {
		Element_sub(AttributeOf_sub[] as, ContentOf_sub...cs) {
			super("sub", as, cs);
	}}


	//// sup ////
	interface ContentOf_sup extends HtmlElement  {}
	public static Element_sup sup(ContentOf_sup... els) {return new Element_sup(ATTRIBUTE_OF_sup, els);}
	public static Element_sup sup(AttributeOf_sup[] atts, ContentOf_sup... els) {return new Element_sup(atts, els);}
	private static final AttributeOf_sup[] ATTRIBUTE_OF_sup = new AttributeOf_sup[0];
	interface AttributeOf_sup extends IHtmlAttr  {}
	public static class Element_sup extends HtmlTag  {
		Element_sup(AttributeOf_sup[] as, ContentOf_sup...cs) {
			super("sup", as, cs);
	}}


	//// tt ////
	interface ContentOf_tt extends HtmlElement  {}
	public static Element_tt tt(ContentOf_tt... els) {return new Element_tt(ATTRIBUTE_OF_tt, els);}
	public static Element_tt tt(AttributeOf_tt[] atts, ContentOf_tt... els) {return new Element_tt(atts, els);}
	private static final AttributeOf_tt[] ATTRIBUTE_OF_tt = new AttributeOf_tt[0];
	interface AttributeOf_tt extends IHtmlAttr  {}
	public static class Element_tt extends HtmlTag  {
		Element_tt(AttributeOf_tt[] as, ContentOf_tt...cs) {
			super("tt", as, cs);
	}}


	//// i ////
	interface ContentOf_i extends HtmlElement  {}
	public static Element_i i(ContentOf_i... els) {return new Element_i(ATTRIBUTE_OF_i, els);}
	public static Element_i i(AttributeOf_i[] atts, ContentOf_i... els) {return new Element_i(atts, els);}
	private static final AttributeOf_i[] ATTRIBUTE_OF_i = new AttributeOf_i[0];
	interface AttributeOf_i extends IHtmlAttr  {}
	public static class Element_i extends HtmlTag  {
		Element_i(AttributeOf_i[] as, ContentOf_i...cs) {
			super("i", as, cs);
	}}


	//// b ////
	interface ContentOf_b extends HtmlElement  {}
	public static Element_b b(ContentOf_b... els) {return new Element_b(ATTRIBUTE_OF_b, els);}
	public static Element_b b(AttributeOf_b[] atts, ContentOf_b... els) {return new Element_b(atts, els);}
	private static final AttributeOf_b[] ATTRIBUTE_OF_b = new AttributeOf_b[0];
	interface AttributeOf_b extends IHtmlAttr  {}
	public static class Element_b extends HtmlTag  {
		Element_b(AttributeOf_b[] as, ContentOf_b...cs) {
			super("b", as, cs);
	}}


	//// big ////
	interface ContentOf_big extends HtmlElement  {}
	public static Element_big big(ContentOf_big... els) {return new Element_big(ATTRIBUTE_OF_big, els);}
	public static Element_big big(AttributeOf_big[] atts, ContentOf_big... els) {return new Element_big(atts, els);}
	private static final AttributeOf_big[] ATTRIBUTE_OF_big = new AttributeOf_big[0];
	interface AttributeOf_big extends IHtmlAttr  {}
	public static class Element_big extends HtmlTag  {
		Element_big(AttributeOf_big[] as, ContentOf_big...cs) {
			super("big", as, cs);
	}}


	//// small ////
	interface ContentOf_small extends HtmlElement  {}
	public static Element_small small(ContentOf_small... els) {return new Element_small(ATTRIBUTE_OF_small, els);}
	public static Element_small small(AttributeOf_small[] atts, ContentOf_small... els) {return new Element_small(atts, els);}
	private static final AttributeOf_small[] ATTRIBUTE_OF_small = new AttributeOf_small[0];
	interface AttributeOf_small extends IHtmlAttr  {}
	public static class Element_small extends HtmlTag  {
		Element_small(AttributeOf_small[] as, ContentOf_small...cs) {
			super("small", as, cs);
	}}


	//// object ////
	interface ContentOf_object extends HtmlElement  {}
	public static Element_object object(ContentOf_object... els) {return new Element_object(ATTRIBUTE_OF_object, els);}
	public static Element_object object(AttributeOf_object[] atts, ContentOf_object... els) {return new Element_object(atts, els);}
	private static final AttributeOf_object[] ATTRIBUTE_OF_object = new AttributeOf_object[0];
	interface AttributeOf_object extends IHtmlAttr  {}
	public static class Element_object extends HtmlTag  {
		Element_object(AttributeOf_object[] as, ContentOf_object...cs) {
			super("object", as, cs);
	}}


	//// param ////
	interface ContentOf_param extends HtmlElement  {}
	public static Element_param param(ContentOf_param... els) {return new Element_param(ATTRIBUTE_OF_param, els);}
	public static Element_param param(AttributeOf_param[] atts, ContentOf_param... els) {return new Element_param(atts, els);}
	private static final AttributeOf_param[] ATTRIBUTE_OF_param = new AttributeOf_param[0];
	interface AttributeOf_param extends IHtmlAttr  {}
	public static class Element_param extends HtmlTag implements ContentOf_object {
		Element_param(AttributeOf_param[] as, ContentOf_param...cs) {
			super("param", as, cs);
	}}


	//// img ////
	interface ContentOf_img extends HtmlElement  {}
	public static Element_img img(ContentOf_img... els) {return new Element_img(ATTRIBUTE_OF_img, els);}
	public static Element_img img(AttributeOf_img[] atts, ContentOf_img... els) {return new Element_img(atts, els);}
	private static final AttributeOf_img[] ATTRIBUTE_OF_img = new AttributeOf_img[0];
	interface AttributeOf_img extends IHtmlAttr  {}
	public static class Element_img extends HtmlTag  {
		Element_img(AttributeOf_img[] as, ContentOf_img...cs) {
			super("img", as, cs);
	}}


	//// map ////
	interface ContentOf_map extends HtmlElement  {}
	public static Element_map map(ContentOf_map... els) {return new Element_map(ATTRIBUTE_OF_map, els);}
	public static Element_map map(AttributeOf_map[] atts, ContentOf_map... els) {return new Element_map(atts, els);}
	private static final AttributeOf_map[] ATTRIBUTE_OF_map = new AttributeOf_map[0];
	interface AttributeOf_map extends IHtmlAttr  {}
	public static class Element_map extends HtmlTag  {
		Element_map(AttributeOf_map[] as, ContentOf_map...cs) {
			super("map", as, cs);
	}}


	//// area ////
	interface ContentOf_area extends HtmlElement  {}
	public static Element_area area(ContentOf_area... els) {return new Element_area(ATTRIBUTE_OF_area, els);}
	public static Element_area area(AttributeOf_area[] atts, ContentOf_area... els) {return new Element_area(atts, els);}
	private static final AttributeOf_area[] ATTRIBUTE_OF_area = new AttributeOf_area[0];
	interface AttributeOf_area extends IHtmlAttr  {}
	public static class Element_area extends HtmlTag implements ContentOf_map {
		Element_area(AttributeOf_area[] as, ContentOf_area...cs) {
			super("area", as, cs);
	}}


	//// form ////
	interface ContentOf_form extends HtmlElement  {}
	public static Element_form form(ContentOf_form... els) {return new Element_form(ATTRIBUTE_OF_form, els);}
	public static Element_form form(AttributeOf_form[] atts, ContentOf_form... els) {return new Element_form(atts, els);}
	private static final AttributeOf_form[] ATTRIBUTE_OF_form = new AttributeOf_form[0];
	interface AttributeOf_form extends IHtmlAttr  {}
	public static class Element_form extends HtmlTag implements ContentOf_object, ContentOf_map, ContentOf_fieldset {
		Element_form(AttributeOf_form[] as, ContentOf_form...cs) {
			super("form", as, cs);
	}}


	//// label ////
	interface ContentOf_label extends HtmlElement  {}
	public static Element_label label(ContentOf_label... els) {return new Element_label(ATTRIBUTE_OF_label, els);}
	public static Element_label label(AttributeOf_label[] atts, ContentOf_label... els) {return new Element_label(atts, els);}
	private static final AttributeOf_label[] ATTRIBUTE_OF_label = new AttributeOf_label[0];
	interface AttributeOf_label extends IHtmlAttr  {}
	public static class Element_label extends HtmlTag  {
		Element_label(AttributeOf_label[] as, ContentOf_label...cs) {
			super("label", as, cs);
	}}


	//// input ////
	interface ContentOf_input extends HtmlElement  {}
	public static Element_input input(ContentOf_input... els) {return new Element_input(ATTRIBUTE_OF_input, els);}
	public static Element_input input(AttributeOf_input[] atts, ContentOf_input... els) {return new Element_input(atts, els);}
	private static final AttributeOf_input[] ATTRIBUTE_OF_input = new AttributeOf_input[0];
	interface AttributeOf_input extends IHtmlAttr  {}
	public static class Element_input extends HtmlTag  {
		Element_input(AttributeOf_input[] as, ContentOf_input...cs) {
			super("input", as, cs);
	}}


	//// select ////
	interface ContentOf_select extends HtmlElement  {}
	public static Element_select select(ContentOf_select... els) {return new Element_select(ATTRIBUTE_OF_select, els);}
	public static Element_select select(AttributeOf_select[] atts, ContentOf_select... els) {return new Element_select(atts, els);}
	private static final AttributeOf_select[] ATTRIBUTE_OF_select = new AttributeOf_select[0];
	interface AttributeOf_select extends IHtmlAttr  {}
	public static class Element_select extends HtmlTag  {
		Element_select(AttributeOf_select[] as, ContentOf_select...cs) {
			super("select", as, cs);
	}}


	//// optgroup ////
	interface ContentOf_optgroup extends HtmlElement  {}
	public static Element_optgroup optgroup(ContentOf_optgroup... els) {return new Element_optgroup(ATTRIBUTE_OF_optgroup, els);}
	public static Element_optgroup optgroup(AttributeOf_optgroup[] atts, ContentOf_optgroup... els) {return new Element_optgroup(atts, els);}
	private static final AttributeOf_optgroup[] ATTRIBUTE_OF_optgroup = new AttributeOf_optgroup[0];
	interface AttributeOf_optgroup extends IHtmlAttr  {}
	public static class Element_optgroup extends HtmlTag implements ContentOf_select {
		Element_optgroup(AttributeOf_optgroup[] as, ContentOf_optgroup...cs) {
			super("optgroup", as, cs);
	}}


	//// option ////
	interface ContentOf_option extends HtmlElement  {}
	public static Element_option option(ContentOf_option... els) {return new Element_option(ATTRIBUTE_OF_option, els);}
	public static Element_option option(AttributeOf_option[] atts, ContentOf_option... els) {return new Element_option(atts, els);}
	private static final AttributeOf_option[] ATTRIBUTE_OF_option = new AttributeOf_option[0];
	interface AttributeOf_option extends IHtmlAttr  {}
	public static class Element_option extends HtmlTag implements ContentOf_optgroup {
		Element_option(AttributeOf_option[] as, ContentOf_option...cs) {
			super("option", as, cs);
	}}


	//// textarea ////
	interface ContentOf_textarea extends HtmlElement  {}
	public static Element_textarea textarea(ContentOf_textarea... els) {return new Element_textarea(ATTRIBUTE_OF_textarea, els);}
	public static Element_textarea textarea(AttributeOf_textarea[] atts, ContentOf_textarea... els) {return new Element_textarea(atts, els);}
	private static final AttributeOf_textarea[] ATTRIBUTE_OF_textarea = new AttributeOf_textarea[0];
	interface AttributeOf_textarea extends IHtmlAttr  {}
	public static class Element_textarea extends HtmlTag  {
		Element_textarea(AttributeOf_textarea[] as, ContentOf_textarea...cs) {
			super("textarea", as, cs);
	}}


	//// fieldset ////
	interface ContentOf_fieldset extends HtmlElement  {}
	public static Element_fieldset fieldset(ContentOf_fieldset... els) {return new Element_fieldset(ATTRIBUTE_OF_fieldset, els);}
	public static Element_fieldset fieldset(AttributeOf_fieldset[] atts, ContentOf_fieldset... els) {return new Element_fieldset(atts, els);}
	private static final AttributeOf_fieldset[] ATTRIBUTE_OF_fieldset = new AttributeOf_fieldset[0];
	interface AttributeOf_fieldset extends IHtmlAttr  {}
	public static class Element_fieldset extends HtmlTag  {
		Element_fieldset(AttributeOf_fieldset[] as, ContentOf_fieldset...cs) {
			super("fieldset", as, cs);
	}}


	//// legend ////
	interface ContentOf_legend extends HtmlElement  {}
	public static Element_legend legend(ContentOf_legend... els) {return new Element_legend(ATTRIBUTE_OF_legend, els);}
	public static Element_legend legend(AttributeOf_legend[] atts, ContentOf_legend... els) {return new Element_legend(atts, els);}
	private static final AttributeOf_legend[] ATTRIBUTE_OF_legend = new AttributeOf_legend[0];
	interface AttributeOf_legend extends IHtmlAttr  {}
	public static class Element_legend extends HtmlTag implements ContentOf_fieldset {
		Element_legend(AttributeOf_legend[] as, ContentOf_legend...cs) {
			super("legend", as, cs);
	}}


	//// button ////
	interface ContentOf_button extends HtmlElement  {}
	public static Element_button button(ContentOf_button... els) {return new Element_button(ATTRIBUTE_OF_button, els);}
	public static Element_button button(AttributeOf_button[] atts, ContentOf_button... els) {return new Element_button(atts, els);}
	private static final AttributeOf_button[] ATTRIBUTE_OF_button = new AttributeOf_button[0];
	interface AttributeOf_button extends IHtmlAttr  {}
	public static class Element_button extends HtmlTag  {
		Element_button(AttributeOf_button[] as, ContentOf_button...cs) {
			super("button", as, cs);
	}}


	//// table ////
	interface ContentOf_table extends HtmlElement  {}
	public static Element_table table(ContentOf_table... els) {return new Element_table(ATTRIBUTE_OF_table, els);}
	public static Element_table table(AttributeOf_table[] atts, ContentOf_table... els) {return new Element_table(atts, els);}
	private static final AttributeOf_table[] ATTRIBUTE_OF_table = new AttributeOf_table[0];
	interface AttributeOf_table extends IHtmlAttr  {}
	public static class Element_table extends HtmlTag  {
		Element_table(AttributeOf_table[] as, ContentOf_table...cs) {
			super("table", as, cs);
	}}


	//// caption ////
	interface ContentOf_caption extends HtmlElement  {}
	public static Element_caption caption(ContentOf_caption... els) {return new Element_caption(ATTRIBUTE_OF_caption, els);}
	public static Element_caption caption(AttributeOf_caption[] atts, ContentOf_caption... els) {return new Element_caption(atts, els);}
	private static final AttributeOf_caption[] ATTRIBUTE_OF_caption = new AttributeOf_caption[0];
	interface AttributeOf_caption extends IHtmlAttr  {}
	public static class Element_caption extends HtmlTag implements ContentOf_table {
		Element_caption(AttributeOf_caption[] as, ContentOf_caption...cs) {
			super("caption", as, cs);
	}}


	//// thead ////
	interface ContentOf_thead extends HtmlElement  {}
	public static Element_thead thead(ContentOf_thead... els) {return new Element_thead(ATTRIBUTE_OF_thead, els);}
	public static Element_thead thead(AttributeOf_thead[] atts, ContentOf_thead... els) {return new Element_thead(atts, els);}
	private static final AttributeOf_thead[] ATTRIBUTE_OF_thead = new AttributeOf_thead[0];
	interface AttributeOf_thead extends IHtmlAttr  {}
	public static class Element_thead extends HtmlTag implements ContentOf_table {
		Element_thead(AttributeOf_thead[] as, ContentOf_thead...cs) {
			super("thead", as, cs);
	}}


	//// tfoot ////
	interface ContentOf_tfoot extends HtmlElement  {}
	public static Element_tfoot tfoot(ContentOf_tfoot... els) {return new Element_tfoot(ATTRIBUTE_OF_tfoot, els);}
	public static Element_tfoot tfoot(AttributeOf_tfoot[] atts, ContentOf_tfoot... els) {return new Element_tfoot(atts, els);}
	private static final AttributeOf_tfoot[] ATTRIBUTE_OF_tfoot = new AttributeOf_tfoot[0];
	interface AttributeOf_tfoot extends IHtmlAttr  {}
	public static class Element_tfoot extends HtmlTag implements ContentOf_table {
		Element_tfoot(AttributeOf_tfoot[] as, ContentOf_tfoot...cs) {
			super("tfoot", as, cs);
	}}


	//// tbody ////
	interface ContentOf_tbody extends HtmlElement  {}
	public static Element_tbody tbody(ContentOf_tbody... els) {return new Element_tbody(ATTRIBUTE_OF_tbody, els);}
	public static Element_tbody tbody(AttributeOf_tbody[] atts, ContentOf_tbody... els) {return new Element_tbody(atts, els);}
	private static final AttributeOf_tbody[] ATTRIBUTE_OF_tbody = new AttributeOf_tbody[0];
	interface AttributeOf_tbody extends IHtmlAttr  {}
	public static class Element_tbody extends HtmlTag implements ContentOf_table {
		Element_tbody(AttributeOf_tbody[] as, ContentOf_tbody...cs) {
			super("tbody", as, cs);
	}}


	//// colgroup ////
	interface ContentOf_colgroup extends HtmlElement  {}
	public static Element_colgroup colgroup(ContentOf_colgroup... els) {return new Element_colgroup(ATTRIBUTE_OF_colgroup, els);}
	public static Element_colgroup colgroup(AttributeOf_colgroup[] atts, ContentOf_colgroup... els) {return new Element_colgroup(atts, els);}
	private static final AttributeOf_colgroup[] ATTRIBUTE_OF_colgroup = new AttributeOf_colgroup[0];
	interface AttributeOf_colgroup extends IHtmlAttr  {}
	public static class Element_colgroup extends HtmlTag  {
		Element_colgroup(AttributeOf_colgroup[] as, ContentOf_colgroup...cs) {
			super("colgroup", as, cs);
	}}


	//// col ////
	interface ContentOf_col extends HtmlElement  {}
	public static Element_col col(ContentOf_col... els) {return new Element_col(ATTRIBUTE_OF_col, els);}
	public static Element_col col(AttributeOf_col[] atts, ContentOf_col... els) {return new Element_col(atts, els);}
	private static final AttributeOf_col[] ATTRIBUTE_OF_col = new AttributeOf_col[0];
	interface AttributeOf_col extends IHtmlAttr  {}
	public static class Element_col extends HtmlTag implements ContentOf_table, ContentOf_colgroup {
		Element_col(AttributeOf_col[] as, ContentOf_col...cs) {
			super("col", as, cs);
	}}


	//// tr ////
	interface ContentOf_tr extends HtmlElement  {}
	public static Element_tr tr(ContentOf_tr... els) {return new Element_tr(ATTRIBUTE_OF_tr, els);}
	public static Element_tr tr(AttributeOf_tr[] atts, ContentOf_tr... els) {return new Element_tr(atts, els);}
	private static final AttributeOf_tr[] ATTRIBUTE_OF_tr = new AttributeOf_tr[0];
	interface AttributeOf_tr extends IHtmlAttr  {}
	public static class Element_tr extends HtmlTag implements ContentOf_thead, ContentOf_tfoot, ContentOf_tbody {
		Element_tr(AttributeOf_tr[] as, ContentOf_tr...cs) {
			super("tr", as, cs);
	}}


	//// th ////
	interface ContentOf_th extends HtmlElement  {}
	public static Element_th th(ContentOf_th... els) {return new Element_th(ATTRIBUTE_OF_th, els);}
	public static Element_th th(AttributeOf_th[] atts, ContentOf_th... els) {return new Element_th(atts, els);}
	private static final AttributeOf_th[] ATTRIBUTE_OF_th = new AttributeOf_th[0];
	interface AttributeOf_th extends IHtmlAttr  {}
	public static class Element_th extends HtmlTag implements ContentOf_tr {
		Element_th(AttributeOf_th[] as, ContentOf_th...cs) {
			super("th", as, cs);
	}}


	//// td ////
	interface ContentOf_td extends HtmlElement  {}
	public static Element_td td(ContentOf_td... els) {return new Element_td(ATTRIBUTE_OF_td, els);}
	public static Element_td td(AttributeOf_td[] atts, ContentOf_td... els) {return new Element_td(atts, els);}
	private static final AttributeOf_td[] ATTRIBUTE_OF_td = new AttributeOf_td[0];
	interface AttributeOf_td extends IHtmlAttr  {}
	public static class Element_td extends HtmlTag  {
		Element_td(AttributeOf_td[] as, ContentOf_td...cs) {
			super("td", as, cs);
	}}

}
