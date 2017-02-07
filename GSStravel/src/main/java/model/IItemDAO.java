package model;

import java.util.List;

public interface IItemDAO {
	public List<ItemVO> getFee(long tra_No) ;//取得所有費用
	public List<ItemVO> getRoomMoney(long tra_No);//房間單獨費用
	public List<ItemVO> getFareMoney(long tra_No);//單一行程團費與保費
	public abstract List<ItemVO> select();
}
