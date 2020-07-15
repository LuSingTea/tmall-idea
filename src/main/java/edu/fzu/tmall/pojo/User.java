package edu.fzu.tmall.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	//主属性配置，主要主键产生方式
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//普通属性配置
	@Column(name = "password",nullable = false,length = 50)
	private String password;
	
	private String name;
	
	//@Column(name = "password",nullable = false,length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnonymousName() {
		if (null == name)
			return null;

		if (name.length() <= 1)
			return "*";

		if (name.length() == 2)
			return name.substring(0, 1) + "*";

		char[] categories = name.toCharArray();
		for (int i = 1; i < categories.length - 1; i++) {
			categories[i] = '*';
		}
		return new String(categories);

	}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
