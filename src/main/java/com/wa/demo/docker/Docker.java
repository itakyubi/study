package com.wa.demo.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import java.io.IOException;
import java.util.List;

/**
 * Docker
 * 2021-02-26 16:55
 *
 * @author wuao
 */
public class Docker {
    public static void main(String[] args) throws IOException {
        DockerClientConfig standard = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
        DockerClient dockerClient = DockerClientBuilder.getInstance(standard).build();

        List<Image> images = dockerClient.listImagesCmd().exec();
        images.forEach(System.out::println);

        dockerClient.close();
    }
}
