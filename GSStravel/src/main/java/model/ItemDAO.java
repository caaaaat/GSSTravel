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
	private static final String roomMoney ="select item_Name,item_Money from Item where tra_No=? and item_name like '%住宿%'";
	private static final String fareMoney ="select item_Name,item_Money from Item  where tra_No = ? and item_name not like '%住宿%'";
	
	public ItemDAO(){
		super();
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/testDB");									  
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ItemVO> getFee(long tra_No) {
		List<ItemVO> result = null;
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(feeTravel);				
				)
		{			
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
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(fareMoney);				
				)
		{			
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
		try (Connection conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(roomMoney);				
				)
		{			
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
