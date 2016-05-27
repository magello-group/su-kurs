package se.r2m.training;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hipchat.HipchatConstants;

public class CamelDemoClientRouteBuilder extends RouteBuilder {

	private static final String SERVER_ADDRESS = "54.84.247.242";
	private static final String HIPCHAT_TOKEN = "PFENG3j84Fvs2YtKUu4HyYShKjJ2hrUGuHm2hDbg";

	// ActiveMQ - Messages from SMS
	// ----------------------------
	//
	// Listen to the topic 'sms.in' on the ActiveMQ Server hosted on SERVER_ADDRESS:61616.
	// The remote ActiveMQ address has already been setup in the blueprint-bean.xml:
	//
	//   <bean id="activemq" class="org.apache.activemq.camel.component.ActiveMQComponent">
    //     <property name="brokerURL" value="tcp://[server-ip]:61616"/>
    //     <property name="userName" value="smx"/>
    //     <property name="password" value="smx"/>
    //   </bean>
    //
	// Messages are recieved in this format: 
	// 
	// <sms>
	//   <message>SMS_MESSAGE_HERE</message>
	//   <from>MOBILE_NUMBER_HERE</from>
	// </sms>
	//
	// whenever an SMS is sent to this number:
	//
	//    0769 - 44 66 66
	//
	// You can also simulate this by calling this URL:
	//
	//   http://[server-ip]:9999/sms-in?Body=I+am+the+walrus&From=123456
	//

	// Translation HTTP service
	// ------------------------
	//
	// The service listens to:
	//
	//   http://[server-ip]:9999/translate?text=Phrase+to+translate
	//
	// where "Phrase+to+translate" is the phrase you want translated.
	// The service will return the text translated to french in the body
	//
	
	// Hipchat group chat
	// ------------------
	//
	// Login to the Hipchat group "Camel-demo" at this url:
	//
	//   https://camel-demo.hipchat.com/home
	//
	// Use the hipchat component
	//
	//   http://camel.apache.org/hipchat.html
	//
	// and connect and send messages to the chat room. Use the authToken defined in the variable
	// HIPCHAT_TOKEN defined above.
	//   

	// Proposed excercise
	// ------------------
	//
	// 1. Listen to the ActiveMQ topic 'sms-in'. Whenever an SMS is sent the server will publish a message
	// on this queue
	// 2. Translate the message from English to French using the translation service
	// 3. Post the result on the Hipchat Group 'camel-demo' using color-coding
	//
	// You maybe can invent some other cool functionality?
	
	@Override
	public void configure() throws Exception {

		// Code your routes here
	}
}
