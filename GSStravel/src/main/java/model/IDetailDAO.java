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
//	public List<String> selectTra_No(String emp_No);
}
