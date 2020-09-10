package Com;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DepartmentProfessorDao;
import DAO.ExamDao;
import DAO.ExamPhaseDao;
import DAO.LoginDAO;
import DAO.QuestionDao;
import DAO.StudentDao;
import DAO.SubjectProfessorDao;
import VO.CollegeVo;
import VO.DepartmentProfessorVo;
import VO.DepartmentVo;
import VO.ExamPhaseVo;
import VO.ExamVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.QuestionVo;
import VO.SemVo;
import VO.StudentVo;
import VO.SubjectProfessorVo;
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
		if (flag.equalsIgnoreCase("hodinsert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("HOD_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("professor")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("Professor_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("stuexaminsert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("HOD_Exam_Reg.jsp");
		}
		
		if (flag.equalsIgnoreCase("searchcollegeexam")) {
			searchCollegeExam(request, response);
			response.sendRedirect("College_Exam_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemestersubjectexam")) {
			searchCollegeSubjectExam(request, response);
			response.sendRedirect("CollegeSemesterSubjectExam.jsp");
		}
		if (flag.equalsIgnoreCase("editexam")) {
			editExam(request, response);
			response.sendRedirect("College_Edit_Exam.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemesterexam")) {
			searchCollegeSemesterExam(request, response);
			response.sendRedirect("CollegeSemExamList.jsp");
		}
		if (flag.equalsIgnoreCase("examdelete")) {
			deleteExam(request, response);
			searchCollegeExam(request, response);
			response.sendRedirect("College_Exam_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegedepartmentsemestersubjectexam")) {
			searchCollegeSubjectExam(request, response);
			response.sendRedirect("CollegeDepartmentSemesterSubjectExam.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegedepartmentsubjectexam")) {
			searchCollegeSubjectExam(request, response);
			response.sendRedirect("CollegeDepartmentSubjectExam.jsp");
		}
		if (flag.equalsIgnoreCase("hodexamlist")) {
			searchDepartmentExam(request, response);
			response.sendRedirect("HOD_Exam_List.jsp");
		}
		if (flag.equalsIgnoreCase("searchprofessordexam")) {
			searchprofessorDepartmentExam(request, response);
			response.sendRedirect("Professor_List_Exam.jsp");
		}
		if (flag.equalsIgnoreCase("studentexamlist")) {
			searchstudentexam(request, response);
			response.sendRedirect("Student_List_Exam.jsp");
		}
		if (flag.equalsIgnoreCase("examrules")) {
			int id = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("examId", id);
			response.sendRedirect("Exam_Rules.jsp");
		}
		
	}

	private void examStart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		int examid = Integer.parseInt(request.getParameter("examid"));
		session.setAttribute("examid", examid);
		System.out.println("-----"+examid);

	}

	private void searchstudentexam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int studentid = Integer.parseInt(request.getParameter("id"));
		StudentVo studentVo  = new StudentVo();
		studentVo.setId(studentid);
		
		StudentDao studentDao = new StudentDao();
		ArrayList<StudentVo> studentlist= studentDao.editStudentProfile(studentVo);
		
		int departmentid = studentlist.get(0).getDepartmentid().getId();
		int semesterid = studentlist.get(0).getSemesterid().getId();
		
		DepartmentVo departmentVo = new DepartmentVo();
		departmentVo.setId(departmentid);
		
		SemVo semVo = new SemVo();
		semVo.setId(semesterid);
		
		ExamPhaseVo examPhaseVo = new ExamPhaseVo();
		examPhaseVo.setDepartmentid(departmentVo);
		examPhaseVo.setSemesterid(semVo);
		
		ExamDao examdao = new ExamDao();
		ArrayList<ExamPhaseVo> examlist=examdao.searchDepartmentSemesterExam(examPhaseVo);
		
		System.out.println(examlist.size());
		session.setAttribute("examlist", examlist);
		
		
	}

	private void searchprofessorDepartmentExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int professorid = Integer.parseInt(request.getParameter("id"));
		System.out.println("-----------------------------------------------------------");
		List<ExamPhaseVo> ExamList = new ArrayList<ExamPhaseVo>();
		System.out.println(professorid);
		ProfessorVo professorVo = new ProfessorVo();
		professorVo.setId(professorid);
		
		DepartmentProfessorVo departmentProfessorVo = new DepartmentProfessorVo();
		departmentProfessorVo.setProfessorid(professorVo);
		
		DepartmentProfessorDao departmentProfessorDao = new  DepartmentProfessorDao();
		ArrayList<DepartmentProfessorVo> departmentlist=  departmentProfessorDao.searchCollegeDepartmentProfessor(departmentProfessorVo);
		System.out.println(departmentlist.size());
		
		for(DepartmentProfessorVo department : departmentlist) {
			int depid = department.getId();
			System.out.println(depid);
			departmentProfessorVo.setId(depid);
			SubjectProfessorVo subjectProfessorVo = new SubjectProfessorVo();
			subjectProfessorVo.setDepartmentprofessorid(departmentProfessorVo);
			
			SubjectProfessorDao subjectProfessorDao = new SubjectProfessorDao();
			ArrayList<SubjectProfessorVo> subjectlist = subjectProfessorDao.searchDepartmentSubjectProfessor(subjectProfessorVo);
			System.out.println(subjectlist.size());
			
			for(SubjectProfessorVo subject : subjectlist) {
				
				int subjectid = subject.getSubjectid().getId();
				System.out.println(subjectid);
				
				SubjectVo subjectVo = new SubjectVo();
				subjectVo.setId(subjectid);
				
				ExamPhaseVo examPhaseVo = new ExamPhaseVo();
				examPhaseVo.setSubjectid(subjectVo);
				
				ExamDao examdao = new ExamDao();
				
				ExamList.addAll(examdao.searchCollegeSubjectExam(examPhaseVo));
				
				System.out.println("-----------------------------------------------------------");
				
			}
		}
		
		System.out.println(ExamList.size());
		session.setAttribute("examlist", ExamList);
	}

	private void searchDepartmentExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = Integer.parseInt(request.getParameter("id"));
			
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);
			
			ExamPhaseVo examvo = new ExamPhaseVo();
			examvo.setDepartmentid(departmentVo);
			
			ExamDao examdao = new ExamDao();
			ArrayList<ExamVo> examList= examdao.searchDepartmentExam(examvo );
			System.out.println(examList.size());
			session.setAttribute("departmentexamlist", examList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchCollegeSemesterExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int subjectid = Integer.parseInt(request.getParameter("id"));
			
			SemVo semVo= new SemVo();
			semVo.setId(subjectid);
			
			ExamPhaseVo examvo = new ExamPhaseVo();
			examvo.setSemesterid(semVo);
			
			ExamDao examdao = new ExamDao();
			ArrayList<ExamVo> examList= examdao.searchCollegeSemesterExam(examvo );
			System.out.println(examList.size());
			session.setAttribute("collegesubjectexamlist", examList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void searchCollegeSubjectExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int subjectid = Integer.parseInt(request.getParameter("id"));
			
			SubjectVo subjectVo = new SubjectVo();
			subjectVo.setId(subjectid);
			
			ExamPhaseVo examvo = new ExamPhaseVo();
			examvo.setSubjectid(subjectVo);
			
			ExamDao examdao = new ExamDao();
			ArrayList<ExamPhaseVo> examList= examdao.searchCollegeSubjectExam(examvo );
			System.out.println(examList.size());
			session.setAttribute("collegesubjectexamlist", examList);

		} catch (Exception e) {
			e.printStackTrace();
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
		if (flag.equalsIgnoreCase("hodinsert")) {
			examInsert(request, response);
			response.sendRedirect("HOD_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("professorinsert")) {
			examInsert(request, response);
			response.sendRedirect("Professor_Exam_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("update")) {
			examUpdate(request, response);
			response.sendRedirect("College_Edit_Exam.jsp");
		}
		if (flag.equalsIgnoreCase("examstart")) {
			examStart(request, response);
			response.sendRedirect("Exam_Start.jsp");
		}
	}

	private void examUpdate(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
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
			String[] examtopic = request.getParameterValues("examtopic[]");
			ExamVo examVo = new ExamVo();
			examVo.setCollegeid(collegevo);
			examVo.setDate(date);
			examVo.setDepartmentid(departmentVo);
			examVo.setSemesterid(semVo);
			examVo.setSubjectid(subjectvo);

			ExamDao examDao = new ExamDao();
			examDao.examIsert(examVo);
			for (int i = 0; i < phase.length; i++) {
				String ph = phase[i];
				int ti = Integer.parseInt(time[i]);
				int ma = Integer.parseInt(marks[i]);
				String topic= examtopic[i];
				ExamPhaseVo examPhaseVo = new ExamPhaseVo();
				examPhaseVo.setMarks(ma);
				examPhaseVo.setPhase(ph);
				examPhaseVo.setTime(ti);
				examPhaseVo.setExamid(examVo);
				examPhaseVo.setDepartmentid(departmentVo);
				examPhaseVo.setSemesterid(semVo);
				examPhaseVo.setSubjectid(subjectvo);
				examPhaseVo.setExamtopic(topic);
				
				ExamPhaseDao  examPhaseDao = new ExamPhaseDao();
				examPhaseDao.insertExamPhase(examPhaseVo);
				

				session.setAttribute("examAdd", "true");
			}
		} catch (Exception e) {
			session.setAttribute("cahck", "true");
		}
	}
	
	private void searchCollegeExam(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int id  = Integer.parseInt(request.getParameter("id"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(id);

			ExamPhaseVo examvo = new ExamPhaseVo();
			examvo.setCollegeid(collegevo);
			
			ExamDao examdao = new ExamDao();
			ArrayList<ExamPhaseVo> examList= examdao.searchCollegeStudent(examvo );
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
}
