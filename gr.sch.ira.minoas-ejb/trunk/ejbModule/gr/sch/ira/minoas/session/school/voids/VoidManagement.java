package gr.sch.ira.minoas.session.school.voids;

import java.util.Collection;
import java.util.Iterator;

import gr.sch.ira.minoas.core.EventConstants;
import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;
import gr.sch.ira.minoas.session.school.SchoolAware;

import javax.ejb.Local;

import org.jboss.seam.annotations.RaiseEvent;

public interface VoidManagement extends SchoolAware {
	
	enum ConversationStatus {
		CREATING_NEW_VOID, UPDATING_EXISTING_VOID, NEW_VOID_SAVED, EXISTING_VOID_UPDATED
	}
	public void setTeachingVoid(TeachingVoid teachingVoid);

	public TeachingVoid getTeachingVoid();
	
public void beginSchoolVoidManagement();
	
	public Collection<TeachingVoid> searchTeachingVoids();



	public void cancel();

	public void addTeachingResource();

	public void removeTeachingResource(TeachingResource teachingResource);

	public TeachingVoid createTeachingVoid();

	public void beginCreateTeachingVoid();
	
	public void beginCreateAnotherTeachingVoid();
	
	public void beginUpdateExistingTeachingVoid(TeachingVoid teachingVoid);

	public void saveCreatedTeachingVoid();
	
	public void saveExistingTeachingVoid();
	
	public void removeExistingTeachingVoid();
	
	
	
}
