package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailService;
import model.EmployeeService;
import model.EmployeeVO;
import model.FamilyService;
import model.FamilyVO;
import model.IItemDAO;
import model.ItemDAO;
import model.ItemService;
import model.ItemVO;
import model.TotalAmountService;
import model.TotalAmountVO;
import model.TravelService;
import model.TravelVO;

@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailService detailService=new DetailService();
	private EmployeeService employeeService=new EmployeeService();
	private FamilyService familyService=new FamilyService();
	private TravelService travelService=new TravelService();
	private ItemService itemService=new ItemService();
    public CancelServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long tra_No=Long.parseLong(request.getParameter("tra_No"));//旅遊編號
		Integer emp_No=Integer.parseInt(request.getParameter("emp_No"));//員工標號
		String myName = employeeService.getName(emp_No.toString());//登入者姓名
		List<String> names = detailService.detailName(tra_No);//已經報明姓名
		Map<String, Integer> mp = detailService.detail(tra_No);//(姓名,人數)
		List<FamilyVO> familyVO = familyService.selectFam(emp_No.toString(),tra_No);//親朋好友
		int familySize = familyVO.size();//親朋好友數量
		TravelVO tra_Vo = travelService.select(tra_No);
		List<ItemVO> itemVo = itemService.getRoomMoney(tra_No);	
		EmployeeVO vo = employeeService.select(emp_No.toString());
		TotalAmountService totalAmountService=new TotalAmountService();
		List<Long> detail=new ArrayList<>();
		long sub;
		long hireMonths;
		java.sql.Date hireDate =vo.getEmp_HireDate();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//現在系統時間
		java.sql.Date today = java.sql.Date.valueOf(date);
		if(hireDate.getTime()/(24*60*60*1000)+365<today.getTime()/(24*60*60*1000)){
			sub=4500;
		}else{				
			long x = today.getTime()/(24*60*60*1000)-hireDate.getTime()/(24*60*60*1000);//相差天數			
			hireMonths=x/31;
			sub=4500/12*hireMonths;			
		}
		TotalAmountVO totalAmountvo = totalAmountService.selectTa_money(emp_No.toString());
		long ta_Money = (long)totalAmountvo.getTa_Money();
		long money = 0;
		List<String> fam_Rels = detailService.selectFam_Rel(emp_No, tra_No);
		IItemDAO itemDAO = new ItemDAO();
		List<ItemVO> itemVO = itemDAO.getFareMoney(tra_No);
		long payMoney=0;
		long counts=0;
		for(ItemVO a:itemVO){
			payMoney+=a.getItem_Money();
		}
		for(String fam_Rel:fam_Rels){
			if("親友".equals(fam_Rel)){
				counts+=1;
			}
		}	
		if(ta_Money<sub){
			if(detailService.selectFam_Rel(emp_No, tra_No)!=null){				
				money=counts*payMoney;
			}else{
				money=0;
			}			
		}else{
			long count=mp.get(myName);//總人數
			long x=count-counts;
			long y=x*payMoney;
			if(y>sub){
				money=ta_Money-sub;
			}else{
				money=counts*payMoney;
			}
		}
		
		detail.add(sub);
		detail.add(ta_Money);
		detail.add(money);
		
		request.setAttribute("detail", detail);
		request.setAttribute("itemVo", itemVo);
		request.setAttribute("tra_Vo", tra_Vo);
		request.setAttribute("familySize", familySize);
		request.setAttribute("familyVO", familyVO);
		request.setAttribute("myName", myName);
		request.setAttribute("emp_No", emp_No);
		request.setAttribute("name", names);
		request.setAttribute("mp", mp);
		request.setAttribute("tra_count", detailService.tra_count(tra_No));//單行程旅遊人數
		request.setAttribute("tra_order", detailService.ranking(tra_No,myName));//報名順序
		request.getRequestDispatcher("/Cancel.jsp").forward(request, response);;	
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
