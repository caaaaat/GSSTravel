package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
	private static final long serialVersionUID = 1L;
	private DetailService detailService = new DetailService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String prodaction = req.getParameter("prodaction");
		String temp_empNo = req.getParameter("emp_No");
		String tra_No = req.getParameter("tra_no");
		String name = req.getParameter("name");
		String temp_money = req.getParameter("det_money");
		
		DetailBean bean = new DetailBean();
		DetailVO Dbean = new DetailVO();
		FamilyVO Fbean = new FamilyVO();
		
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
			} catch (NumberFormatException e) {
				e.printStackTrace();
				DetInError.put("InError", "員工編號必須輸入整數");
			}
		}
		if ("儲存".equals(prodaction)) {	
//			List<DetailBean> list= detailService.select_emp_fam_No(tra_No);
//			for(DetailBean b:list){
//				if(emp_No==b.getEmp_No()){
//					if(){
//						
//					} 
//				}else{
//						
//					}
//				}
//				Dbean.setEmp_No(emp_No);
//				Dbean.setTra_No(tra_No);
//				Dbean.setDet_money(money);
//				detailService.insert_emp(Dbean);
//				req.setAttribute("tra_no", tra_No);
//				req.getRequestDispatcher("/detail?tra_no=" + tra_No).forward(req, resp);
//				return;
//			}else{
//				DetInError.put("InError", "找不到此姓名");
//				req.setAttribute("tra_no", tra_No);
//				req.getRequestDispatcher("/Detail_Insert.jsp").forward(req, resp);
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
