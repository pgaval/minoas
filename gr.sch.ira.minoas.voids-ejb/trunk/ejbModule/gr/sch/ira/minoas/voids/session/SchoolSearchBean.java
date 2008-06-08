package gr.sch.ira.minoas.voids.session;

import gr.sch.ira.minoas.model.core.School;

import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateless;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.log.Log;
import org.jboss.seam.faces.FacesMessages;

@Stateless
@Name("schoolSearch")
@Scope(ScopeType.SESSION)
public class SchoolSearchBean implements SchoolSearch {
	
    @Logger private Log log;
	
    @In FacesMessages facesMessages;
    
    @DataModel
    private List<School> schools;
    
    public void schoolSearch()
    {
        //implement your business logic here
        log.info("schoolSearch.schoolSearch() action called");
        facesMessages.add("schoolSearch");
    }
    
    //add additional action methods
    
    @Remove
    public void destroy() {
    	
    }
    
}
