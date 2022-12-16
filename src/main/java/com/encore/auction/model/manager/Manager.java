package com.encore.auction.model.manager;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.hibernate.annotations.Where;

import com.encore.auction.controller.manager.requests.ManagerUpdateRequest;
import com.encore.auction.model.BaseEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "state = false")
public class Manager extends BaseEntity {

	@Id
	@Column(length = 20)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 30)
	private String court;

	@Column(nullable = false, length = 50)
	private String department;

	@Column(nullable = false)
	private String salt;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false, length = 13)
	private String phoneNumber;

	@Column(nullable = false, length = 123)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, columnDefinition = "varchar(11) default 'DISAPPROVAL'")
	private ManagerRole managerRole;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public Manager(String id, String password, String court, String department, String salt, String name, Integer age,
		String phoneNumber, String email, ManagerRole managerRole, Boolean state) {
		this.id = id;
		this.password = password;
		this.court = court;
		this.department = department;
		this.salt = salt;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.managerRole = managerRole;
		this.state = state;
	}

	@Override
	public String toString() {
		return "Manager{" +
			"id='" + id + '\'' +
			", password='" + password + '\'' +
			", court='" + court + '\'' +
			", department='" + department + '\'' +
			", salt='" + salt + '\'' +
			", name='" + name + '\'' +
			", age=" + age +
			", phoneNumber='" + phoneNumber + '\'' +
			", email='" + email + '\'' +
			", managerRole=" + managerRole +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Manager manager = (Manager)o;
		return Objects.equals(id, manager.id) && Objects.equals(password, manager.password)
			&& Objects.equals(court, manager.court) && Objects.equals(department, manager.department)
			&& Objects.equals(salt, manager.salt) && Objects.equals(name, manager.name)
			&& Objects.equals(age, manager.age) && Objects.equals(phoneNumber, manager.phoneNumber)
			&& Objects.equals(email, manager.email) && managerRole == manager.managerRole
			&& Objects.equals(state, manager.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, court, department, salt, name, age, phoneNumber, email, managerRole, state);
	}

	public void updateManager(ManagerUpdateRequest managerUpdateRequest, String encryptedPassword, String newSalt) {
		this.password = encryptedPassword;
		this.salt = newSalt;
		this.court = managerUpdateRequest.getCourt();
		this.department = managerUpdateRequest.getDepartment();
		this.age = managerUpdateRequest.getAge();
		this.email = managerUpdateRequest.getEmail();
		this.name = managerUpdateRequest.getName();
	}

	public void deleteManager() {
		this.state = true;
	}
}
