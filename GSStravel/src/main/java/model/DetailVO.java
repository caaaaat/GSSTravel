package model;

public class DetailVO {
	private int det_No;
	private int emp_No;
	private int fam_No;
	private String tra_No;
	private java.sql.Time det_Date;
	private java.sql.Time det_CanDate;
	private float det_money;
	
	public int getDet_No() {
		return det_No;
	}
	public void setDet_No(int det_No) {
		this.det_No = det_No;
	}
	public int getEmp_No() {
		return emp_No;
	}
	public void setEmp_No(int emp_No) {
		this.emp_No = emp_No;
	}
	public int getFam_No() {
		return fam_No;
	}
	public void setFam_No(int fam_No) {
		this.fam_No = fam_No;
	}
	public String getTra_No() {
		return tra_No;
	}
	public void setTra_No(String tra_No) {
		this.tra_No = tra_No;
	}
	public java.sql.Time getDet_Date() {
		return det_Date;
	}
	public void setDet_Date(java.sql.Time det_Date) {
		this.det_Date = det_Date;
	}
	public java.sql.Time getDet_CanDate() {
		return det_CanDate;
	}
	public void setDet_CanDate(java.sql.Time det_CanDate) {
		this.det_CanDate = det_CanDate;
	}
	public float getDet_money() {
		return det_money;
	}
	public void setDet_money(float det_money) {
		this.det_money = det_money;
	}
	
}
