package VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Login",uniqueConstraints = @UniqueConstraint(name="unique",columnNames = "Email"))
public class LoginVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Password")
	private String password;

	@Column(name="Roll")
	private String roll;
	
	@Column(name="LastLogin")
	private String lastlogin;
	
	@ManyToOne()
	@JoinColumn(name="LoginCollegeid")
	private CollegeVo collegeid;
	
	@ManyToOne()
	@JoinColumn(name="LoginProfessorid")
	private ProfessorVo professorid;

	@ManyToOne
	@JoinColumn(name="LoginStudentId")
	private StudentVo studentid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}

	public CollegeVo getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(CollegeVo collegeid) {
		this.collegeid = collegeid;
	}

	public ProfessorVo getProfessorid() {
		return professorid;
	}

	public void setProfessorid(ProfessorVo professorid) {
		this.professorid = professorid;
	}

	public StudentVo getStudentid() {
		return studentid;
	}

	public void setStudentid(StudentVo studentid) {
		this.studentid = studentid;
	}

}
