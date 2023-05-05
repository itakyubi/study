package com.wa.flink.groovy;

import com.wa.flink.model.Event;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.apache.flink.cep.pattern.Pattern;

/**
 * Groovy
 * 2023/4/26 10:38 上午
 *
 * @author wuao
 */
public class Groovy {

    public static void main(String[] args) throws Exception {
        cepDemo();
    }

    private static void demo() {
        //创建GroovyShell
        GroovyShell groovyShell = new GroovyShell();
        //装载解析脚本代码
        Script script = groovyShell.parse("package groovy\n" +
                "\n" +
                "def HelloWorld(){\n" +
                "    println \"hello world\"\n" +
                "}");
        //执行
        script.invokeMethod("HelloWorld", null);
    }

    private static void cepDemo() {
        String scriptStr = "package com.wa.flink.groovy\n" +
                "\n" +
                "import com.wa.flink.model.Event\n" +
                "import org.apache.flink.cep.pattern.Pattern\n" +
                "\n" +
                "def getP() {\n" +
                "    return Pattern.<Event>begin(\"start\")\n" +
                "            .where(new MyCondition()).times(5);\n" +
                "}\n" +
                "\n";

        /*GroovyClassLoader classLoader = new GroovyClassLoader();
        Class<?> groovyClass = classLoader.parseClass(script);
        GroovyObject groovyObject = (GroovyObject) groovyClass.newInstance();
        Pattern<Event, Event> pattern = (Pattern<Event, Event>) groovyObject.invokeMethod("getP",null);;*/


        /*ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        engine.eval(script);
        Invocable invocable = (Invocable) engine;
        Pattern<Event, Event> pattern = (Pattern<Event, Event>) invocable.invokeFunction("getP");*/

        GroovyShell groovyShell = new GroovyShell();
        Script script = groovyShell.parse(scriptStr);
        Pattern<Event, Event> pattern = (Pattern<Event, Event>) script.invokeMethod("getP", null);

        System.out.println();
    }
}
