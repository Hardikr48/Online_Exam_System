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
@Table(name = "Department")
public class DepartmentVo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "DepartmentName")
	private String department;

	@ManyToOne
	@JoinColumn(name = "DepartmentSemid")
	private SemVo semid;
	
	@ManyToOne
	@JoinColumn(name="DepartmentCollegeid")
	private CollegeVo departmentcollegeid;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<SubjectVo> departmentid;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departmentid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<ProfessorVo> professorid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public SemVo getSemid() {
		return semid;
	}

	public void setSemid(SemVo semid) {
		this.semid = semid;
	}

	public CollegeVo getDepartmentcollegeid() {
		return departmentcollegeid;
	}

	public void setDepartmentcollegeid(CollegeVo departmentcollegeid) {
		this.departmentcollegeid = departmentcollegeid;
	}

	public List<SubjectVo> getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(List<SubjectVo> departmentid) {
		this.departmentid = departmentid;
	}

	public List<ProfessorVo> getProfessorid() {
		return professorid;
	}

	public void setProfessorid(List<ProfessorVo> professorid) {
		this.professorid = professorid;
	}
	
}
