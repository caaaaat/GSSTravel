package model;

import java.util.List;

public class DetailInsertSelectService {
	private DetailInsertSelectDAO dISDAO;
	private DetailDAO detailDAO;
	public DetailInsertBean selectempName(int emp_No){
		dISDAO= new DetailInsertSelectDAO();
		return dISDAO.selectempName(emp_No);
	}
	public DetailInsertBean selectDetailEmp(int emp_No ,String tra_No){
		dISDAO=new DetailInsertSelectDAO();
		return dISDAO.selectDetailEmp(emp_No, tra_No);
	}
	public List<DetailInsertBean> selecDetailFam(int emp_No ,String tra_No){
		dISDAO=new DetailInsertSelectDAO();
		return dISDAO.selecDetailFam(emp_No, tra_No);
	}
	public int detail_Count(String emp_No,String tra_No){
		long tra_no = Long.parseLong(tra_No);
		return detailDAO.detail_Count(emp_No,tra_no) ;
	}
}
