package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TotalAmountDAO;

@WebServlet(
		urlPatterns={"/product.controller"}
)
public class TotalAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收資料
		String temp1=request.getParameter("emp_No");
		String tra_No=request.getParameter("tra_No");
		String temp2[] = request.getParameterValues("TA_money");
		System.out.println(temp2.length);
		for(String a:temp2){
			System.out.println(a);
		}
		//轉換資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);
		
		int emp_No=Integer.parseInt(temp1);
		float TA_money;
//		if(temp2!=null && temp2.length()!=0){
//			TA_money=Float.parseFloat(temp2);
//		}else
//			TA_money=0;
//		System.out.println(emp_No+tra_No+TA_money);
		TotalAmountDAO TADAO=new TotalAmountDAO();
		
//		boolean b=TADAO.update(TA_money, tra_No, emp_No);
		//request.getRequestDispatcher("Detail_Money.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
