package org.yuzz.web;
import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;
import static org.yuzz.xml.NodeStatics.td;
import static org.yuzz.xml.NodeStatics.th;
import static org.yuzz.xml.NodeStatics.tr;
import org.snuvy.DbRow;
import org.snuvy.Schema;

import java.util.Iterator;

import org.yuzz.xml.Xhtml.Tr;
import org.yuzz.functor.Function.Fun1;

public class RowToTr extends Fun1<Tr, DbRow> {
	private final Schema _schema;

	public RowToTr(Schema schema) {
		_schema = schema;
	}

	@Override
	public Tr apply(DbRow row) {
		 //long rowId = row.getRowId();
		 Tr tr = tr(a("class", "dbrow"));
		 Iterator<String> colnames = _schema.getColumnNames();
		 while(colnames.hasNext()) {
			 String colname = colnames.next();
			 tr.add(td(t(row.toString(colname))));
		 }
		 return tr;
	}

	public Tr header() {
		 Iterator<String> colnames = _schema.getColumnNames();
		 Tr tr = tr(a("class", "dbrowheader"));
		 while(colnames.hasNext()) {
			 String colname = colnames.next();
			 tr.add(th(t(colname)));
		 }
		 return tr;
	}
}
