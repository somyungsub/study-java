package camelinaction;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopierWithCamel {

    public static void main(String args[]) throws Exception {
        // create CamelContext
        CamelContext context = new DefaultCamelContext();

        String fromPath = "book/camel_in_action_2nd/chapter1/file-copy/data/inbox?noop=true";
        String toPath = "book/camel_in_action_2nd/chapter1/file-copy/data/outbox";

        // add our route to the CamelContext
        context.addRoutes(new RouteBuilder() {
            public void configure() {
//                from("file:data/inbox?noop=true").to("file:data/outbox");
                from("file:"+fromPath).to("file:"+toPath);
            }
        });

        // start the route and let it do its work
        context.start();
        Thread.sleep(1000);

        // stop the CamelContext
        context.stop();
    }

}
