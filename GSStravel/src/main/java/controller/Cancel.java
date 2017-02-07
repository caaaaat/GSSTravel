package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailService;
import model.EmployeeService;
import model.TotalAmountService;

@WebServlet("/Cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailService detailService=new DetailService();
	private EmployeeService employeeService=new EmployeeService();
	TotalAmountService totalAmountService=new TotalAmountService();
    public Cancel() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tra_No=request.getParameter("tra_No");//旅遊編號
		String emp_No=request.getParameter("emp_No");//員工標號
		detailService.updateDet_CanDate(emp_No, tra_No);
		totalAmountService.deleteTotalAmount(Integer.parseInt(emp_No), tra_No);
		if(totalAmountService.selectAll(emp_No)){
			employeeService.updateEmp_Sub(true,emp_No);
		}
		if(employeeService.select(emp_No).getEmp_SubTra().equals(tra_No)){
			if(totalAmountService.selectAll(emp_No)){
				employeeService.updateEmp_SubTra("null", emp_No);
			}else{
				tra_No=totalAmountService.selectTa_money(emp_No).getTra_No();
				employeeService.updateEmp_SubTra(tra_No, emp_No);
			}
		}	
		response.sendRedirect(request.getContextPath()+"/AllTravel");

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
