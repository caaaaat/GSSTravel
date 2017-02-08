package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailBean;
import model.DetailService;
import model.DetailVO;
import model.FamilyVO;
@WebServlet(
		urlPatterns = {("/detail_insert")}
)
public class DetailInsertServlet extends HttpServlet {
	private DetailService detailService = new DetailService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String prodaction = req.getParameter("prodaction");
		DetailBean bean = new DetailBean();
		DetailVO Dbean = new DetailVO();
		FamilyVO Fbean = new FamilyVO();
		String temp_empNo = req.getParameter("emp_No");
		String tra_No = req.getParameter("tra_no");
		String name = req.getParameter("name");
		String temp_money = req.getParameter("det_money");
		int emp_No = 0;
		int fam_No = 0;
		float money = 0;
		String temp_famNo = null;
		
		Map<String, String> DetInError = new HashMap<String, String>();
		req.setAttribute("DetInError", DetInError);
		
		if(temp_empNo != null && temp_empNo.trim().length()!=0){
			try {
				emp_No = Integer.parseInt(temp_empNo);
				temp_famNo = detailService.SELECT_Name(emp_No, name);
				System.out.println(temp_famNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				DetInError.put("InError", "員工編號必須輸入整數");
			}
		}
		
		if(money <= 0 && temp_money != null && temp_money.trim().length()!=0){
			try {
				money = Float.parseFloat(temp_money);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				DetInError.put("InError", "報名總金額必須輸入數字且大於零");
			}
		}
		
		if ("儲存".equals(prodaction)) {
			
			if(money > 0 && temp_famNo != null && "員工".equals(temp_famNo)){
				Dbean.setEmp_No(emp_No);
				Dbean.setTra_No(tra_No);
				Dbean.setDet_money(money);
				detailService.insert_emp(Dbean);
				req.setAttribute("tra_no", tra_No);
				req.getRequestDispatcher("/detail?tra_no=" + tra_No).forward(req, resp);
				return;
			}else if(money > 0 && temp_famNo != null && !"員工".equals(temp_famNo)){
				fam_No = Integer.parseInt(temp_famNo);
				Dbean.setEmp_No(emp_No);
				Dbean.setFam_No(fam_No);
				Dbean.setTra_No(tra_No);
				Dbean.setDet_money(money);
				detailService.insert(Dbean);
				req.setAttribute("tra_no", tra_No);
				req.getRequestDispatcher("/detail?tra_no=" + tra_No).forward(req, resp);
				return;
			}else if(money <= 0 && temp_famNo != null){
				DetInError.put("InError", "報名總金額必須輸入數字且大於零");
				req.setAttribute("tra_no", tra_No);
				req.getRequestDispatcher("/Detail_Insert.jsp").forward(req, resp);
			}else{
				DetInError.put("InError", "找不到此姓名");
				req.setAttribute("tra_no", tra_No);
				req.getRequestDispatcher("/Detail_Insert.jsp").forward(req, resp);
			}
			
		}
		
		if ("回前頁".equals(prodaction)) {
			req.setAttribute("tra_no", tra_No);
			req.getRequestDispatcher("/detail?tra_no=" + tra_No).forward(req, resp);
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
