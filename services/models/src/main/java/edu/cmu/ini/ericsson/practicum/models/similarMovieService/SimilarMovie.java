package edu.cmu.ini.ericsson.practicum.models.similarMovieService;
import org.springframework.data.annotation.Id;

public class SimilarMovie {
	@Id
	private String id;
	
	private String name;
	
	private String firstChoice;
	private String secondChoice;
	private String thirdChoice;
	
	public SimilarMovie() {
		
	}
	
	public SimilarMovie(String id, String name, String first,
			String second, String third) {
		this.id = id;
		
		this.name = name;
		
		this.firstChoice = first;
		this.secondChoice = second;
		this.thirdChoice = third;
	}
	
	@Override
    public String toString() {
        return String.format("%s -- %s --  %s --  %s --  %s -- ", 
        		this.id, this.name, this.firstChoice, this.secondChoice, this.thirdChoice);
    }
	
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
