package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemService;
import model.ItemVO;
import model.TravelService;
import model.TravelVO;


@WebServlet(urlPatterns = { ("/Travel_Edit") })


/*---------*/
public class TravelServlet extends HttpServlet {

// private TravelService travelService =null;
	private TravelService travelService = new TravelService();
	private ItemService itemService = new ItemService();
	/* 初始化 */

	private SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/* 初始化END */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 1.接收資料
		String inputdate = "";
		if (request.getParameter("inputdate") != "" && request.getParameter("inputdate") != null)
		inputdate = request.getParameter("inputdate");

//		String traNo = request.getParameter("edittraNO");//正式用
		String traNo = request.getParameter("tra_no"); // 單頁更新測試用
		System.out.println(traNo);
		String traName = request.getParameter("edittraName");
		String traLoc = request.getParameter("edittraLoc");
		String traOn = request.getParameter("edittraOn");
		String traOff = request.getParameter("edittraOff");
		String traBeg = request.getParameter("edittraBeg");
		String traEnd = request.getParameter("edittraEnd");
		String traTotal = request.getParameter("edittraTotal");
		String traMax = request.getParameter("edittraMax");
		String traIntr = request.getParameter("edittraIntr");
		String traCon = request.getParameter("edittraCon");
		String traAtter = request.getParameter("edittraAtter");
		String traFile = request.getParameter("edittraFile");

		String itemNo = request.getParameter("edititemNo");
		String itemName = request.getParameter("edititemName");
		String itemMoney = request.getParameter("edititemMoney");
		String inputerrors = request.getParameter("inputerrors");
		
		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		/*
		 * if("Insert".equals(inputerrors) || "Update".equals(prodaction) ||
		 * "Delete".equals(prodaction)) { if(temp1==null || temp1.length()==0) {
		 * errors.put("id", "請輸入Id以便執行"+inputerrors); } }
		 */

		// 3.轉換資料

		/* 活動代碼 */ // 單筆測試用
		
		String edittraNO = "";
		if (traNo != null && traNo.length() != 0) {
			try {
				edittraNO = traNo;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("edittraNO", "活動代碼必須輸入");
			}
		}
		
		/* 活動地點 */ // 測試用

		String edittraLoc = "";
		if (traLoc != null && traLoc.length() != 0) {
			try {
				edittraLoc = traLoc;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("edittraLoc", "活動名稱必須輸入");
			}

		}
		///* 活動名稱 */ // 測試用

		String edittraName = "";
		if (traName != null && traName.length() != 0) {
			try {
				edittraName = traName;
			} catch (Exception e) {
				e.printStackTrace();
				errors.put("edittraName", "活動名稱必須輸入");
			}

		}
		System.out.println(edittraName+","+request.getParameter("edittraName"));

		/* 活動起始日 */
		//java.util.Date edittraOn = new java.util.Date(edittraOn.getTime());  // sql -> util
		java.util.Date edittraOn = null;
		if (traOn != null && traOn.length() != 0) {
			try {
				edittraOn = sDate.parse(traOn);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("edittraOn", "活動起始日必須是符合yyyy-Mm-dd格式的日期");
			}
		}

		/*---活動結束日---*/
		//java.util.Date edittraOff = new java.util.Date(edittraOff.getTime());  // sql -> util
		java.util.Date edittraOff = null;
		if (traOff != null && traOff.length() != 0) {
			try {
				edittraOff = sDate.parse(traOff);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("edittraOff", "活動結束日必須是符合yyyy-Mm-dd格式的日期");
			}
		}

		/*---活動報名登記日---*/
		java.util.Date edittraBeg = null;
		if (traBeg != null && traBeg.length() != 0) {
			try {
				edittraBeg = sDateTime.parse(traBeg);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("edittraBeg", "活動報名登記日必須是符合yyyy-Mm-dd HH:mm:ss格式的日期");
			}
		}

		/*---活動報名結束日---*/

		//java.sql.Timestamp edittraEnd = null;
		java.util.Date edittraEnd = null;
		if (traEnd != null && traEnd.length() != 0) {
			try {
				edittraEnd = sDateTime.parse(traEnd);
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("edittraEnd", "活動報名結束日必須是符合yyyy-Mm-dd HH:mm:ss格式的日期");
			}
		}

		/*---活動總人數---*/

		int edittraTotal = 0;
		if (traTotal != null && traTotal.length() != 0) {
			try {
				edittraTotal = Integer.parseInt(traTotal);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edittraTotal", "活動總人數必須是整數");
			}
		}

		/*---活動報名上限人數---*/

		int edittraMax = 0;
		if (traMax != null && traMax.length() != 0) {
			try {
				edittraMax = Integer.parseInt(traMax);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edittraMax", "活動報名上限人數必須是整數");
			}
		}

		/*---活動說明---*/

		String edittraIntr = "0";
		if (traIntr != null && traIntr.length() != 0) {
			try {
				edittraIntr = traIntr;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edittraIntr", "活動說明必須輸入");
			}
		}

		/*---活動內容---*/

		String edittraCon = "0";
		if (traCon != null && traCon.length() != 0) {
			try {
				edittraCon = traCon;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edittraCon", "活動內容必須是輸入");
			}
		}

