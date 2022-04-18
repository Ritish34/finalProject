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

import model.User;
import services.UserService;
import services.UserServiceImp;

/**
 * Servlet implementation class GetOneUserData
 */
@WebServlet("/GetOneUserData")
public class GetOneUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOneUserData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private final static Logger logger = LogManager.getLogger(GetOneUserData.class);
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("UserId");
		int userid;
		if(id == null|| id.equals("")) {
			
			HttpSession session = request.getSession(false);
			
			userid = (Integer) session.getAttribute("userid");
		}
		else {
			userid = Integer.parseInt(id);
		}
		
		logger.info(userid);
		
		
		UserService impl = new UserServiceImp();
		
		List<User> list= null;
		try {
			list = impl.getUser(userid);
			
			//convert into gson data
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
