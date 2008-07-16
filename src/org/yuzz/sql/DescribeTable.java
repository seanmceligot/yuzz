package org.yuzz.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DescribeTable {
	
	public void describe(Connection conn, final String table) throws SQLException, IOException {
		SqlQuery searchQuery = new SqlQuery() {
			public String getSql() {
				return "select * from "+table+" where rownum < 1";
			}
			public void populate(PreparedStatement pstmt) throws SQLException {}
			
			public void process(ResultSet rs) throws SQLException {
				ResultSetMetaData md = rs.getMetaData();
				int count = md.getColumnCount();
				for (int i=1; i < count+1; i++) {
					System.out.println(md.getColumnName(i)+" "+md.getColumnTypeName(i)+" "+md.getPrecision(i)+" "+md.getScale(i));
				}
			}
		};
		SqlHelper sh = new SqlHelper();
		sh.runQuery(conn, searchQuery);
		
		
	}
	public static void main(String[] args) {
			String pf = args[0];
			String db = args[1];
			String table = args[2];
			try {
				//new DescribeTable().describe(pf,db,table);
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}
}
