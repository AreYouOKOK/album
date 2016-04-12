package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@WebFilter(filterName = "requestFilter", urlPatterns = { "*" })
public class RequestFilter implements Filter {
	
	private static Log log =  LogFactory.getLog(RequestFilter.class.getName()); 
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path =httpRequest.getRequestURL().toString();
		log.info("ÇëÇóµÄurl"+path);
		httpRequest.setCharacterEncoding("utf-8");
		if(path.endsWith(".jsp")){
			httpResponse.sendRedirect("jsp/error.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
