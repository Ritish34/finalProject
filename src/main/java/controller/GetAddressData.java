package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.Address;
import services.AddressService;
import services.AddressServiceImp;


/**
 * Servlet implementation class GetAddressData
 */
@WebServlet("/GetAddressData")
public class GetAddressData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAddressData() {
        super();
        // TODO Auto-generated constructor stub
    }
    private final static Logger logger = LogManager.getLogger(GetAddressData.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		String id = request.getParameter("UserId");
		int userid;
		
		//if userid is not present than use session userid
		if(id == null| id.equals("")) {
			
			userid = (Integer) session.getAttribute("userid");
		}
		else {
			userid = Integer.parseInt(id);
		}
		
		logger.debug(userid);
		
		
		AddressService impl = new AddressServiceImp();
		
		List<Address> list= null;
		try {
			list = impl.getUserAddress(userid);
			
			session.setAttribute("addresslist", list);
			
			//convert data into gson type
			  Gson gson = new GsonBuilder().setPrettyPrinting().create();
			  JsonObject json = new JsonObject(); 
			  json.add("data", gson.toJsonTree(list)); 
			  out.print(json);
			 
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
