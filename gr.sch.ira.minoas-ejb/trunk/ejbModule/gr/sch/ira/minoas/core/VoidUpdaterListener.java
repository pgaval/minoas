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

	@Observer(EventConstants.EVENT_TEACHING_VOID_ADDED)
	public void foo() {
		log.info("received teaching void (#0) added event for school #1. ",
				teachingVoid, teachingVoid.getSchool());
		teachingVoid = getEntityManager().contains(teachingVoid) ? teachingVoid
				: getEntityManager().merge(teachingVoid);
		if (teachingVoid.getTeachingResources() != null) {
			long required_hours = teachingVoid.getRequiredHours().longValue();
			long teaching_hours = 0L;
			long diff = 0L;
			for (TeachingResource resource : teachingVoid
					.getTeachingResources()) {
				teaching_hours = +resource.getTeachingHours().longValue();
			}
			diff = (required_hours - teaching_hours * (-1));
			log
					.info(
							"teaching void #0 has totally #1 teaching hours registered, thus #2 difference.",
							teachingVoid, teachingVoid.getTeachingHours(), Long
									.valueOf(diff));
			teachingVoid.setTeachingHours(Long.valueOf(diff));
			log.info("teaching void #0 has been updated", teachingVoid);
		}
	}

}
