package model;

public interface ITotalAmountDAO {

	public boolean update(float TA_money, String tra_No, int emp_No);
	public void insertTotalAmount( String tra_No, int emp_No,float TA_money);
	public void deleteTotalAmount( int emp_No,String tra_No );
	public TotalAmountVO selectTa_money(String emp_No);//目前使用者報名旅程的最大費用
	public boolean selectAll(String emp_No);//確地是否還有使用補助金
	public int counts(String emp_No);
}
