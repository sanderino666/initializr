package io.spring.initializr.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import io.spring.initializr.util.Agent.AgentId;
import org.junit.Test;

public class AgentTest {

  @Test
  public void testConstructor() {
    String version = "Versie";
    Agent agent = new Agent(AgentId.STS, version);

    assertThat(agent.getId(), equalTo(AgentId.STS));
    assertThat(agent.getVersion(), equalTo(version));
  }

  @Test
  public void testFromUserAgentCurl() {
    Agent agent = Agent.fromUserAgent("curl/1.2.4");
    assertThat(agent.getId(), equalTo(Agent.AgentId.CURL));
    assertThat(agent.getVersion(), equalTo("1.2.4"));
  }

  @Test
  public void testFromUserAgentSTSHttpie() {
    Agent agent = Agent.fromUserAgent("HTTPie/0.8.0");
    assertThat(agent.getId(), equalTo(Agent.AgentId.HTTPIE));
    assertThat(agent.getVersion(), equalTo("0.8.0"));
  }

  @Test
  public void testFromUserAgentSpringBootCli() {
    Agent agent = Agent.fromUserAgent("SpringBootCli/1.3.1.RELEASE");
    assertThat(agent.getId(), equalTo(Agent.AgentId.SPRING_BOOT_CLI));
    assertThat(agent.getVersion(), equalTo("1.3.1.RELEASE"));
  }

  @Test
  public void testFromUserAgentSts() {
    Agent agent = Agent.fromUserAgent("STS 3.7.0.201506251244-RELEASE");
    assertThat(agent.getId(), equalTo(Agent.AgentId.STS));
    assertThat(agent.getVersion(), equalTo("3.7.0.201506251244-RELEASE"));
  }

  @Test
  public void testFromUserAgentIntelliJIDEA() {
    Agent agent = Agent.fromUserAgent("IntelliJ IDEA");
    assertThat(agent.getId(), equalTo(Agent.AgentId.INTELLIJ_IDEA));
    assertThat(agent.getVersion(), is(nullValue()));
  }

  @Test
  public void testFromUserAgentIntelliJIDEAWithVersion() {
    Agent agent = Agent.fromUserAgent("IntelliJ IDEA/144.2 (Community edition; en-us)");
    assertThat(agent.getId(), equalTo(Agent.AgentId.INTELLIJ_IDEA));
    assertThat(agent.getVersion(), is("144.2"));
  }

  @Test
  public void testFromUserAgentNetBeans() {
    Agent agent = Agent.fromUserAgent("nb-springboot-plugin/0.1");
    assertThat(agent.getId(), equalTo(Agent.AgentId.NETBEANS));
    assertThat(agent.getVersion(), is("0.1"));
  }

  @Test
  public void testFromUserAgentGenericBrowser() {
    Agent agent = Agent.fromUserAgent("Mozilla/5.0 (Linux; Android 6.0.1; Nexus 5 Build/MMB29K) ");
    assertThat(agent.getId(), equalTo(Agent.AgentId.BROWSER));
    assertThat(agent.getVersion(), is(nullValue()));
  }

  @Test
  public void testFromUserAgentRobot() {
    Agent agent = Agent.fromUserAgent("Googlebot-Mobile");
    assertThat(agent, is(nullValue()));
  }

  @Test
  public void testFromUserAgentUnknown() {
    Agent agent = Agent.fromUserAgent("Unknown");
    assertThat(agent, is(nullValue()));
  }
}
