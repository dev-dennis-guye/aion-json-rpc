module rpc.lib4j {
    requires java.net.http;
    requires aion.types;
    requires aion.util;
    requires json;
    requires org.apache.httpcomponents.httpcore;
    requires org.apache.httpcomponents.httpclient;
    requires slf4j.api;

    exports org.aion.rpc.client;
    exports org.aion.rpc.constants;
    exports org.aion.rpc.errors;
    exports org.aion.rpc.server;
    exports org.aion.rpc.types;
}