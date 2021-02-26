package com.wa.demo.k8s;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * Kubernetes
 * 2020-10-10 14:43
 *
 * @author wuao
 */
public class Kubernetes {
    public static void main(String[] args) throws JsonProcessingException {
        KubernetesClient client = new DefaultKubernetesClient();

        // get all namespace
        NamespaceList namespaceList = client.namespaces().list();
        for (Namespace namespace : namespaceList.getItems()) {
            System.out.println(namespace.getMetadata().getName());
        }

        // get single namespace
        Namespace namespace = client.namespaces().withName("baetyl-edge-system").get();
        System.out.println(namespace);

        // get pods in namespace
        PodList podList = client.pods().inNamespace("baetyl-edge-system").list();
        for (Pod pod : podList.getItems()){
            System.out.println(pod.getStatus());

            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(pod.getStatus()));
        }
    }
}
