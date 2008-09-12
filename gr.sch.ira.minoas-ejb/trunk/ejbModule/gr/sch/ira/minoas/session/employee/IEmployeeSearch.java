package gr.sch.ira.minoas.session.employee;

import gr.sch.ira.minoas.model.core.SchoolYear;
import gr.sch.ira.minoas.model.core.Specialization;

public interface IEmployeeSearch {
	
	public String search();
	
	public String select();
	
	public void setSpecializationFilter(Specialization specialization_filter);
	
	public void setSchoolYearFilter(SchoolYear school_year);
	
	public SchoolYear getSchoolYearFilter();
	
	public Specialization getSpecializationFilter();
	
	public void setEmploymentFilter(Boolean employment_filter);
	
	public Boolean getEmploymentFilter();

}
