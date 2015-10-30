package edu.cmu.ini.ericsson.practicum.models.userService;

import org.springframework.data.annotation.Id;


public class User {

	@Id
    private String id;
	private String name;
    private String age;
    private String gender;
    private String occupation;
    private String zipCode;
    private String password;
    
    public User() {
    	
    }
    
	public User(String id, String age, String gender, String occupation, String zipCode, String password,
			String name) {
		super();
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.occupation = occupation;
		this.zipCode = zipCode;
		this.password = password;
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", age=" + age + ", gender=" + gender + ", occupation=" + occupation + ", zipCode="
				+ zipCode + ", password=" + password + ", name=" + name + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
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
	   	
}
