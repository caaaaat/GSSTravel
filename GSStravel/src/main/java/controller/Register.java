package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeService;
import model.EmployeeVO;
import model.FamilyService;
import model.FamilyVO;

@WebServlet("/register")
public class Register extends HttpServlet {
	private EmployeeService employeeservice = new EmployeeService();
	private FamilyService familyservice= new FamilyService();
	
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {    	
    	HttpSession session = req.getSession();
		Integer emp_No = (Integer) session.getAttribute("emp_No");
		long tra_No = 0;
		
		EmployeeVO empstart = employeeservice.select(emp_No.toString());
		req.setAttribute("empno", emp_No);
		req.setAttribute("empname", empstart.getEmp_Name());
		req.setAttribute("empphone", empstart.getEmp_Phone());
		req.setAttribute("empben",empstart.getEmp_Ben());
		req.setAttribute("empbenrel",empstart.getEmp_BenRel());
		req.setAttribute("empemg",empstart.getEmp_Emg());
		req.setAttribute("empemgphone",empstart.getEmp_EmgPhone());
		req.setAttribute("empeat",empstart.getEmp_Eat());
		req.setAttribute("empnote",empstart.getEmp_Note());
		

		List<FamilyVO> famstart=familyservice.selectFam(emp_No.toString(),tra_No);
		req.setAttribute("famstartsize", famstart.size());
		req.setAttribute("famstart", famstart);
		
		req.getRequestDispatcher("datainsert.jsp").forward(req, res);
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
	
