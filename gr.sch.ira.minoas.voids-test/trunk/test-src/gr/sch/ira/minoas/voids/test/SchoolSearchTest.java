package gr.sch.ira.minoas.voids.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class SchoolSearchTest extends SeamTest {

	@Test
	public void test_schoolSearch() throws Exception {
		new FacesRequest() {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{schoolSearch.schoolSearch}");
			}
		}.run();
	}
}
