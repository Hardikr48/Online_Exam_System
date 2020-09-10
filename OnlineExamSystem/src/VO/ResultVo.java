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
@Table(name = "Result")
public class ResultVo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;

	@Column(name = "Result")
	private String result;

	@Column(name = "Mark_Obtained")
	private float markobtained;

	@Column(name = "Time")
	private int time;

	@Column(name = "Correct")
	private int correct;
	
	@Column(name = "Wrong")
	private int wrong;
	
	@Column(name = "MarkReview ")
	private int markreview ;
	
	@Column(name = "Not_Attended")
	private int notattended;
	
	@Column(name = "totalmcq ")
	private int totalmcq ;
	
	@Column(name = "totalmark ")
	private int totalmark ;
	
	@Column(name = "Date")
	private String date;
	
	@Column(name = "Topic")
	private String topic;
	
	@ManyToOne
	@JoinColumn(name = "Studentid")
	private StudentVo studentid;
	
	@ManyToOne
	@JoinColumn(name = "Sujectid")
	private SubjectVo subjectid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public float getMarkobtained() {
		return markobtained;
	}

	public void setMarkobtained(float markobtained) {
		this.markobtained = markobtained;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getWrong() {
		return wrong;
	}

	public void setWrong(int wrong) {
		this.wrong = wrong;
	}

	public int getMarkreview() {
		return markreview;
	}

	public void setMarkreview(int markreview) {
		this.markreview = markreview;
	}

	public int getNotattended() {
		return notattended;
	}

	public void setNotattended(int notattended) {
		this.notattended = notattended;
	}

	public int getTotalmcq() {
		return totalmcq;
	}

	public void setTotalmcq(int totalmcq) {
		this.totalmcq = totalmcq;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public StudentVo getStudentid() {
		return studentid;
	}

	public void setStudentid(StudentVo studentid) {
		this.studentid = studentid;
	}

	public SubjectVo getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(SubjectVo subjectid) {
		this.subjectid = subjectid;
	}

	public int getTotalmark() {
		return totalmark;
	}

	public void setTotalmark(int totalmark) {
		this.totalmark = totalmark;
	}
	
	
}
