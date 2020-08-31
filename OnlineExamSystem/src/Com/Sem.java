package Com;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.SemDao;
import VO.CollegeVo;
import VO.DepartmentVo;
import VO.SemVo;

/**
 * Servlet implementation class College
 */
@WebServlet("/Sem")
public class Sem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Sem() {
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
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Sem_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("viewsemlist")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			viewSemList(request, response);
			response.sendRedirect("College_Sem_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentsemlist")) {
			viewDepaertmentSemList(request, response);
			response.sendRedirect("College_Sem_List.jsp");
		}
		if (flag.equalsIgnoreCase("deletesem")) {
			deleteSem(request, response);
			viewSemList(request, response);
			response.sendRedirect("College_Sem_List.jsp");
		}
	}

	private void viewDepaertmentSemList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = Integer.parseInt(request.getParameter("id"));

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setId(departmentid);
			
			SemVo semvo = new SemVo();
			semvo.setDepartmentid(departmentvo);

			SemDao semdao = new SemDao();
			ArrayList<SemVo> semlist = semdao.searchDepartmentSem(semvo);

			session.setAttribute("semlist", semlist);

		} catch (Exception e) {
			e.printStackTrace();
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
			semInsert(request, response);
			response.sendRedirect("College_Sem_Reg.jsp");
		}
	}

	private void semInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int collegeid = (int) session.getAttribute("collegeid");
			int sem = Integer.parseInt(request.getParameter("sem"));
			int departmentid = Integer.parseInt(request.getParameter("departmentid"));
			if (sem > 0 && sem < 9) {
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

				SemVo semvo = new SemVo();
				semvo.setCollege(collegevo);
				semvo.setSemname(sem);
				semvo.setDepartmentid(departmentvo);

				SemDao semdao = new SemDao();
				ArrayList<SemVo> semlist = semdao.chackhDepartmentSem(semvo);
				System.out.println(semlist.size());
				if (semlist.isEmpty() == true) {
					String s = semdao.insertSame(semvo);
					System.out.println(s);
					if (s.equals("erorr")) {
						session.setAttribute("erorr", "true");
					} else if (s.equals("add")) {
						session.setAttribute("addsem", "true");
					}
				}
			} else {
				session.setAttribute("semno", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void viewSemList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = (int) session.getAttribute("collegeid");

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			SemVo semvo = new SemVo();
			semvo.setCollege(collegevo);

			SemDao semdao = new SemDao();
			ArrayList<SemVo> semlist = semdao.searchSem(semvo);

			session.setAttribute("semlist", semlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteSem(HttpServletRequest request, HttpServletResponse response) {
		try {
			System.out.println("........................................");
			int semid = Integer.parseInt(request.getParameter("id"));

			SemVo semvo = new SemVo();
			semvo.setId(semid);

			SemDao semdao = new SemDao();
			semdao.deleteSem(semvo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
