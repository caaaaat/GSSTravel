package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class TravelService {
	private ITravelDAO travelDAO=new TravelDAO();
	private IDetailDAO detailDAO=new DetailDAO();
	public List<TravelVO> select() throws SQLException {		
		return travelDAO.getAll();
	}
	public TravelVO select(long tra_NO) {
		return travelDAO.getAll(tra_NO);
	}
	
	public List<TravelVO> get_by_no_name(String no,String name){
		return new TravelDAO().search(no, name);
	}
	
	public List<TravelVO> AfterOn(List<TravelVO> travelVO,Date start){
		List<TravelVO> bean = new ArrayList<TravelVO>();
		for(TravelVO v:travelVO){
			if(v.getTra_On().after(start)||v.getTra_On().equals(start)){
				bean.add(v);
			}
		}
		return bean;		
	}

	public List<TravelVO> BeforeOff(List<TravelVO> travelVO,Date end){
		List<TravelVO> bean = new ArrayList<TravelVO>();
		for(TravelVO v:travelVO){
			if(v.getTra_Off().before(end)||v.getTra_Off().equals(end)){
				bean.add(v);
			}
		}
		return bean;	
	}

	public List<TravelVO> where(List<TravelVO> travelVO,String[] location){
		List<TravelVO> bean = new ArrayList<TravelVO>();
		for(TravelVO v:travelVO){
			for(String loca:location){
				if(v.getTra_Loc().equals(loca)){
					bean.add(v);
				}
			}
		}
		return bean;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray to_Json(List<TravelVO> travelVO){		
		//需要的參數設定
		java.text.SimpleDateFormat simple = new java.text.SimpleDateFormat();
		simple.applyPattern("yyyy-MM-dd");	
		JSONArray array = new JSONArray();
		for(TravelVO v:travelVO){
			JSONObject obj = new JSONObject();
			obj.put("id", v.getTra_NO());
			obj.put("name", v.getTra_Name());
			obj.put("onDate", simple.format((v.getTra_On())));
			obj.put("offDate", simple.format((v.getTra_Off())));
			obj.put("bDate", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format((v.getTra_Beg())));
			obj.put("eDate", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format((v.getTra_End())));
			obj.put("people",v.getTra_Total());
			obj.put("location",v.getTra_Loc());
			obj.put("peopleNow",detailDAO.tra_count((Long.valueOf(v.getTra_NO()))));
			array.add(obj);
		}
		return array;
	} 
	
	
	public Map<String,String> selectTra_No(String emp_No) throws SQLException{
		Map<String,String> mp= new HashMap<String, String>();
		Map<String,String> selectTra_NoTra_End = travelDAO.selectTra_NoTra_End();//(活動編號,活動登記結束時間)
		Map<String, Integer> selectTra_Count = detailDAO.tra_count();//(活動編號,目前參加人數)
		List<String> selectTra_No = travelDAO.selectTra_No();//活動編號		
		for(int i=0;i<selectTra_No.size();i++){
			String Tra_End=selectTra_NoTra_End.get(selectTra_No.get(i));
			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());//現在系統時間
			int a=java.sql.Timestamp.valueOf(Tra_End).compareTo(java.sql.Timestamp.valueOf(date));	
			// a 回傳-1 已過期,回傳1尚可報名
			if(a==1){
				int b=detailDAO.detail_Enter(emp_No, selectTra_No.get(i));
				if(b==1){
					mp.put(selectTra_No.get(i), "3");
				}else {
					TravelVO travelVO = travelDAO.getAll(Long.parseLong(selectTra_No.get(i)));
					if(selectTra_Count.get(selectTra_No.get(i))<travelVO.getTra_Total()){
						mp.put(selectTra_No.get(i), "0");
						
					}else{
						mp.put(selectTra_No.get(i), "2");
					}
				}
			}
			if(a==-1){
				mp.put(selectTra_No.get(i), "1");
			}
			
		}		
		return mp;//0代表可報名1代表已過期2代表已額滿3代表已經報名
	}
	//柯
		public List<TravelVO> select(TravelVO bean) {
			List<TravelVO> result = null;
			if (bean != null && bean.getTra_NO() != null) {
				TravelVO temp = travelDAO.getAll(Long.parseLong(bean.getTra_NO()));
				if (temp != null) {
					result = new ArrayList<TravelVO>();
					result.add(temp);
				}
			} else {
				result = travelDAO.getAll();
			}
			return result;
		}
		public List<TravelVO> selectOne(String traNo) {
			List<TravelVO> result = null;
			TravelVO temp = travelDAO.getAll(Long.parseLong(traNo));
			result = new ArrayList<TravelVO>();
			result.add(temp);
			return result;
		}
}
