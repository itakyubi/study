package com.wa.test;

import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Test2
 * 2021-12-22 09:34
 *
 * @author wuao
 */
public class Test2 {

    public static void main(String[] args) {

    }

    static class Task {
        int cameraId;
        long maxTimestamp;
        long endTimestamp;
        String taskId;
    }

    static class Request {
        int cameraId;
        long startTimestamp;
        long recordPeriod;
    }

    private static long maxPeriod = 10 * 60 * 1000;
    private static Map<Integer, List<Task>> cameraMap = new HashMap<>();

    private static Map<String, TaskExecutor> threadMap = new HashMap<>();

    public static void record(Request request) {
        List<Task> taskList = cameraMap.getOrDefault(request.cameraId, new ArrayList<>());
        if (CollectionUtils.isEmpty(taskList)) {
            Task task = new Task();
            task.taskId = UUID.randomUUID().toString();
            task.cameraId = request.cameraId;
            task.endTimestamp = request.startTimestamp + request.recordPeriod;
            task.maxTimestamp = request.startTimestamp + maxPeriod;
            taskList.add(task);

            TaskExecutor taskExecutor = new TaskExecutor(task);
            taskExecutor.start();
            threadMap.put(task.taskId, taskExecutor);
        } else {
            boolean flag = false;
            for (Task task : taskList) {
                long endTime = request.startTimestamp + request.recordPeriod;
                if (endTime <= task.endTimestamp) {
                    flag = true;
                    break;
                } else {
                    if (endTime <= task.maxTimestamp) {
                        task.endTimestamp = endTime;
                        threadMap.get(task.taskId).setEndTime(endTime);
                    }
                }
            }

            if (!flag) {
                Task newTask = new Task();
                newTask.taskId = UUID.randomUUID().toString();
                newTask.cameraId = request.cameraId;
                newTask.endTimestamp = request.startTimestamp + request.recordPeriod;
                newTask.maxTimestamp = request.startTimestamp + maxPeriod;
                taskList.add(newTask);

                TaskExecutor taskExecutor = new TaskExecutor(newTask);
                taskExecutor.start();
                threadMap.put(newTask.taskId, taskExecutor);
            }


        }
    }

    static class TaskExecutor extends Thread {

        Task task;

        public TaskExecutor(Task task) {
            this.task = task;
        }

        public void setEndTime(long endTime) {
            task.endTimestamp = endTime;
        }

        @SneakyThrows
        @Override
        public void run() {
            long time = System.currentTimeMillis();
            while (time < task.endTimestamp && time < task.maxTimestamp) {
                Thread.sleep(100);
                time = System.currentTimeMillis();
            }

        }
    }

}

