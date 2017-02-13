package model;

import java.util.List;
import java.util.Map;

public interface ITravelDAO {
	public List<TravelVO> getAll(); //所有的活動行程
	public TravelVO getAll(long tra_NO);//單筆活動行程
	public Map<String,String> selectTra_NoTra_End();//所有活動行程編號跟對應的登記結束日期
	public List<String> selectTra_No();//所有活動行程編號
	public List<TravelVO> search(String no,String name);
	public Map<String,String> selectTra_NoTra_Beg();//所有活動行程編號跟對應的登記開始日期

}
