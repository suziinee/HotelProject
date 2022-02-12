package model;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.MessageException;
import exception.NotExistException;
import model.dto.BookingDTO;
import model.dto.CustomerDTO;
import model.dto.RoomDTO;


public class HotelService {

	
	
	/* -----customer CRUD----- */
	
	public static void notExistCustomer(String customerId) throws NotExistException, SQLException{
		CustomerDTO customer = CustomerDAO.getCustomer(customerId);
		if(customer == null){
			throw new NotExistException("검색하는 고객이 미존재합니다");
		}
	}
	
	
	/* customer 저장 (CustomerDAO.addCustomer()) */
	
	public static boolean addCustomer(CustomerDTO customer) throws MessageException{
		boolean result = false;
		try{
			result = CustomerDAO.addCustomer(customer);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 고객입니다 다시 시도 하세요");
		}
		return result;
	}
	
	
	/* customer 삭제 (CustomerDAO.deleteCustomer()) */
	
	public static boolean deleteCustomer(String customerId) throws SQLException, NotExistException{
		notExistCustomer(customerId);
		boolean result = CustomerDAO.deleteCustomer(customerId);
		if(!result){
			throw new NotExistException("고객 정보 삭제 실패");
		}
		return result;
	}
	
	
	/* customer Name 수정 (CustomerDAO.updateCustomerName()) */
	
	public static boolean updateCustomerName(String customerId, String name) throws SQLException, NotExistException{		
		notExistCustomer(customerId);
		boolean result = CustomerDAO.updateCustomerName(customerId, name);
		if(!result){
			throw new NotExistException("고객 이름 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* customer id로 customer 정보 반환 (CustomerDAO.getCustomer()) */
	
	public static CustomerDTO getCustomer(String customerId) throws SQLException, NotExistException{
		CustomerDTO customer = CustomerDAO.getCustomer(customerId);
		if(customer == null){
			throw new NotExistException("검색하는 고객이 미존재합니다");
		}
		return customer;
	}
	
	
	/* 모든 customer 정보 반환 (CustomerDAO.getAllCustomers()) */
	
	public static ArrayList<CustomerDTO> getAllCustomers() throws SQLException{
		return CustomerDAO.getAllCustomers();
	}
	
	
	
	/* -----Booking CRUD----- */
	
	public static void notExistBooking(int bookingReservationNum) throws NotExistException, SQLException{
		BookingDTO booking = BookingDAO.getBooking(bookingReservationNum);
		if(booking == null){
			throw new NotExistException("검색하는 예약이 미존재합니다");
		}
	}
	
	
	/* booking 저장 (BookingDAO.addBooking()) */
	
	public static boolean addBooking(BookingDTO booking) throws MessageException{
		boolean result = false;
		try{
			result = BookingDAO.addBooking(booking);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 예약입니다 다시 시도 하세요");
		}
		return result;
	}
	
	
	/* booking 삭제 (BookingDAO.deleteBooking()) */
	
	public static boolean deleteBooking(int bookingReservationNum) throws SQLException, NotExistException{
		notExistBooking(bookingReservationNum);
		boolean result = BookingDAO.deleteBooking(bookingReservationNum);
		if(!result){
			throw new NotExistException("예약 정보 삭제 실패");
		}
		return result;
	}
	
	
	/* booking roomType 수정 (BookingDAO.updateBookingRoomType()) */
	
	public static boolean updateBookingRoomType(int bookingReservationNum, String roomType) throws SQLException, NotExistException{		
		notExistBooking(bookingReservationNum);
		boolean result = BookingDAO.updateBookingRoomType(bookingReservationNum, roomType);
		if(!result){
			throw new NotExistException("예약 방 타입 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* booking adults 수정 (BookingDAO.updateBookingAdults()) */
	
	public static boolean updateBookingAdults(int bookingReservationNum, int adults) throws SQLException, NotExistException{		
		notExistBooking(bookingReservationNum);
		boolean result = BookingDAO.updateBookingAdults(bookingReservationNum, adults);
		if(!result){
			throw new NotExistException("예약 성인 인원 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* booking kids 수정 (BookingDAO.updateBookingKids()) */
	
	public static boolean updateBookingKids(int bookingReservationNum, int kids) throws SQLException, NotExistException{		
		notExistBooking(bookingReservationNum);
		boolean result = BookingDAO.updateBookingKids(bookingReservationNum, kids);
		if(!result){
			throw new NotExistException("예약 성인 인원 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* booking reservationNum으로 booking 정보 반환 (BookingDAO.getBooking()) */
	
	public static BookingDTO getBooking(int bookingReservationNum) throws SQLException, NotExistException{
		BookingDTO booking = BookingDAO.getBooking(bookingReservationNum);
		if(booking == null){
			throw new NotExistException("검색하는 예약이 미존재합니다");
		}
		return booking;
	}
	
	
	/* 모든 booking 정보 반환 (BookingDAO.getAllBookings()) */
	
	public static ArrayList<BookingDTO> getAllBookings() throws SQLException{
		return BookingDAO.getAllBookings();
	}
	
	
	
	/* -----room CRUD----- */
	
	public static void notExistRoom(String roomId) throws NotExistException, SQLException{
		RoomDTO room = RoomDAO.getRoom(roomId);
		if(room == null){
			throw new NotExistException("검색하는 재능 기부자가 미 존재합니다.");
		}
	}
	
	
	/* room 저장 (RoomDAO.addRoom()) */
	
	public static boolean addRoom(RoomDTO room) throws MessageException{
		boolean result = false;
		try{
			result = RoomDAO.addRoom(room);
		}catch(SQLException s){
			throw new MessageException("이미 존재하는 객실입니다 다시 시도 하세요");
		}
		return result;
	}
	
	
	/* room 삭제 (RoomDAO.deleteRoom()) */
	
	public static boolean deleteRoom(String roomId) throws SQLException, NotExistException{
		notExistRoom(roomId);
		boolean result = RoomDAO.deleteRoom(roomId);
		if(!result){
			throw new NotExistException("객실 정보 삭제 실패");
		}
		return result;
	}
		
	
	/* room price 수정 (RoomDAO.updateRoomPrice()) */
	
	public static boolean updateRoomPrice(String roomId, int price) throws SQLException, NotExistException{		
		notExistRoom(roomId);
		boolean result = RoomDAO.updateRoomPrice(roomId, price);
		if(!result){
			throw new NotExistException("객실 가격 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* room bedSize 수정 (RoomDAO.updateRoomBedSize()) */
	
	public static boolean updateRoomBedSize(String roomId, String bedSize) throws SQLException, NotExistException{		
		notExistRoom(roomId);
		boolean result = RoomDAO.updateRoomBedSize(roomId, bedSize);
		if(!result){
			throw new NotExistException("객실 침대 사이즈 정보 갱신 실패");
		}
		return result;
	}
	
	
	/* room id로 room 정보 반환 (RoomDAO.getRoom()) */
	
	public static RoomDTO getRoom(String roomId) throws SQLException, NotExistException{
		RoomDTO room = RoomDAO.getRoom(roomId);
		if(room == null){
			throw new NotExistException("검색하는 객실이 미존재합니다");
		}
		return room;
	}
	
	
	/* 모든 room 정보 반환 (RoomDAO.getAllRooms()) */
	
	public static ArrayList<RoomDTO> getAllRooms() throws SQLException{
		return RoomDAO.getAllRooms();
	}
	
	
	
}
