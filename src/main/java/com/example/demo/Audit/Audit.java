package com.example.demo.Audit;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="AUDIT")
public class Audit {
	
	public Audit(String string, String string2, String string3, String string4, String string5)
	{
		
	}
	
	@Id
	public String id;
	
	private String rootId;
	private String statusMsg;
	private String applicationName;
	private String errorId;

}
