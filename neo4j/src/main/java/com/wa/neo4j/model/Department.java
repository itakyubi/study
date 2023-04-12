package com.wa.neo4j.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Department
 * 2023/2/22 6:36 下午
 *
 * @author wuao
 */

@Data
@Builder
@NodeEntity(label = "department") // 标明是一个节点实体
public class Department {

    @Id // 实体主键
    @GeneratedValue // 实体属性值自增
    private Long id;

    @Property(name = "name") // 实体属性
    private String name;
}
