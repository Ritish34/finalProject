package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import services.regdata;

@WebServlet("/RegController")
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
		PrintWriter out = response.getWriter();
		
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String date = request.getParameter("date");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		
		String favlangs="";
		String favlang[]=request.getParameterValues("checkbox");
		for(int i=0;i< favlang.length;i++){
			favlangs+=favlang[i]+" ";
		}
		
		String file=request.getParameter("file");
		
		String[] address = request.getParameterValues("address[]");
		String[] zip = request.getParameterValues("zip[]");
		String[] city = request.getParameterValues("city[]");
		String[] state = request.getParameterValues("state[]");
		String[] contry = request.getParameterValues("contry[]");
		
		regdata.regUser(fname,lname,email,pass,date,phone,gender,favlangs,file,address,zip,city,state,contry){
			
		}
	}

}
