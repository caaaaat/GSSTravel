package model;

import java.util.List;

public interface IFamilyDAO {
	public List<FamilyVO> selectFam(String emp_No);//抓取登入者親屬
	public Integer selectfam_No(String fam_Name);//取得親屬編號
	public String selectfam_Rel(String emp_No,String fam_Name);
}
