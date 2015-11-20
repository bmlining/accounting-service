package org.deepsource.doterra.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.stringContainsInOrder;

import com.codahale.metrics.MetricRegistry;

import io.dropwizard.jersey.DropwizardResourceConfig;

import org.deepsource.doterra.representations.HelloResponse;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class HelloResourceTest extends JerseyTest {


  @Override
  protected Application configure() {
    //forceSet(TestProperties.CONTAINER_PORT, "0");

    return DropwizardResourceConfig.forTesting(new MetricRegistry())
        .register(HelloResponse.class)
        .register(HelloResource.class);
  }

  @Test
  public void testWithoutName() {
    Response response = target("/hello").request().get();
    assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    assertThat(response.readEntity(HelloResponse.class).getMessage(),
        stringContainsInOrder("Hello!"));
  }

  @Test
  public void testWithName() {
    Response response = target("/hello").queryParam("name", "FooBar").request().get();
    assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    assertThat(response.readEntity(HelloResponse.class).getMessage(),
        stringContainsInOrder("Hello", "FooBar!"));
  }

  @Test
  public void testWithNameAndWhitespace() {
    Response response = target("/hello").queryParam("name", "Foo Bar").request().get();
    assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    assertThat(response.readEntity(HelloResponse.class).getMessage(),
        stringContainsInOrder("Hello", "Foo Bar!"));
  }
}
