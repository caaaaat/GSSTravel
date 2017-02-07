package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailService;
import model.EmployeeService;
import model.EmployeeVO;
import model.TotalAmountFormBean;

@WebServlet("/TravelDetail")
public class TravelDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailService detailService = new DetailService();
	private EmployeeService employeeService = new EmployeeService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String tra_No = request.getParameter("tra_no");
		List<Float> years_money = new ArrayList<>();
		List<TotalAmountFormBean> list = detailService.select(tra_No);
		if (list.size() != 0) {
			for (TotalAmountFormBean bean : list) {
				if (bean.getFam_No() == 0) {
					Integer emp_No = bean.getEmp_No();
					EmployeeVO employeeVo = employeeService.select(emp_No.toString());
					java.sql.Date hireDate = employeeVo.getEmp_HireDate();
					String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// 現在系統時間
					java.sql.Date today = java.sql.Date.valueOf(date);
					if (hireDate.getTime() / (24 * 60 * 60 * 1000) + 365 < today.getTime() / (24 * 60 * 60 * 1000)) {
						float money = 4500;
						years_money.add(money);
					} else {
						long x = today.getTime() / (24 * 60 * 60 * 1000) - hireDate.getTime() / (24 * 60 * 60 * 1000);// 相差天數
						long hireMonths = x / 31;
						float money = 4500 / 12 * hireMonths;
						years_money.add(money);
					}
				}
			}
			String tra_Name = list.get(0).getTra_Name();
			request.setAttribute("list", list);
			request.setAttribute("years_money", years_money);
			request.setAttribute("tra_Name", tra_Name);
			request.setAttribute("tra_No", tra_No);
			request.getRequestDispatcher("Detail_Money.jsp").forward(request, response);
		} else {
			request.setAttribute("nopeople", "無人報名");
			request.getRequestDispatcher("/search1.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
