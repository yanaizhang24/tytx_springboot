package cn.les;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */


import cn.les.Utils.SpringUtil;
import cn.les.controller.VeController;
import cn.les.entity.VeLocation;
import cn.les.webSocket.ExampleClient;
import org.java_websocket.drafts.Draft_17;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
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

        final ApplicationContext applicationContext=SpringApplication.run(Application.class);
        //获取上下文
        System.out.println("SpringUtil获取上下文");
        SpringUtil.setApplicationContext(applicationContext);
        ExampleClient c = null; // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts
        try {
            //需要用到draft_17以上
            c = new ExampleClient( new URI( "ws://192.168.40.240:8880/IoT_Harbor/websocketInterface" ), new Draft_17() );
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //websocket建立连接
        c.connect();
    }
}
