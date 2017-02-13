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
	public TravelDAO(){
		super();
		try {
			Context ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/testDB");									  
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
		
	private static final String GET_ALL_STMT = "SELECT tra_NO, tra_Name,tra_On, tra_Off, tra_Beg, tra_End, tra_Total, tra_Max,tra_Intr,tra_Con,tra_Atter,tra_File, tra_Loc  FROM Travel ORDER BY tra_NO";
	private static final String selectFortravel = "SELECT tra_NO, tra_Name,tra_On, tra_Off, tra_Beg, tra_End, tra_Total, tra_Max,tra_Intr,tra_Con,tra_Atter,tra_File  FROM Travel where tra_NO= ? order by tra_NO ";
	private static final String selectTra_NoTra_End="select tra_No,tra_End from Travel";
	private static final String selectTra_NoTra_Beg="select tra_No,tra_Beg from Travel";
	private static final String selectTra_No="select tra_No from Travel";
	private static final String SEARCH_BY_NO_NAME = " select * from travel where tra_No like ? and tra_Name like ? ";
	
	@Override
	public List<TravelVO> getAll() {
		List<TravelVO> result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(GET_ALL_STMT);
				ResultSet rset = stmt.executeQuery();) {
			IDetailDAO detailDAO=new DetailDAO();
			result = new ArrayList<TravelVO>();
			while(rset.next()) {
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
		ResultSet rset=null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectFortravel);
				) {
			stmt.setLong(1, tra_NO);
			 rset = stmt.executeQuery();
			while(rset.next()) {
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
		}finally {
			if (rset!=null) {
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
	public Map<String,String> selectTra_NoTra_Beg(){
		Map<String,String> result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_NoTra_Beg);
				ResultSet rset = stmt.executeQuery();)
		{	
			result =new HashMap<String,String>();
			while(rset.next()){
				String x=rset.getString("tra_NO");
				String y=rset.getString("tra_Beg");
				result.put(x, y);
		}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Map<String,String> selectTra_NoTra_End(){
		Map<String,String> result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_NoTra_End);
				ResultSet rset = stmt.executeQuery();)
		{	
			result =new HashMap<String,String>();
			while(rset.next()){
				String x=rset.getString("tra_NO");
				String y=rset.getString("tra_End");
				result.put(x, y);
		}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<String> selectTra_No(){
		List<String> result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(selectTra_No);
				ResultSet rset = stmt.executeQuery();)
		{	
			result =new ArrayList<String>();
			while(rset.next()){
				String x=rset.getString("tra_NO");				
				result.add(x);
		}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<TravelVO> search(String no,String name){
		List<TravelVO> result = null;
		try(
				Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(SEARCH_BY_NO_NAME);
				) {
			if(no==null){no="";}
			if(name==null){name="";}
			stmt.setString(1, "%"+no+"%");	
			stmt.setString(2, "%"+name+"%");
			ResultSet rset = stmt.executeQuery();			
			result = new ArrayList<TravelVO>();
			while(rset.next()) {
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
	
	@Override
	public int[] Count(long tra_No) {
		return null;
	}
}
