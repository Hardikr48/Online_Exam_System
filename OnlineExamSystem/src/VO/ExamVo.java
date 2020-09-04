package VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Exam")
public class ExamVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Column(name="Date")
	private java.sql.Date date;
	
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
	
	@OneToMany( mappedBy = "examid", cascade = CascadeType.REMOVE)
	private List<ExamPhaseVo> examid ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public List<ExamPhaseVo> getExamid() {
		return examid;
	}

	public void setExamid(List<ExamPhaseVo> examid) {
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

	public CollegeVo getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(CollegeVo collegeid) {
		this.collegeid = collegeid;
	}

	public SubjectVo getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(SubjectVo subjectid) {
		this.subjectid = subjectid;
	}
	
	
}
