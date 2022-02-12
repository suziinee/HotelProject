package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.BookingDTO;
import model.util.DBUtil;


public class BookingDAO {

	
	
	/* insert -> RNumDupulicatedException*/
	
	public static boolean addBooking(BookingDTO booking) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into booking values(?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, booking.getCheckin());
			pstmt.setString(2, booking.getCheckout());
			pstmt.setString(3,  booking.getRoomType());
			pstmt.setInt(4, booking.getAdults());
			pstmt.setInt(5, booking.getKids());
			pstmt.setInt(6, booking.getReservationNum());
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* delete */
	
	public static boolean deleteBooking(int reservationNum) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from booking where booking_reservationNum=?");
			pstmt.setInt(1, reservationNum);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	
	/* update (roomType) */
	
	public static boolean updateBookingRoomType(int reservationNum, String roomType ) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update booking set booking_roomType=? where booking_reservationNum=?");
			pstmt.setString(1, roomType);
			pstmt.setInt(2, reservationNum);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* update (adults) */
	
	public static boolean updateBookingAdults(int reservationNum, int adults) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update booking set booking_adults=? where booking_reservationNum=?");
			pstmt.setInt(1, adults);
			pstmt.setInt(2, reservationNum);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* update (kids) */
	
	public static boolean updateBookingKids(int reservationNum, int kids) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update booking set booking_kids=? where booking_reservationNum=?");
			pstmt.setInt(1, kids);
			pstmt.setInt(2, reservationNum);
			int result = pstmt.executeUpdate();
			if(result == 1){
				return true;
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	
	/* booking reservationNum으로 booking 정보 반환 */
	
	public static BookingDTO getBooking(int reservationNum) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		BookingDTO booking = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from booking where booking_reservationNum=?");
			pstmt.setInt(1, reservationNum);
			rset = pstmt.executeQuery();
			if(rset.next()){
				booking = new BookingDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getInt(4) ,rset.getInt(5), rset.getInt(6));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return booking;
	}
	
	
	/* 모든 booking 정보 반환 */
	
	public static ArrayList<BookingDTO> getAllBookings() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<BookingDTO> list = null;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from booking");
			rset = pstmt.executeQuery();
			list = new ArrayList<BookingDTO>();
			while(rset.next()){
				list.add(new BookingDTO(rset.getString(1), rset.getString(2), rset.getString(3), rset.getInt(4) ,rset.getInt(5), rset.getInt(6)));
			}
		}finally{
			DBUtil.close(con, pstmt, rset);
		}
		return list;
	}
	
	
	/* BookingDTO 생성 후 다음 reservationNum 반환 */
	
	public static int addBookingFromReservationNum(String checkin, String checkout, String roomType, int adults, int kids) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		ResultSet rset2 = null;
		int nextReservationNum = 0;
		try{
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into booking values(?, ?, ?, ?, ?, booking_reservationNum_seq.nextval)");
			pstmt.setString(1, checkin);
			pstmt.setString(2, checkout);
			pstmt.setString(3, roomType);
			pstmt.setInt(4, adults);
			pstmt.setInt(5, kids);
			pstmt2 = con.prepareStatement("select booking_reservationNum_seq.currval from dual");
			rset = pstmt.executeQuery();
			rset2 = pstmt2.executeQuery();
			if (rset.next() && rset2.next()) {
				nextReservationNum = rset2.getInt(1);
			}
		}finally{
			DBUtil.close(con, pstmt);
		}
		return nextReservationNum;
	}
	
	
	
}
