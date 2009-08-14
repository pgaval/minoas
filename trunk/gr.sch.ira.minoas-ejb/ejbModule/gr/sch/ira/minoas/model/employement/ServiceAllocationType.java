/**
 * 
 */
package gr.sch.ira.minoas.model.employement;

/**
 * @author slavikos
 *
 */
public enum ServiceAllocationType {
	
	SCHOOL_HEADMASTER("SCHOOL_HEADMASTER_ALLOCATION_SERVICE_TYPE"),
	SCHOOL_SUBHEADMASTER("SCHOOL_SUBHEADMASTER_ALLOCATION_SERVICE_TYPE"),
	GRAND_HEADMASTER("GRAND_HEADMASTER_ALLOCATION_SERVICE_TYPE"),
	OFFICE_CHIEF("OFFICE_CHIEF_ALLOCATION_SERVICE_TYPE"),
	EDUCATION_CHIEF("EDUCATION_CHIEF_SERVICE_TYPE"),
	KESSYP("KESSYP_SERVICE_TYPE"),
	KPE("KPE_SERVICE_TYPE"),
	GRASEP("GRASEP_SERVICE_TYPE"),
	KEKPLHNET("KEKPLHNET_SERVICE_TYPE"),
	EKFE("EKFE_SERVICE_TYPE"),
	YP_POLITISTIKON("YP_POLITISTIKON_SERVICE_TYPE"),
	YP_PERIBALONTIKIS("YP_PERIBALONTIKIS_SERVICE_TYPE");
	
	
	private ServiceAllocationType(String key) {
		this.key = key;
	}

	private String key;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
}