		/*---活動注意事項---*/

		String edittraAtter = "";
		if (traAtter != null && traAtter.length() != 0) {
			try {
				edittraAtter = traAtter ;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edittraAtter", "活動注意事項必須是輸入");
			}
		}

		/*---費用項目代碼---*/ // 測試用
		int edititemNo = 0;
		if (itemNo != null && itemNo.length() != 0) {
			try {
				edititemNo = Integer.parseInt(itemNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edititemNo", "活動說明必須輸入");
			}
		}
		
		/*---費用項目---*/

		String edititemName = "";
		if (itemName != null && itemName.length() != 0) {
			try {
				edititemName =  itemName;
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edititemName", "費用項目必須是輸入");
			}
		}

		/*---費用金錢---*/
		float edititemMoney = 0;
		if (itemMoney != null && itemMoney.length() != 0) {
			try {
				edititemMoney = Float.parseFloat(itemMoney);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errors.put("edititemMoney", "活動說明必須輸入");
			}
		}
		
		/*回報錯誤*/
		 if(errors!=null && !errors.isEmpty()) {
		 request.getRequestDispatcher(
		 "Travel_Edit.jsp").forward(request, response);
		 return;
		 }
		
		 // 4.呼叫Model
		/*---單頁測試用---*/
		TravelVO travelview = travelService.select(traNo);
		System.out.println("travelService位置 : "+travelview);
		List<ItemVO> itemview = itemService.select(traNo);	//list
		request.setAttribute("params", travelview);
		request.setAttribute("paramsi", itemview);
		System.out.println("itemService資料數 : "+itemview.size());

		// 5.根據Model執行結果，決定需要顯示的View元件
		
		/*----Insert----*/
		if("Insert".equals(inputerrors)) {
			/*--Travel--*/
			
			travelview.setTra_NO(edittraNO);
			travelview.setTra_Name(edittraName);
			travelview.setTra_Loc(edittraLoc);
			travelview.setTra_On(new java.sql.Date(edittraOn.getTime()));
			travelview.setTra_Off(new java.sql.Date(edittraOff.getTime()));
			travelview.setTra_Beg(new java.sql.Timestamp(edittraBeg.getTime()));
			travelview.setTra_End(new java.sql.Timestamp(edittraEnd.getTime()));
			travelview.setTra_Total(edittraTotal);;
			travelview.setTra_Max(edittraMax);;
			travelview.setTra_Intr(traIntr);;
			travelview.setTra_Con(traCon);
			travelview.setTra_Atter(traAtter);
			travelview.setTra_Loc(traLoc);
			travelview.setTra_File(traFile);
			TravelVO resultnew = travelService.insert(travelview);
			System.out.println("travelService資料 : "+travelview.getTra_On());
			
			/*--item--*/
			
			if(resultnew==null) {
				errors.put("action", "Insert fail");
			} else {
				session.setAttribute("insert", resultnew);
			}
			request.getRequestDispatcher("/Travel_New.jsp").forward(request, response);
		}
	
		/*----Update----*/
		else if ("Update".equals(inputerrors)) {
			
		/*--Travel--*/	
			travelview.setTra_Name(edittraName);
			travelview.setTra_Loc(edittraLoc);
			System.out.println(travelview);
			travelview.setTra_On(new java.sql.Date(edittraOn.getTime()));
			travelview.setTra_Off(new java.sql.Date(edittraOff.getTime()));
			travelview.setTra_Beg(new java.sql.Timestamp(edittraBeg.getTime()));
			travelview.setTra_End(new java.sql.Timestamp(edittraEnd.getTime()));
			travelview.setTra_Total(edittraTotal);;
			travelview.setTra_Max(edittraMax);;
			travelview.setTra_Intr(traIntr);;
			travelview.setTra_Con(traCon);
			travelview.setTra_Atter(traAtter);
			travelview.setTra_Loc(traLoc);
			travelview.setTra_File(traFile);
			 
		/*--item--*/	 
			 List<ItemVO> itemfor = new ArrayList<ItemVO>();  
			 for(ItemVO v:itemview){
				 v.setItem_Name(edititemName);
				 System.out.println(edititemName);
				 v.setItem_Money(edititemMoney);
				 System.out.println(edititemMoney);
				 ItemVO result1 = itemService.update(v);
				 itemfor.add(result1);
			}
			 //System.out.println(test1out);
			 TravelVO resultEdit = travelService.update(travelview);
			
			//ItemVO bean = new ItemVO();
			//List<ItemVO> result1 = (List<ItemVO>) itemService.update(bean);
			if (resultEdit == null ) {	//& result1 == null
				errors.put("action", "Update fail");
			} else {
				session.setAttribute("update", resultEdit);
			}
		/*----Delete----*/	
		} else if ("Delete".equals(inputerrors)) {
			boolean result = travelService.delete(travelview);
			if (!result) {
				session.setAttribute("delete", 0);
			} else {
				session.setAttribute("delete", 1);
			}	
		}
		 request.getRequestDispatcher("/Travel_Edit.jsp").forward(request,response); //測試用
	}// doGet

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}// END
