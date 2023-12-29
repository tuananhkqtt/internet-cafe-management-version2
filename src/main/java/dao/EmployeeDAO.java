package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import database.JDBCUtil;
import model.Employee;
import model.Role;

public class EmployeeDAO implements DAOInterface<Employee>{
	public static EmployeeDAO getInstance() {
		// TODO Auto-generated method stub
		return new EmployeeDAO();
	}
	
	public Employee selectByAccountId(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Employees WHERE AccountId = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employee.getAccountId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setId(resultSet.getInt("Id"));
				employee.setName(resultSet.getString("Name"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				return employee;
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
	public void insert(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "INSERT INTO Employees (Name, AccountId, Email, PhoneNumber, Address) VALUES (?, ?, ?, ?, ?)";			
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setInt(2, employee.getAccountId());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setString(4, employee.getPhoneNumber());
			preparedStatement.setString(5, employee.getAddress());
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
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "UPDATE Employees SET Name=?, Email=?, PhoneNumber=?, Address=? WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmail());
			preparedStatement.setString(3, employee.getPhoneNumber());
			preparedStatement.setString(4, employee.getAddress());
			preparedStatement.setInt(5, employee.getId());
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
	public void delete(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "DELETE FROM Employees WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employee.getId());
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
	public TreeSet<Employee> selectAll() {
		// TODO Auto-generated method stub
		TreeSet<Employee> employees = new TreeSet<Employee>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Employees";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getInt("Id"));
				employee.setName(resultSet.getString("Name"));
				employee.setAccountId(resultSet.getInt("AccountId"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				employees.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return employees;
	}

	@Override
	public Employee selectById(Employee employee) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Employees WHERE Id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, employee.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				employee.setId(resultSet.getInt("Id"));
				employee.setName(resultSet.getString("Name"));
				employee.setAccountId(resultSet.getInt("AccountId"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				return employee;
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
	public TreeSet<Employee> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		TreeSet<Employee> employees = new TreeSet<Employee>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Employees WHERE "+condition;
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();	
				employee.setId(resultSet.getInt("Id"));
				employee.setName(resultSet.getString("Name"));
				employee.setAccountId(resultSet.getInt("AccountId"));
				employee.setEmail(resultSet.getString("Email"));
				employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
				employee.setAddress(resultSet.getString("Address"));
				employees.add(employee);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return employees;
	}

}
