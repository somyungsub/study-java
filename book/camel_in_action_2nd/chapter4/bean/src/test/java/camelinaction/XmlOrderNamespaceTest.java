package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;

/**
 * Test to demonstrate using @XPath with @Namespace.
 */
public class XmlOrderNamespaceTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camelinaction/xmlOrderNamespace.xml");
    }

    @Override
    public void setUp() throws Exception {
        deleteDirectory("target/order");
        super.setUp();
    }

    @Test
    public void sendIncomingOrderWithNamespace() throws Exception {
        MockEndpoint mock = getMockEndpoint("mock:queue:order");
        mock.expectedMessageCount(1);

        // prepare a XML document from a String which is converted to a DOM
        // notice we have included the namespace in the XML
        String body = "<order xmlns=\"http://camelinaction.com/order\" customerId=\"4444\"><item>Camel in action</item></order>";
        Document xml = context.getTypeConverter().convertTo(Document.class, body);

        // store the order as a file which is picked up by the route
        template.sendBodyAndHeader("file://target/order", xml, Exchange.FILE_NAME, "order.xml");

        mock.assertIsSatisfied();
    }

}
