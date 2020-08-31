package VO;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "College", uniqueConstraints = @UniqueConstraint(name = "unique", columnNames = "Email"))
public class CollegeVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "CollegeName")
	private String collegename;
	
	@Lob
	@Column(name = "CollegeImage")
	private byte[] image;

	@Column(name = "Address")
	private String address;

	@Column(name = "Password")
	private String password;

	@Column(name = "Email")
	private String email;
	
	@Column(name = "JoiningDate")
	private String joiningdate;
	
	@OneToMany(mappedBy = "college")
	private List<SemVo> samid;
	
	@OneToMany( mappedBy = "collegeid")
	private List<LoginVO> loginid ;
	
	@OneToMany( mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<StudentVo> studentid ;
   
	@OneToMany(mappedBy = "departmentcollegeid", cascade = CascadeType.REMOVE)
	private List<DepartmentVo> departmentid;
	
	@OneToMany(mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<SubjectVo> subjectid;
	
	@OneToMany(mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<ProfessorVo> professortid;
	
	@OneToMany(mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<DepartmentProfessorVo> departmentprofessor;
	
	@OneToMany(mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<SemProfessorVo> semprofessor;
	
	@OneToMany(mappedBy = "collegeid", cascade = CascadeType.REMOVE)
	private List<SubjectProfessorVo> subjectprofessor;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<SemVo> getSamid() {
		return samid;
	}

	public void setSamid(List<SemVo> samid) {
		this.samid = samid;
	}

	public String getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(String joiningdate) {
		this.joiningdate = joiningdate;
	}

	public List<LoginVO> getLoginid() {
		return loginid;
	}

	public void setLoginid(List<LoginVO> loginid) {
		this.loginid = loginid;
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

	public List<ProfessorVo> getProfessortid() {
		return professortid;
	}

	public void setProfessortid(List<ProfessorVo> professortid) {
		this.professortid = professortid;
	}

	public List<DepartmentProfessorVo> getDepartmentprofessor() {
		return departmentprofessor;
	}

	public void setDepartmentprofessor(List<DepartmentProfessorVo> departmentprofessor) {
		this.departmentprofessor = departmentprofessor;
	}

	public List<SemProfessorVo> getSemprofessor() {
		return semprofessor;
	}

	public void setSemprofessor(List<SemProfessorVo> semprofessor) {
		this.semprofessor = semprofessor;
	}

	public List<SubjectProfessorVo> getSubjectprofessor() {
		return subjectprofessor;
	}

	public void setSubjectprofessor(List<SubjectProfessorVo> subjectprofessor) {
		this.subjectprofessor = subjectprofessor;
	}

	public List<StudentVo> getStudentid() {
		return studentid;
	}

	public void setStudentid(List<StudentVo> studentid) {
		this.studentid = studentid;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
}
