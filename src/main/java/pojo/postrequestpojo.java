package pojo;

import java.util.List;

public class postrequestpojo {
	private String name;
	private String job;
	private List<String> languages;
	private List<String> technology;
	List<Cityrequest> cityrequestbody;

	public List<Cityrequest> getCityrequestbody() {
		return cityrequestbody;
	}

	public void setCityrequestbody(List<Cityrequest> cityrequestbody) {
		this.cityrequestbody = cityrequestbody;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public List<String> getTechnology() {
		return technology;
	}

	public void setTechnology(List<String> technology) {
		this.technology = technology;
	}

	public List<String> getMarks() {
		return Marks;
	}

	public void setMarks(List<String> marks) {
		Marks = marks;
	}

	private List<String> Marks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
