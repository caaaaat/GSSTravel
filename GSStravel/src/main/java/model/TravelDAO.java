package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class TravelDAO implements ITravelDAO {
	private DataSource ds;

	public TravelDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ALL_STMT = "SELECT tra_NO, tra_Name,tra_On, tra_Off, tra_Beg, tra_End, tra_Total, tra_Max,tra_Intr,tra_Con,tra_Atter,tra_File, tra_Loc  FROM Travel ORDER BY tra_NO";
	private static final String selectFortravel = "SELECT tra_NO, tra_Name,tra_On, tra_Off, tra_Beg, tra_End, tra_Total, tra_Max,tra_Intr,tra_Con,tra_Atter,tra_File  FROM Travel where tra_NO= ? order by tra_NO ";
	private static final String selectTra_NoTra_End = "select tra_No,tra_End from Travel";
	private static final String selectTra_NoTra_Beg = "select tra_No,tra_Beg from Travel";
	private static final String selectTra_No = "select tra_No from Travel";
	private static final String SEARCH_BY_NO_NAME = " select * from travel where tra_No like ? and tra_Name like ? ";
	private static final String insert = "insert into Travel(tra_Name, tra_Loc, tra_On, tra_Off, tra_Beg, tra_End, tra_Total, tra_Max, tra_Intr, tra_Con, tra_Atter, tra_File, tra_NO) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "update Travel set tra_Name=?, tra_Loc=?, tra_On=?, tra_Off=?, tra_Beg=?, tra_End=?, tra_Total=?, tra_Max=?, tra_Intr=?, tra_Con=?, tra_Atter=?, tra_File=? where tra_NO=?";
	private static final String DELETE = "delete from Travel where tra_NO=?";

	@Override
	public boolean delete(String tra_NO) {
		try (
				Connection conn = ds.getConnection(); // web用
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {
			stmt.setString(1, tra_NO);
			int i = stmt.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public TravelVO update(TravelVO Travelupdate) {
		TravelVO result = null;
		try (Connection conn = ds.getConnection(); // web用
				PreparedStatement insertdata = conn.prepareStatement(UPDATE);) {

			insertdata.setString(1, Travelupdate.getTra_Name());
			insertdata.setString(2, Travelupdate.getTra_Loc());
			Long ondate = Travelupdate.getTra_On().getTime();
			insertdata.setDate(3, new java.sql.Date(ondate));
			Long offdate = Travelupdate.getTra_Off().getTime();
			insertdata.setDate(4, new java.sql.Date(offdate));
			Long ontime = Travelupdate.getTra_Beg().getTime();
			insertdata.setTimestamp(5, new java.sql.Timestamp(ontime));
			Long offtime = Travelupdate.getTra_End().getTime();
			insertdata.setTimestamp(6, new java.sql.Timestamp(offtime));
			insertdata.setInt(7, Travelupdate.getTra_Total());
			insertdata.setInt(8, Travelupdate.getTra_Max());
			insertdata.setString(9, Travelupdate.getTra_Intr());
			insertdata.setString(10, Travelupdate.getTra_Con());
			insertdata.setString(11, Travelupdate.getTra_Atter());
			insertdata.setString(12, Travelupdate.getTra_File());
			insertdata.setString(13, Travelupdate.getTra_NO());
			int i = insertdata.executeUpdate();
			if (i == 1) {
				result = Travelupdate;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TravelVO insert(TravelVO bean) {
		TravelVO result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement insertdata = conn.prepareStatement(insert);) {
			if (bean != null) {

				insertdata.setString(1, bean.getTra_Name());
				insertdata.setString(2, bean.getTra_Loc());
				Long ondate = bean.getTra_On().getTime();
				insertdata.setDate(3, new java.sql.Date(ondate));
				Long offdate = bean.getTra_Off().getTime();
				insertdata.setDate(4, new java.sql.Date(offdate));
				Long ontime = bean.getTra_Beg().getTime();
				insertdata.setTimestamp(5, new java.sql.Timestamp(ontime));
				Long offtime = bean.getTra_End().getTime();
				insertdata.setTimestamp(6, new java.sql.Timestamp(offtime));
				insertdata.setInt(7, bean.getTra_Total());
				insertdata.setInt(8, bean.getTra_Max());
				insertdata.setString(9, bean.getTra_Intr());
				insertdata.setString(10, bean.getTra_Con());
				insertdata.setString(11, bean.getTra_Atter());
				insertdata.setString(12, bean.getTra_File());
				insertdata.setString(13, bean.getTra_NO()); // 整合時取消
				int i = insertdata.executeUpdate();
				if (i == 1) {
					result = bean;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public List<TravelVO> getAll() {
		List<TravelVO> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_STMT);
				ResultSet rset = stmt.executeQuery();) {
			IDetailDAO detailDAO = new DetailDAO();
			result = new ArrayList<TravelVO>();
			while (rset.next()) {
				TravelVO vo = new TravelVO();
				vo.setTra_NO(rset.getString("tra_NO"));
				vo.setTra_Name(rset.getString("tra_Name"));
				vo.setTra_On(rset.getDate("tra_On"));
				vo.setTra_Off(rset.getDate("tra_Off"));
				vo.setTra_Beg(rset.getTimestamp("tra_Beg"));
				vo.setTra_End(rset.getTimestamp("tra_End"));
				vo.setTra_Total(rset.getInt("tra_Total"));
				vo.setTra_Max(rset.getInt("tra_Max"));
				vo.setTra_Intr(rset.getString("tra_Intr"));
				vo.setTra_Con(rset.getString("tra_Con"));
				vo.setTra_Atter(rset.getString("tra_Atter"));
				vo.setTra_File(rset.getString("tra_File"));
				vo.setTra_Loc(rset.getString("tra_Loc"));
				vo.setSign_InTotal(detailDAO.tra_count(Long.parseLong(rset.getString("tra_NO"))));
				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TravelVO getAll(long tra_NO) {
		TravelVO result = null;
		ResultSet rset = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(selectFortravel);) {
			stmt.setLong(1, tra_NO);
			rset = stmt.executeQuery();
			while (rset.next()) {
				result = new TravelVO();
				result.setTra_NO(rset.getString("tra_NO"));
				result.setTra_Name(rset.getString("tra_Name"));
				result.setTra_On(rset.getDate("tra_On"));
				result.setTra_Off(rset.getDate("tra_Off"));
				result.setTra_Beg(rset.getTimestamp("tra_Beg"));
				result.setTra_End(rset.getTimestamp("tra_End"));
				result.setTra_Total(rset.getInt("tra_Total"));
				result.setTra_Max(rset.getInt("tra_Max"));
				result.setTra_Intr(rset.getString("tra_Intr"));
				result.setTra_Con(rset.getString("tra_Con"));
				result.setTra_Atter(rset.getString("tra_Atter"));
				result.setTra_File(rset.getString("tra_File"));

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

	@Override
	public Map<String, String> selectTra_NoTra_Beg() {
		Map<String, String> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_NoTra_Beg);
				ResultSet rset = stmt.executeQuery();) {
			result = new HashMap<String, String>();
			while (rset.next()) {
				String x = rset.getString("tra_NO");
				String y = rset.getString("tra_Beg");
				result.put(x, y);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Map<String, String> selectTra_NoTra_End() {
		Map<String, String> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_NoTra_End);
				ResultSet rset = stmt.executeQuery();) {
			result = new HashMap<String, String>();
			while (rset.next()) {
				String x = rset.getString("tra_NO");
				String y = rset.getString("tra_End");
				result.put(x, y);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<String> selectTra_No() {
		List<String> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_No);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<String>();
			while (rset.next()) {
				String x = rset.getString("tra_NO");
				result.add(x);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<TravelVO> search(String no, String name) {
		List<TravelVO> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SEARCH_BY_NO_NAME);) {
			if (no == null) {
				no = "";
			}
			if (name == null) {
				name = "";
			}
			stmt.setString(1, "%" + no + "%");
			stmt.setString(2, "%" + name + "%");
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<TravelVO>();
			while (rset.next()) {
				TravelVO vo = new TravelVO();
				vo.setTra_NO(rset.getString("tra_NO"));
				vo.setTra_Name(rset.getString("tra_Name"));
				vo.setTra_On(rset.getDate("tra_On"));
				vo.setTra_Off(rset.getDate("tra_Off"));
				vo.setTra_Beg(new Timestamp(rset.getDate("tra_Beg").getTime()));
				vo.setTra_End(new Timestamp(rset.getDate("tra_End").getTime()));
				vo.setTra_Total(rset.getInt("tra_Total"));
				vo.setTra_Max(rset.getInt("tra_Max"));
				vo.setTra_Loc(rset.getString("tra_Loc"));
				result.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
