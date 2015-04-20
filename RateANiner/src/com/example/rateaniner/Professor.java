package com.example.rateaniner;

public class Professor {
	String name, dept, poistion;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPoistion() {
		return poistion;
	}

	public void setPoistion(String poistion) {
		this.poistion = poistion;
	}

	@Override
	public String toString() {
		return "Professor [name=" + name + ", dept=" + dept + ", poistion="
				+ poistion + "]";
	}

}
