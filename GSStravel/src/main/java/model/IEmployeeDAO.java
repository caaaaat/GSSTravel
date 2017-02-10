package model;

public interface IEmployeeDAO {
	public String selectEmp_Name(String emp_No);//找到員工姓名(報名明細使用)
	public Integer selectEmp_Sub(String emp_No);//找尋補助金是否使用
	public void updateEmp_Sub(boolean emp_Sub,String emp_No);//更新補助金使用(確定使用)
	public EmployeeVO select(int emp_NO);
	public void updateEmp_SubTra(String tra_No,String emp_No);
	public void update(EmployeeVO empVoUpdate);
	
}
