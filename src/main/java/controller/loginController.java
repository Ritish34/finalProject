package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
import services.UserService;
import services.UserServiceImp;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(LoginController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
		//get form parameter value
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		logger.debug("inside loginController");
		
		try {
			//make object of service
			UserService service = new UserServiceImp();
			
			//call service method
			User user = service.getUserRole(email, pass);
			
			//get role from database
			String role = user.getRole();
				
			if(role == null || role.isEmpty()) {
//				out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>Username And Password Mismatched!!</h4></center>");
				out.print("<input type='hidden' id='response' value='Username And Password Mismatched!!'>");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getFname());
				session.setAttribute("userid", user.getId());
				session.setAttribute("role", user.getRole());
				if(role.equals("Admin")) {
					//send redirect to admin home page
					response.sendRedirect("Admin-Dashboard.jsp");
				}
				else{
					//send redirect to user home page
					response.sendRedirect("User-Dashboard.jsp");
				}
			}

		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
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
