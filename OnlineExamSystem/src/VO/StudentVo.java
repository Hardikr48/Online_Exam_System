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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Student", uniqueConstraints = @UniqueConstraint(name = "unique", columnNames = "Email"))

public class StudentVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Email")
	private String email;

	@Column(name = "Password")
	private String password;

	@Column(name = "Contact_NO")
	private String con_no;

	@Column(name = "Address")
	private String address;

	@Column(name = "Gender")
	private String gender;

	@Column(name = "Roll")
	private String roll;

	@Column(name = "JoiningDate")
	private String joiningdate;
	
	@ManyToOne
	@JoinColumn(name = "DepartmentId")
	private DepartmentVo departmentid;
	
	@ManyToOne
	@JoinColumn(name = "SemesterId")
	private SemVo semesterid;

	@ManyToOne
	@JoinColumn(name = "collegeId")
	private CollegeVo collegeid;
	
	@OneToMany( mappedBy = "studentid", cascade = CascadeType.REMOVE)
	@org.hibernate.annotations.OnDelete(action = OnDeleteAction.CASCADE)
	private List<LoginVO> loginid ;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getCon_no() {
		return con_no;
	}

	public void setCon_no(String con_no) {
		this.con_no = con_no;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
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

	public List<LoginVO> getLoginid() {
		return loginid;
	}

	public void setLoginid(List<LoginVO> loginid) {
	}

	public CollegeVo getCollegeid() {
		return collegeid;
	}

	public void setCollegeid(CollegeVo collegeid) {
		this.collegeid = collegeid;
	}
}
