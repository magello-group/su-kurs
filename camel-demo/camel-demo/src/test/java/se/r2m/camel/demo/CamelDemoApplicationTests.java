package se.r2m.camel.demo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CamelDemoApplication.class)
public class CamelDemoApplicationTests {

	@Autowired
	private ProducerTemplate producerTemplate;
	 
	@Autowired
	private CamelContext camelContext;

	@Test
	//@Ignore
	public void testTranslation() throws CamelExecutionException, UnsupportedEncodingException {
		Object res = producerTemplate.sendBody("direct:translate", ExchangePattern.InOut, URLEncoder.encode("Hej din gamla pastej!", "UTF-8"));
		System.out.println(res);
	}

	@Test
	@Ignore
	public void testHipchat() throws CamelExecutionException, UnsupportedEncodingException {
		Object res = producerTemplate.sendBody("direct:hipchat", ExchangePattern.InOut, "Pannkakan föll platt till marken");
		System.out.println(res);
	}
}
