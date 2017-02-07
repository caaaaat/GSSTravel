package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DetailBean;
import model.DetailDAO;

@WebServlet("/TravelDetail")
public class TravelDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//String tra_No = request.getParameter("Tra_No");
    	String tra_No="201608020001";
    	float years_money=2800;
    	DetailDAO dao=new DetailDAO();
    	List<DetailBean> list=dao.select(tra_No);
    	request.setAttribute("list",list);
    	request.setAttribute("years_money",years_money);
    	request.setAttribute("tra_No", tra_No);
    	
    	request.getRequestDispatcher("Detail_Money.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
