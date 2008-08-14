package gr.sch.ira.minoas.session.school.voids;

import gr.sch.ira.minoas.model.voids.TeachingRequirement;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.session.school.SchoolAware;

import java.util.Collection;

public interface VoidManagement extends SchoolAware {
	
	enum ConversationStatus {
		CREATING_NEW_VOID, UPDATING_EXISTING_VOID, NEW_VOID_SAVED, EXISTING_VOID_UPDATED
	}
	public void setTeachingVoid(TeachingRequirement teachingVoid);

	public TeachingRequirement getTeachingVoid();
	
public void beginSchoolVoidManagement();
	
	public Collection<TeachingRequirement> searchTeachingVoids();



	public void cancel();

	public void addTeachingResource();

	public void removeTeachingResource(TeachingResource teachingResource);

	public TeachingRequirement createTeachingVoid();

	public void beginCreateTeachingVoid();
	
	public void beginCreateAnotherTeachingVoid();
	
	public void beginUpdateExistingTeachingVoid(TeachingRequirement teachingVoid);

	public void saveCreatedTeachingVoid();
	
	public void saveExistingTeachingVoid();
	
	public void removeExistingTeachingVoid();
	
	
	
}
