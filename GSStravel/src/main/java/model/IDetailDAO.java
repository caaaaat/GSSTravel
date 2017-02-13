package model;

import java.util.List;
import java.util.Map;

public interface IDetailDAO {
	public Map<String, Integer> tra_count();//抓取所有旅遊目前報名人數
	public int tra_count(long tra_No);//抓取單一旅遊目前報名人數
	public List<String> detail_Emp_No(long tra_No);//現在已經報名的員工編號
	public int detail_Count(String emp_No,long tra_No);//報名人員攜帶人數	
	public void tra_Enter(int emp_No,String fam_No,String tra_No ,String det_Date, float det_money);//報名
	public int detail_Enter(String emp_No,String tra_No);//查詢是否報名過
	public void updateDet_CanDate(String emp_No,String tra_No);//登記取消時間	
	public abstract List<DetailBean> select(String tra_No);
	public abstract String select_emp_Name(int Emp_No, String Emp_Name);
	public abstract String select_fam_Name(int Emp_No, String Fam_Name);
	public abstract String SELECT_emp_SubTra(int Emp_No);
	public abstract String SELECT_top1_Tra_No(int Emp_No);
	public abstract String SELECT_top2_Tra_No(int Emp_No);
	public abstract int select_emp_No(int det_No);
	public abstract DetailVO insert(DetailVO bean);
	public abstract DetailVO insert_emp(DetailVO bean);
	public abstract List<DetailBean> update(int det_No, String det_canNote, String tra_No);
	public abstract boolean UPDATE_emp_Sub(int Emp_No);
	public abstract boolean UPDATE_emp_SubTra(String Tra_No, int Emp_No);
	public abstract boolean UPDATE_empData(EmployeeVO bean);
	public abstract boolean UPDATE_famData(FamilyVO bean); 
	public List<TotalAmountFormBean> selectBean(String tra_No);
	public boolean update_empNo( String det_note ,float det_noteMoney, String tra_No, int emp_No);
	public boolean update_famNo(String det_note,float det_noteMoney, String tra_No , int fam_No);
	public boolean selectFam_No(int fam_No,long tra_No);
	public List<String> selectFam_Rel(int emp_No,long tra_No);
	}
