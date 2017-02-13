package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import model.EmployeeVO;
import model.FamilyVO;

@WebServlet(urlPatterns = { ("/detail") })
public class DetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DetailService detailService = new DetailService();
	String test;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		String prodaction = req.getParameter("prodaction");
		String tra_no = req.getParameter("tra_no");
		String can_detNo = req.getParameter("can_detNo");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
			if (det_canNote.trim().length() != 0) {
				int canNum = Integer.parseInt(can_detNo);
				bean.setDet_No(canNum);
				bean.setDet_canNote(det_canNote);
				bean.setTra_NO(det_canTraNo);
				List<DetailBean> result1 = detailService.update(bean);
				req.setAttribute("select", result1);
				req.getRequestDispatcher("/Detail_CanSuccess.jsp").forward(req, resp);
				return;
			} else {
				DetCanError.put("CanError", "必須輸入取消原因！");
				req.getRequestDispatcher("/Detail_Cancel.jsp").forward(req, resp);
				return;
			}
		}

		if ("save".equals(prodaction)) {
			String tempEmp_No = req.getParameter("emp_No");
			int emp_No = Integer.parseInt(tempEmp_No);
			String rel = req.getParameter("fam_Rel");
			String name = req.getParameter("name");
			String sex = req.getParameter("sex");
			String ID = req.getParameter("ID");
			String Bdate = req.getParameter("Bdate");
			String Phone = req.getParameter("Phone");
			String eat = req.getParameter("eat");
			String ben = req.getParameter("ben");
			String ben_Rel = req.getParameter("ben_Rel");
			String emg = req.getParameter("emg");
			String emg_Phone = req.getParameter("emg_Phone");
			String note = req.getParameter("note");
			if (!rel.equals("員工")) {
				try {
					String temp_FamNo = req.getParameter("fam_No");
					String car = req.getParameter("car");
					String spe = req.getParameter("text_multiselect");
					int fam_No = Integer.parseInt(temp_FamNo);
					FamilyVO Fbean = new FamilyVO();
					Fbean.setFam_Name(name);
					Fbean.setFam_Rel(rel);
					Fbean.setFam_Phone(Phone);
					Fbean.setFam_Sex(sex);
					Fbean.setFam_Id(ID);
					java.util.Date JDate = sdf.parse(Bdate);
					Date date = new Date(JDate.getTime());
					Fbean.setFam_Bdate(date);
					Fbean.setFam_Eat(eat);
					if (car == null) {
						Fbean.setFam_Car(true);
					} else {
						Fbean.setFam_Car(false);
					}
					if (spe.contains("幼童(0~3歲)")) {
						Fbean.setFam_Bady(true);
					} else {
						Fbean.setFam_Bady(false);
					}
					if (spe.contains("兒童(4~11歲)")) {
						Fbean.setFam_kid(true);
					} else {
						Fbean.setFam_kid(false);
					}
					if (spe.contains("持身心障礙手冊")) {
						Fbean.setFam_Dis(true);
					} else {
						Fbean.setFam_Dis(false);
					}
					if (spe.contains("孕婦(媽媽手冊)")) {
						Fbean.setFam_Mom(true);
					} else {
						Fbean.setFam_Mom(false);
					}
					Fbean.setFam_Ben(ben);
					Fbean.setFam_BenRel(ben_Rel);
					Fbean.setFam_Emg(emg);
					Fbean.setFam_EmgPhone(emg_Phone);
					Fbean.setFam_Note(note);
					Fbean.setFam_No(fam_No);
					detailService.update_famData(Fbean);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					EmployeeVO Ebean = new EmployeeVO();
					Ebean.setEmp_Name(name);
					Ebean.setEmp_Phone(Phone);
					Ebean.setEmp_Sex(sex);
					Ebean.setEmp_ID(ID);
					java.util.Date JDate = sdf.parse(Bdate);
					Date date = new Date(JDate.getTime());
					Ebean.setEmp_Bdate(date);
					Ebean.setEmp_Eat(eat);
					Ebean.setEmp_Ben(ben);
					Ebean.setEmp_BenRel(ben_Rel);
					Ebean.setEmp_Emg(emg);
					Ebean.setEmp_EmgPhone(emg_Phone);
					Ebean.setEmp_Note(note);
					Ebean.setEmp_No(emp_No);
					detailService.update_empData(Ebean);

				} catch (Exception e) {
					e.printStackTrace();
				}
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
