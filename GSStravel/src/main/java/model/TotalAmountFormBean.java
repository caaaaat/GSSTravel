package model;

import java.sql.Date;

public class TotalAmountFormBean {
	private String dept_No;
	private int det_No;
	private int emp_No;
	private int fam_No;
	private String tra_Name;
	private Date det_Date;
	private Date det_CanDate;
	private float det_money;
	private String emp_Name;
	private String fam_Name;
	private String det_note;
	private float det_noteMoney;
	private boolean emp_sub;
	
	public String getDept_No() {
		return dept_No;
	}
	public void setDept_No(String dept_No) {
		this.dept_No = dept_No;
	}
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
	public String getTra_Name() {
		return tra_Name;
	}
	public void setTra_Name(String tra_Name) {
		this.tra_Name = tra_Name;
	}
	public Date getDet_Date() {
		return det_Date;
	}
	public void setDet_Date(Date det_Date) {
		this.det_Date = det_Date;
	}
	public Date getDet_CanDate() {
		return det_CanDate;
	}
	public void setDet_CanDate(Date det_CanDate) {
		this.det_CanDate = det_CanDate;
	}
	public float getDet_money() {
		return det_money;
	}
	public void setDet_money(float det_money) {
		this.det_money = det_money;
	}
	public String getEmp_Name() {
		return emp_Name;
	}
	public void setEmp_Name(String emp_Name) {
		this.emp_Name = emp_Name;
	}
	public String getFam_Name() {
		return fam_Name;
	}
	public void setFam_Name(String fam_Name) {
		this.fam_Name = fam_Name;
	}
	public String getDet_note() {
		return det_note;
	}
	public void setDet_note(String det_note) {
		this.det_note = det_note;
	}
	public float getDet_noteMoney() {
		return det_noteMoney;
	}
	public void setDet_noteMoney(float det_noteMoney) {
		this.det_noteMoney = det_noteMoney;
	}
	public boolean getEmp_sub() {
		return emp_sub;
	}
	public void setEmp_sub(boolean emp_sub) {
		this.emp_sub = emp_sub;
	}
}
