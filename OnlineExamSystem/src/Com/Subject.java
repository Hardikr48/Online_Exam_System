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
import DAO.SubjectDao;
import VO.CollegeVo;
import VO.DepartmentVo;
import VO.SemVo;
import VO.SubjectVo;

/**
 * Servlet implementation class College
 */
@WebServlet("/Subject")
public class Subject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Subject() {
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
			department.serchSem(request, response);
			response.sendRedirect("College_Subject_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("hodinsert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			department.serchSem(request, response);
			response.sendRedirect("HOD_Subject_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegesubject")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			searchCollegeSubject(request, response);
			response.sendRedirect("College_Subject_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemestersubject")) {
			searchSemSubject(request, response);
			response.sendRedirect("CollegeSemSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentsubject")) {
			searchDepartmentSubject(request, response);
			response.sendRedirect("CollegeDepartmentSubjectList.jsp");
		}
		if (flag.equalsIgnoreCase("hodviewsubject")) {
			searchDepartmentSubject(request, response);
			response.sendRedirect("HOD_Subject_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegedepartmentsemestersubject")) {
			searchSemSubject(request, response);
			response.sendRedirect("CollegeDepartmentSemSubjectList.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
				subjectInsert(request, response);
				response.sendRedirect("College_Subject_Reg.jsp");
		}
		
		if (flag.equalsIgnoreCase("hodinsert")) {
			subjectInsert(request, response);
			response.sendRedirect("HOD_Subject_Reg.jsp");
		}
	}

	private void subjectInsert(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = (int) session.getAttribute("collegeid");
			int semid = (Integer.parseInt(request.getParameter("semid")));
			int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
			String subjectname = request.getParameter("subject");

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			SemVo semvo = new SemVo();
			semvo.setId(semid);

			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setId(departmentid);

			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setCollegeid(collegevo);
			subjectvo.setSemid(semvo);
			subjectvo.setDepartmentid(departmentvo);
			subjectvo.setSubject(subjectname);

			SubjectDao subjectdao = new SubjectDao();
			ArrayList<SubjectVo> subjecttist = subjectdao.chackSubject(subjectvo);
			System.out.println(subjecttist.size());
			System.out.println("------------------------");
			if (subjecttist.isEmpty() == true) {
				subjectdao.insertSubject(subjectvo);
				session.setAttribute("addsubject", "true");
			} else if(subjecttist.isEmpty() == false) {
					session.setAttribute("cahcksubject", "true");
			}
		} catch (Exception e) {
			session.setAttribute("selectsemordepartment", "true");
		}
	}
	protected void searchCollegeSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int collegeid = (int) session.getAttribute("collegeid");
			
			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);
			
			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setCollegeid(collegevo);
			
			SubjectDao subjectdao = new SubjectDao();
			ArrayList<SubjectVo> subjecttist = subjectdao.searchAllSubject(subjectvo);
			
			
			session.setAttribute("subjectlist", subjecttist);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchSemSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			
			int semid = (Integer.parseInt(request.getParameter("id")));
			
			
			SemVo semvo = new SemVo();
			semvo.setId(semid);
			
			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setSemid(semvo);
			
			SubjectDao subjectdao = new SubjectDao();
			ArrayList<SubjectVo> subjecttist = subjectdao.searchSemSubject(subjectvo);
			System.out.println(subjecttist.size());
			session.setAttribute("semsubjectlist", subjecttist);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void searchDepartmentSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = (Integer.parseInt(request.getParameter("id")));
			
			DepartmentVo departmentvo = new DepartmentVo();
			departmentvo.setId(departmentid);
			
			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setDepartmentid(departmentvo);
			
			SubjectDao subjectdao = new SubjectDao();
			ArrayList<SubjectVo> subjecttist = subjectdao.searchDepartmentSubject(subjectvo);
			session.setAttribute("subjectlist", subjecttist);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
