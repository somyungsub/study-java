package thejava;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class MasulsaAgent {

  public static void premain(String agentArgs, Instrumentation instrumentation) {
    new AgentBuilder
            .Default()
            .type(ElementMatchers.any())
            .transform(new AgentBuilder.Transformer() {
              @Override
              public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
                return builder.method(named("pullOut")).intercept(FixedValue.value("Rabbit~~~~"));
              }
            }).installOn(instrumentation);

  }
}
