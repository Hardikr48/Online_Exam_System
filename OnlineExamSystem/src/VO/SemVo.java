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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Sam", uniqueConstraints = @UniqueConstraint(name = "unique", columnNames = "SemName"))
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "semid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<DepartmentVo> departmentid;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "semid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<SubjectVo> subjectid;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "semid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProfessorVo> professorid;
	
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

	public List<DepartmentVo> getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(List<DepartmentVo> departmentid) {
		this.departmentid = departmentid;
	}

	public List<SubjectVo> getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(List<SubjectVo> subjectid) {
		this.subjectid = subjectid;
	}

	public List<ProfessorVo> getProfessorid() {
		return professorid;
	}

	public void setProfessorid(List<ProfessorVo> professorid) {
		this.professorid = professorid;
	}
	
}
