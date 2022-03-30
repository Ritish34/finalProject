package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import services.regdata;

//@WebServlet("/RegController")
public class RegController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LogManager.getLogger(RegController.class);   
	
    public RegController() {
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
		
		//check session already started or not
		HttpSession session = request.getSession(false);
		
		//get all data from registration page
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String date = request.getParameter("date");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		//convert checkbox data to string
		String favlangs="";
		String arr[]=request.getParameterValues("checkbox");
		for(int i=0;i< arr.length;i++){
			favlangs+=arr[i]+" ";
		}
		//take image input
//		String file=request.getParameter("file");
//		Part filePart
//        = request.getPart("file");
		
		//take addresses
		String[] address = request.getParameterValues("address[]");
		String[] zip = request.getParameterValues("zip[]");
		String[] city = request.getParameterValues("city[]");
		String[] state = request.getParameterValues("state[]");
		String[] contry = request.getParameterValues("contry[]");
		
//		out.println(fname);
//		out.println(lname);
//		out.println(email);
//		out.println(pass);
//		out.println(date);
//		out.println(phone);
//		out.println(gender);
//		out.println(favlangs);
//		out.println(filePart);
//		out.print(address.length);
//		out.print(city.length);
		
		try {
			logger.info("Inside Registration Controller");
			
			//call dao method for storing data
			boolean flag = regdata.regUser(fname,lname,email,pass,date,phone,gender,favlangs,address,zip,city,state,contry);//,file
			if(flag) {
				
				logger.info("Registration Sucessfull");
				out.print("<center><h4 style='color: #e2eae2;background:#9053c7;'>User Successfully Registered</h4></center>");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
			}
			else {
				
				logger.info("Registration Error");
				out.print("<center><h4 style='color:red;background:#9053c7;'>Registration fails</h4></center>");
				RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
				rd.include(request, response);
			}
		} catch (ClassNotFoundException | FileNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			out.print(e);
		}
	}

}
