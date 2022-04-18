package util;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * Servlet Filter implementation class ValidationFilter
 */
@WebFilter(filterName = "ValidationFilter" , urlPatterns = {"/RegController"})
public class ValidationFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	private final static Logger logger = LogManager.getLogger(ValidationFilter.class);

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		logger.debug("inside filter");
		StringBuilder error=new StringBuilder(""); 
		
		String fname = request.getParameter("first_name");
		String lname = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String date = request.getParameter("date");
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		//take addresses
		String[] address = request.getParameterValues("address[]");
		String[] zip = request.getParameterValues("zip[]");
		String[] city = request.getParameterValues("city[]");
		String[] state = request.getParameterValues("state[]");
		String[] contry = request.getParameterValues("contry[]");
		
		if(isNull(fname) || !checkRegex("[a-zA-Z\\s]+",fname)) {
			error.append("first_name ,");
		}
		else {
			request.setAttribute("first_name", fname);
		}
		if(isNull(lname) || !checkRegex("^([a-zA-Z])+(\\s)*$",lname)) {
			error.append("last_name ,");
		}
		else {
			request.setAttribute("last_name", lname);
		}
		if(isNull(email) || !checkRegex("^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}$",email)) {
			error.append("email ,");
		}
		else {
			request.setAttribute("email", email);
		}
		if(isNull(pass) || !checkRegex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",pass)) {
			error.append("password ,");
		}
		else {
			request.setAttribute("password", pass);
		}
		if(isNull(date)) {
			error.append("date ,");
		}
		else {
			request.setAttribute("date", date);
		}
		if(isNull(phone)) {
			error.append("phone ,");
		}
		else {
			request.setAttribute("phone", phone);
		}
		if(isNull(gender)) {
			error.append("gender ,");
		}
		for(int i=0;i<address.length;i++) {
			if(isNull(address[i]) || isNull(zip[i]) || isNull(city[i]) || isNull(state[i]) || isNull(contry[i]) ) {
				error.append("Address ,");
			}
		}

//		Enumeration<String> en = request.getParameterNames();
//		
//		while(en.hasMoreElements()) {
//			String param = en.nextElement();
//			String value = request.getParameter(param);
//			
//				if(!(param.equals("addressid")) && isNull(value)) {
//					error.append(param+",");
////					logger.debug(param);
//				}
//			map.put(param, value);	
//		}
		
		if(error.toString().equals("")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else
		{		
			error.append(" can't be Empty!!");
			response.setContentType("text/html");
			request.setAttribute("error", error);
			request.setAttribute("back", "Registration");
			request.getRequestDispatcher("Registration.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public boolean isNull(String element) {
		if(element.isEmpty()) {
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkRegex(String regex,String element) {
		
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(element); 
        boolean flag = matcher.matches();
        return flag;
	}
}
