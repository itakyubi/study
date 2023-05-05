package com.wa.flink;

/**
 * CepTask
 * 2023/4/20 4:41 下午
 *
 * @author wuao
 */
public class CepTask {

    public static void main(String[] args) throws Exception {
        /*StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<Event> streamSource = env.addSource(new InternalSource());

        // 有几个触发器类型起几个flink任务
        Pattern pattern = Pattern.<Event>begin("start")
                .where(new SimpleCondition<Event>() {
                    @Override
                    public boolean filter(Event event) throws Exception {
                        // 从外部获取规则
                        List<Rule> rules;

                        // 转换成aviator计算表达式


                        // 计算表达式


                        return event.getName().equals("a");
                    }
                }).times(5);

        // 把规则转换成
        // 核心是利用condition从外部获取规则，然后用aviator计算表达式的值

        PatternStream patternStream = CEP.pattern(streamSource, pattern);
        PatternStream patternStream2 = CEP.pattern(streamSource, pattern);

        env.execute("cep");


        // 集群信息
        Configuration configuration = new Configuration();
        configuration.setString(JobManagerOptions.ADDRESS, "localhost");
        configuration.setInteger(JobManagerOptions.PORT, 6123);
        // 需要rest.port
        configuration.setInteger(RestOptions.PORT, 8081);

        RestClusterClient<StandaloneClusterId> restClusterClient = new RestClusterClient<StandaloneClusterId>(
                configuration, StandaloneClusterId.getInstance());


        restClusterClient.cancel(JobID.fromHexString("jobId"));


        int parallelism = 1;
        File jarFile = new File("");
        SavepointRestoreSettings savepointRestoreSettings = SavepointRestoreSettings.none();
        PackagedProgram program = PackagedProgram.newBuilder()
                .setConfiguration(configuration)
                .setEntryPointClassName("org.apache.flink.table.examples.java.WordCountSQL")
                .setJarFile(jarFile)
                .setSavepointRestoreSettings(savepointRestoreSettings).build();
        JobGraph jobGraph = PackagedProgramUtils.createJobGraph(program, configuration, parallelism, false);
        restClusterClient.submitJob(jobGraph);


        Pattern p = Pattern.<Event>begin("start")
                .where("c1==1")
                .within(10)
                .where("c2")
                .or("c3")
                .times(3);*/

    }

/*
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        env.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        Properties consumerProperties = getConsumerProperties();
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<String>(
                "flinkcep2",
                new SimpleStringSchema(),
                consumerProperties);
        //todo 策略模式
        kafkaConsumer.setStartFromEarliest();
        DataStreamSource<String> sourceStream = env.addSource(kafkaConsumer);
        DataStream<LoginEvent> mapStream = sourceStream.map(new MapFunction<String, LoginEvent>() {
            @Override
            public LoginEvent map(String inputData) throws Exception {
                String[] dataArray = inputData.split(",");
                LoginEvent loginEvent = new LoginEvent(Long.valueOf(dataArray[0].trim()),
                        dataArray[1].trim(),
                        dataArray[2].trim(),
                        Long.valueOf(dataArray[3].trim()),
                        "{name:'zhangsan',age:12,mum:100}");
//                        dataArray[4].trim());
                return loginEvent;
            }
        }).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<LoginEvent>(Time.seconds(5)) {
            @Override
            public long extractTimestamp(LoginEvent loginEvent) {
                return loginEvent.getEventTime()* 1000L;
            }
        });
//    mapStream.print("===>>");
        //todo 生成pattern对象
        //todo 新的消费
        Pattern<LoginEvent, ?> pattern = Pattern.<LoginEvent>begin("start").where(new RichIterativeCondition<LoginEvent>() {
            private String script = "";
            @Override
            public void open(Configuration parameters) throws Exception {
                super.open(parameters);
                //todo 初始化从外部获取规则。
                script = "getString(eventType)=='fail' && getLong(eventTime)>1558430826 && getInt(info.age)>=12";
                System.out.println("script = " + script);
            }
            @Override
            public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                LogEventCondition logEventCondition = new LogEventCondition(script);
                boolean success = logEventCondition.filter(value);
                //todo 这是条件
//              System.out.println("filter = " + filter);
                return success;
            }
        })
                .next("middle").where(new RichIterativeCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                        boolean fail = value.getEventType().equals("fail");
                        return fail;
                    }
                })
                .next("end").where(new RichIterativeCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                        String script_end = "getInt(info.mum)>=12";
                        LogEventCondition logEventCondition = new LogEventCondition(script_end);
                        boolean filter_end = logEventCondition.filter(value);
                        return filter_end;
                    }
                }).within(Time.minutes(5));
        PatternStream patternStream = CEP.pattern(mapStream, pattern);
        PatternStream patternstream  = patternStream.registerListener(new CepListener<LoginEvent>() {
            private String updateScript = "";
            @Override
            public void init() {
                System.out.println("初始化。。");
                updateScript = "getString(eventType)=='fail' && getLong(eventTime)>1558430826 && getInt(info.age)>=12";
            }
            @Override
            public Boolean needChange(LoginEvent element) {
                String eventType = element.getEventType();
//          System.out.println("遇到数据为change的时候修改规则...........");
                return eventType.equals("change");
            }
            @Override
            public Pattern<LoginEvent, ?> returnPattern(LoginEvent flagElement) {
                //todo 返回新的Pattern对象
                return Pattern.<LoginEvent>begin("start").where(new RichIterativeCondition<LoginEvent>() {
                    @Override
                    public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                        System.out.println("修改之后的updateScript = " + updateScript);
                        LogEventCondition logEventCondition = new LogEventCondition(updateScript);
                        boolean success = logEventCondition.filter(value);
                        //todo 这是条件
                        return success;
                    }
                })
                        .next("middle").where(new RichIterativeCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                                boolean success = value.getEventType().equals("success");
                                return success;
                            }
                        })
                        .next("end").where(new RichIterativeCondition<LoginEvent>() {
                            @Override
                            public boolean filter(LoginEvent value, Context<LoginEvent> ctx) throws Exception {
                                String script_end = "getInt(info.mum)>=12";
                                LogEventCondition logEventCondition = new LogEventCondition(script_end);
                                boolean filter_end = logEventCondition.filter(value);
                                return filter_end;
                            }
                        }).within(Time.minutes(5));
            }
        });
        patternstream.select(new RichPatternSelectFunction<LoginEvent, String>() {
            @Override
            public String select(Map<String, List<LoginEvent>> pattern) throws Exception {
                String start =  pattern.containsKey("start") ? ((ArrayList)pattern.get("start")).get(0).toString() : "" ;
//          System.out.println("start = " + start);
                String middle = pattern.containsKey("middle") ? ((ArrayList)pattern.get("middle")).get(0).toString() : "" ;
//          System.out.println("middle = " + middle);
                String end = pattern.containsKey("end") ? ((ArrayList)pattern.get("end")).get(0).toString() : "" ;
//          System.out.println("end = " + end);
                return start+"$$$"+middle+"$$$"+end;
            }
        }).print();
        env.execute("cep");
    }
    public static Invocable invocable(String script) throws Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("groovy");
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        return inv;
    }
    public static Properties getConsumerProperties() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", Constants.BOOTSTRAP_SERVERS);
        properties.put("group.id", Constants.GROUP_ID);
        properties.setProperty("flink.partition-discovery.interval-millis",300 * 1000+"");  // 自动发现消费的partition变化
        return properties;
    }
    public static Properties getSinkProperties() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers",Constants.BOOTSTRAP_SERVERS);
        props.setProperty("buffer.memory", Constants.BUFFER_MEMORY);
        props.setProperty("batch.size", Constants.BATCH_SIZE);
        props.setProperty("linger.ms", Constants.LINGER_MS);
        props.setProperty("max.request.size", Constants.MAX_REQUEST_SIZE);
        props.setProperty("acks", Constants.ACKS);
        props.setProperty("retries", Constants.RETRIES);
        props.setProperty("retry.backoff.ms", Constants.RETRY_BACKOFF_MS);
        return props;
    }*/
}
