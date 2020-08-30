package Com;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ExamDao;
import DAO.LoginDAO;
import DAO.StudentDao;
import VO.CollegeVo;
import VO.DepartmentVo;
import VO.ExamVo;
import VO.LoginVO;
import VO.SemVo;
import VO.StudentVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Exam
 */
@WebServlet("/Exam")
public class Exam extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Exam() {
		super();
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
			response.sendRedirect("College_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegeexam")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			searchCollegeExam(request, response);
			response.sendRedirect("College_Exam_List.jsp");
		}
		if (flag.equalsIgnoreCase("editexam")) {
			editExam(request, response);
			response.sendRedirect("College_Edit_Exam.jsp");
		}
		if (flag.equalsIgnoreCase("examdelete")) {
			deleteExam(request, response);
			searchCollegeExam(request, response);
			response.sendRedirect("College_Exam_List.jsp");
		}
	}

	

	private void deleteExam(HttpServletRequest request, HttpServletResponse response) {
		int examid = (Integer.parseInt(request.getParameter("id")));

		ExamVo examvo = new ExamVo();
		examvo.setId(examid);

		ExamDao examdao= new ExamDao();
		examdao.deleteExam(examvo);
		
		HttpSession h1 = request.getSession();
		h1.setAttribute("examdelete", "true");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
			examInsert(request, response);
			response.sendRedirect("College_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("update")) {
			examUpdate(request, response);
			response.sendRedirect("College_Edit_Exam.jsp");
		}
	}

	private void examInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		try {

			int collegeid = (int) session.getAttribute("collegeid");
			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			int departmentid = Integer.parseInt(request.getParameter("departmentid"));
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);

			int semid = Integer.parseInt(request.getParameter("semid"));
			SemVo semVo = new SemVo();
			semVo.setId(semid);
			
			int subjectid = Integer.parseInt(request.getParameter("subjectid"));
			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setId(subjectid);
			
			LocalDateTime localDateTime = LocalDateTime.now();
			java.sql.Date date = java.sql.Date.valueOf(localDateTime.toLocalDate());

			String[] phase = request.getParameterValues("phase[]");
			String[] time = request.getParameterValues("time[]");
			String[] marks = request.getParameterValues("marks[]");
			
			for (int i = 0; i < phase.length; i++) {
				String ph = phase[i];
				int ti = Integer.parseInt(time[i]);
				int ma = Integer.parseInt(marks[i]);

				ExamVo examVo = new ExamVo();
				examVo.setCollegeid(collegevo);
				examVo.setDate(date);
				examVo.setDepartmentid(departmentVo);
				examVo.setMarks(ma);
				examVo.setPhase(ph);
				examVo.setSemesterid(semVo);
				examVo.setTime(ti);
				examVo.setSubjectid(subjectvo);

				ExamDao examDao = new ExamDao();
				examDao.examIsert(examVo);
				session.setAttribute("examAdd", "true");
			}

		} catch (Exception e) {
			session.setAttribute("cahck", "true");
		}
	}
	
	private void searchCollegeExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = (int) session.getAttribute("collegeid");

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			ExamVo examvo = new ExamVo();
			examvo .setCollegeid(collegevo);
			
			ExamDao examdao = new ExamDao();
			ArrayList<ExamVo> examList= examdao.searchCollegeStudent(examvo );
			System.out.println(examList.size());
			session.setAttribute("collegeExam", examList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void editExam(HttpServletRequest request, HttpServletResponse response) {
		int examid = (Integer.parseInt(request.getParameter("id")));

		ExamVo examvo = new ExamVo();
		examvo.setId(examid);

		ExamDao examdao= new ExamDao();
		List examList = examdao.editExam(examvo);
		
		System.out.println(examList.size());
		HttpSession h1 = request.getSession();
		
		h1.setAttribute("examlist", examList);
	}

	protected void updateProfessorProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				int collegeid = (int) session.getAttribute("collegeid");
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);
				int semid = (Integer.parseInt(request.getParameter("semid")));
				int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");

				SemVo semvo = new SemVo();
				semvo.setId(semid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				String joiningdate = t1.toString();

				StudentVo studentvo = new StudentVo();
				studentvo.setFirstName(firstname);
				studentvo.setLastName(lastname);
				studentvo.setEmail(email);
				studentvo.setPassword(pass);
				studentvo.setCon_no(con_no);
				studentvo.setAddress(Address);
				studentvo.setGender(gender);
				studentvo.setRoll(roll);
				studentvo.setJoiningdate(joiningdate);
				studentvo.setDepartmentid(departmentvo);
				studentvo.setSemesterid(semvo);

				LoginVO loginvo = new LoginVO();
				loginvo.setStudentid(studentvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);

				LoginDAO logdao = new LoginDAO();

				StudentDao studentdao = new StudentDao();

				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					String chack = logdao.studentLoginUpdate(loginvo);
					if (chack.equals("add")) {
						studentdao.studentProfileUpdate(studentvo);
						session.setAttribute("loginResult", "true");
					}
				} else if (emailchack.isEmpty() == false) {
					logdao.loginupdate(loginvo);
					studentdao.studentProfileUpdate(studentvo);
					session.setAttribute("loginResult", "true");
				} else {
					session.setAttribute("wrong", "true");
				}

			}
		} catch (Exception e) {
			session.setAttribute("selecterorr", "true");
		}
	}
	
	private void examUpdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}
}
