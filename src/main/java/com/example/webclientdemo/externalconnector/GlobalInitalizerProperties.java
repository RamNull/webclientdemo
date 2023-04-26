package com.example.webclientdemo.externalconnector;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "library")
public class GlobalInitalizerProperties {

    // enable configuration properties scan at application level for this to work 
    @ConstructorBinding
    private GlobalInitalizerProperties(@DefaultValue("false") boolean proxy,
            @DefaultValue("") String proxyURL, @DefaultValue("8080") int port) {

        setproperties(proxy, proxyURL, port);
    }

    private static void setproperties(boolean isProxy, String proxyString, int portId) {
        port = portId;
        proxy = isProxy;
        proxyURL = proxyString;
    }

    private static boolean proxy;
    private static String proxyURL;
    private static int port;

    public static int getport() {
        return port;
    }

    public static boolean isProxy() {
        return proxy;
    }

    public static String getProxyURL() {
        return proxyURL;
    }

}