package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CustomerDAO;
import model.dto.CustomerDTO;


@WebServlet("/login")
public class LoginValidate extends HttpServlet {
       

    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("customerId");
        String pw = request.getParameter("customerPw");
        
        ArrayList<CustomerDTO> all = null;
		try {
			all = CustomerDAO.getAllCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(CustomerDTO customer : all) {
			if(customer.getId().equals(id) && customer.getPw().equals(pw)) {
				request.setAttribute("customer", customer);
				request.getRequestDispatcher("booking.html").forward(request, response);
				break;
			}
		}
		response.sendRedirect("loginFail.jsp");
    }
    

	
}

