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

import services.AddressService;
import services.AddressServiceImp;

/**
 * Servlet implementation class DeleteAddress
 */
@WebServlet("/DeleteAddress")
public class DeleteAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAddress() {
        super();
        // TODO Auto-generated constructor stub
    }

    private final static Logger logger = LogManager.getLogger(DeleteAddress.class);
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//set response content type
		response.setContentType("text/html");
		
		//make printwriter object
		PrintWriter out = response.getWriter();
		
		//take addressid which we want to delete
		String arr[] = request.getParameterValues("Array[]");
		
		//make service class object
		AddressService service = new AddressServiceImp();
		
		try {
			boolean flag = service.deleteAddress(arr);
			
			if(flag) {
				out.print("sucess");
			}
			else {
				out.print("fail");
			}
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e);
//			out.print(e);
		}
		finally {
			//out closed
			if(out != null)
			out.close();
		}
		
	}

}
