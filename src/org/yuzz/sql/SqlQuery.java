package org.yuzz.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface SqlQuery {
	public String getSql();
	public void populate(PreparedStatement pstmt) throws SQLException;
	public void process(ResultSet rs) throws SQLException;
}
