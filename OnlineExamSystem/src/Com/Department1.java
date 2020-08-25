package Com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.DepartmentDao;
import VO.DepartmentVo;
import VO.SemVo;

/**
 * Servlet implementation class Department1
 */
@WebServlet("/Department1")
public class Department1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Department1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int semid = Integer.parseInt(request.getParameter("q"));
		SemVo semvo = new SemVo();
		semvo.setId(semid);
		System.out.println("..................................................................");
		DepartmentVo departmentvo = new DepartmentVo();
		departmentvo.setSemid(semvo);
		Gson gson = new Gson();
		DepartmentDao departmentdao = new DepartmentDao();
		ArrayList<DepartmentVo> departmentlist = departmentdao.searchSemDepartment(departmentvo);
		System.out.println(departmentlist.size());
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(departmentlist));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
