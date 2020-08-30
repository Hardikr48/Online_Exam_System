package VO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Photos")
public class PhotoUploadVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	
	@Lob
    @Column(name="ProfessorImage", nullable=false)
    private byte[] image;
	
	@ManyToOne
	@JoinColumn(name = "ProfessorId")
	private ProfessorVo professorid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public ProfessorVo getProfessorid() {
		return professorid;
	}

	public void setProfessorid(ProfessorVo professorid) {
		this.professorid = professorid;
	}
	
	
}
