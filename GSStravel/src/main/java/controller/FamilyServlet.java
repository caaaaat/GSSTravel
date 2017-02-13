package controller;

import java.io.IOException;
import java.sql.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeeService;
import model.EmployeeVO;
import model.FamilyService;
import model.FamilyVO;



@WebServlet("/FamilyServlet")
public class FamilyServlet extends HttpServlet {
	
private static final ArrayList<Object> Integer = null;
private EmployeeService employeeservice = new EmployeeService();
private FamilyService familyservice= new FamilyService();
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		//接收資料 驗證資料 轉換資料 呼叫Model 根據Model執行結果，決定需要顯示的View元件
		String empphone = req.getParameter("empphone");
		String empben =  req.getParameter("empben");
		String empbenrel = req.getParameter("empbenrel");
		String empemg =req.getParameter("empemg");
		String empemgphone= req.getParameter("empemgphone");		
		String empeat = req.getParameter("empeat");
		String empnote = req.getParameter("empnote");
		//親屬
		String[] famrel = req.getParameterValues("famrel");
		String[] famname = req.getParameterValues("famname");
		String[] famsex = req.getParameterValues("famsex");
		String[] famid = req.getParameterValues("famid");
		String[] fambdatedate= req.getParameterValues("fambdate");
		String[] famphone=req.getParameterValues("famphone");
		String[] fameat = req.getParameterValues("fameat");
//		String famspa=req.getParameter("famspa");
		String[] famspa=req.getParameterValues("famspa");

		
		String[] famben = req.getParameterValues("famben");
		String[] fambenrel = req.getParameterValues("fambenrel");
		String[] famemg = req.getParameterValues("famemg");
		String[] famemgphpone = req.getParameterValues("famemgphpone");
		String[] famemgrel =req.getParameterValues("famemgrel");
		String[] famnote = req.getParameterValues("famnote");
		
		String buttoninsert =req.getParameter("button");
		String buttondelete =req.getParameter("button");
		String buttonsave = req.getParameter("button");
		
		
		Map<String,String> errormsg = new HashMap<String, String>();
		req.setAttribute("error", errormsg);
		try{
	           String orderId = req.getAttribute("selectvalue").toString();
	        
	           System.out.println(orderId);
	       
		}catch(Exception ex){
			System.out.println("aaaaa");
		}
		
//		String[] items = loca.replaceAll("\\[", "").replaceAll("\"","")
//                .replaceAll("\\]", "").split(",");
//		if(famspa!=null){
//			 String[] spa = famspa.replaceAll("\\[", "").replaceAll("\"","").replaceAll("\\]", "").split(",");
//			 System.out.println(spa);
//		}
		
		//員工 轉值
		if(empphone==null|| empphone.length()==0){
			errormsg.put("empphone", "員工電話不能為空值");}
		if(empemgphone==null|| empemgphone.length()==0){
			errormsg.put("empemgphone", "員工緊急聯絡人電話不能為空值");}
		if(empben==null || empben.length()==0 ){
			errormsg.put("empben", "員工保險受益人不能為空值");}
		if( empbenrel ==null ||empbenrel.length()==0){
			errormsg.put("empbenrel", "員工保險受益人關係不能為空值");}
		if(empemg ==null ||empemg.length()==0){
			errormsg.put("empemg", "員工緊急聯絡人不能為空值");}
		
		
		//親屬 轉值
		List<Date> fambdate = new ArrayList<Date>();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
				for(String bdate:fambdatedate){					
					try {
						fambdate.add(sdf.parse(bdate));
					} catch (ParseException e) {
						errormsg.put("fambdate","日期錯誤必須符合 年-月-日格式");
						System.out.println("使用者輸入日期錯誤");
					}
				}
				
