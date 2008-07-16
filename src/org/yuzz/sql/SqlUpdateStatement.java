package org.yuzz.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlUpdateStatement {
	public String getSql();
	public void populate(PreparedStatement pstmt) throws SQLException;
}
