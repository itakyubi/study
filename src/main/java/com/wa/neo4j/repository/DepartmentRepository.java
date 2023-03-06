package com.wa.neo4j.repository;

import com.wa.neo4j.model.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * DepartmentRepository
 * 2023/2/22 6:43 下午
 *
 * @author wuao
 */

@Repository
public interface DepartmentRepository extends Neo4jRepository<Department, Long> {

}
