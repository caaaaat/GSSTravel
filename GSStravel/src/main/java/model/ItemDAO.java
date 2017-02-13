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

public class ItemDAO implements IItemDAO {
	private DataSource ds;
	private static final String feeTravel = "select item_Name,item_Money from Item  where tra_No = ?";
	private static final String SELECT_ONE_STMT = "SELECT item_Money FROM Item WHERE item_No=1 ORDER BY tra_No";
	private static final String roomMoney = "select item_Name,item_Money from Item where tra_No=? and item_name like '%住宿%'";
	private static final String fareMoney = "select item_Name,item_Money from Item  where tra_No = ? and item_name not like '%住宿%'";
	private static final String SELECT = "select * from Item where tra_No =?";
	private static final String insert = "insert into Item(item_No, item_Name, item_Money) values (?,?,?)";
	private static final String UPDATE = "update Item set item_Name=?, item_Money=? where item_No=?";
	private static final String DELETE ="delete from Item where item_NO=?";
	
	public ItemDAO() {
		super();
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/testDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	@Override
	public boolean delete(int item_No) {
		try(
				Connection conn = ds.getConnection();	//web用
				PreparedStatement stmt = conn.prepareStatement(DELETE);) {			
			stmt.setInt(1, item_No);
			int i = stmt.executeUpdate();
			if(i==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public ItemVO update(ItemVO Itemupdate) {
		ItemVO result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement insertdata = conn.prepareStatement(UPDATE);) {
			insertdata.setString(1, Itemupdate.getItem_Name());
			insertdata.setDouble(2, Itemupdate.getItem_Money());
			insertdata.setInt(3, Itemupdate.getItem_No());
			int i = insertdata.executeUpdate();
			if (i == 1) {
				result = Itemupdate;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ItemVO insert(ItemVO bean) {
		ItemVO result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement insertdata = conn.prepareStatement(insert);) {
			if (bean != null) {
				insertdata.setInt(1, bean.getItem_No());
				insertdata.setString(2, bean.getItem_Name());
				insertdata.setDouble(3, bean.getItem_Money());
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
	public List<ItemVO> select(String tra_No) {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT);) {
			stmt.setString(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<ItemVO>();
			while (rset.next()) {
				ItemVO bean = new ItemVO();
				bean.setTra_No(rset.getString("tra_No"));
				bean.setItem_No(rset.getInt("item_No"));
				bean.setItem_Name(rset.getString("item_Name"));
				bean.setItem_Money(rset.getFloat("item_Money"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ItemVO> getFee(long tra_No) {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(feeTravel);) {
			stmt.setLong(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<ItemVO>();
			while (rset.next()) {
				ItemVO vo = new ItemVO();
				vo.setItem_Name(rset.getString("item_Name"));
				vo.setItem_Money(rset.getFloat("item_Money"));
				result.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ItemVO> getFareMoney(long tra_No) {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(fareMoney);) {
			stmt.setLong(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<ItemVO>();
			while (rset.next()) {
				ItemVO vo = new ItemVO();
				vo.setItem_Name(rset.getString("item_Name"));
				vo.setItem_Money(rset.getFloat("item_Money"));
				result.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ItemVO> getRoomMoney(long tra_No) {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(roomMoney);) {
			stmt.setLong(1, tra_No);
			ResultSet rset = stmt.executeQuery();
			result = new ArrayList<ItemVO>();
			while (rset.next()) {
				ItemVO vo = new ItemVO();
				vo.setItem_Name(rset.getString("item_Name"));
				vo.setItem_Money(rset.getFloat("item_Money"));
				result.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<ItemVO> select() {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SELECT_ONE_STMT);
				ResultSet rset = stmt.executeQuery();) {
			result = new ArrayList<ItemVO>();
			while (rset.next()) {
				ItemVO bean = new ItemVO();
				bean.setItem_Money(rset.getFloat("item_Money"));
				result.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
