package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Address;
import model.User;
import services.UserService;
import services.UserServiceImp;


@WebServlet("/RegController")
@MultipartConfig(maxFileSize = 16177215)
public class RegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(RegController.class);   
	
    public RegController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Make PrintWriter Object to Write data
		PrintWriter out = response.getWriter();
		
		//set response type
		response.setContentType("text/html");
		
		//get all data from registration page
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String date = request.getParameter("date");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		
		//convert checkbox data to string
		StringBuffer buf = new StringBuffer();
		String arr[]=request.getParameterValues("checkbox");
		for(int i=0;i< arr.length;i++){
			buf.append(arr[i]);
			buf.append(" ");
		}
		String favlangs=buf.toString();
		
		Part filePart = request.getPart("image");
		
		//take addresses
		String[] address = request.getParameterValues("address[]");
		String[] zip = request.getParameterValues("zip[]");
		String[] city = request.getParameterValues("city[]");
		String[] state = request.getParameterValues("state[]");
		String[] contry = request.getParameterValues("contry[]");
		
		//create user object
		User user = new User();
		user.setFname(fname);
		user.setLname(lname);
		user.setEmail(email);
		user.setGender(gender);
		user.setLang(favlangs);
		user.setPassword(pass);
		user.setPhone(phone);
		user.setDob(date);
		user.setRole("User");
		
		ArrayList<Address> list = new ArrayList<Address>();
		//create address object
		for(int i=0;i<zip.length;i++) {
			Address obj = new Address();
			obj.setAddress(address[i]);
			obj.setCity(city[i]);
			obj.setContry(contry[i]);
			obj.setState(state[i]);
			obj.setZip(Integer.parseInt(zip[i]));
			list.add(obj);
		}
				
		try {
			logger.info("Inside Registration Controller");
			
			UserService service = new UserServiceImp();
			
			//call dao method for storing data
			boolean flag = service.saveUser(user,list,filePart);//,file
			if(flag) {
				
				logger.info("Registration Sucessfull");
				HttpSession session = request.getSession(false);
				if (session.getAttribute("username") == null) {
//					out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>User Successfully Registered</h4></center>");
					out.print("<input type='hidden' id='response' value='success'>");
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.include(request, response);
				} else {
					request.setAttribute("status", "adduser");
					request.setAttribute("back", "Registration");
					out.print("<input type='hidden' id='response' value='Added'>");
//					out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>User Successfully Added</h4></center>");
					RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
					rd.include(request, response);
				}
			}
			else {
				
				logger.info("Registration Error");
				request.setAttribute("back", "Registration");
				out.print("<input type='hidden' id='response' value='error'>");
//				out.print("<center><h4 style='color:red;background:#9053c7;'>Registration fails</h4></center>");
				RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
				rd.include(request, response);
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1   must-revalidate

			response.setHeader("Pragma", "no-cache"); //HTTP 1.0

			response.setHeader("Expires" ,"0"); //Proxy
		} catch (ClassNotFoundException | FileNotFoundException | SQLException | ParseException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			logger.debug(e);
//			out.print(e);
		}
		finally {
			//out closed
			if (out != null) {
				out.close();
			}
		}
	}
}
