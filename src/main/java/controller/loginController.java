package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.User;
import services.CheckLoginUser;
import services.UserService;

@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(RegController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Make PrintWriter Object to Write data
		PrintWriter out = response.getWriter();
		
		//set response type
		response.setContentType("text/html");
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		try {
			UserService service = new UserService();
			
			User user = service.getUserRole(email, pass);
			
//			String str = CheckLoginUser.getUser(email, pass);
//			logger.info(str);
			
			/*
			 * String[] output = str.split("@");
			 * 
			 * String role = output[0]; String username = output[1];
			 */
			
			if(user.getRole().equals("Admin")) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getFname());
				//send redirect to admin home page
				response.sendRedirect("Admin-Dashboard.jsp");
			}
			else if(user.getRole().equals("User")) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getFname());
				out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>Login Success</h4></center>");
				//send redirect to user home page
			}
			else {
				out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>Login Fails</h4></center>");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			out.print(e);
		}
		finally {
			out.close();
		}
		
	}

}
