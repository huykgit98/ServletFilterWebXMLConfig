package main.java.com.huynguyen.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.UserAgent;

public class BlockingFilter implements Filter {
        
    public BlockingFilter() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
        System.out.println("BlockingFilter init!");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("Entering BlockingFilter.doFilter()");
        HttpServletRequest request = (HttpServletRequest) req;
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        if (userAgent.getBrowser().getName().equals("Microsoft Edge")) {
            System.out.println("user has just accessed this site using Microsoft Edge browser");
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>You are not authorized to access</font>");
        } else {
            chain.doFilter(request, resp);
        }
        System.out.print("\n");

    }
    
    /**
     * Destroy method for this filter
     */
    public void destroy() {
        System.out.println("BlockingFilter destroy!");

    }


}
