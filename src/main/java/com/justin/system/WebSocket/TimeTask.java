package com.justin.system.WebSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

@EnableScheduling
@Component
public class TimeTask {

    // 代码涞源 https://www.jianshu.com/p/62132f605669
    private static Logger logger = LoggerFactory.getLogger(TimeTask.class);

    @Scheduled(cron = "0/10 * * * * ?")
    public void test() {
        logger.info("定时任务执行中...");
        CopyOnWriteArraySet<CustomWebSocket> webSocketSet = CustomWebSocket.getWebSocketSet();
        int i = 0;
        webSocketSet.forEach(item -> {
            try {
                item.sendMessage("定时消息 " + new Date().toInstant());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