		if(famname==null || famname.length==0 ){
			errormsg.put("famname", "親屬家人不能為空值");}
		if( famid ==null ||famid.length==0){
			errormsg.put("famid", "親屬身分證不能為空值");}
		if(famphone==null || famphone.length==0){
			errormsg.put("famphone", "親屬電話不能為空值");}
		if(famben ==null ||famben.length==0){
			errormsg.put("famben", "親屬保險受益人不能為空值");}
		if(fambenrel ==null ||fambenrel.length==0){
			errormsg.put("fambenrel", "親屬保險受益人關係不能為空值");}
		if(famemg ==null ||famemg.length==0){
			errormsg.put("famemg", "親屬緊急聯絡人不能為空值");}
		if(famemgphpone ==null ||famemgphpone.length==0){
			errormsg.put("famemgphpone", "親屬緊急聯絡人電話不能為空值");}
		if(famemgrel==null || famemgrel.length==0){
			errormsg.put("famemgrel", "親屬緊急聯絡人關係不能為空值");}
		
			if(errormsg!=null && !errormsg.isEmpty()){
				System.out.println("親屬員工檢查錯誤");
				req.getRequestDispatcher("register").forward(req, res);
				return;	
			}else{
				System.out.println("親屬員工檢查完畢");
			}
			


			List<Integer> spa = new ArrayList();
			if(famspa!=null){
				for(int i =1;i<=famname.length*4-3;i+=4){ //假如 一位親屬無特殊身份? 	
					for(String xxx:famspa){//1234 5678 9101112 13 17  
						if(xxx.equals("no")==true){spa.add(i,0);
							spa.add(i+1, 0); spa.add(i+2, 0); spa.add(i+3, 0);}
						if(xxx.equals("baby")==true){ spa.add(i, 1);}
						if(xxx.equals("baby")==false){ spa.add(i, 0);}
						if(xxx.equals("kid")==true){ spa.add(i+1, 1);}
						if(xxx.equals("kid")==false){ spa.add(i+1, 0);}
						if(xxx.equals("dis")==true){ spa.add(i+2, 1);}
						if(xxx.equals("dis")==false){ spa.add(i+2, 0);}
						if(xxx.equals("mom")==true){ spa.add(i+3, 1);}
						if(xxx.equals("mom")==false){ spa.add(i+3, 0);}
					}
				}
				
			}else{
				for(int i =1;i<=famname.length*4-3;i+=4){
//					spa.add(i, 0);//完全沒有時出現此行錯誤 
					spa.add(0);
//					System.out.println(spa);
				}
			}
			
			for(Integer yyy:spa){
				System.out.print(yyy + "+");
			}
			
	    	HttpSession session = req.getSession();
			Integer emp_No = (Integer) session.getAttribute("emp_No");
			
