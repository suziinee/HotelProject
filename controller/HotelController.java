package controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HotelService;
import model.dto.BookingDTO;
import model.dto.CustomerDTO;
import model.dto.RoomDTO;


@WebServlet("/controller")
public class HotelController extends HttpServlet {
	


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command");

		
		/* 모든 crud 메소드 호출 */
		
		try{
			if(command.equals("addCustomer")){
				addCustomer(request, response);
			}else if(command.equals("deleteCustomer")){
				deleteCustomer(request, response);
			}else if(command.equals("updateCustomerName")){
				updateCustomerName(request, response);
			}else if(command.equals("addBooking")){
				addBooking(request, response);
			}else if(command.equals("deleteBooking")){
				deleteBooking(request, response);
			}else if(command.equals("updateBookingRoomType")) {
				updateBookingRoomType(request, response);
			}else if(command.equals("updateBookingAdults")){
				updateBookingAdults(request, response);
			}else if(command.equals("updateBookingKids")) {
				updateBookingKids(request, response);
			}else if(command.equals("addRoom")){
				addRoom(request, response);
			}else if(command.equals("deleteRoom")){
				deleteRoom(request, response);
			}else if(command.equals("updateRoomPrice")){
				updateRoomPrice(request, response);
			}else if(command.equals("updateRoomBedSize")){
				updateRoomBedSize(request, response);
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			s.printStackTrace();
		}

	}
	
	
	
	/* -------------호출된 개별 메소드 구현------------- */
	
	/* customer */
	/* customer 추가 */
	
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String name = request.getParameter("customerName");
		String id = request.getParameter("customerId");
		String pw = request.getParameter("customerPw");
			
		CustomerDTO customer = new CustomerDTO(name, id, pw);
		try{
			boolean result = HotelService.addCustomer(customer);
			if(result){
				request.setAttribute("customer", customer);
				request.setAttribute("successMsg", "가입 완료");
//				url = "activistDetail.jsp"; -> 가입 완료시 넘어가는 화면 jsp
			}else{
				request.setAttribute("errorMsg", "가입을 다시 시도하세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* customer 삭제 */
	
	public void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String id = request.getParameter("customerId");
			if(HotelService.deleteCustomer(id)){
				request.setAttribute("customerAll", HotelService.getAllCustomers());
//				url = "activistList.jsp";
			}else{
				request.setAttribute("errorMsg", "회원 탈퇴를 재 실행 해 주세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", "예약중인 Room이 있습니다");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* customer 이름 수정 */
	
	public void updateCustomerName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String id = request.getParameter("customerId");
			String name = request.getParameter("customerName");
			HotelService.updateCustomerName(id, name);
			request.setAttribute("customer", HotelService.getCustomer(id));
//			url = "activistDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* booking */
	/* booking 추가 -> 추가 완료시 booking.html */
	
	protected void addBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String checkin = request.getParameter("bookingCheckin"); 
		String checkout = request.getParameter("bookingCheckout"); 
		String roomType = request.getParameter("bookingRoomType");
		int adults = Integer.parseInt(request.getParameter("bookingAdults"));
		int kids = Integer.parseInt(request.getParameter("bookingKids"));
		int reservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
			
		BookingDTO booking = new BookingDTO(checkin, checkout, roomType, adults, kids, reservationNum);
		try{
			boolean result = HotelService.addBooking(booking);
			if(result){
				request.setAttribute("booking", booking);
				request.setAttribute("successMsg", "예약 완료");
				url = "booking.html"; 
			}else{
				request.setAttribute("errorMsg", "예약을 다시 시도하세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* booking 삭제 -> 삭제 완료시 bookingDelete.jsp */
	
	public void deleteBooking(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			int reservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
			if(HotelService.deleteBooking(reservationNum)){
				request.setAttribute("bookingAll", HotelService.getAllBookings());
				url = "bookingDelete.jsp";
			}else{
				request.setAttribute("errorMsg", "예약 취소를 재 실행 해 주세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", "예약 취소가 실패되었습니다.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* booking roomType 수정 -> 수정 완료시 bookingModifyDetail.jsp */
	
	public void updateBookingRoomType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			int reservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
			String roomType = request.getParameter("bookingRoomType");
			HotelService.updateBookingRoomType(reservationNum, roomType);
			request.setAttribute("booking", HotelService.getBooking(reservationNum));
			url = "bookingModifyDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* booking adults 수정 -> 수정 완료시 bookingModifyDetail.jsp*/
	
	public void updateBookingAdults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			int reservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
			int adults = Integer.parseInt(request.getParameter("bookingAdults"));
			HotelService.updateBookingAdults(reservationNum, adults);
			request.setAttribute("booking", HotelService.getBooking(reservationNum));
			url = "bookingModifyDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* booking kids 수정 -> 수정 완료시 bookingModifyDetail.jsp */
	
	public void updateBookingKids(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			int reservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
			int kids = Integer.parseInt(request.getParameter("bookingKids"));
			HotelService.updateBookingKids(reservationNum, kids);
			request.setAttribute("booking", HotelService.getBooking(reservationNum));
			url = "bookingModifyDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* room */
	/* room 추가 */
	
	protected void addRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		
		String id = request.getParameter("roomId");
		String roomType = request.getParameter("roomRoomType");
		int price = Integer.parseInt(request.getParameter("roomPrice"));
		String bedSize = request.getParameter("roomBedSize");
			
		RoomDTO room = new RoomDTO(id, roomType, price, bedSize);
		try{
			boolean result = HotelService.addRoom(room);
			if(result){
				request.setAttribute("room", room);
				request.setAttribute("successMsg", "객실 추가 완료");
//				url = "activistDetail.jsp"; -> 객실 추가 완료시 넘어가는 화면 jsp
			}else{
				request.setAttribute("errorMsg", "다시 시도하세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* room 삭제 */
	
	public void deleteRoom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String id = request.getParameter("roomId");
			if(HotelService.deleteRoom(id)){
				request.setAttribute("roomAll", HotelService.getAllRooms());
//				url = "activistList.jsp";
			}else{
				request.setAttribute("errorMsg", "객실 삭제를 재 실행 해 주세요");
			}
		}catch(Exception s){
			request.setAttribute("errorMsg", "예약이 있는 객실입니다.");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* room price 수정 */
	
	public void updateRoomPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String id = request.getParameter("roomId");
			int price = Integer.parseInt(request.getParameter("roomPrice"));
			HotelService.updateRoomPrice(id, price);
			request.setAttribute("room", HotelService.getRoom(id));
//			url = "activistDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	/* room bedSize 수정 */
	
	public void updateRoomBedSize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String id = request.getParameter("roomId");
			String bedSize = request.getParameter("roomBedSize");
			HotelService.updateRoomBedSize(id, bedSize);
			request.setAttribute("room", HotelService.getRoom(id));
//			url = "activistDetail.jsp";
		}catch(Exception s){
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	
}
