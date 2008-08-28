package gr.sch.ira.minoas.session.school;

public interface ISchoolAdmin {

	public abstract String selectSchool();

	public abstract String saveSchool();

	public abstract String deleteSchool();

	public abstract String cancelSchool();

	public abstract String search();

	public abstract String newSchool();
	
	public String getSearchString();

	public void setSearchString(String searchString);

}