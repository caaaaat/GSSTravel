package model;

public class ItemVO {
	private int item_No;
	private String tra_No;
	private String item_Name;
	private float item_Money;
	
	public String getTra_No() {
		return tra_No;
	}
	public void setTra_No(String tra_No) {
		this.tra_No = tra_No;
	}
	public int getItem_No() {
		return item_No;
	}
	public void setItem_No(int item_No) {
		this.item_No = item_No;
	}	
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public float getItem_Money() {
		return item_Money;
	}
	public void setItem_Money(float item_Money) {
		this.item_Money = item_Money;
	}
	
	
}
