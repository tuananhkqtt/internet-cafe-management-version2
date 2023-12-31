package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.TreeSet;

import database.JDBCUtil;
import model.Account;
import model.InvoiceDetail;
import model.Product;
import model.Role;

public class InvoiceDetailDAO implements DAOInterface<InvoiceDetail>{
	public static InvoiceDetailDAO getInstance() {
		// TODO Auto-generated method stub
		return new InvoiceDetailDAO();
	}
	
	public TreeSet<InvoiceDetail> selectByInvoiceId(int invoiceId) {
		// TODO Auto-generated method stub
		TreeSet<InvoiceDetail> invoiceDetails = new TreeSet<InvoiceDetail>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = JDBCUtil.getConnection();
			String sqlStatement = "SELECT * FROM InvoiceDetails WHERE InvoiceId = ?";
			preparedStatement = connection.prepareStatement(sqlStatement);
			preparedStatement.setInt(1, invoiceId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				InvoiceDetail invoiceDetail = new InvoiceDetail();
				invoiceDetail.setInvoiceId(invoiceId);
				invoiceDetail.setProductId(resultSet.getInt("ProductId"));
				invoiceDetail.setQuantity(resultSet.getInt("Quantity"));
				invoiceDetail.setPrice(resultSet.getInt("Price"));
				invoiceDetail.setAmount(resultSet.getInt("Amount"));
				invoiceDetails.add(invoiceDetail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection(connection);
			JDBCUtil.closePreparedStatement(preparedStatement);
			JDBCUtil.closeResultSet(resultSet);
		}
		return invoiceDetails;
	}

	@Override
	public void insert(InvoiceDetail t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(InvoiceDetail t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(InvoiceDetail t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TreeSet<InvoiceDetail> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InvoiceDetail selectById(InvoiceDetail t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeSet<InvoiceDetail> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
