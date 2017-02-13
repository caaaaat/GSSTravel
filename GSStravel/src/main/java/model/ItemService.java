package model;

import java.util.ArrayList;
import java.util.List;


public class ItemService {
	private IItemDAO itemDAO=new ItemDAO();
	
	public List<ItemVO> select(long tra_No)  {
		return itemDAO.getFee(tra_No);
	}
	
	public List<ItemVO> getRoomMoney(long tra_No){
		return itemDAO.getRoomMoney(tra_No);
	}
	
	public List<ItemVO> select(ItemVO bean) {
		List<ItemVO> result = null;
		if (bean != null && bean.getItem_No() != 0) {
			List<ItemVO> temp = itemDAO.select();
			if (temp != null) {
				result = new ArrayList<ItemVO>();
				result.add((ItemVO) temp);
			}
		} else {
			result = itemDAO.select();
		}
		return result;
	}
	public List<ItemVO> getFareMoney(long tra_No){
		return itemDAO.getFareMoney(tra_No);
	}
}
