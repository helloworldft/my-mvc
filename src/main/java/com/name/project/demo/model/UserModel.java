package com.name.project.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import com.name.project.common.model.BaseModel;

/**   
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:58:02 
 *
 */
@Entity
@Table(name = "T_MY_USER", uniqueConstraints = {@UniqueConstraint(columnNames={"USER_NAME"})})
public class UserModel extends BaseModel {
	
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	@Id
	private Integer id;
	@Column(name = "user_name", length = 20)
	private String userName;
	@Column(name = "password", length = 50)
	private String password;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "phone", length = 20)
	private String phone;
	@Column(name = "sex", length = 1)
	private Integer sex;
	@Column(name = "create_user", length = 50)
	private String createUser;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "last_update_user", length = 50)
	private String lastUpdateUser;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_time")
	private Date lastUpdateTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	
}
