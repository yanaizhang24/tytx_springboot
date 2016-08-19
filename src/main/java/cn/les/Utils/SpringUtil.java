package cn.les.Utils;


import cn.les.controller.VeController;
import cn.les.service.VeService;
import cn.les.webSocket.ExampleClient;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;


/**
 * Created by Administrator on 2016/8/12 0012.
 */
public class SpringUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static void doSomething(String message) {
         VeController veService=applicationContext.getBean(VeController.class);
        if(!StringUtils.isEmpty(message)){

            veService.saveData(message);
        }
    }
}
