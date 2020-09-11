package Com;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import DAO.DepartmentDao;
import DAO.DepartmentProfessorDao;
import DAO.LoginDAO;
import DAO.ProfessorDao;
import DAO.SemProfessorDao;
import DAO.StudentDao;
import DAO.SubjectProfessorDao;
import VO.CollegeVo;
import VO.DepartmentProfessorVo;
import VO.DepartmentVo;
import VO.LoginVO;
import VO.ProfessorVo;
import VO.QuestionVo;
import VO.SemProfessorVo;
import VO.SemVo;
import VO.StudentVo;
import VO.SubjectProfessorVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Professor
 */
@WebServlet("/Student")
@MultipartConfig
public class Student extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Student() {
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
			System.out.println(collegeid);
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Student_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("hodinsert")) {
			int collegeid = Integer.parseInt(request.getParameter("id"));
			System.out.println(collegeid);
			session.setAttribute("collegeid", collegeid);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("HOD_Student_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("editprofile")) {
			int studentid = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("studentid", studentid);
			editStudentProfile(request, response);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("College_Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("editprofilestudent")) {
			int studentid = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("studentid", studentid);
			editStudentProfile(request, response);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("professoreditstudent")) {
			int studentid = (Integer.parseInt(request.getParameter("id")));
			session.setAttribute("studentid", studentid);
			editStudentProfile(request, response);
			Department department = new Department();
			department.viewcollegeDepartmentList(request, response);
			response.sendRedirect("Professor_Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("searchcollegestudent")) {
			searchCollegeStudent(request, response);
			response.sendRedirect("College_Student_List.jsp");
		}
		if (flag.equalsIgnoreCase("viewdepartmentstudentlist")) {
			searchDepartmentStudent(request, response);
			response.sendRedirect("CollegeDepartmentStudentList.jsp");
		}
		if (flag.equalsIgnoreCase("professorstudent")) {
			searchProfessorStudent(request, response);
			response.sendRedirect("Professor_Student_List.jsp");
		}
		if (flag.equalsIgnoreCase("hodstudentlist")) {
			searchDepartmentStudent(request, response);
			response.sendRedirect("HOD_Student_List.jsp");
		}
		
		if (flag.equalsIgnoreCase("searchsubjectprofessor")) {
			searchSubjectProfessor(request, response);
			response.sendRedirect("College_professor_Edit_Profile.jsp");
		}
		if (flag.equalsIgnoreCase("viewcollegesemesterstudent")) {
			searchSemesterStudent(request, response);
			response.sendRedirect("CollegeSemStudentList.jsp");
		}
		
		if (flag.equalsIgnoreCase("")) {
			searchSemesterStudent(request, response);
			response.sendRedirect("CollegeSemStudentList.jsp");
		}
	}

	private void searchProfessorStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int professorid = Integer.parseInt(request.getParameter("id"));
		
		ProfessorVo professorVo = new ProfessorVo();
		professorVo.setId(professorid);
		
		SemProfessorVo semProfessorVo = new SemProfessorVo();
		semProfessorVo.setProfessorid(professorVo);
		
		
		SemProfessorDao semProfessorDao = new SemProfessorDao();
		
		ArrayList<SemProfessorVo> semseterlist= semProfessorDao.searchCollegeProfessorsemester(semProfessorVo);
		List<StudentVo> QueList = new ArrayList<StudentVo>();
		for(SemProfessorVo sem : semseterlist) {
			int semid = sem.getSemid().getId();
			SemVo semVo = new SemVo();
			semVo.setId(semid);
			
			StudentVo studentVo = new StudentVo();
			studentVo.setSemesterid(semVo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchSemesterStudent(studentVo);
			QueList.addAll(Studentlist);
		}
			session.setAttribute("professorstudentlist", QueList);
		
		
	}

	private void searchSemesterStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int semid = Integer.parseInt(request.getParameter("id"));

			SemVo semVo = new SemVo();
			semVo.setId(semid);
			
			StudentVo studentVo = new StudentVo();
			studentVo.setSemesterid(semVo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchSemesterStudent(studentVo);
			
			session.setAttribute("SemesterStudentlist", Studentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void searchDepartmentStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int departmentid = Integer.parseInt(request.getParameter("id"));

			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);
			
			StudentVo studentVo = new StudentVo();
			studentVo.setDepartmentid(departmentVo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchDepartmentStudent(studentVo);
			
			session.setAttribute("collegeStudentlist", Studentlist);

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
		HttpSession session = request.getSession();
		
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("insert")) {
			studentInsert(request, response);
			response.sendRedirect("College_Student_Reg.jsp");
		}
		if (flag.equalsIgnoreCase("hodinsert")) {
			studentInsert(request, response);
			response.sendRedirect("HOD_Student_Reg.jsp");
		}
		
		if (flag.equalsIgnoreCase("update")) {
			updateStudentProfile(request, response);
			editStudentProfile(request, response);
			response.sendRedirect("College_Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("updatestudent")) {
			updateStudentProfile(request, response);
			editStudentProfile(request, response);
			response.sendRedirect("Student_Edit.jsp");
		}
		if (flag.equalsIgnoreCase("updateprofessorstudent")) {
			updateStudentProfile(request, response);
			editStudentProfile(request, response);
			response.sendRedirect("Professor_Student_Edit.jsp");
		}
	}

	private void studentInsert(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		try {
			System.out.println("............................................");
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				System.out.println("............................................");
				int collegeid = (int) session.getAttribute("collegeid");
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);

				int departmentid = Integer.parseInt(request.getParameter("departmentid"));
				int semid = Integer.parseInt(request.getParameter("semid"));
				
				DepartmentVo departmentVo = new DepartmentVo();
				departmentVo.setId(departmentid);
				DepartmentDao departmentDao = new DepartmentDao();
				ArrayList<DepartmentVo> getdepartmentcode =departmentDao.getdepartmentcode(departmentVo);
				String departmentcode = String.valueOf(getdepartmentcode.get(0).getDepartmentcode());
				SemVo semVo = new SemVo();
				semVo.setId(semid);
				
				System.out.println(collegeid+" "+semid+" "+departmentid);
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String pass = request.getParameter("pass");
				String roll = null;
				
				Timestamp t1 = new Timestamp(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss aa");
				String joiningdate = sdf.format(t1);
				SimpleDateFormat sdf2 = new SimpleDateFormat("yy");
				String yearoflastdigital = sdf2.format(t1);
				
				
				StudentDao studentdao = new StudentDao();
				ArrayList<StudentVo> getlastrecord = studentdao.getlastrecord();
				String lastrecode =getlastrecord.get(0).getRoll();
				int n = Integer.valueOf(lastrecode);
				
				int d =n%1000;
				System.out.println(d);
				d++;
				int a = 0;
				int b = 0;
				int c = d;
				System.out.println(c);
				String s1 = String.valueOf(a);
				String s2 = String.valueOf(b);
				String s3 = String.valueOf(c);

				if (c >= 1 && c < 10) {
					roll = departmentcode.concat(yearoflastdigital).concat(s1).concat(s2).concat(s3);
					System.out.println(roll);

				} else if (c >= 10 && c < 100) {
					roll = departmentcode.concat(yearoflastdigital).concat(s1).concat(s3);
					System.out.println(roll);
				}

				else {
					roll = departmentcode.concat(yearoflastdigital).concat(s3);
					System.out.println(roll);

				}

				
				Part filepart = request.getPart("myfile");
				InputStream inputstream = null;
				if (filepart != null) {
					inputstream = filepart.getInputStream();
				}
				byte[] bytes = IOUtils.toByteArray(inputstream);
				
				

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
				studentvo.setDepartmentid(departmentVo);
				studentvo.setSemesterid(semVo);
				studentvo.setCollegeid(collegevo);
				studentvo.setImage(bytes);
				
				LoginVO loginvo = new LoginVO();
				loginvo.setStudentid(studentvo);
				loginvo.setEmail(email);
				loginvo.setPassword(pass);
				loginvo.setLastlogin(joiningdate);
				loginvo.setRoll("Student");

				LoginDAO logdao = new LoginDAO();
				ArrayList<LoginVO> emailchack = logdao.emailverify(loginvo);
				if (emailchack.isEmpty() == true) {
					studentdao.studentInsert(studentvo, loginvo);
					session.setAttribute("rollno", roll);
					session.setAttribute("studentadd", "true");
				} else {
					session.setAttribute("emailidadd", "true");
				}
			} else {
				session.setAttribute("emailwrong", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("cahck", "true");
		}
	}

	protected void editStudentProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int studentid = (int) session.getAttribute("studentid");

		StudentVo studentvo = new StudentVo();
		studentvo.setId(studentid);

		StudentDao studentdao = new StudentDao();

		List studentlist = studentdao.editStudentProfile(studentvo);
		System.out.println(studentlist.size());
		HttpSession h1 = request.getSession();
		h1.setAttribute("studentlist", studentlist);
	}

	protected void updateStudentProfile(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			String email = request.getParameter("email");
			if (EmailValidation.isValid(email)) {
				int collegeid = Integer.parseInt(request.getParameter("collegeid"));
				CollegeVo collegevo = new CollegeVo();
				collegevo.setId(collegeid);
				int semid = (Integer.parseInt(request.getParameter("semid")));
				int departmentid = (Integer.parseInt(request.getParameter("departmentid")));
				String firstname = request.getParameter("firstName");
				String lastname = request.getParameter("lastName");
				String con_no = request.getParameter("Con_no");
				String Address = request.getParameter("address");
				String gender = request.getParameter("gender");
				String joiningdate = request.getParameter("joiningdate");
				String pass = request.getParameter("pass");
				String roll = request.getParameter("roll");
				int studentid = Integer.parseInt(request.getParameter("id"));

				SemVo semvo = new SemVo();
				semvo.setId(semid);

				DepartmentVo departmentvo = new DepartmentVo();
				departmentvo.setId(departmentid);

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
				studentvo.setId(studentid);

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

	private void searchCollegeStudent(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("id"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			StudentVo studentVo = new StudentVo();
			studentVo.setCollegeid(collegevo);
			
			StudentDao studentdao = new StudentDao();
			ArrayList<StudentVo> Studentlist= studentdao.searchCollegeStudent(studentVo);
			
			session.setAttribute("collegeStudentlist", Studentlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchSubjectProfessor(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			int collegeid = Integer.parseInt(request.getParameter("collegeid"));
			int subjectid = Integer.parseInt(request.getParameter("subjectid"));

			CollegeVo collegevo = new CollegeVo();
			collegevo.setId(collegeid);

			SubjectVo subjectvo = new SubjectVo();
			subjectvo.setId(subjectid);

			ProfessorVo professorvo = new ProfessorVo();
			professorvo.setCollegeid(collegevo);

			ProfessorDao professordao = new ProfessorDao();
			ArrayList<ProfessorVo> professorlist = professordao.searchCollegeSubjectProfessor(professorvo);
			System.out.println(professorlist.size());
			session.setAttribute("collegeDepartmentProfessorlist", professorlist);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
