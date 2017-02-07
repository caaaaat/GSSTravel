package model;

import java.util.List;

public interface IFamilyDAO {
	public List<FamilyVO> selectFam(String emp_No);//抓取登入者親屬
	public Integer selectfam_No(String fam_Name);//取得親屬編號
	public String selectfam_Rel(String emp_No,String fam_Name);
//	public List<FamilyVO> select(Integer famno);
	public void insert(FamilyVO famvo);
	public void update(FamilyVO famvo);
	public void delete(FamilyVO famvo);
	public List<String> selectid(Integer empno);
//	public List<FamilyVO>selectstart(Integer empno);//selectFam
}
