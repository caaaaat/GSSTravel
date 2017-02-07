package model;

import java.util.Arrays;

public class EmployeeService {
	private IEmployeeDAO employeeDAO=new EmployeeDAO();
	
	public String getName(String emp_No){
		return employeeDAO.selectEmp_Name(emp_No);
	}
	
	public void updateEmp_Sub(boolean emp_Sub,String emp_No){
		employeeDAO.updateEmp_Sub(emp_Sub,emp_No);
	}
	public EmployeeVO login(int account, String pwd) {
		EmployeeVO bean = employeeDAO.select(account);
		if(bean!=null) {
			if(pwd!=null && pwd.length()!=0) {
				byte[] pass = bean.getEmp_PW();
				byte[] temp = pwd.getBytes();
				if(Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}
	
	public void updateEmp_SubTra(String tra_No, String emp_No){
		employeeDAO.updateEmp_SubTra(tra_No, emp_No);
	}
	
	public EmployeeVO select(String emp_NO){
		return employeeDAO.select(Integer.parseInt(emp_NO));
	}
	
	public void update(EmployeeVO empVoUpdate){
		employeeDAO.update(empVoUpdate);
	}
}
