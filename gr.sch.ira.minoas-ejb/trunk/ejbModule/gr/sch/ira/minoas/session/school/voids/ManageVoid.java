package gr.sch.ira.minoas.session.school.voids;

import java.util.Collection;

import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

@Local
public interface ManageVoid extends SchoolAware {
	
	enum ConversationStatus {
		CREATING_NEW_VOID, UPDATING_EXISTING_VOID, NEW_VOID_SAVED, EXISTING_VOID_UPDATED
	}
	public void setTeachingVoid(TeachingVoid teachingVoid);

	public TeachingVoid getTeachingVoid();

	public void remove();

	public void begin();

	public void cancel();

	public void addTeachingResource();

	public void removeTeachingResource(TeachingResource teachingResource);

	public TeachingVoid createTeachingVoid();

	public void beginCreateTeachingVoid();
	
	public void beginCreateAnotherTeachingVoid();
	
	public void beginUpdateExistingTeachingVoid(TeachingVoid teachingVoid);

	public void saveCreatedTeachingVoid();
	
	
}
