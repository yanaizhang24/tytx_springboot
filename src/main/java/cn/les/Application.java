package cn.les;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */


import cn.les.controller.VeController;
import cn.les.entity.VeLocation;
import cn.les.webSocket.ExampleClient;
import org.java_websocket.drafts.Draft_17;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.net.URISyntaxException;


@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:application.properties")
public class Application {
    public static void main(String[] args) {
        //SpringApplication.run(TestController.class);

        SpringApplication.run(Application.class);
        System.out.println("jkhgjhjh");
//        ExampleClient c = null; // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
//        try {
//            c = new ExampleClient( new URI( "ws://192.168.40.240:8880/IoT_Harbor/websocketInterface" ), new Draft_17() );
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        c.connect();
    }
}
