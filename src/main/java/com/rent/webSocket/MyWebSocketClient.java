package com.rent.webSocket;

import com.rent.common.utils.MjConfig;
import com.rent.common.utils.RestfulUtil;
import com.rent.services.DoorlockUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import java.net.URI;

/**
 * @author liwenxiang
 * Date:2019/3/1
 * Time:17:08
 */
public class MyWebSocketClient {
    public static WebSocketClient client;
    private static Log logger = LogFactory.getLog(MyWebSocketClient.class);

    public static void createConnect() throws Exception{

        client = new WebSocketClient(new URI(MjConfig.get("websockettype")+"://"+ MjConfig.get("tcpHost")+":"+Integer.parseInt(MjConfig.get("tcpPort"))+"/ws/receiveAsyncReply"), new Draft_6455()) {

            @Override
            public void onOpen(ServerHandshake arg0) {
                logger.debug("Start building a connection...");
                client.send(RestfulUtil.getAuthorization());
            }

            @Override
            public void onMessage(String msg) {
                logger.info("Server request detected..."+msg);
                if(StringUtils.isNotBlank(msg)&&msg.contains("receipt")) {
                    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
                    DoorlockUserService doorlockUserService = (DoorlockUserService) wac.getBean("doorlockUserService");
                    doorlockUserService.updateDoorlockUser(msg);
                }
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                logger.error("客户端发生错误,即将关闭!");
            }

            @Override
            public void onClose(int arg0, String arg1, boolean arg2) {
                logger.debug("WebSocket client closed , Start trying to reconnect...");
                try {
                    MyWebSocketClient.createConnect();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("重新连接失败,请检查网络!");
                }
            }
        };

        client.connect();
        //判断连接状态,
        while (client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
            logger.info("成功建立链接!");
        }
    }

    public static void send(String message) {
        client.send(message);
    }

}
