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
import DAO.ExamPhaseDao;
import DAO.QuestionDao;
import DAO.SemDao;
import DAO.SubjectDao;
import VO.CollegeVo;
import VO.Common;
import VO.DepartmentVo;
import VO.ExamPhaseVo;
import VO.ExamVo;
import VO.QuestionVo;
import VO.Questionlist;
import VO.SemVo;
import VO.SubjectVo;

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
		
		int departmentid = Integer.parseInt(request.getParameter("q"));

		DepartmentVo departmentvo = new DepartmentVo();
		departmentvo.setId(departmentid);

		SemVo semvo = new SemVo();
		semvo.setDepartmentid(departmentvo);

		SemDao semdao = new SemDao();
		ArrayList<SemVo> semlist = semdao.searchDepartment(semvo);
		System.out.println(semlist.size());
		List<Common> list = new ArrayList<Common>();

		for (SemVo sem : semlist) {
			int id = sem.getId();
			int semester = sem.getSemname();
			Common common = new Common();
			common.setId(id);
			common.setSemester(semester);
			list.add(common);
		}

		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("....................................");
		String flag = request.getParameter("flag");
		if(flag.equalsIgnoreCase("exam")) {
			examStart(request,response);
		}
		System.out.println(flag);
		if (flag.equalsIgnoreCase("semester")) {
			System.out.println("0320.32......................");
			int departmentid = Integer.parseInt(request.getParameter("department"));
			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setId(departmentid);

			SemVo semVo = new SemVo();
			semVo.setDepartmentid(departmentVo);

			SemDao semDao = new SemDao();
			ArrayList<SemVo> semlist = semDao.searchDepartment(semVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();
			for (SemVo sem : semlist) {
				int id = sem.getId();
				int semester = sem.getSemname();
				Common common = new Common();
				common.setId(id);
				common.setSemester(semester);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();

		} else if(flag.equalsIgnoreCase("deaprtment")){
			
			int departmentid = 1;

			CollegeVo collegeVo = new CollegeVo();
			collegeVo.setId(departmentid);

			DepartmentVo departmentVo = new DepartmentVo();
			departmentVo.setDepartmentcollegeid(collegeVo);

			DepartmentDao departmentDao = new DepartmentDao();
			ArrayList<DepartmentVo> semlist = departmentDao.searchCollegeDepartment(departmentVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();

			for (DepartmentVo sem : semlist) {
				int id = sem.getId();
				String semester = sem.getDepartment();
				Common common = new Common();
				common.setId(id);
				common.setDepartment(semester);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();
			
		}else if(flag.equalsIgnoreCase("subject")){
			int semsterid = Integer.parseInt(request.getParameter("semesterid"));

			SemVo semVo = new SemVo();
			semVo.setId(semsterid);

			SubjectVo subjectVo = new SubjectVo();
			subjectVo.setSemid(semVo);
			
			SubjectDao subjectDao = new SubjectDao();
			ArrayList<SubjectVo> semlist = subjectDao.searchSem(subjectVo);
			System.out.println(semlist.size());
			List<Common> list = new ArrayList<Common>();

			for (SubjectVo subjectlist : semlist) {
				int id = subjectlist.getId();
				String subject = subjectlist.getSubject();
				Common common = new Common();
				common.setId(id);
				common.setSubject(subject);
				list.add(common);
			}

			Gson gson = new Gson();
			PrintWriter out = response.getWriter();
			out.print(gson.toJson(list));
			out.flush();
			out.close();
		}
	}

	private void examStart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		System.out.println("-------------------------------------------------");
		List<QuestionVo> QueList = new ArrayList<QuestionVo>();
		int examid = (int)session.getAttribute("examid");
		
		ExamVo examVo = new ExamVo();
		examVo.setId(examid);
		
		
		System.out.println(examid);
		ExamPhaseVo examPhaseVo =  new ExamPhaseVo();
		examPhaseVo.setExamid(examVo);
		
		ExamPhaseDao examPhaseDao = new ExamPhaseDao();
		ArrayList<ExamPhaseVo> questionlist= examPhaseDao.getPhaseList(examPhaseVo);
		System.out.println(questionlist.size());
		
		for(ExamPhaseVo list : questionlist) {
			int id = list.getId();
			System.out.println("---------------"+id);
			examPhaseVo.setId(id);
			
			QuestionVo questionVo = new QuestionVo();
			questionVo.setExamphaseid(examPhaseVo);
			
			QuestionDao questionDao = new QuestionDao();
			ArrayList<QuestionVo> questionlist2= questionDao.getquestion(questionVo);
			System.out.println("-8-8-8-8-8-----"+questionlist2);
			QueList.addAll(questionlist2);
		}
		System.out.println(QueList.size());
		session.setAttribute("quelist", QueList);
		
		List<Questionlist> list = new ArrayList<Questionlist>();
		
		for (QuestionVo quelist: QueList) {
			int id = quelist.getId();
			String id1 = String.valueOf(id);
			String que = quelist.getQuestion();
			String op1 = quelist.getOpta();
			String op2 = quelist.getOptb();
			String op3 = quelist.getOptc();
			String op4 = quelist.getOptd();
			String ans = quelist.getAns();
			System.out.println("online exam system=   "+id+" "+que+" "+op1+" "+op2+" "+op3+" "+op4+" "+ans );
			Questionlist questionlist1 = new Questionlist();
			questionlist1.setQue(que);
			questionlist1.setId(id1);
			questionlist1.setOp1(op1);
			questionlist1.setOp2(op2);
			questionlist1.setOp3(op3);
			questionlist1.setOp4(op4);
			questionlist1.setAns(ans);
			list.add(questionlist1);
		}
		System.out.println(list.size());
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		
	}
	
	private void getTime(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		System.out.println("-------------------------------------------------");
		List<QuestionVo> QueList = new ArrayList<QuestionVo>();
		int examid = (int)session.getAttribute("examid");
		
		ExamVo examVo = new ExamVo();
		examVo.setId(examid);
		
		
		System.out.println(examid);
		ExamPhaseVo examPhaseVo =  new ExamPhaseVo();
		examPhaseVo.setExamid(examVo);
		
		ExamPhaseDao examPhaseDao = new ExamPhaseDao();
		ArrayList<ExamPhaseVo> questionlist= examPhaseDao.getPhaseList(examPhaseVo);
		System.out.println(questionlist.size());
		System.out.println(questionlist.get(0).getTime());
		
		for(ExamPhaseVo list : questionlist) {
			int id = list.getId();
			System.out.println("---------------"+id);
			examPhaseVo.setId(id);
			
			QuestionVo questionVo = new QuestionVo();
			questionVo.setExamphaseid(examPhaseVo);
			
			QuestionDao questionDao = new QuestionDao();
			ArrayList<QuestionVo> questionlist2= questionDao.getquestion(questionVo);
			System.out.println("-8-8-8-8-8-----"+questionlist2);
			QueList.addAll(questionlist2);
		}
		System.out.println(QueList.size());
		session.setAttribute("quelist", QueList);
		
		List<Questionlist> list = new ArrayList<Questionlist>();
		
		for (QuestionVo quelist: QueList) {
			int id = quelist.getId();
			String id1 = String.valueOf(id);
			String que = quelist.getQuestion();
			String op1 = quelist.getOpta();
			String op2 = quelist.getOptb();
			String op3 = quelist.getOptc();
			String op4 = quelist.getOptd();
			String ans = quelist.getAns();
			System.out.println("online exam system=   "+id+" "+que+" "+op1+" "+op2+" "+op3+" "+op4+" "+ans );
			Questionlist questionlist1 = new Questionlist();
			questionlist1.setQue(que);
			questionlist1.setId(id1);
			questionlist1.setOp1(op1);
			questionlist1.setOp2(op2);
			questionlist1.setOp3(op3);
			questionlist1.setOp4(op4);
			questionlist1.setAns(ans);
			list.add(questionlist1);
		}
		System.out.println(list.size());
		Gson gson = new Gson();
		PrintWriter out = response.getWriter();
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		
	}
}
