package org.yuzz.web;
import static org.yuzz.xml.NodeStatics.a;
import static org.yuzz.xml.NodeStatics.div;
import static org.yuzz.xml.NodeStatics.n;
import static org.yuzz.xml.NodeStatics.t;

import java.util.Arrays;
import java.util.List;

import org.snuvy.DbTable;
import org.snuvy.Schema;
import org.yuzz.functor.Functions;
import org.yuzz.xml.Node;
import org.yuzz.xml.NodeOperators;
import org.yuzz.xml.NodeStatics;
import org.yuzz.xml.Xhtml.Table;
import org.yuzz.xml.Xhtml.Tr;



public class ListView implements Noder {
	private final DbTable _dbTable;
	public ListView(DbTable dbTable) {
		_dbTable = dbTable;
	
	}

	//@Override
	public Node node() throws Throwable {
		final Table htmlTable = NodeStatics.table();
		Schema schema = _dbTable.getSchema();
		// retrieve all rows using index byHost into List<Tr> using RowToTr
		RowToTr rowToTr = new RowToTr(schema);
		htmlTable.add(rowToTr.header());
		List<Tr> results = _dbTable.map(rowToTr, schema.getPk());
		// add the results into the html table byHostTable
		//Functions.reduce(new NodeOperators.AddNodes<Tr, Table>(), htmlTable, Arrays.asList(results));
		return div(a("class","dblist"),
						n("h3", t(_dbTable.getSchema().getName())), 
						htmlTable);


	}


}
