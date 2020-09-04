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
@Table(name="ExamPhase")
public class ExamPhaseVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name = "Phase")
	private String phase;
	
	@Column(name = "ExamTopic")
	private String examtopic;
	
	@Column(name = "Time")
	private int time;
	
	@Column(name = "Marks")
	private int marks;
	
	@ManyToOne
	@JoinColumn(name = "ExamId")
	private ExamVo examid;
	
	@ManyToOne
	@JoinColumn(name = "DepartmentId")
	private DepartmentVo departmentid;
	
	@ManyToOne
	@JoinColumn(name = "SemesterId")
	private SemVo semesterid;

	@ManyToOne
	@JoinColumn(name = "SubjectId")
	private SubjectVo subjectid;
	
	@ManyToOne
	@JoinColumn(name = "collegeId")
	private CollegeVo collegeid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public ExamVo getExamid() {
		return examid;
	}

	public void setExamid(ExamVo examid) {
		this.examid = examid;
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

	public String getExamtopic() {
		return examtopic;
	}

	public void setExamtopic(String examtopic) {
		this.examtopic = examtopic;
	}

	public CollegeVo getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(CollegeVo collegeid) {
		this.collegeid = collegeid;
	}
	
	
}
