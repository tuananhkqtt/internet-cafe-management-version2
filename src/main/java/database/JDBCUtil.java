package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	private static DatabaseInfor databaseInfor;

	public static void setDatabaseInfor(DatabaseInfor databaseInfor) {
		JDBCUtil.databaseInfor = databaseInfor;
	}

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:"+databaseInfor.getDbms()+"://"+databaseInfor.getServerName()+":"+databaseInfor.getPort()+";databaseName="+databaseInfor.getDatabaseName()+";trustServerCertificate=true;encrypt=true;";
		Connection c = DriverManager.getConnection(url, databaseInfor.getUsername(), databaseInfor.getPassword());
		return c;
	}

	public static void closeConnection(Connection c) {
		try {
			if (c != null) {
				c.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement(PreparedStatement p) {
		try {
			if (p != null) {
				p.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet r) {
		try {
			if (r != null) {
				r.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printInfo(Connection c) {
		try {
			if (c != null) {
				DatabaseMetaData mtdt = c.getMetaData();
				System.out.println(mtdt.getDatabaseProductName());
				System.out.println(mtdt.getDatabaseProductVersion());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}