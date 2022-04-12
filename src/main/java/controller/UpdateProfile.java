package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Address;
import model.User;
import services.AddressService;
import services.AddressServiceImp;
import services.UserService;
import services.UserServiceImp;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
@MultipartConfig(maxFileSize = 16177215)
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(UpdateProfile.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		
		//set response type
				response.setContentType("text/html");
				
				//get all data from registration page
				String fname = request.getParameter("first_name");
				String lname = request.getParameter("last_name");
				String email = request.getParameter("email");
				String date = request.getParameter("date");
				String phone = request.getParameter("phone");
				String gender = request.getParameter("gender");
				
				//convert checkbox data to string
				StringBuffer buf = new StringBuffer();
				String arr[]=request.getParameterValues("checkbox");
				for(int i=0;i< arr.length;i++){
//					favlangs+=arr[i]+" ";
					buf.append(arr[i]);
					buf.append(" ");
				}
				String favlangs=buf.toString();
				
				Part filePart = request.getPart("image");
				
				//take addresses
				String[] addressid = request.getParameterValues("addressid");
				String[] address = request.getParameterValues("address[]");
				String[] zip = request.getParameterValues("zip[]");
				String[] city = request.getParameterValues("city[]");
				String[] state = request.getParameterValues("state[]");
				String[] contry = request.getParameterValues("contry[]");
				
				User user = new User();
				user.setFname(fname);
				user.setLname(lname);
				user.setEmail(email);
				user.setGender(gender);
				user.setLang(favlangs);
				user.setPhone(phone);
				user.setDob(date);
				user.setRole("User");
				
				ArrayList<Address> updatelist = new ArrayList<Address>();
				ArrayList<Address> list = new ArrayList<Address>();
				//create address object
				for(int i=0;i<zip.length;i++) {
					if(addressid[i].isBlank()) {
						Address obj = new Address();
						obj.setAddress(address[i]);
						obj.setCity(city[i]);
						obj.setContry(contry[i]);
						obj.setState(state[i]);
						obj.setZip(Integer.parseInt(zip[i]));
						list.add(obj);
					}
					else {
						Address obj = new Address();
						obj.setAddressid(Integer.parseInt(addressid[i]));
						obj.setAddress(address[i]);
						obj.setCity(city[i]);
						obj.setContry(contry[i]);
						obj.setState(state[i]);
						obj.setZip(Integer.parseInt(zip[i]));
						updatelist.add(obj);
					}
				}
				
				UserService service = new UserServiceImp();
				
				AddressService ser = new AddressServiceImp();
				
				try {
					int userid = service.updateUser(user, filePart);
					
					if(userid == -1) {
						out.print("error");
					}
					else {
						boolean flag = ser.updateAddress(list, updatelist, userid);
						if(flag) {
							out.print("Data Upadted Succesfully");
						}
						else {
							out.print("error");
						}
					}
					request.setAttribute("UserId", userid);
					request.setAttribute("status", "edituser");
					
					//include request
					RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
					rd.include(request, response);
				} catch (ClassNotFoundException | IOException | SQLException e) {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
