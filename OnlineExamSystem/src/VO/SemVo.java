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

import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Sam")
public class SemVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "SemName")
	private int semname;

	@ManyToOne
	@JoinColumn(name = "Collegeid")
	private CollegeVo college;
	
	@ManyToOne
	@JoinColumn(name = "DepartmentId")
	private DepartmentVo departmentid;
	
	@OneToMany(mappedBy = "semid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<SubjectVo> subjectid;
	
	@OneToMany( mappedBy = "semid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<SemProfessorVo> semprofessorid;
	
	@OneToMany(mappedBy = "semesterid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<StudentVo> studentid;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSemname() {
		return semname;
	}

	public void setSemname(int semname) {
		this.semname = semname;
	}

	public CollegeVo getCollege() {
		return college;
	}

	public void setCollege(CollegeVo college) {
		this.college = college;
	}

	public DepartmentVo getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(DepartmentVo departmentid) {
		this.departmentid = departmentid;
	}

	public List<SubjectVo> getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(List<SubjectVo> subjectid) {
		this.subjectid = subjectid;
	}

	public List<SemProfessorVo> getSemprofessorid() {
		return semprofessorid;
	}

	public void setSemprofessorid(List<SemProfessorVo> semprofessorid) {
		this.semprofessorid = semprofessorid;
	}

	public List<StudentVo> getStudentid() {
		return studentid;
	}

	public void setStudentid(List<StudentVo> studentid) {
		this.studentid = studentid;
	}
}
