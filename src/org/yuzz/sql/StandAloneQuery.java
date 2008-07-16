package org.yuzz.sql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class StandAloneQuery {
	public int update(final Connection conn, final String sql) throws SQLException, IOException {
		SqlUpdateStatement update = new SqlUpdateStatement() {
			public String getSql() {
				return sql;
			}
			public void populate(PreparedStatement pstmt) throws SQLException {}
			
		};
		SqlHelper ju = new SqlHelper();
		int result = ju.runUpdate(conn, update);
		return result;
		
	}
	public void query(final Connection conn, final String sql) throws SQLException, IOException {
		SqlQuery searchQuery = new SqlQuery() {
			public String getSql() {
				return sql;
			}
			public void populate(PreparedStatement pstmt) throws SQLException {}
			
			public void process(ResultSet rs) throws SQLException {
				System.out.println(sql);
				ResultSetMetaData md = rs.getMetaData();
				int count = md.getColumnCount();
				for (int i=1; i < count+1; i++) {
					System.out.print(md.getColumnName(i)+" | ");
				}
				System.out.println();
				while(rs.next()) {
					for (int i=1; i < count+1; i++) {
						System.out.print(rs.getString(i)+" | ");
					}
					System.out.println();
				}

			}
		};
		SqlHelper ju = new SqlHelper();
		ju.runQuery(conn, searchQuery);
		
		
	}
	public static void main(String[] args) {
			String pf = "../config/ant.properties";
			String db = "tfg";
			try {
				StandAloneQuery query = new StandAloneQuery();
				//query.update(pf,db,"delete from tfg_user_role where  user_id = 'mcel0013'");
				//query.update(pf,db,"insert into tfg_user_role values ('mcel0013', 'TFG_ADMIN')");
				
				//query.query(conn,"tfg","select * from TFG_USER_ROLE");
				//query.query(pf,"tfg","Select dodaac, addr_line1 from tdr_dodaac where dodaac like 'FB%'");
				//query.query(pf,"spotbid","Select owner,table_name from all_tables");
				//query.query(pf,"tfg","Select owner,table_name from all_tables where owner = 'TFG' order by table_name");
				//query.query(pf,"tfg","Select * from bdest where bus_id = 23404");
				//query.query(pf,"tfg","Select * from bus where rownum < 2");
				//query.query(pf,"tfg","Select * from bservice where rownum < 2");
				
				//query.query(pf,"tfg","Select * from site1 where dodaac='FB4634'");
				//query.query(pf,"tfg","Select * from tfg_safehaven where dodaac='FB4634'");
				//query.query(pf,"tfg","Select * from site1 where safehaven_dodaac_only is not null");
				//query.query(pf,"tfg","Select * from all_columns where table_name = 'bservice'");
				//
			} catch (Throwable e) {
				e.printStackTrace();
			}
	}
}
