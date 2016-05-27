package se.su.camel;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Ignore;
import org.junit.Test;

public class MyRouteBuilderTest extends CamelTestSupport {
 
    @Produce(uri = "file:/tmp/CamelTraining1/in")
    protected ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

    @Override
    public boolean isDumpRouteCoverage() {
        return true;
    }
 
    @Test
    public void testSendMatchingMessage() throws Exception {
        String expectedBody = "<matched/>";
        resultEndpoint.expectedBodiesReceived(expectedBody);
        template.sendBody(expectedBody);
        resultEndpoint.assertIsSatisfied();
    }
 
    @Test
    public void testSendNotMatchingMessage() throws Exception {
        String expectedBody = "<matched/>";
        resultEndpoint.expectedBodiesReceived(expectedBody + "1");
        template.sendBody(expectedBody);
        resultEndpoint.assertIsNotSatisfied();
    }
 
    @Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("file:/tmp/CamelTraining1/in")
                .to("mock:result");
            }
        };
    }
}