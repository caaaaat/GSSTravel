package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TravelService;
import model.TravelVO;

@WebServlet("/AllTravel")
public class AllTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TravelService travelService=new TravelService();
	private Map<String, String> mp;
    public AllTravel() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TravelVO> result;
		try {
			HttpSession session = request.getSession();
			Integer emp_No = (Integer) session.getAttribute("emp_No");
			mp = travelService.selectTra_No(emp_No.toString());//代員工編號
			result = travelService.select();
			
			request.setAttribute("select", result);
			request.setAttribute("mp", mp);
			request.getRequestDispatcher("/Travel.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
