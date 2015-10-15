package api_gateway.services.similar_movie.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SimilarMovie {
	private String id;
	private String name;
	private String firstChoice;
	private String secondChoice;
	private String thirdChoice;

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
	public String getFirstChoice() {
		return firstChoice;
	}
	public void setFirstChoice(String firstChoice) {
		this.firstChoice = firstChoice;
	}
	public String getSecondChoice() {
		return secondChoice;
	}
	public void setSecondChoice(String secondChoice) {
		this.secondChoice = secondChoice;
	}
	public String getThirdChoice() {
		return thirdChoice;
	}
	public void setThirdChoice(String thirdChoice) {
		this.thirdChoice = thirdChoice;
	}
}
