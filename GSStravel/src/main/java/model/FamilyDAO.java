package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FamilyDAO implements IFamilyDAO {
	private DataSource ds;
	public FamilyDAO() {
		super();
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/testDB");									  
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	private static final String selectFam="select fam_Rel,fam_Name,fam_No from Family where emp_No=?";
	private static final String selectfam_No="select fam_No from Family where fam_Name=?";
	private static final String selectfam_Rel="select fam_Rel from Family where emp_No=? and fam_Name=?";
	
	
	public String selectfam_Rel(String emp_No,String fam_Name){	
		String fam_Rel = null;
		try( Connection conn=ds.getConnection();
			 PreparedStatement stem=conn.prepareStatement(selectfam_Rel);
			 	){
			stem.setString(1,emp_No);
			stem.setString(2,fam_Name);
			ResultSet rset = stem.executeQuery();
			while(rset.next()){
				fam_Rel=rset.getString("fam_Rel");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return fam_Rel;
	}
	
	@Override
	public List<FamilyVO> selectFam(String emp_No){	
		List<FamilyVO> familyVOs=new ArrayList<>();
		try( Connection conn=ds.getConnection();
			 PreparedStatement stem=conn.prepareStatement(selectFam);
			 	){
			stem.setString(1,emp_No);
			ResultSet rset = stem.executeQuery();
			while(rset.next()){
				FamilyVO familyVO=new FamilyVO();
				familyVO.setFam_Rel(rset.getString("fam_Rel"));
				familyVO.setFam_Name(rset.getString("fam_Name"));
				familyVO.setFam_No(rset.getInt("fam_No"));
				familyVOs.add(familyVO);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return familyVOs;
	}
	
	public Integer selectfam_No(String fam_Name){
		Integer fam_No = 0;
		try( Connection conn=ds.getConnection();
			 PreparedStatement stem=conn.prepareStatement(selectfam_No);
				 	){
				stem.setString(1,fam_Name);
				ResultSet rset = stem.executeQuery();
				while(rset.next()){
					fam_No=Integer.parseInt(rset.getString("fam_No"));
				}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return fam_No;
	}
}
