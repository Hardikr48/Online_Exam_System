package VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Question")
public class QuestionVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Question")
	private String question;

	@Column(name = "OptionA")
	private String opta;

	@Column(name = "OptionB")
	private String optb;

	@Column(name = "OptionC")
	private String optc;

	@Column(name = "OptionD")
	private String optd;
	
	@Column(name = "Answer")
	private String ans;
	
	@ManyToOne
	@JoinColumn(name = "ExamPhaseID")
	private ExamPhaseVo examphaseid;
	
	@ManyToOne
	@JoinColumn(name = "DepartmentId")
	private DepartmentVo departmentid;
	
	@ManyToOne
	@JoinColumn(name = "SemesterId")
	private SemVo semesterid;

	@ManyToOne
	@JoinColumn(name = "SubjectId")
	private SubjectVo subjectid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOpta() {
		return opta;
	}

	public void setOpta(String opta) {
		this.opta = opta;
	}

	public String getOptb() {
		return optb;
	}

	public void setOptb(String optb) {
		this.optb = optb;
	}

	public String getOptc() {
		return optc;
	}

	public void setOptc(String optc) {
		this.optc = optc;
	}

	public String getOptd() {
		return optd;
	}

	public void setOptd(String optd) {
		this.optd = optd;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public DepartmentVo getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(DepartmentVo departmentid) {
		this.departmentid = departmentid;
	}

	public SemVo getSemesterid() {
		return semesterid;
	}

	public void setSemesterid(SemVo semesterid) {
		this.semesterid = semesterid;
	}

	public SubjectVo getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(SubjectVo subjectid) {
		this.subjectid = subjectid;
	}

	public ExamPhaseVo getExamphaseid() {
		return examphaseid;
	}

	public void setExamphaseid(ExamPhaseVo examphaseid) {
		this.examphaseid = examphaseid;
	}
	
	
	
}
