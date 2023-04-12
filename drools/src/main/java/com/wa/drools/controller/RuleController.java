package com.wa.drools.controller;

import com.wa.drools.model.Person;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * RuleController
 * 2023/2/15 11:19 上午
 *
 * @author wuao
 */
@RequestMapping("/v1/rule")
@RestController
public class RuleController {

    @Autowired
    private InternalKnowledgeBase base;

    @Autowired
    private StatelessKieSession session;

    @PostMapping("/add")
    public Object add(@RequestParam int idx) {
        String rule = generateRule(idx);
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newByteArrayResource(rule.getBytes()), ResourceType.DRL);
        base.addPackages(builder.getKnowledgePackages());
        return "add";
    }

    @PostMapping("/delete")
    public Object delete(@RequestParam int idx) {
        String rule = "man" + idx;
        base.removeRule("com.wa.drools", rule);
        return "delete";
    }

    @GetMapping("/exec")
    public Object exec(@RequestParam int idx) {
        Map<String, Object> output = new HashMap<>();
        session.setGlobal("output", output);
        session.execute(new Person(idx, String.valueOf(idx)));
        return output;
    }


    private String generateRule(int idx) {
        return String.format(
                "package com.wa.drools.rules\n" +
                        "import com.wa.drools.model.Person\n" +
                        "dialect \"java\"\n" +
                        "global java.util.Map output\n" +
                        "rule \"man%d\"\n" +
                        "    when\n" +
                        "        $p : Person(age < %d)\n" +
                        "    then\n" +
                        "        output.put(drools.getRule().getName(), $p);\n" +
                        "end\n", idx, idx);
    }
}
