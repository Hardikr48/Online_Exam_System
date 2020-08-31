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
@Table(name = "SubjectProfessor")
public class SubjectProfessorVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "Collegeid")
	private CollegeVo collegeid;

	@ManyToOne
	@JoinColumn(name = "Subjectid")
	private SubjectVo subjectid;

	@ManyToOne
	@JoinColumn(name = "professorid")
	private ProfessorVo professorid ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ProfessorVo getProfessorid() {
		return professorid;
	}

	public void setProfessorid(ProfessorVo professorid) {
		this.professorid = professorid;
	}
}