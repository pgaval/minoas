package gr.sch.ira.minoas.core.session;

import gr.sch.ira.minoas.model.core.School;
import gr.sch.ira.minoas.model.core.Specialization;
import gr.sch.ira.minoas.model.voids.TeachingRequirement;

import java.util.Collection;

import javax.ejb.Remote;

@Remote
public interface CoreSearching {
	public Collection<TeachingRequirement> searchVoids(School school);

	public Collection<TeachingRequirement> searchVoids(School school,
			Specialization specialization, int minHours);
	
	/**
	 * Searches for schools with a given pattern.
	 * @param school_search_pattern
	 * @return
	 */
	public Collection<School> searchShools(String school_search_pattern);
	
	public Collection<School> searchShools(String school_search_pattern, String regionCode);
	
	public Specialization getSpecialization(String id);
}
