package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BookingDAO;
import model.dto.BookingDTO;


@WebServlet("/confirmation")
public class BookingConfirmation extends HttpServlet {



	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String checkin = request.getParameter("bookingCheckin");
		String checkout = request.getParameter("bookingCheckout");
		String roomType = request.getParameter("bookingRoomType");
		

		/* type=number는 0 인식이 안됨 */
		
		int adults;
		try {
			adults = Integer.parseInt(request.getParameter("bookingAdults"));
		} catch(Exception e) {
			adults = 0;
		}
		
		int kids;
		try {
			kids = Integer.parseInt(request.getParameter("bookingKids"));
		} catch(Exception e) {
			kids = 0;
		}
		
		
		/* reservationNum으로 BookingDTO 생성 */
		
		try {
			//여기서 이미 adding까지 완료
			int reservationNum = BookingDAO.addBookingFromReservationNum(checkin, checkout, roomType, adults, kids);
			
			//setAttribute를 위해 Booking 객체 만들기
			BookingDTO booking = new BookingDTO(checkin, checkout, roomType, adults, kids, reservationNum);
			request.setAttribute("booking", booking);
			request.getRequestDispatcher("bookingDetail.jsp").forward(request, response);			
		} catch (Exception e) {
			response.sendRedirect("bookingConfirmationFail.jsp");
		}
	}



}