		//網路頁面上取值 
		EmployeeVO employeevo = new EmployeeVO();
		employeevo.setEmp_No(emp_No);
		employeevo.setEmp_Phone(empphone);
		employeevo.setEmp_Ben(empben);
		employeevo.setEmp_BenRel(empbenrel);
		employeevo.setEmp_Emg(empemg);
		employeevo.setEmp_EmgPhone(empemgphone);      
		employeevo.setEmp_Eat(empeat);
		if(empnote!=null || empnote.length()!=0){
			employeevo.setEmp_Note(empnote);
		}else{
			employeevo.setEmp_Note(null);
		}


		
		if("save".equals(buttonsave)){
		 employeeservice.update(employeevo);

		 int idlength=0;
		 idlength = famid.length;
//		 System.out.println(idlength);
		 
		 for(int i=0;i<idlength;i++){
//			 System.out.println(i); //帶i=0之後就帶不下去了 找index size錯誤
		 FamilyVO familyvo = new FamilyVO();
			familyvo.setFam_Rel(famrel[i]);
			
			familyvo.setFam_Name(famname[i]);
			familyvo.setFam_Sex(famsex[i]);
			familyvo.setFam_Id(famid[i]);
			familyvo.setFam_Bdate(new java.sql.Date(fambdate.get(i).getTime()));
			familyvo.setFam_Phone(famphone[i]);
			familyvo.setFam_Eat(fameat[i]);
			
//			if(famcar!=null){
//				for(String car:famcarboolean){
//					if(car.equals("on")){//{true}
//						familyvo.setFam_Car(true);
//					}
//					if(car.equals("off")){
//						familyvo.setFam_Car(false);
//					}
//				}
//			}else{
//				familyvo.setFam_Car(famcar.get(i).valueOf(false));
//			}
			

//			System.out.println(famcar.get(0).toString()+"xxxxxx");
//			System.out.println(famcar.get(i).toString()+"yyyyyy");
//			familyvo.setFam_Car(famcar.get(i));
//			
//			if(fambaby!=null){
//				if(fambaby.get(i).equals(true)){
//					familyvo.setFam_Bady(true);
//				}else{
//					familyvo.setFam_Bady(false);
//				}
//			}else{
//				familyvo.setFam_Bady(fambaby.get(i).valueOf(false));
//			}
//			familyvo.setFam_Bady(fambaby.get(i));
			
//			if(famkid!=null){
//				if(famkid.get(i).equals(true)){
//					familyvo.setFam_kid(true);
//				}else{
//					familyvo.setFam_kid(false);
//				}
//			}else{
//				familyvo.setFam_kid(famkid.get(i).valueOf(false));
//			}
//			familyvo.setFam_kid(famkid.get(i));
			
			
//			if(famdis!=null){
//				if(famdis.get(i).equals(true)){
//					familyvo.setFam_Dis(true);
//				}else{
//					familyvo.setFam_Dis(false);
//				}
//			}else{
//				familyvo.setFam_Dis(famdis.get(i).valueOf(false));
//			}
//			familyvo.setFam_Dis(famdis.get(i));
			
			
//			if(fammom!=null){
//				if(fammom.get(i).equals(true)){
//					familyvo.setFam_Mom(true);
//				}else{
//					familyvo.setFam_Mom(false);
//				}
//			}else{
//				familyvo.setFam_Mom(fammom.get(i).valueOf(false));
//			}
//			familyvo.setFam_Mom(fammom.get(i));
//			
			familyvo.setFam_Ben(famben[i]);
			familyvo.setFam_BenRel(fambenrel[i]);
			familyvo.setFam_Emg(famemg[i]);
			familyvo.setFam_EmgPhone(famemgphpone[i]);
			familyvo.setFam_EmgRel(famemgrel[i]);
			if(famnote!=null || famnote.length!=0){
				familyvo.setFam_Note(famnote[i]);
			}else{
				familyvo.setFam_Note(null);
			}
			familyvo.setEmp_No(emp_No);
//			//這邊用isFam_Car?? 看是做什麼用?xx servletid寫錯?xx familyvo值要加什麼?xx 
//			//單單只會抓到 第一筆裡面的值? 動態空白欄位的關係?抓到空白值 並且進去db? {判斷沒正確 裡面原本famid有的一樣會insert進去}
//			//單純抓到第一筆資料而已? 
			
			 List<String> id = familyservice.selectid(emp_No);
			
//			 for(String idid: id){
//			 System.out.println(idid);
//			 }
//			 System.out.println(famid.equals("Q250939543"));
//			 for(String x: famid){
//			 System.out.println(x);
//			 System.out.println(x=="Q250939543");
//			 System.out.println(x.equals("Q250939543"));
//			 
//			 }
//寫回圈帶入 SYs(famid[i]);帶一個值而已?
			 

//				 for(String y:id){
//					if(famid[i].equals(y)==true){//帶3個y入跟一個值去比對
//						System.out.println("xxxxx");//3 update
//					}else{
//						System.out.println("yyyyy");// 1-2 1-3 2-1 2-3 3-1 3-2
//					}
//					
//				 }
			 
//				 for(String idid: id){	
//					if(famid.equals(idid)==true){
//						familyservice.update(familyvo); 
//						System.out.println("修改完畢");
//					 }else{
//						 familyservice.insert(familyvo);
//						 System.out.println("新增完畢");
//					 }
//			 	}	
			 
//			 System.out.println(idlength);
		 }//回圈結束
		 
		 req.getRequestDispatcher("Register").forward(req, res);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.doPost(req, res);
	}
	
}
 