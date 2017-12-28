package com.cngc.simanalysis.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SampleService {
    @Async
    public void asyncSse(SseEmitter sse);
}
