package gr.sch.ira.minoas.session;

import gr.sch.ira.minoas.model.core.School;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.FacesMessages;

@Stateful
@Name("createTeachingVoid")
public class CreateTeachingVoidBean implements CreateTeachingVoid {
	
    @Logger private Log log;
	
    @In(required=false)
    @Out(required=false)
    private School school;
    
    @In FacesMessages facesMessages;
    
    public void createTeachingVoid()
    {
        //implement your business logic here
        log.info("createTeachingVoid.createTeachingVoid() action called");
        facesMessages.add("createTeachingVoid");
    }

    
    @Begin(join=true, pageflow="createTeachingVoid")
	public void begin() {
		log.info("conversation has begun");
		// TODO Auto-generated method stub
		
	}

    @End
	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	@Remove
	@Destroy
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void end() {
		// TODO Auto-generated method stub
		
	}
    
    
    
}
