package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import database.JDBCUtil;
import model.Account;
import model.Role;

public class AccountDAO implements DAOInterface<Account>{
	public static AccountDAO getInstance() {
		// TODO Auto-generated method stub
		return new AccountDAO();
	}
	
	public Account selectByUsername(String username) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Accounts WHERE Username = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("Id"));
				account.setUsername(resultSet.getString("Username"));
				account.setPassword(resultSet.getString("Password"));
				Role role = Role.user;
				account.setRole(role.getRoleByString(resultSet.getString("Role")));
				account.setBalance(resultSet.getInt("Balance"));
				account.setCreatedAt(resultSet.getDate("CreatedAt"));
				return account;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return null;
	}

	@Override
	public void insert(Account account) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "INSERT INTO Accounts (Username, Password, Role, Balance, CreatedAt) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setString(3, account.getRole().getStringRole());
			preparedStatement.setInt(4, account.getBalance());
			preparedStatement.setDate(5, (Date) account.getCreatedAt());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "UPDATE Accounts SET Password=?, Role=?, Balance=? WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, account.getPassword());
			preparedStatement.setString(2, account.getRole().getStringRole());
			preparedStatement.setInt(3, account.getBalance());
			preparedStatement.setInt(4, account.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "DELETE FROM Accounts WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, account.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
		}
	}

	@Override
	public TreeSet<Account> selectAll() {
		// TODO Auto-generated method stub
		TreeSet<Account> accounts = new TreeSet<Account>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Accounts";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("Id"));
				account.setUsername(resultSet.getString("Username"));
				account.setPassword(resultSet.getString("Password"));
				Role role = Role.user;
				account.setRole(role.getRoleByString(resultSet.getString("Role")));
				account.setBalance(resultSet.getInt("Balance"));
				account.setCreatedAt(resultSet.getDate("CreatedAt"));
				accounts.add(account);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return accounts;
	}

	@Override
	public Account selectById(Account account) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Accounts WHERE Id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, account.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				account.setUsername(resultSet.getString("Username"));
				account.setPassword(resultSet.getString("Password"));
				Role role = Role.user;
				account.setRole(role.getRoleByString(resultSet.getString("Role")));
				account.setBalance(resultSet.getInt("Balance"));
				account.setCreatedAt(resultSet.getDate("CreatedAt"));
				return account;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return null;
	}

	@Override
	public TreeSet<Account> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		TreeSet<Account> accounts = new TreeSet<Account>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Accounts WHERE "+condition;
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account();
				account.setId(resultSet.getInt("Id"));
				account.setUsername(resultSet.getString("Username"));
				account.setPassword(resultSet.getString("Password"));
				Role role = Role.user;
				account.setRole(role.getRoleByString(resultSet.getString("Role")));
				account.setBalance(resultSet.getInt("Balance"));
				account.setCreatedAt(resultSet.getDate("CreatedAt"));
				accounts.add(account);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return accounts;
	}
}
