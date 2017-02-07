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

public class FineDAO implements IFineDAO {
	private static final String SELECT_ONE_STMT = "SELECT * FROM Fines WHERE fine_Dates=?";
	private static final String SELECT_ALL_STMT = "SELECT * FROM Fines ORDER BY fine_Dates DESC";
	public static final String INSERT_STMT = "INSERT INTO Fines VALUES(?, ?)";
	public static final String UPDATE_STMT = "UPDATE Fines SET fine_Per=? WHERE fine_Dates=?";
	private static final String DELETE_STMT = "DELETE FROM Fines";

	private DataSource dataSource;

	public FineDAO() {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public FineVO select(int day) {
		FineVO result = null;
		ResultSet rset = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ONE_STMT);) {
			stmt.setInt(1, day);
			rset = stmt.executeQuery();
			if (rset.next()) {
				result = new FineVO();
				result.setFine_Dates(rset.getInt("fine_Dates"));
				result.setFine_Per(rset.getFloat("fine_Per"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rset != null) {
				try {
					rset.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public List<FineVO> select() {
		List<FineVO> result = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_STMT);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<FineVO>();
			while (rset.next()) {
				FineVO bean = new FineVO();
				bean.setFine_Dates(rset.getInt("fine_Dates"));
				bean.setFine_Per(rset.getFloat("fine_Per"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public FineVO insert(FineVO bean) {
		FineVO result = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_STMT);) {
			if (bean != null) {
				stmt.setInt(1, bean.getFine_Dates());
				stmt.setFloat(2, bean.getFine_Per());
				int i = stmt.executeUpdate();
				if (i == 1) {
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

//	public FineVO update(float percent, int day) {
//		FineVO result = null;
//		try (Connection conn = dataSource.getConnection();
//				PreparedStatement stmt = conn.prepareStatement(UPDATE_STMT);) {
//			stmt.setFloat(1, percent);
//			stmt.setInt(2, day);
//			int i = stmt.executeUpdate();
//			if (i == 1) {
//				result = this.select(day);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

//	public boolean delete(int day) {
//		try (Connection conn = dataSource.getConnection();
//				PreparedStatement stmt = conn.prepareStatement(DELETE_STMT);) {
//			int i = stmt.executeUpdate();
//			if (i == 1) {
//				return true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public void delete(int day) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(DELETE_STMT);) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
