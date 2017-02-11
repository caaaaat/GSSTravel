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

@WebServlet(urlPatterns = { ("/detail") })
public class DetailServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private DetailService detailService = new DetailService();
	String test;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String cancel = req.getParameter("cancel");
		String prodaction = req.getParameter("prodaction");
		String tra_no = req.getParameter("tra_no");
		String can_detNo = req.getParameter("can_detNo");
		
		DetailBean bean = new DetailBean();
		
		Map<String, String> DetCanError = new HashMap<String, String>();
		req.setAttribute("DetCanError", DetCanError);
		
		if ("insert".equals(prodaction)) {
			req.setAttribute("tra_no", tra_no);
			req.getRequestDispatcher("/Detail_Insert.jsp").forward(req, resp);
			return;
		}
		// 點選取消按鈕，更新取消日期
		if ("送出".equals(prodaction) && can_detNo != null) {
			String det_canNote = req.getParameter("det_CanNote");
			String det_canTraNo = req.getParameter("can_traNo");
			if(det_canNote.trim().length()!=0){
				int canNum = Integer.parseInt(can_detNo);
				bean.setDet_No(canNum);
				bean.setDet_canNote(det_canNote);
				bean.setTra_NO(det_canTraNo);
				List<DetailBean> result1 = detailService.update(bean);
				req.setAttribute("select", result1);
				req.getRequestDispatcher("/Detail_CanSuccess.jsp").forward(req, resp);
				return;
			}else{
				DetCanError.put("CanError", "必須輸入取消原因！");
				req.getRequestDispatcher("/Detail_Cancel.jsp").forward(req, resp);
				return;
			}
		}

		bean.setTra_NO(tra_no);
		List<DetailBean> result = detailService.select(bean);
		req.setAttribute("select", result);
		req.getRequestDispatcher("/Detail.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
