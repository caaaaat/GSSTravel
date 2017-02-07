package controller;

import java.io.IOException;
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

import model.EmployeeService;
import model.EmployeeVO;
import model.FamilyService;
import model.FamilyVO;



@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	
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
		String[] fameat = req.getParameterValues("fameat");
		String[] famcarboolean = req.getParameterValues("famcar");
		String[] fambabyboolean = req.getParameterValues("fambaby");
		String[] famkidboolean = req.getParameterValues("famkid");
		String[] famdisboolean = req.getParameterValues("famdis");
		String[] fammomboolean = req.getParameterValues("fammom");
		String[] famben = req.getParameterValues("famben");
		String[] fambenrel = req.getParameterValues("fambenrel");
		String[] famemg = req.getParameterValues("famemg");
		String[] famemgphpone = req.getParameterValues("famemgphpone");
		String[] famnote = req.getParameterValues("famnote");
		
		String buttoninsert =req.getParameter("button");
		String buttondelete =req.getParameter("button");
		String buttonsave = req.getParameter("button");
		
		
		Map<String,String> errormsg = new HashMap<String, String>();
		req.setAttribute("error", errormsg);
		
		
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
		List<Integer> famcar =  new ArrayList<Integer>();
		List<Integer> fambaby = new ArrayList<Integer>();
		List<Integer> famkid = new ArrayList<Integer>();
		List<Integer> famdis = new ArrayList<Integer>();
		List<Integer> fammom = new ArrayList<Integer>();
		try{
		if(famcarboolean!=null){
			
			for(String car: famcarboolean){
				if(car=="on"){//陣列無法加一個原素上去
					famcar.add(1);
				}else{
					famcar.add(0);
				}
			}
		}
			
		if(fambabyboolean!=null){
		
			for(String baby: fambabyboolean){
				if(baby=="on"){
					fambaby.add(1);
				}else{
					fambaby.add(0);
				}
			}
		}
			
		if(famkidboolean!=null){
	
			for(String kid: famkidboolean){
				if(kid=="on"){
					famkid.add(1);
				}else{
					famkid.add(0);
				}
			}
		}
		
		if(famdisboolean!=null){//不是on就是null不過null怎麼寫進去db裡面?

			for(String dis: famdisboolean){
				if(dis=="on"){
					famdis.add(1);
				}else{
					famdis.add(0);
				}
			}
		}else{
			
//			for(String dis: famdisboolean){//伊定要把陣列裡面的String給取出來 把null帶進去 為0? 還是null寫的進去資料庫
//				if(dis==null){
//					famdis=0;
//				}
//			}
			
		}
			
		if(fammomboolean!=null){

			for(String mom: fammomboolean){
				if(mom=="on"){
					fammom.add(1);
				}else{
					fammom.add(0);
				}
			}
		}
			
		}catch(NumberFormatException e){
			errormsg.put("boolean","Boolean 格式轉換錯誤");
			System.out.println("Boolean 格式轉換錯誤");
			e.printStackTrace();
			
		}
		
		java.util.Date fambdate = null ;
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(fambdatedate.length);
				for(String bdate:fambdatedate){					
					try {
						fambdate =sdf.parse(bdate);
//						System.out.println(sdf.format(fambdate));
					} catch (ParseException e) {
						errormsg.put("fambdate","日期錯誤必須符合 年-月-日格式");
						System.out.println("使用者輸入日期錯誤");
					}
				}
			
		if(famname==null || famname.length==0 ){
			errormsg.put("famname", "親屬家人不能為空值");}
		if( famid ==null ||famid.length==0){
			errormsg.put("famid", "親屬身分證不能為空值");}
		if(famben ==null ||famben.length==0){
			errormsg.put("famben", "親屬保險受益人不能為空值");}
		if(fambenrel ==null ||fambenrel.length==0){
			errormsg.put("fambenrel", "親屬保險受益人關係不能為空值");}
		if(famemg ==null ||famemg.length==0){
			errormsg.put("famemg", "親屬緊急聯絡人不能為空值");}
		if(famemgphpone ==null ||famemgphpone.length==0){
			errormsg.put("famemgphpone", "親屬緊急聯絡人電話不能為空值");}
			
		
		
		  	
		
			if(errormsg!=null && !errormsg.isEmpty()){
				System.out.println("親屬員工檢查錯誤");
				req.getRequestDispatcher("start").forward(req, res);
				return;	
			}else{
				System.out.println("親屬員工檢查完畢");
			}
			
			
		//網路頁面上取值 
		EmployeeVO employeevo = new EmployeeVO();
		employeevo.setEmp_Phone(empphone);//200
		employeevo.setEmp_Ben(empben);//丹尼爾
		employeevo.setEmp_BenRel(empbenrel);//夫妻
		employeevo.setEmp_Emg(empemg);//喬治
		employeevo.setEmp_EmgPhone(empemgphone);//200       
		employeevo.setEmp_Eat(empeat);//葷
		if(empnote!=null || empnote.length()!=0){
			employeevo.setEmp_Note(empnote);
		}else{
			employeevo.setEmp_Note(null);
		}
		//測試 把其他值放進去
		// employeevo.setEmpno(310);
		// employeevo.setEmpid("A226874245");
		// employeevo.setEmpname("2000000");
		// employeevo.setEmpsex("女");
		// employeevo.setEmpbdate(new Date(1977-12-01));
		// employeevo.setEmpemgrel("200");
		// employeevo.setEmphiredate(new Date(1974-11-01));
		// employeevo.setEmpsub(true);
		// String str = "kitty";
		// byte[] bytes = str.getBytes();
		// employeevo.setEmppw(bytes);
		// employeevo.setDeptno("K01");
		// employeevo.setEmpemail("阿福@gmail.com");
		// employeevo.setEmprole(false);//true 為一  false 為零		

		
		if("save".equals(buttonsave)){
		 employeeservice.update(employeevo);
		 
		 int idlength=0;
		 for(String id:famid){
			 idlength=famid.length;
		 };
//		 System.out.println(idlength);
		 
//		 for(int i=0;i<=idlength;i++){
//		 FamilyVO familyvo = new FamilyVO();
//			familyvo.setFamrel(famrel[i]);
//			familyvo.setFamname(famname[i]);
//			familyvo.setFamsex(famsex[i]);
//			familyvo.setFamid(famid[i]);
//			familyvo.setFambdate(fambdate);
//			familyvo.setFameat(fameat[i]);
//			familyvo.setFamcar(famcar.add(i));
//			familyvo.setFambaby(fambaby.add(i));
//			familyvo.setFamkid(famkid.add(i));
//			familyvo.setFamdis(famdis.add(i));
//			familyvo.setFammom(fammom.add(i));
//			familyvo.setFamben(famben[i]);
//			familyvo.setFambenrel(fambenrel[i]);
//			familyvo.setFamemg(famemg[i]);
//			familyvo.setFamemgphone(famemgphpone[i]);
//			if(famnote!=null || famnote.length!=0){
//				familyvo.setFamnote(famnote[i]);
//			}else{
//				familyvo.setFamnote(null);
//			}
//			//測試 
//			int no=102;
//			familyvo.setEmpno(no);
//			familyvo.setFamphone("2222222222");
//			familyvo.setFamemgrel("喬治");
//			
			
			
			//登入後帶入empno 找出 famname
			//判斷 有些要update 有些要insert?
			//假如famno有存在就用update 假如不存在就用insert?
//			 List<String> id = familyservice.selectid(no);
//			 for(String fam: famid){
//				 for(String idid: id){	
//					if(fam.equals(idid)==true){
//						familyservice.update(familyvo); 
//						req.getRequestDispatcher("start").forward(req, res);
//						System.out.println("修改完畢");
//					 }else{
//						 familyservice.insert(familyvo);
//						 req.getRequestDispatcher("start").forward(req, res);
//						 System.out.println("新增完畢");
//					 }
//			 	}
//			 }
			 //等等測試
//			 req.getRequestDispatcher("start").forward(req, res);
				
				
//				
//		 }
		 
			
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		this.doPost(req, res);
	}
	
}
 