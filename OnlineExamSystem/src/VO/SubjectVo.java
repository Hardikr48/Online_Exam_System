package VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Subject")
public class SubjectVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "SubjectName")
	private String subject;
	
	@ManyToOne
	@JoinColumn(name="Collegeid")
	private CollegeVo collegeid;
	
	@ManyToOne
	@JoinColumn(name = "Semid")
	private SemVo semid;
	
	@ManyToOne
	@JoinColumn(name = "Departmentid")
	private DepartmentVo departmentid;
	
	@OneToMany( mappedBy = "subjectid")
	private List<SubjectProfessorVo> subjectprofessorid;
	
	@OneToMany( mappedBy = "subjectid")
	private List<ExamVo> examid;
	
	@OneToMany( mappedBy = "subjectid")
	private List<ResultVo> resultid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public CollegeVo getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(CollegeVo collegeid) {
		this.collegeid = collegeid;
	}

	public SemVo getSemid() {
		return semid;
	}

	public void setSemid(SemVo semid) {
		this.semid = semid;
	}

	public DepartmentVo getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(DepartmentVo departmentid) {
		this.departmentid = departmentid;
	}

	public List<SubjectProfessorVo> getSubjectprofessorid() {
		return subjectprofessorid;
	}

	public void setSubjectprofessorid(List<SubjectProfessorVo> subjectprofessorid) {
		this.subjectprofessorid = subjectprofessorid;
	}

	public List<ExamVo> getExamid() {
		return examid;
	}

	public void setExamid(List<ExamVo> examid) {
		this.examid = examid;
	}

	public List<ResultVo> getResultid() {
		return resultid;
	}

	public void setResultid(List<ResultVo> resultid) {
		this.resultid = resultid;
	}
	
}