package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailBean;
import model.DetailService;
import model.DetailVO;
@WebServlet(
		urlPatterns = {("/detail_insert")}
)
public class DetailInsertServlet extends HttpServlet {
	private DetailService detailService = new DetailService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		DetailBean bean = new DetailBean();
		
		String temp_empNo = req.getParameter("emp_No");
		String tra_No = req.getParameter("tra_no");
		String name = req.getParameter("name");
		int emp_No = Integer.parseInt(temp_empNo);
		String temp_famNo = detailService.SELECT_Name(emp_No, name);
		
		if(temp_famNo != null && "員工".equals(temp_famNo)){
			temp_famNo = "NULL";
		}else if(temp_famNo != null){
			Integer.parseInt(temp_famNo);
		}
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
