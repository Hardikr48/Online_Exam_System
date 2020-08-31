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
@Table(name = "SemProfessor")
public class SemProfessorVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "Collegeid")
	private CollegeVo collegeid;

	@ManyToOne
	@JoinColumn(name = "Semid")
	private SemVo semid;
	
	@ManyToOne
	@JoinColumn(name = "Professorid")
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

	public SemVo getSemid() {
		return semid;
	}

	public void setSemid(SemVo semid) {
		this.semid = semid;
	}

	public ProfessorVo getProfessorid() {
		return professorid;
	}

	public void setProfessorid(ProfessorVo professorid) {
		this.professorid = professorid;
	}


}
