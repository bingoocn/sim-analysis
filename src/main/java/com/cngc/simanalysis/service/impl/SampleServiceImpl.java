package com.cngc.simanalysis.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.cngc.simanalysis.service.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
    protected Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
    @Override
    public void asyncSse(SseEmitter sse) {
        for(int i=0; i<5; i++) {
            try {
                sse.send(i);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                logger.debug("线程id为{}的sse发送信息失败",Thread.currentThread().getId());
            }
        }
        try {
            sse.send("end");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sse.complete();
    }

}
