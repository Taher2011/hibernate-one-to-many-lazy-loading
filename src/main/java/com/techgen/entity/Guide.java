package com.techgen.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Guide {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String code;

	private String name;

	@OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST) // by default fetch types for @OneToMany and @ManyToMany mappings are LAZY
	@OrderBy("name DESC, subject ASC")
	private Set<Student> students = new HashSet<>();

	public Guide(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public void addStudent(Student student) {
		students.add(student);
		student.setGuide(this);
	}

}
