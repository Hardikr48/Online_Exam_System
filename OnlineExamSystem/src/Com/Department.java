package Com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import DAO.DepartmentDao;
import DAO.SemDao;
import VO.CollegeVo;
import VO.DepartmentVo;
import VO.SemVo;

/**
 * Servlet implementation class College
 */
@WebServlet("/Department")
public class Department extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Department() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		if (flag.equalsIgnoreCase("insert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			serchSem(request, response);
			response.sendRedirect("College_Department_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentlist")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Department_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartrmentsearch")) {
//			viewSemDepartmentList(request, response);
			response.sendRedirect("College_Sem_Department_List.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
			departmentInsert(request, response);
			response.sendRedirect("College_Department_Reg.jsp");
		}
	}

	protected void serchSem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int collegeid = (int) session.getAttribute("collegeid");

		CollegeVo collegevo = new CollegeVo();
		collegevo.setId(collegeid);

		SemVo semvo = new SemVo();
		semvo.setCollege(collegevo);

		SemDao semdao = new SemDao();
		ArrayList<SemVo> semlist = semdao.searchSem(semvo);
		System.out.println(semlist.size());
		session.setAttribute("semlist", semlist);
	}

	protected void departmentInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
			try {

			int companyid = (int) session.getAttribute("collegeid");
			System.out.println(companyid);
			String departmentname = request.getParameter("departmentname");
			int code = Integer.parseInt(request.getParameter("departmentcode"));
			
			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(companyid);

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setDepartment(departmentname);
			departmentvo.setDepartmentcollegeid(collegevo);
			departmentvo.setDepartmentcode(code);

			DepartmentDao departmentdao = new DepartmentDao();
			ArrayList<DepartmentVo> departmentist = departmentdao.chackDepartment(departmentvo);
			System.out.println(departmentist);
			if (departmentist.isEmpty() == true) {
				departmentdao.insertDepartment(departmentvo);
				session.setAttribute("adddepartment", "true");
			} else {
				int companyid1 = departmentist.get(0).getDepartmentcollegeid().getId();
				if (companyid1 == companyid) {
					session.setAttribute("department", "true");
				} else {
					departmentdao.insertDepartment(departmentvo);
					session.setAttribute("adddepartment", "true");
				}
			}
		} catch (Exception e) {
			session.setAttribute("selectsem", "true");
		}
	}

	protected void viewcollegeDepartmentList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = (int) session.getAttribute("collegeid");

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setDepartmentcollegeid(collegevo);

			DepartmentDao departmentdao = new DepartmentDao();
			ArrayList<DepartmentVo> departmentist = departmentdao.searchCollegeDepartment(departmentvo);
			System.out.println(departmentist.size());
			session.setAttribute("departmentist", departmentist);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	private void viewSemDepartmentList(HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession();
//		try {
//			int semid = Integer.parseInt(request.getParameter("id"));
//
//			SemVo semvo = new SemVo();
//			semvo.setId(semid);
//
//			DepartmentVo departmentvo = new DepartmentVo();
//			departmentvo.setSemid(semvo);
//
//			DepartmentDao departmentdao = new DepartmentDao();
//			ArrayList<DepartmentVo> departmentist = departmentdao.searchSemDepartment(departmentvo);
//
//			session.setAttribute("departmentist", departmentist);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
