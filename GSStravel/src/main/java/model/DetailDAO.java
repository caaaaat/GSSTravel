package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DetailDAO implements IDetailDAO {
	private DataSource ds;

	public DetailDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String tra_Count = "SELECT COUNT(*) AS count FROM Detail where tra_No =? and det_CanDate IS  NULL";
	private static final String insertDetail = "insert into Detail (emp_No,fam_No,tra_No,det_Date,det_CanDate,det_money) values(?,?,?,?,null,?)";
	private static final String detail_Count = "select count(f.fam_Name) as count from Detail d join Family f ON f.fam_No=d.fam_No where d.emp_No=? and d.tra_No=?";
	private static final String detail_Emp_No = "select distinct emp_No,det_Date from Detail where tra_No=? and det_CanDate is null order by det_Date";
	private static final String detail_Enter = "select det_Date from Detail where emp_No=? and tra_No=? and det_CanDate is null order by det_Date";	                                            
	private static final String updateDet_CanDate = "update Detail set det_CanDate=? where emp_No=? and tra_No=?";
	private static final String SELECT_BY_TRA_NO = "select tra_No,dept_No , e.emp_No , f.fam_No  ,emp_Name, fam_Name ,det_money from Detail d join Employee e on d.emp_No=e.emp_No left join Family f on d.fam_No = f.fam_No where tra_No=? ";
//	private static final String selectTra_No="select tra_No from Detail where emp_No=? and det_CanDate is  null";
	
//	@Override
//	public List<String> selectTra_No(String emp_No) {
//		List<String>detail_Tra_No=new ArrayList<>();;
//		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectTra_No);) {
//			stmt.setString(1, emp_No);
//			ResultSet rset = stmt.executeQuery();			
//			while (rset.next()) {	
//				detail_Tra_No.add(rset.getString("tra_No"));				
//			}			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return detail_Tra_No;
//	}
	
	@Override
	public int detail_Enter(String emp_No, String tra_No) {
		int a=0 ;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(detail_Enter);) {
			stmt.setString(1, emp_No);
			stmt.setString(2, tra_No);
			ResultSet rset = stmt.executeQuery();			
			while (rset.next()) {	
				a=1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public List<String> detail_Emp_No(long tra_No) {
		List<String> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(detail_Emp_No);) {
			result = new ArrayList<>();
			stmt.setLong(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				result.add(rset.getString("emp_No"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int detail_Count(String emp_No, long tra_No) {
		int count = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(detail_Count);) {
			stmt.setString(1, emp_No);
			stmt.setLong(2, tra_No);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt("count");// 活動參加人數
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int tra_count(long tra_No) {
		int count = 0;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(tra_Count);) {
			stmt.setLong(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			while (rset.next()) {
				count = rset.getInt("count");// 活動參加人數
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Map<String, Integer> tra_count() {
		ITravelDAO travelDAO = new TravelDAO();
		List<String> allTra_No = travelDAO.selectTra_No();// 所有活動編號
		Map<String, Integer> result = new HashMap<String, Integer>();
		for (String tra_No : allTra_No) {
			try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(tra_Count);) {
				stmt.setString(1, tra_No);
				ResultSet rset = stmt.executeQuery();
				while (rset.next()) {
					int count = rset.getInt("count");// 活動參加人數
					result.put(tra_No, count);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public void tra_Enter(int emp_No, String fam_No, String tra_No, String det_Date, float det_money) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(insertDetail);) {
			stmt.setInt(1, emp_No);
			stmt.setString(2, fam_No);
			stmt.setString(3, tra_No);
			stmt.setString(4, det_Date);
			stmt.setFloat(5, det_money);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void updateDet_CanDate(String emp_No,String tra_No) {
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(updateDet_CanDate);) {
			String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());//現在系統時間
			stmt.setString(1, date);
			stmt.setString(2, emp_No);
			stmt.setString(3, tra_No);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DetailBean> select(String tra_No) {
		List<DetailBean> result = null;
		try {
			Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SELECT_BY_TRA_NO);
			stmt.setString(1, tra_No);
			ResultSet rset = stmt.executeQuery();

			result = new ArrayList<DetailBean>();
			while (rset.next()) {
				DetailBean bean = new DetailBean();
				bean.setTra_No(rset.getString("tra_No"));
				bean.setDept_No(rset.getString("dept_No"));
				bean.setEmp_No(rset.getInt("emp_No"));
				bean.setFam_No(rset.getInt("fam_No"));
				bean.setDet_money(rset.getFloat("det_money"));
				bean.setEmp_Name(rset.getString("emp_Name"));
				bean.setFam_Name(rset.getString("fam_Name"));
				result.add(bean);
			}
			rset.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public DetailBean update(DetailBean bean) {
		return null;
	}
}
