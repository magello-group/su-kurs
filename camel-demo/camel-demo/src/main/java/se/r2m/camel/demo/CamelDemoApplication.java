package se.r2m.camel.demo;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hipchat.HipchatConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelDemoApplication extends RouteBuilder {
	
	//  Query string: ?key=...&source=en&target=fr&q=...
	//
	//  Response:
	//	{
	//		data: {
	//			translations: [
	//				{
	//					translatedText: "..."
	//				}
	//			]
	//		}
	//	}
	private final String translate = "https://www.googleapis.com/language/translate/v2";
	
	//
	// Skicka rum och meddelande som header
	//
	private final String hipchat = "hipchat://?authToken=" + System.getenv("hipchat.key");
	
	//
	// Request
	//
	// Response
	//	<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>...</Message></Response>
	//
	
	@Override
	public void configure() throws Exception {

		
		from("direct:translate").id("translate")
			.removeHeaders("*", "From", "Body", "Translated")
			.setBody().groovy("URLEncoder.encode(request.body)")
			.setHeader(Exchange.HTTP_QUERY, simple("key=${sysenv.google.key}&source=en&target=fr&q=${body}"))
			.setBody(simple("${null}"))
			.to(translate)
			.transform().jsonpath("$.data.translations[0].translatedText")
			.setHeader("Translated", simple("${body}"));

		from("direct:hipchat").id("hipchat")
			.removeHeaders("*", "Body", "From", "Translated")
			.setHeader(HipchatConstants.TO_ROOM, constant("Camel-demo"))
			.choice()
				.when(header("From").regex("^.*[01]$"))
					.setHeader(HipchatConstants.MESSAGE_BACKGROUND_COLOR, constant("green"))
				.when(header("From").regex("^.*[23]$"))
					.setHeader(HipchatConstants.MESSAGE_BACKGROUND_COLOR, constant("red"))
				.when(header("From").regex("^.*[45]$"))
					.setHeader(HipchatConstants.MESSAGE_BACKGROUND_COLOR, constant("purple"))
				.when(header("From").regex("^.*[67]$"))
					.setHeader(HipchatConstants.MESSAGE_BACKGROUND_COLOR, constant("gray"))
				.otherwise()
					.setHeader(HipchatConstants.MESSAGE_BACKGROUND_COLOR, constant("yellow"))
			.to(hipchat);
		
		from("jetty:http://0.0.0.0:9999/sms-in").id("sms-in")
			.setBody(simple("${headers.Body}"))
			.to("direct:translate")
			.setBody().groovy("request.headers.get('From')[-4..-1] + ' - ' + request.body")
			.multicast()
				.to("direct:hipchat")
			.end()
			.setBody(simple("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>${headers.Translated}</Message></Response>"))
			.setHeader("Content-Type", constant("application/xml"));	

	}

    public static void main(String[] args) {
    	SpringApplication.run(CamelDemoApplication.class, args);
    }
}
