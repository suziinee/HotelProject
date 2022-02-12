package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.CustomerDTO;
import model.util.DBUtil;


public class CustomerDAO {

	
	
	/* insert */
	
	public static boolean addCustomer(CustomerDTO customer) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into customer values(?, ?, ?)");
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getId());
			pstmt.setString(3, customer.getPw());
			
			int result = pstmt.executeUpdate();
		
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* delete (탈퇴) */
	
	public static boolean deleteCustomer(String customerId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from customer where customer_id=?");
			pstmt.setString(1, customerId);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* update (name) -> IdNotFoundException */
	
	public static boolean updateCustomerName(String customerId, String name) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement("update customer set customer_name=? where customer_id=?");
			pstmt.setString(1, name);
			pstmt.setString(2, customerId);
			
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* customer id로 customer 정보 반환 */
	
	public static CustomerDTO getCustomer(String customerId) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CustomerDTO customer = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from customer where customer_id=?");
			pstmt.setString(1, customerId);
			rset = pstmt.executeQuery();
			if(rset.next()){
				customer = new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return customer; //customer toString에서 pw 빼기
	}
	
	
	/* 모든 customer 정보 반환 */
	
	public static ArrayList<CustomerDTO> getAllCustomers() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CustomerDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from customer");
			rset = pstmt.executeQuery();
			list = new ArrayList<CustomerDTO>();
			while(rset.next()){
				list.add(new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	
	
}
