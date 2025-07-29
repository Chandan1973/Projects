package com.onlinelearningplatform.models;

public class Answer {
	private int questionId; // ID of the question
	private String choose; // Chosen answer (e.g., optionA, optionB)

	

	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Answer(int questionId, String choose) {
		super();
		this.questionId = questionId;
		this.choose = choose;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}
}
