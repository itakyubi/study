package com.wa.neo4j.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * RelationShip
 * 2023/2/22 6:39 下午
 *
 * @author wuao
 */

@Data
@Builder
@RelationshipEntity(type = "relationShip") // 标明是一个关系实体
public class RelationShip {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode // 开始节点（可以理解为父节点）
    private Department parent;

    @EndNode // 结束节点（可以理解为子节点）
    private Department child;
}
