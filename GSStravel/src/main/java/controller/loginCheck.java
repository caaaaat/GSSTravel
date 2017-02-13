package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeDAO;


@WebFilter("/*")
public class loginCheck implements Filter {


	public loginCheck() {

	}


	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		//請求源頭
		String path = servletRequest.getRequestURI();
		String pro = servletRequest.getContextPath();
		
		if (path.indexOf("login") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (session.getAttribute("emp_No") == null || "".equals(session.getAttribute("emp_No"))) {
			servletResponse.sendRedirect(pro+"/notlogin.jsp");
		} else {
			EmployeeDAO employeeDAO=new EmployeeDAO();//資料庫年度資訊
			String date = new SimpleDateFormat("yyyy").format(new Date());// 現在系統時
			if(employeeDAO.year()!=Integer.parseInt(date)){
				employeeDAO.updateYear(Integer.parseInt(date));
				employeeDAO.updateEmp();
			}
			chain.doFilter(request, response);
			return;
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
	
	}

}
