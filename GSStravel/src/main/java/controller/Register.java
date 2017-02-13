package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/Register")
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
				

			long betweenDate =0;
			FamilyVO start=null;
			for(int i=0;i<famstart.size();i++){//0 1 2 3 4
				 start=famstart.get(i);
				 Date bdate = start.getFam_Bdate();	 
				 Calendar calendar = Calendar.getInstance();
				 long nowDate = calendar.getTime().getTime(); //Date.getTime() 獲得毫秒型 現在日期
				 
				 long specialDate = bdate.getTime();//把要比較的值放這(親屬日期)
				 betweenDate = (nowDate - specialDate) / (1000 * 60 * 60 * 24); //計算間隔多少天，則除以毫秒到天的轉換公式			 
//				 System.out.println(betweenDate);  //10353 1745 43 43 43 
				 if(betweenDate<365*3){
					 start.setFam_Bady(true);
				 }else{
					 start.setFam_Bady(false);
				 }
				 if(betweenDate<365*11){
					 start.setFam_kid(true);
				 }else{
					 start.setFam_kid(false);
				 }

				 
			}
			
		req.getRequestDispatcher("Datainsert.jsp").forward(req, res);
    }
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
	
