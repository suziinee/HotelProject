package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerDAO;
import model.dto.BookingDTO;
import model.BookingDAO;


@WebServlet("/bookNum")
public class BookingReservationNumValidate extends HttpServlet {
       
	
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bookingReservationNum = Integer.parseInt(request.getParameter("bookingReservationNum"));
		Optional<Integer> opt = Optional.ofNullable(bookingReservationNum);
		ArrayList<BookingDTO> all = null;
		try {
			all = BookingDAO.getAllBookings();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(BookingDTO booking : all) {
			if (opt.isPresent() && booking.getReservationNum() == bookingReservationNum) {
				request.setAttribute("booking", booking);
				request.getRequestDispatcher("bookingDetail.jsp").forward(request, response);
				break;
			}
		}
		response.sendRedirect("bookingNumFail.jsp");
	}
	
	

}

