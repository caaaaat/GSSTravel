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

public class DetailInsertSelectDAO {
	private DataSource ds;
	
	public DetailInsertSelectDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final String select_emp_No="select emp_No,emp_Name from employee where emp_No=?";
	//查詢是否有此員工
	public DetailInsertBean selectempName(int emp_No){

		DetailInsertBean bean =null;
		try (Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(select_emp_No);) {
			stmt.setInt(1, emp_No);						
			ResultSet rset = stmt.executeQuery();
			while(rset.next()){
				bean=new DetailInsertBean();
				bean.setNo(rset.getInt("emp_No"));
				bean.setName(rset.getString("emp_Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	private final String select_emp_name="select e.emp_No ,emp_Name , det_No from Employee e left join "
			+ "(select * from detail where tra_No=? and det_CanDate is null ) d on e.emp_No = d.emp_No "
			+ "where e.emp_No=? and det_No is null "; 
	//是否此員工有無報名
	public DetailInsertBean selectDetailEmp(int emp_No ,String tra_No){
		DetailInsertBean bean =null;
		try (Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(select_emp_name);) {
			stmt.setString(1, tra_No);
			stmt.setInt(2,emp_No);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()){
				bean=new DetailInsertBean(); 
				bean.setNo(rset.getInt("emp_No"));
				bean.setName(rset.getString("emp_Name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}
	private final String select_fam_name="select f.fam_No ,fam_Name , det_No from family f left join "
			+ "(select * from detail where tra_No=? and det_CanDate is null ) d on f.fam_No = d.fam_No "
			+ "where f.emp_No=? and det_No is null"; 
	//此員工之家屬有無報名
	public List<DetailInsertBean> selecDetailFam(int emp_No ,String tra_No){
		List<DetailInsertBean> list =null;
		DetailInsertBean bean=null;
		try (Connection conn = ds.getConnection(); 
			PreparedStatement stmt = conn.prepareStatement(select_fam_name);) {
			stmt.setString(1, tra_No);
			stmt.setInt(2,emp_No);
			ResultSet rset = stmt.executeQuery();
			list = new ArrayList<DetailInsertBean>();
			while (rset.next()){
				bean =new DetailInsertBean();
				bean.setNo(rset.getInt("fam_No"));
				bean.setName(rset.getString("fam_Name"));
				list.add(bean);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return list;
	}
}
