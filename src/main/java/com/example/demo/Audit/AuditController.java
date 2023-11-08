package com.example.demo.Audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {
	
	@Autowired
	public AuditService aus;
	
	@PostMapping("/audit")
	public Audit createAudit (@Validated @RequestBody Audit adt)
	{
		return aus.create(adt);
	}
	
	@GetMapping("/audits")
	public List<Audit> getAllAudits()
	{
		return aus.getAll();
	}
	
	@GetMapping("/audit/{id}")
	public Optional<Audit> getAuditById (@PathVariable String id)
	{
		return aus.getById(id);
	}
	
	@PutMapping("/audit/{id}")
	public Audit updateAudit (@PathVariable String id, @Validated @RequestBody Audit adt)
	{
		return aus.update(id, adt);
	}
	
	@DeleteMapping("/audit/{id}")
	public String deleteAudit (@PathVariable String id)
	{
		aus.delete(id);
		return "Audit with id:" + id + "deleted successfully.";
	}

}
