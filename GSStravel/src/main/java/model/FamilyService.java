package model;

import java.util.List;

public class FamilyService {
	private IFamilyDAO familyDAO=new FamilyDAO();
	public List<FamilyVO> selectFam(String emp_No){
		return familyDAO.selectFam(emp_No);
	}

	public void insert(FamilyVO famvo){
		familyDAO.insert(famvo);
	}
	public void update(FamilyVO famvo){
		familyDAO.update(famvo);
	}
	public void delete(FamilyVO famvo){
		familyDAO.delete(famvo);
	}
	public List<String> selectid(Integer empno){
		return familyDAO.selectid(empno);
	} 
}
