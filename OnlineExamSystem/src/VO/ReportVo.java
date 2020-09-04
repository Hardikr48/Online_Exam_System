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
@Table(name="Exam")
public class ReportVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Date")
	private String date;

	@Column(name = "Marks")
	private String marks;
	
	@ManyToOne
	@JoinColumn(name = "SubjectId")
	private SubjectVo subjectid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public SubjectVo getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(SubjectVo subjectid) {
		this.subjectid = subjectid;
	}
}
