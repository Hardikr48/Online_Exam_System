package Com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ExamPhaseDao;
import DAO.QuestionDao;
import VO.DepartmentVo;
import VO.ExamPhaseVo;
import VO.ExamVo;
import VO.QuestionVo;
import VO.SemVo;
import VO.SubjectVo;

/**
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("------------jclshao----------------");
		HttpSession session = request.getSession();
		String flag = request.getParameter("flag");
		if (flag.equalsIgnoreCase("professoraddque")) {
			int examid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("examid", examid);
			response.sendRedirect("Professor_Question_Add.jsp");
		}
		if (flag.equalsIgnoreCase("examrules")) {
			int examid = Integer.parseInt(request.getParameter("id"));
			session.setAttribute("examid", examid);
			response.sendRedirect("Exam_Rules.jsp");
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
			professorQuestionAdd(request, response);
			response.sendRedirect("Professor_Question_Add.jsp");
		}
	}

	private void professorQuestionAdd(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("----ds-d-s-ds-d-s-d-s-d-s-d-sd-s-d-s-d-s-d-s-------");
		HttpSession session = request.getSession();
		int examid = (int) session.getAttribute("examid");

		ExamPhaseVo examPhaseVo = new ExamPhaseVo();
		examPhaseVo.setId(examid);

		ExamPhaseDao examPhaseDao = new ExamPhaseDao();

		ArrayList<ExamPhaseVo> examlist = examPhaseDao.searchExamphase(examPhaseVo);

		int departmentid = examlist.get(0).getDepartmentid().getId();
		int subjectid = examlist.get(0).getSubjectid().getId();
		int semesterid = examlist.get(0).getSemesterid().getId();

		DepartmentVo departmentVo = new DepartmentVo();
		departmentVo.setId(departmentid);

		SubjectVo subjectVo = new SubjectVo();
		subjectVo.setId(subjectid);

		SemVo semVo = new SemVo();
		semVo.setId(semesterid);

		String[] question = request.getParameterValues("question[]");
		String[] op1 = request.getParameterValues("op1[]");
		String[] op2 = request.getParameterValues("op2[]");
		String[] op3 = request.getParameterValues("op3[]");
		String[] op4 = request.getParameterValues("op4[]");
		String[] answer = request.getParameterValues("ans[]");

		for (int i = 0; i < question.length; i++) {
			String que = question[i];
			String opa = op1[i];
			String opb = op2[i];
			String opc = op3[i];
			String opd = op4[i];
			String ans = answer[i];

			if (ans.equalsIgnoreCase(opa) || ans.equalsIgnoreCase(opb) || ans.equalsIgnoreCase(opc)
					|| ans.equalsIgnoreCase(opd)) {
				QuestionVo questionVo = new QuestionVo();
				questionVo.setDepartmentid(departmentVo);
				questionVo.setSemesterid(semVo);
				questionVo.setSubjectid(subjectVo);
				questionVo.setExamphaseid(examPhaseVo);
				questionVo.setQuestion(que);
				questionVo.setOpta(opa);
				questionVo.setOptb(opb);
				questionVo.setOptc(opc);
				questionVo.setOptd(opd);
				questionVo.setAns(ans);

				QuestionDao dao = new QuestionDao();
				dao.insert(questionVo);
				session.setAttribute("question", "true");
			}
			else {
				session.setAttribute("anserror", "true");
			}
		}
	}
}
