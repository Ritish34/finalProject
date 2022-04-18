package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import services.UserService;
import services.UserServiceImp;

/**
 * Servlet implementation class CheckEmailController
 */
@WebServlet("/CheckEmail")
public class CheckEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmailController() {
        super();
        // TODO Auto-generated constructor stub
    }

    private final static Logger logger = LogManager.getLogger(CheckEmailController.class);
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//make printwriter object
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("emailId");
		logger.debug("Inside Controller");
		
		try {
			UserService ser = new UserServiceImp();
			
			if(ser.checkDupEmail(email)) {
				out.write("Duplicate");
			}
			else {
				out.write("New");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
//			out.print(e);
		}
		finally {
			//out closed
			if(out != null)
			out.close();
		}
	}
}
