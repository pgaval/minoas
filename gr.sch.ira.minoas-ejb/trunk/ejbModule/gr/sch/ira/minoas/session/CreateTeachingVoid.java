package gr.sch.ira.minoas.session;

import javax.ejb.Local;

@Local
public interface CreateTeachingVoid {  
    
	public void createTeachingVoid();  
	
	public void begin();
	public void cancel();
	public void end();
    public void destroy();
}