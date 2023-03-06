package com.wa.neo4j.repository;

import com.wa.neo4j.model.RelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * RelationShipRepository
 * 2023/2/22 6:44 下午
 *
 * @author wuao
 */
@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip, Long> {
}
