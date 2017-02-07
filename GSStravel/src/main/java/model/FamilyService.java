package model;

import java.util.List;

public class FamilyService {
	private IFamilyDAO familyDAO=new FamilyDAO();
	public List<FamilyVO> selectFam(String emp_No){
		return familyDAO.selectFam(emp_No);
	}
}
