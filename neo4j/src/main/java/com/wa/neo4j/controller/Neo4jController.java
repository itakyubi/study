package com.wa.neo4j.controller;

import com.wa.neo4j.model.Department;
import com.wa.neo4j.model.RelationShip;
import com.wa.neo4j.repository.DepartmentRepository;
import com.wa.neo4j.repository.RelationShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Neo4jController
 * 2023/2/22 6:46 下午
 *
 * @author wuao
 */

@RequestMapping("/neo4j")
@RestController
public class Neo4jController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RelationShipRepository relationShipRepository;


    /*
     * a
     * - aa
     *   - aaa1
     *   - aaa2
     * - bb
     *   - bbb1
     *   - bbb2
     *   - bbb3
     * */
    @GetMapping("/add")
    public void add() {
        Department da = Department.builder().name("a").build();
        Department daa = Department.builder().name("aa").build();
        Department daaa1 = Department.builder().name("aaa1").build();
        Department daaa2 = Department.builder().name("aaa2").build();
        Department dbb = Department.builder().name("bb").build();
        Department dbbb1 = Department.builder().name("bbb1").build();
        Department dbbb2 = Department.builder().name("bbb2").build();
        Department dbbb3 = Department.builder().name("bbb3").build();

        List<Department> departmentList = Arrays.asList(da, daa, daaa1, daaa2, dbb, dbbb1, dbbb2, dbbb3);
        departmentRepository.saveAll(departmentList);

        RelationShip ra_aa = RelationShip.builder().parent(da).child(daa).build();
        RelationShip raa_aaa1 = RelationShip.builder().parent(daa).child(daaa1).build();
        RelationShip raa_aaa2 = RelationShip.builder().parent(daa).child(daaa2).build();
        RelationShip ra_bb = RelationShip.builder().parent(da).child(dbb).build();
        RelationShip rbb_bbb1 = RelationShip.builder().parent(dbb).child(dbbb1).build();
        RelationShip rbb_bbb2 = RelationShip.builder().parent(dbb).child(dbbb2).build();
        RelationShip rbb_bbb3 = RelationShip.builder().parent(dbb).child(dbbb3).build();
        List<RelationShip> relationShipList = Arrays.asList(ra_aa, raa_aaa1, raa_aaa2, ra_bb, rbb_bbb1, rbb_bbb2, rbb_bbb3);
        relationShipRepository.saveAll(relationShipList);
    }

    @GetMapping("get")
    public RelationShip get(Long id) {
        Optional<RelationShip> byId = relationShipRepository.findById(id);
        return byId.orElse(null);
    }

    @GetMapping("deleteRelationShip")
    public void deleteRelationShip(Long id) {
        relationShipRepository.deleteById(id);
    }

    @GetMapping("deleteDept")
    public void deleteDept(Long id) {
        departmentRepository.deleteById(id);
    }

    @GetMapping("deleteAll")
    public void deleteAll() {
        departmentRepository.deleteAll();
        relationShipRepository.deleteAll();
    }
}
