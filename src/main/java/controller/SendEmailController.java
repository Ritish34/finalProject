package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
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
import util.GeneratePassword;
import util.SendEmail;

/**
 * Servlet implementation class SendEmailController
 */
@WebServlet("/SendEmail")
public class SendEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(SendEmailController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		
		GeneratePassword gp = new GeneratePassword();
		SendEmail mail = new SendEmail();
		
		StringBuilder sb = new StringBuilder();
		sb.append(gp.generateRandomPassword(4, 97, 122));
		sb.append("@");
		sb.append(gp.generateRandomPassword(3, 48, 57));
		
		String newPassword = sb.toString();
		
		UserService service = new UserServiceImp();
		
		try {
			
			boolean flag = service.updatePassword(newPassword, email);
			
			if(flag) {
				String mailresponse =mail.sendmail(newPassword, email);
				out.print(mailresponse);
			}
			else {
				out.print("fail");
			}
			
		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
			out.print(e);
		}
		finally {
			//out closed
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
