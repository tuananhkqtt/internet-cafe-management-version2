package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import database.JDBCUtil;
import model.Computer;

public class ComputerDAO implements DAOInterface<Computer>{
	public static ComputerDAO getInstance() {
		// TODO Auto-generated method stub
		return new ComputerDAO();
	}
	
	public Computer selectByName(String name) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Computers WHERE Name = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Computer computer = new Computer();
				computer.setId(resultSet.getInt("Id"));
				computer.setName(resultSet.getString("Name"));
				computer.setPrice(resultSet.getInt("Price"));
				computer.setCreatedAt(resultSet.getDate("CreatedAt"));
				return computer;
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
	public void insert(Computer computer) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "INSERT INTO Computers (Name, Price, CreatedAt) VALUES (?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDouble(2, computer.getPrice());
			preparedStatement.setDate(3, (Date) computer.getCreatedAt());
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
	public void update(Computer computer) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "UPDATE Computers SET Name=?, Price=? WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setInt(2, computer.getPrice());
			preparedStatement.setInt(3, computer.getId());
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
	public void delete(Computer computer) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "DELETE FROM Computers WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, computer.getId());
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
	public TreeSet<Computer> selectAll() {
		// TODO Auto-generated method stub
		TreeSet<Computer> computers = new TreeSet<Computer>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Computers";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Computer computer = new Computer();
				computer.setId(resultSet.getInt("Id"));
				computer.setName(resultSet.getString("Name"));
				computer.setPrice(resultSet.getInt("Price"));
				computer.setCreatedAt(resultSet.getDate("CreatedAt"));
				computers.add(computer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return computers;
	}

	@Override
	public Computer selectById(Computer computer) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Computers WHERE Id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, computer.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				computer.setId(resultSet.getInt("Id"));
				computer.setName(resultSet.getString("Name"));
				computer.setPrice(resultSet.getInt("Price"));
				computer.setCreatedAt(resultSet.getDate("CreatedAt"));
				return computer;
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
	public TreeSet<Computer> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		TreeSet<Computer> computers = new TreeSet<Computer>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Computers WHERE "+condition;
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Computer computer = new Computer();
				computer.setId(resultSet.getInt("Id"));
				computer.setName(resultSet.getString("Name"));
				computer.setPrice(resultSet.getInt("Price"));
				computer.setCreatedAt(resultSet.getDate("CreatedAt"));
				computers.add(computer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return computers;
	}

}
