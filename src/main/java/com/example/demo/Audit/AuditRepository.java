package com.example.demo.Audit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends MongoRepository<Audit,String>{

}
