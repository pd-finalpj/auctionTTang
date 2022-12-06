package com.encore.auction.model.user;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Where;

import com.encore.auction.controller.user.requests.UserUpdateRequest;
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
public class User extends BaseEntity {

	@Id
	@Column(length = 20)
	private String id;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String salt;

	@Column(nullable = false, length = 10)
	private String name;

	@Column(nullable = false)
	private Integer age;

	@Column(nullable = false, length = 10)
	private String nickname;

	@Column(nullable = false, length = 13)
	private String phoneNumber;

	@Column(nullable = false, length = 123)
	private String email;

	@Column(nullable = false, columnDefinition = "bit(1) default 0", length = 1)
	private Boolean state;

	public User(String id, String password, String salt, String name, Integer age, String nickname, String phoneNumber,
		String email, Boolean state) {
		this.id = id;
		this.password = password;
		this.salt = salt;
		this.name = name;
		this.age = age;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.state = state;
	}

	@Override
	public String toString() {
		return "User{" +
			"id='" + id + '\'' +
			", password='" + password + '\'' +
			", salt='" + salt + '\'' +
			", name='" + name + '\'' +
			", age=" + age +
			", nickname='" + nickname + '\'' +
			", phoneNumber='" + phoneNumber + '\'' +
			", email='" + email + '\'' +
			", state=" + state +
			'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(id, user.id) && Objects.equals(password, user.password)
			&& Objects.equals(salt, user.salt) && Objects.equals(name, user.name)
			&& Objects.equals(age, user.age) && Objects.equals(nickname, user.nickname)
			&& Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email)
			&& Objects.equals(state, user.state);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, password, salt, name, age, nickname, phoneNumber, email, state);
	}

	public void updateUser(UserUpdateRequest userUpdateRequest, String encryptedPassword, String newSalt) {
		this.password = encryptedPassword;
		this.salt = newSalt;
		this.age = userUpdateRequest.getAge();
		this.nickname = userUpdateRequest.getNickname();
		this.email = userUpdateRequest.getEmail();
		this.name = userUpdateRequest.getName();
	}

	public void deleteUser() {
		this.state = true;
	}
}
