package controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FineService;
import model.FineVO;
import model.ItemService;
import model.ItemVO;
import model.TravelService;
import model.TravelVO;

@WebServlet("/FineShowOneServlet")
public class FineShowOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FineService fineService = new FineService();
	private TravelService travelService = new TravelService();
	private ItemService itemService = new ItemService();
	int countI = 0;
	int countJ = 0;

	public FineShowOneServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FineVO fineBean = null;
		ItemVO itemBean = null;
		boolean power = true;
		power = true;		
		String tra_No = request.getParameter("tra_No");
		List<TravelVO> tOneResult = travelService.selectOne(tra_No);
		List<FineVO> fResult = fineService.select(fineBean);
		List<ItemVO> iResult = itemService.select(itemBean);
		countI = fResult.size() - 1;
		countJ = tOneResult.size() - 1;
		request.setAttribute("countI", countI);
		request.setAttribute("countJ", countJ);
		request.setAttribute("tSelect", tOneResult);
		request.setAttribute("fSelect", fResult);
		request.setAttribute("iSelect", iResult);
		request.setAttribute("power", power);

		String formatDay = null;
		String startDay = null;
		String[][] totalDays = new String[tOneResult.size()][fResult.size() + 1];
		String[][] afterDay = new String[tOneResult.size()][fResult.size() + 1];
		List<String> days = new ArrayList<String>();
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");

		long date = tOneResult.get(0).getTra_On().getTime() / 1000;
		startDay = tOneResult.get(0).getTra_On().toString();
		days.add(startDay);
		for (int j = fResult.size() - 1; j >= 0; j--) {
			long beforeDay = date - 60 * 60 * 24 * fResult.get(j).getFine_Dates();
			tOneResult.get(0).getTra_On().setTime(beforeDay * 1000);
			formatDay = formatter.format(tOneResult.get(0).getTra_On());
			String[] num = formatDay.split("-");
			int d = 0;
			d = Integer.parseInt(num[2]) + 1;
			String ym = formatDay.substring(0, 8);
			if (d < 10) {
				afterDay[0][j] = (String) ym + "0" + d;
			} else {
				afterDay[0][j] = (String) ym + d;
			}
			days.add(formatDay);
		}
		Collections.sort(days);
		for (int k = 0; k <= fResult.size(); k++) {
			totalDays[0][k] = days.get(k).toString();
		}
		request.setAttribute("afterDay", afterDay);
		request.setAttribute("totalDays", totalDays);
		days.clear();

		RequestDispatcher rd = request.getRequestDispatcher("/view/FineShowOne.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
