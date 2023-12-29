package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import database.JDBCUtil;
import model.Product;

public class ProductDAO implements DAOInterface<Product>{
	public static ProductDAO getInstance() {
		// TODO Auto-generated method stub
		return new ProductDAO();
	}

	@Override
	public void insert(Product product) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "INSERT INTO Products (Name, Price, Quantity, ImageUrl, CreatedAt) VALUES (?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getImageUrl());
			preparedStatement.setDate(5, (Date) product.getCreatedAt());
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
	public void update(Product product) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "UPDATE Products SET Name=?, Price=?, Quantity=?, ImageUrl=? WHERE Id=?";			
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setInt(2, product.getPrice());
			preparedStatement.setInt(3, product.getQuantity());
			preparedStatement.setString(4, product.getImageUrl());
			preparedStatement.setInt(5, product.getId());
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
	public void delete(Product product) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "DELETE FROM Products WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, product.getId());
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
	public TreeSet<Product> selectAll() {
		// TODO Auto-generated method stub
		TreeSet<Product> products = new TreeSet<Product>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Products";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("Id"));
				product.setName(resultSet.getString("Name"));
				product.setPrice(resultSet.getInt("Price"));
				product.setQuantity(resultSet.getInt("Quantity"));
				product.setImageUrl(resultSet.getString("ImageUrl"));
				product.setCreatedAt(resultSet.getDate("CreatedAt"));
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return products;
	}

	@Override
	public Product selectById(Product product) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Products WHERE Id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, product.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				product.setName(resultSet.getString("Name"));
				product.setPrice(resultSet.getInt("Price"));
				product.setQuantity(resultSet.getInt("Quantity"));
				product.setImageUrl(resultSet.getString("ImageUrl"));
				product.setCreatedAt(resultSet.getDate("CreatedAt"));
				return product;
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
	public TreeSet<Product> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		TreeSet<Product> products = new TreeSet<Product>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Products WHERE "+condition;
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("Id"));
				product.setName(resultSet.getString("Name"));
				product.setPrice(resultSet.getInt("Price"));
				product.setQuantity(resultSet.getInt("Quantity"));
				product.setImageUrl(resultSet.getString("ImageUrl"));
				product.setCreatedAt(resultSet.getDate("CreatedAt"));
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return products;
	}
}
