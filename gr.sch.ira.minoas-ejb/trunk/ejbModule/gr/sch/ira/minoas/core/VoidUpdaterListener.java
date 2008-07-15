/**
 * 
 */
package gr.sch.ira.minoas.core;

import gr.sch.ira.minoas.model.voids.TeachingResource;
import gr.sch.ira.minoas.model.voids.TeachingVoid;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.framework.EntityController;
import org.jboss.seam.log.Log;

/**
 * @author <a href="mailto:filippos@slavik.gr">Filippos Slavik</a>
 * 
 */
@Name("voidUpdateListener")
public class VoidUpdaterListener extends EntityController {
	@Logger
	private Log log;

	@In(required = true)
	private TeachingVoid teachingVoid;

	@Observer({EventConstants.EVENT_TEACHING_VOID_ADDED, EventConstants.EVENT_TEACHING_VOID_MODIFIED})
	public void foo() {
		log.info("received teaching void (#0) added event for school #1. ",
				teachingVoid, teachingVoid.getSchool());
		teachingVoid = getEntityManager().merge(teachingVoid);
		getEntityManager().refresh(teachingVoid);
		if (teachingVoid.getTeachingResources() != null) {
			long teaching_hours = 0L;
			for (TeachingResource resource : teachingVoid
					.getTeachingResources()) {
				teaching_hours += resource.getTeachingHours().longValue();
			}
			
			teachingVoid.setTeachingHours(Long.valueOf(teaching_hours));
			log
					.info(
							"teaching void #0 has totally #1 teaching hours registered.",
							teachingVoid, teachingVoid.getTeachingHours());
			getEntityManager().persist(teachingVoid);
			log.info("teaching void #0 has been updated", teachingVoid);
		}
	}
}
