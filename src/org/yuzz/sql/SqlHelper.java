package org.yuzz.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static java.util.logging.Level.*;

public class SqlHelper {
    private static final Logger _log = Logger.getLogger(SqlHelper.class.getName());
	private final ThreadLocal<Connection> _threadConnection = new ThreadLocal<Connection>();
	private static boolean _useCounter = true;
	private int _counter = 0;

	public static void usefinalize() {
		_useCounter = false;
	}
	public void close( Connection conn ) {
		closeConnection(conn);
	}

	public void close(Connection conn, String threadName) {
		closeConnection(conn);
	}
	public void finalizeConnection() {
		Connection conn = _threadConnection.get();
		try {
			if (conn != null) {
				_threadConnection.remove();
				conn.close();
			}
		} catch (SQLException se) {
			_log.log(SEVERE, "finalizeConnection" ,se);
		}
	}/*
	public void assertClosed() {
		String threadName = Thread.currentThread().getName();
		ConnectionHolder holder = ( ConnectionHolder ) mConnections.get( threadName );
		if ( ( holder == null ) || ( holder.getConnection() == null ) ) {
			return;
		}
		_classLog.fatal("JDBC leak detected (not closing)", new Exception());
		closeConnection(holder.getConnection());
	}*/
	public void closeConnection( Connection conn, String threadName ){
		closeConnection(conn);
	}
	
	public void closeConnection( Connection conn ) {
		if (conn == null) {
			return;
		}
		_counter--;
		if (!_useCounter)  {
			return;
		}
		if (_counter == 0) {
				finalizeConnection();
		}
	}

	public static void close( ResultSet resultset ) {
		if ( resultset != null ) {
			try {
				resultset.close();
			} catch ( SQLException e ) {
				_log.log(SEVERE, "Unable to execute resultset close", e );
			}
		}
	}
	public static void close( Statement statement ) {
		if ( statement != null ) {
			try {
				statement.close();
			} catch ( SQLException e ) {
				_log.log(SEVERE, "Unable to execute statement close" , e);
			}
		}
	}
	/**
	 * like getConnection, but will return null is a connection does not already exist
	 */
	public Connection getExistingConnection() {
		Connection conn = _threadConnection.get();
		if (conn == null) {
			return null;
		}
		return conn;
	}
	
	public Connection getExistingConnection(String threadName) {
		return getExistingConnection();
	}

	public void close() {
		closeConnection(getExistingConnection());
	}
	private boolean testConnection(Connection conn) {
		Statement st = null;
		try {
			st = conn.createStatement();
			ResultSet rs =st.executeQuery("select count(*) from dual");
			if (rs.next()) {
			_log.log(FINE,"testConnection: passed");
				return true;
			}
			rs.close();
		} catch (Throwable th) {
			_log.log(SEVERE,"testConnection", th);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException se) {}
			}
		}
		
		// only close if not true
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException se) {
				_log.log(FINE,se.toString(),se);
			}
		}
		return false;
	}
	public Connection getConnection(String name) throws SQLException, NamingException {	
		Connection conn = _threadConnection.get();

		if (conn == null) {
			conn = createConnection(name);
			_threadConnection.set(conn);
		}
		
		if (!testConnection(conn)) {
			conn = createConnection(name);
			_threadConnection.set(conn);
		}
		_counter++;
		//_classLog.warn("getConnection: "+conn);//, new Exception());
		return conn;	
	}
	private Connection createConnection(String name) throws NamingException,
			SQLException {
		Connection conn;
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup(name);
		conn = ds.getConnection();
		return conn;
	}
	/**
	 * Closs all remaining connections when object is destroyed
	 */
	protected void finalize() {
		finalizeConnection();
	}
	
	public void rollback() {
		rollback(getExistingConnection());	
	}	
	public void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (Throwable th) {
				// no reason to handle this because we're already handling an error
				_log.log(SEVERE,"exception during rollback", th);
			}
		}
	}	
	public void close(Connection conn, Statement st) {
		close(st);
		close(conn);
	}
	public void close(Connection conn, Statement st, ResultSet rs) {
		close(rs);
		close(st);
		close(conn);
	}
	public void runQuery(Connection conn, SqlQuery query) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		try{
			pstmt = conn.prepareStatement( query.getSql() );
			if (_log.isLoggable(FINE)) { 
				pstmt = new DebugPreparedStatement( pstmt,query.getSql() );
			}
			query.populate(pstmt);
			rset = pstmt.executeQuery();
			query.process(rset);
	    } finally {
	    	close( conn, pstmt, rset );
		}
	}
	public int runUpdate(Connection conn, SqlUpdateStatement query) throws SQLException {
		PreparedStatement pstmt = null;

		try{
			pstmt = conn.prepareStatement( query.getSql() );
			if (_log.isLoggable(FINE)) {
				pstmt = new DebugPreparedStatement( pstmt,query.getSql() );
			}
			query.populate(pstmt);
			int result = pstmt.executeUpdate();
			return result;
	    } finally {
	    	close( conn, pstmt, null);
		}
	}
}
