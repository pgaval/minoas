package gr.sch.ira.minoas.session.security;

import gr.sch.ira.minoas.model.security.Role;

import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Factory;

public interface IRoleGroupManagement {

	
	public  void search();

	public  void setSearchString(String searchString);

	public  void updateRoleGroup();

	public  void removeRoleGroup();
	
	public void selectRoleGroup();
	
	public void constructNewRoleGroup();

	public abstract void saveRoleGroup();

}