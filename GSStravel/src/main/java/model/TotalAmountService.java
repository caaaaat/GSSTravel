package model;

public class TotalAmountService {
	TotalAmountDAO totalAmountDAO=new TotalAmountDAO();
	
	public void insertTotalAmount(String tra_No, int emp_No,float TA_money){
		totalAmountDAO.insertTotalAmount(tra_No, emp_No, TA_money);
	}
	
	public void deleteTotalAmount(int emp_No,String tra_No){
		totalAmountDAO.deleteTotalAmount( emp_No, tra_No);
	}
	
	public boolean selectAll(String emp_No){
		return totalAmountDAO.selectAll(emp_No);
	}
	public int counts(String emp_No){
		return totalAmountDAO.counts(emp_No);
	}
	
	public TotalAmountVO selectTa_money(String emp_No){
		return totalAmountDAO.selectTa_money(emp_No);
	}
}
