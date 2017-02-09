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
	private static final String selectFam="select * from Family where emp_No=?";
	private static final String selectfam_No="select fam_No from Family where fam_Name=?";
	private static final String selectfam_Rel="select fam_Rel from Family where emp_No=? and fam_Name=?";
	
//	private static final String selectall= "select * from Family where fam_No =?";
	private static final String selectfamid="select fam_Id from Family where emp_No=?";
//	private static final String selectstart="select * from Family where emp_No=?";
//	private static final String on = "SET IDENTITY_INSERT Family ON;";
//	private static final String off = "SET IDENTITY_INSERT Family OFF;";
	private static final String insert = "insert into Family (emp_No,fam_Name,fam_Rel,fam_Bdate,fam_Sex,fam_Eat,fam_Id,fam_Phone,fam_Note,fam_Ben,fam_BenRel,fam_Car,fam_Emg,fam_EmgPhone,fam_EmgRel,fam_Bady,fam_kid,fam_Dis,fam_Mom) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	private static final String update = "update Family set fam_Name=?,fam_Rel=?,fam_Bdate=?,fam_Sex=?,fam_Eat=?,fam_Id=?,fam_Phone=?,fam_Note=?,fam_Ben=?,fam_BenRel=?,fam_Car=?,fam_Emg=?,fam_EmgPhone=?,fam_EmgRel=?,fam_Bady=?,fam_kid=?,fam_Dis=?,fam_Mom=? where fam_No=?";
	private static final String delete = "delete from Family where fam_No=?";
	
	
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
	public List<FamilyVO> selectFam(String emp_No,long tra_No){	
		List<FamilyVO> familyVOs=new ArrayList<>();
		IDetailDAO detailDAO=new DetailDAO();
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
				familyVO.setFam_Bdate(rset.getDate("fam_Bdate"));
				familyVO.setFam_Sex(rset.getString("fam_Sex"));
				familyVO.setFam_Eat(rset.getString("fam_Eat"));
				familyVO.setFam_Id(rset.getString("fam_Id"));
				familyVO.setFam_Phone(rset.getString("fam_Phone"));//
				familyVO.setFam_Note(rset.getString("fam_Note"));
				familyVO.setFam_Ben(rset.getString("fam_Ben"));
				familyVO.setFam_BenRel(rset.getString("fam_BenRel"));
				familyVO.setFam_Car(rset.getBoolean("fam_Car"));
				familyVO.setFam_Emg(rset.getString("fam_Emg"));
				familyVO.setFam_EmgPhone(rset.getString("fam_EmgPhone"));
				familyVO.setFam_EmgRel(rset.getString("fam_EmgRel"));//
				familyVO.setFam_Bady(rset.getBoolean("fam_Bady"));
				familyVO.setFam_kid(rset.getBoolean("fam_kid"));
				familyVO.setFam_Dis(rset.getBoolean("fam_Dis"));
				familyVO.setFam_Mom(rset.getBoolean("fam_Mom"));
				if(tra_No==0){					
				}else if(detailDAO.selectFam_No(rset.getInt("fam_No"), tra_No)){
					familyVO.setChecked("checked");
				}else{
					familyVO.setChecked("");
				}			
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

	@Override
	public void insert(FamilyVO famvo) {
		
		try(Connection connection = ds.getConnection();
				PreparedStatement state = connection.prepareStatement(insert);)
		{
			state.setInt(1, famvo.getEmp_No());
//			state.setInt(2, famvo.getFamno());  famno 流水號新增時會直接帶進去
			state.setString(2, famvo.getFam_Name());
			state.setString(3, famvo.getFam_Rel());
			Long Fambdate = famvo.getFam_Bdate().getTime();		
			state.setDate(4, new java.sql.Date(Fambdate));//?
			state.setString(5, famvo.getFam_Sex());	
			state.setString(6, famvo.getFam_Eat());
			state.setString(7, famvo.getFam_Id());
			state.setString(8, famvo.getFam_Phone());
			state.setString(9, famvo.getFam_Note());
			state.setString(10, famvo.getFam_Ben());
			state.setString(11, famvo.getFam_BenRel());
			state.setBoolean(12, famvo.isFam_Car());
			state.setString(13, famvo.getFam_Emg());
			state.setString(14, famvo.getFam_EmgPhone());
			state.setString(15, famvo.getFam_EmgRel());
			state.setBoolean(16, famvo.isFam_Bady());
			state.setBoolean(17, famvo.isFam_kid());
			state.setBoolean(18, famvo.isFam_Dis());
			state.setBoolean(19, famvo.isFam_Mom());
			state.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(FamilyVO famvo) {
		try(Connection connection = ds.getConnection();
				PreparedStatement state = connection.prepareStatement(update);)
		{	
			state.setString(1, famvo.getFam_Name());
			state.setString(2, famvo.getFam_Rel());
//			state.setDate(3, famvo.getFambdate());
			Long Fambdate = famvo.getFam_Bdate().getTime();		
			state.setDate(3, new java.sql.Date(famvo.getFam_Bdate().getTime()));
			state.setString(4, famvo.getFam_Sex());	
			state.setString(5, famvo.getFam_Eat());
			state.setString(6, famvo.getFam_Id());
			state.setString(7, famvo.getFam_Phone());
			state.setString(8, famvo.getFam_Note());
			state.setString(9, famvo.getFam_Ben());
			state.setString(10, famvo.getFam_BenRel());
			state.setBoolean(11,famvo.isFam_Car());
			state.setString(12, famvo.getFam_Emg());
			state.setString(13, famvo.getFam_EmgPhone());
			state.setString(14, famvo.getFam_EmgRel());
			state.setBoolean(15, famvo.isFam_Bady());
			state.setBoolean(16, famvo.isFam_kid());
			state.setBoolean(17, famvo.isFam_Dis());
			state.setBoolean(18, famvo.isFam_Mom());
			state.setInt(19, famvo.getFam_No());
			state.executeUpdate();			
		}catch(SQLException e){
			e.printStackTrace();
		}		
	}

	@Override
	public void delete(FamilyVO famvo) {
		try(
			Connection connection = ds.getConnection();
			PreparedStatement state = connection.prepareStatement(delete);)
		{
			state.setInt(1,famvo.getFam_No());
			state.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> selectid(Integer empno) {
		List<String> famid=new ArrayList<String>();
		
		try(Connection connection = ds.getConnection();
				PreparedStatement state = connection.prepareStatement(selectfamid);)
			{
			state.setInt(1,empno);
			ResultSet resultset = state.executeQuery();
			if(resultset!=null){
				while(resultset.next()){
					famid.add(resultset.getString("fam_Id"));
					//迴圈 每抓一次會進去一次 在裡面設new會重新跑
				}
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
		return famid;
	}
}
