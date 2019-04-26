package com.lovo.uploadsystem.entity;

        import cn.afterturn.easypoi.excel.annotation.Excel;
        import com.fasterxml.jackson.annotation.JsonFormat;
   
        import org.hibernate.annotations.GenericGenerator;
        import org.springframework.web.bind.annotation.Mapping;

        import javax.persistence.*;
        import java.util.Date;

@Table(name="t_roster")
@Entity
public class RosterEntity {

    @Id
    @Column(length=32)
    @GenericGenerator(name="valueUUid", strategy="uuid")
    @GeneratedValue(generator="valueUUid")
    private String id;//表单值主键id

    @Column(length=255)
    @Excel(name = "值班人")
    private String name;


    @Column(length = 255)
    @Excel(name = "值班人电话")
    private String tel;


    @Column(length = 255)
    @Excel(name = "开始日期",format = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;

    @Column(length = 255)
    @Excel(name = "结束日期",format = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public RosterEntity(String id, String name, String tel, Date startDate, Date endDate) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public RosterEntity() {
	
	}
   
}
