package com.example.demo.Audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
	
	@Autowired
	public AuditRepository aur;
	
	public Audit create (Audit adt)
	{
		return aur.save(adt);
	}
	
	public List<Audit> getAll()
	{
		return aur.findAll();
	}
	
	public Optional<Audit> getById(String id)
	{
		return aur.findById(id);
	}
	
	public Audit update (String id,Audit adt)
	{
		adt.setId(id);
		return aur.save(adt);
	}
	
	public String delete (String id)
	{
		aur.deleteById(id);
		return "Audit with id:" + id + "deleted successfully.";
	}

}
