package com.example.demo;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.Audit.Audit;
import com.example.demo.Audit.AuditController;
import com.example.demo.Audit.AuditService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest (controllers = AuditController.class)
public class AuditControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private AuditService service;
	
	private List<Audit> audits;
	
	private Optional<Audit> auditById;
	
	private Audit adt;
	
	private Audit updateAudit;
	
	private String deleteAudit;
	
	@BeforeEach
	void setUp()
	{
		audits = List.of(new Audit("g65aa2","R1","DONE","ACROS","E1"), new Audit("d65pp3","R2","RESOLVED","BAKUTA","E2"), new Audit("i65tt4","R3","ERROR","CARUDI","E3"), new Audit("m65ii5","R4","PENDING","DURPE","E4"));
		adt = new Audit("1234567","R5","DONE","ENRODE","E5");
		auditById = Optional.of(new Audit("1234567","R5","DONE","ENRODE","E5"));
	}
	
	@Test
	//@Disabled
	void createAuditTest() throws Exception
	{
		Mockito.when(service.create(Mockito.any(Audit.class))).thenReturn(adt);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/audit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(adt).getBytes(StandardCharsets.UTF_8))
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		String adtJson = result.getResponse().getContentAsString();
		Assertions.assertThat(adtJson).isNotEmpty();
		Assertions.assertThat(adtJson).isEqualToIgnoringCase(mapper.writeValueAsString(adt));
	}
	
	@Test
	//@Disabled
	void getAuditsTest() throws Exception
	{
		Mockito.when(service.getAll()).thenReturn(audits);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/audits")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		String adtJson = result.getResponse().getContentAsString();
		Assertions.assertThat(adtJson).isEqualToIgnoringCase(mapper.writeValueAsString(audits));
	}
	
	@Test
	//@Disabled
	void getAuditByIdTest() throws Exception
	{
		String id = "654215c6fc4b5e273d493d3a";
		Mockito.when(service.getById(id)).thenReturn(auditById);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/audit/654215c6fc4b5e273d493d3a")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		String adtJson = result.getResponse().getContentAsString();
		Assertions.assertThat(adtJson).isNotEqualToIgnoringCase(mapper.writeValueAsString(auditById));
	}
	
	@Test
	//@Disabled
	void updateAuditByIdTest() throws Exception
	{
		String id = "65412256956f9843dc665ff2";
		Mockito.when(service.update(id, adt)).thenReturn(adt);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/audit/65412256956f9843dc665ff2")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(adt).getBytes(StandardCharsets.UTF_8))
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		Assertions.assertThat(result).isNotNull();
		String adtJson = result.getResponse().getContentAsString();
		Assertions.assertThat(adtJson).isEqualToIgnoringCase(mapper.writeValueAsString(adt));
	}
	
	@Test
	//@Disabled
	void deleteAuditByIdTest() throws Exception
	{
		String id = "65412256956f9843dc665ff2";
		
		Mockito.when(service.getById(id)).thenReturn(null);
		
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/audit/65412256956f9843dc665ff2"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Assertions.assertThat(result).isNotNull();
	}

}
