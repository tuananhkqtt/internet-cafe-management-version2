package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeSet;

import database.JDBCUtil;
import model.BillStatus;
import model.Invoice;

public class InvoiceDAO implements DAOInterface<Invoice>{
	public static InvoiceDAO getInstance() {
		// TODO Auto-generated method stub
		return new InvoiceDAO();
	}
	
	public TreeSet<Invoice> selectAllOrderByCreatedAtOffset(int offset, int limit) {
		// TODO Auto-generated method stub
		TreeSet<Invoice> invoices = new TreeSet<Invoice>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Invoices ORDER BY CreatedAt DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, offset);
			preparedStatement.setInt(2, limit);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt("Id"));
				invoice.setAccountId(resultSet.getInt("AccountId"));
				invoice.setComputerId(resultSet.getInt("ComputerId"));
				invoice.setTotal(resultSet.getInt("Total"));
				invoice.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
				BillStatus status = BillStatus.uncompleted;
				invoice.setStatus(status.getBillStatusByString(resultSet.getString("Status")));
				invoice.setCreatedBy(resultSet.getInt("CreatedBy"));
				invoices.add(invoice);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return invoices;
	}
	
	public int getTodayRevenueSum() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT SUM(Total) AS Sum FROM Invoices WHERE CAST(createdAt AS DATE) = CAST(GETDATE() AS DATE) AND Status = 'completed'";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
                return resultSet.getInt("Sum");
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return 0;
	}

	@Override
	public void insert(Invoice t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Invoice invoice) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "UPDATE Invoices SET Status=?, Total=? WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setString(1, invoice.getStatus().getStringBillStatus());
			preparedStatement.setInt(2, invoice.getTotal());
			preparedStatement.setInt(3, invoice.getId());
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
	public void delete(Invoice invoice) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "DELETE FROM Invoices WHERE Id=?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, invoice.getId());
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
	public TreeSet<Invoice> selectAll() {
		// TODO Auto-generated method stub
		TreeSet<Invoice> invoices = new TreeSet<Invoice>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Invoices";
			preparedStatement = connection.prepareStatement(sqlStatement);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultSet.getInt("Id"));
				invoice.setAccountId(resultSet.getInt("AccountId"));
				invoice.setComputerId(resultSet.getInt("ComputerId"));
				invoice.setTotal(resultSet.getInt("Total"));
				invoice.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
				BillStatus status = BillStatus.uncompleted;
				invoice.setStatus(status.getBillStatusByString(resultSet.getString("Status")));
				invoice.setCreatedBy(resultSet.getInt("CreatedBy"));
				invoices.add(invoice);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return invoices;
	}

	@Override
	public Invoice selectById(Invoice invoice) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM Invoices WHERE Id = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, invoice.getId());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				invoice.setAccountId(resultSet.getInt("AccountId"));
				invoice.setComputerId(resultSet.getInt("ComputerId"));
				invoice.setTotal(resultSet.getInt("Total"));
				invoice.setCreatedAt(resultSet.getTimestamp("CreatedAt"));
				BillStatus status = BillStatus.uncompleted;
				invoice.setStatus(status.getBillStatusByString(resultSet.getString("Status")));
				invoice.setCreatedBy(resultSet.getInt("CreatedBy"));
				return invoice;
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
	public TreeSet<Invoice> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
