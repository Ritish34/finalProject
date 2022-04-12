package util;

import java.io.IOException;
import java.util.Enumeration;
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

import controller.GetAddressData;

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
		
		System.out.println("inside filter");
		StringBuilder error=new StringBuilder(""); 
		
		Enumeration<String> en = request.getParameterNames();
		
		while(en.hasMoreElements()) {
			String param = en.nextElement();
			String value = request.getParameter(param);
			
				if(!(param.equals("addressid")) && isNull(value)) {
					error.append(param+",");
//					logger.debug(param);
				}
				request.setAttribute(param, value);
		}
		
		if(error.toString().equals("")) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		else
		{
			error.append(" can't be Empty!!");
			response.setContentType("text/html");
			request.setAttribute("error", error);
			request.getRequestDispatcher("/Registration.jsp").forward(request, response);
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
        return (matcher.matches());
	}
}
