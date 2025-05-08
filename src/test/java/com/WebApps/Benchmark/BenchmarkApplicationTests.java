package com.WebApps.Benchmark;

import com.WebApps.Benchmark.DTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BenchmarkApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper; // comes from jackson

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateApplication() throws Exception {
		ApplicationDTO dto = new ApplicationDTO();
		dto.setAppName("testx");
		dto.setUrl("testx.com");

		mockMvc.perform(post("/api/applications")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.appName").value("testx"))
				.andExpect(jsonPath("$.url").value("testx.com"));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateAppRelease() throws Exception {
		AppReleaseDTO dto = new AppReleaseDTO();
		dto.setApplicationID(1);
		dto.setReleaseName("1.1");

		mockMvc.perform(post("/api/app_releases")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.releaseName").value("1.1"))
				.andExpect(jsonPath("$.applicationID").value(1));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateTestSuite() throws Exception {
		TestSuiteDTO dto = new TestSuiteDTO();
		dto.setTestSuiteName("test suite");
		dto.setApplicationId(1);

		mockMvc.perform(post("/api/test_suites")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.testSuiteName").value("test suite"))
				.andExpect(jsonPath("$.applicationId").value(1));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateTestCases() throws Exception {
		TestCaseDTO dto = new TestCaseDTO();
		dto.setTestCaseName("test case");
		dto.setDescription("test description");
		dto.setTestSuiteId(1);

		mockMvc.perform(post("/api/test_cases")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.testCaseName").value("test case"))
				.andExpect(jsonPath("$.description").value("test description"))
				.andExpect(jsonPath("$.testSuiteId").value(1));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateTestCaseVersions() throws Exception {
		TestCaseVersionDTO dto = new TestCaseVersionDTO();
		dto.setTestCaseVersionName("test case version");
		dto.setTestCaseId(1);

		mockMvc.perform(post("/api/test_case_versions")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.testCaseVersionName").value("test case version"))
				.andExpect(jsonPath("$.testCaseId").value(1));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateBreakageReasons() throws Exception {
		BreakageReasonDTO dto = new BreakageReasonDTO();
		dto.setReasonName("unable to locate element");

		mockMvc.perform(post("/api/breakage_reasons")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.reasonName").value("unable to locate element"));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateLocatingMethods() throws Exception {
		LocatingMethodDTO dto = new LocatingMethodDTO();
		dto.setLocatingMethodName("xpath");

		mockMvc.perform(post("/api/locating_methods")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.locathingMethodName").value("xpath"));
	}

	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateBreakages() throws Exception {
		BreakageDTO dto = new BreakageDTO();
		dto.setAppRelease(1);
		dto.setTestCaseVersion(1);
		dto.setTaxonomyDescription("test taxonomy");
		dto.setBreakageReasonID(1);
		dto.setLocatingMethodID(1);

		mockMvc.perform(post("/api/breakages")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.taxonomyDescription").value("test taxonomy"))
				.andExpect(jsonPath("$.appRelease").value(1))
				.andExpect(jsonPath("$.testCaseVersion").value(1))
				.andExpect(jsonPath("$.breakageReasonID").value(1))
				.andExpect(jsonPath("$.locatingMethodID").value(1));
	}


	@Test
	@WithMockUser(username = "testuser", roles = {"USER"})
	public void testCreateRepairs() throws Exception {
		RepairDTO dto = new RepairDTO();
		dto.setBreakageID(1);

		mockMvc.perform(post("/api/repair_a_line_of_codes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.breakageID").value(1));
	}

}
