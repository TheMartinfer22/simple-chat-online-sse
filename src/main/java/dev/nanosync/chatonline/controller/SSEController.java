package dev.nanosync.chatonlineintegration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.Date;

@RestController
@RequestMapping("/sse")
public class SSEController {

    private final Sinks.Many<String> sink;

    public SSEController(Sinks.Many<String> sink) {
        this.sink = sink;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> sseEndpoint() {
        return sink.asFlux();
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody String message) {
        String formattedMessage = "[" + new Date() + "] " + message;
        sink.tryEmitNext(formattedMessage);
    }
}
