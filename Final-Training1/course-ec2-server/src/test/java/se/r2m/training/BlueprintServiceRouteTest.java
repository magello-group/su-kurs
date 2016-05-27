package se.r2m.training;

import java.util.Dictionary;
import java.util.Map;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.camel.util.KeyValueHolder;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class BlueprintServiceRouteTest extends CamelBlueprintTestSupport {
	
    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/blueprint-service.xml";
    }

    @Test
    public void testRoute() throws Exception {
        // the route is timer based, so every 5th second a message is send
        // we should then expect at least one message
        getMockEndpoint("mock:result").expectedMinimumMessageCount(1);

        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
