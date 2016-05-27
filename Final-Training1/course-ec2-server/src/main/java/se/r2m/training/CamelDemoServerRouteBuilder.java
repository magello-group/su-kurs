package se.r2m.training;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hipchat.HipchatConstants;

public class CamelDemoServerRouteBuilder extends RouteBuilder {
	
	//  Google Translate
	//
	//  URI: https://www.googleapis.com/language/translate/v2
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
	
	// Twilio Response
	//
	//	<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>...</Message></Response>
	//
	
	@Override
	public void configure() throws Exception {

		//
		// Returns
		//
		// <sms>
		//   <message>SMS_MESSAGE_HERE</message>
		//   <from>MOBILE_NUMBER_HERE</from>
		// </sms>
		//
		from("jetty:http://0.0.0.0:9999/sms-in").id("sms-in")
			.setExchangePattern(ExchangePattern.InOut)
			.setBody(simple("<?xml version=\"1.0\" encoding=\"UTF-8\"?><sms><message>${headers.Body}</message><from>${headers.From}</from></sms>"))
			.multicast()
				.to("activemq:topic:sms.in?disableReplyTo=true")
			.end()
			.setBody(simple("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Response><Message>Tack för ditt meddelande!</Message></Response>"));
		
		from("jetty:http://0.0.0.0:9999/translate").id("translate")
			.setBody(header("text"))
			.removeHeaders("CamelHttp*")
			.setHeader(Exchange.HTTP_QUERY, simple("key={{google}}&source=en&target=fr&q=${body}"))
			.setBody(simple("${null}"))
			.to("https://www.googleapis.com/language/translate/v2")
			.transform().jsonpath("$.data.translations[0].translatedText");

	}
}
