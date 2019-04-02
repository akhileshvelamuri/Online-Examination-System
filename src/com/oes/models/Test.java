package com.oes.models;

import java.sql.Time;

public class Test {

	private int test_id;
	private String name;
	private Time duration;
	private int max_score;
	private int subject_id;
	
	
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Time getDuration() {
		return duration;
	}
	public void setDuration(Time duration) {
		this.duration = duration;
	}
	public int getMax_score() {
		return max_score;
	}
	public void setMax_score(int max_score) {
		this.max_score = max_score;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	
	
	
}
