package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO {
	private DataSource ds;

	public EmployeeDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String selectEmp_Name = "select emp_Name from Employee where emp_No=?";
	private static final String selectEmp_Sub = "select emp_Sub from Employee where emp_No=?";
	private static final String updateEmp_Sub ="update Employee set emp_Sub=? where emp_No=?";	
	private static final String SELECT_BY_emp_NO = "select * from employee where emp_NO=?";
	private static final String updateEmp_SubTra="update Employee set emp_SubTra = ? where emp_No=?";
	private static final String update = "update Employee set emp_Name=?,emp_Phone=?, emp_ID=?, emp_Sex=?, emp_Bdate=?, emp_Eat=?, emp_Emg=?, emp_EmgPhone=?, emp_EmgRel=?, emp_HireDate=?, emp_Sub=?, emp_PW=?, emp_Ben=?, emp_BenRel=?, dept_NO=?, emp_Note=?, emp_Mail=?, emp_Role=? where emp_NO=?";
	
	@Override
	public String selectEmp_Name(String emp_No) {
		String name = null;
		try (	Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(selectEmp_Name);) {
			stmt.setString(1, emp_No);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				name = rset.getString("emp_Name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	@Override
	public Integer selectEmp_Sub(String emp_No) {
		int Emp_Sub = 0;
		try (	Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(selectEmp_Sub);) {
			stmt.setString(1, emp_No);
			ResultSet rset = stmt.executeQuery();			
			while (rset.next()) {				
				Emp_Sub = Integer.parseInt(rset.getString("emp_Sub"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Emp_Sub;
	}//1未使用補助金0以使用補助金
	@Override
	public void updateEmp_Sub(boolean emp_Sub,String emp_No){
		try (	Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(updateEmp_Sub);) {
			stmt.setBoolean(1, emp_Sub);
			stmt.setString(2, emp_No);
			stmt.executeUpdate();			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public EmployeeVO select(int emp_NO) {
		EmployeeVO empVO = new EmployeeVO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_emp_NO);
			pstmt.setInt(1, emp_NO);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				empVO.setEmp_No(rs.getInt("emp_No"));
				empVO.setEmp_Name(rs.getString("emp_Name"));
				empVO.setEmp_Phone(rs.getString("emp_Phone"));
				empVO.setEmp_ID(rs.getString("emp_ID"));
				empVO.setEmp_Sex(rs.getString("emp_Sex"));
				empVO.setEmp_Bdate(rs.getDate("emp_Bdate"));
				empVO.setEmp_Eat(rs.getString("emp_Eat"));
				empVO.setEmp_Emg(rs.getString("emp_Emg"));
				empVO.setEmp_EmgPhone(rs.getString("emp_EmgPhone"));
				empVO.setEmp_HireDate(rs.getDate("emp_HireDAte"));
				empVO.setEmp_Sub(rs.getBoolean("emp_Sub"));
				empVO.setEmp_PW(rs.getBytes("emp_PW"));
				empVO.setEmp_BenRel(rs.getString("emp_BenRel"));
				empVO.setDept_NO(rs.getString("dept_No"));
				empVO.setEmp_Note(rs.getString("emp_Note"));
				empVO.setEmp_Mail(rs.getString("emp_Mail"));
				empVO.setEmp_Role(rs.getBoolean("emp_Role"));
				empVO.setEmp_SubTra(rs.getString("emp_SubTra"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return empVO;
	}

	@Override
	public void updateEmp_SubTra(String tra_No,String emp_No){
		try (	Connection conn = ds.getConnection(); 
				PreparedStatement stmt = conn.prepareStatement(updateEmp_SubTra);) {
			stmt.setString(1, tra_No);
			stmt.setString(2, emp_No);
			stmt.executeUpdate();			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(EmployeeVO empvoupdate) {
		try (
				Connection connection = ds.getConnection();
				PreparedStatement state = connection.prepareStatement(update);) {
			state.setString(1, empvoupdate.getEmp_Name());
			state.setString(2, empvoupdate.getEmp_Phone());
			state.setString(3, empvoupdate.getEmp_ID());
			state.setString(4, empvoupdate.getEmp_Sex());
			Long empbdate = empvoupdate.getEmp_Bdate().getTime();
			state.setDate(5, new java.sql.Date(empbdate));
			state.setString(6, empvoupdate.getEmp_Eat());
			state.setString(7, empvoupdate.getEmp_Emg());
			state.setString(8, empvoupdate.getEmp_EmgPhone());
			state.setString(9, empvoupdate.getEmp_EmgRel());
			Long emphiredate = empvoupdate.getEmp_HireDate().getTime();
			state.setDate(10,  new java.sql.Date(emphiredate));
			state.setBoolean(11, empvoupdate.isEmp_Sub());
			state.setBytes(12, empvoupdate.getEmp_PW());
			state.setString(13, empvoupdate.getEmp_Ben());
			state.setString(14, empvoupdate.getEmp_BenRel());
			state.setString(15, empvoupdate.getDept_NO());
			state.setString(16, empvoupdate.getEmp_Note());
			state.setString(17, empvoupdate.getEmp_Mail());
			state.setBoolean(18, empvoupdate.isEmp_Role());
			state.setInt(19, empvoupdate.getEmp_No());
			state.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}