package main.java.com.huynguyen.filters;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class LogFilter implements Filter {
    HashMap<String, Integer> register;

    public LogFilter() {
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("LogFilter init!");
        register = new HashMap<String, Integer>();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        System.out.println("Entering LogFilter.doFilter()");

        String IpAddr = request.getRemoteAddr();
        int count = 1;
        if (register.containsKey(IpAddr)) {
            count += register.get(IpAddr);
            register.replace(IpAddr, count);
        } else {
            register.put(IpAddr, count);
        }
        System.out.print("\n");
        System.out.println("IP: " + IpAddr + "; count: " + count + "; Date/time: "
                + new java.util.Date());
        System.out.print("\n");
        // pass the request along the filter chain
        chain.doFilter(request, response);

    }
    
    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
        /* Called before the Filter instance is removed from service by the web container*/
        System.out.println("LogFilter destroy!");
    }

}
