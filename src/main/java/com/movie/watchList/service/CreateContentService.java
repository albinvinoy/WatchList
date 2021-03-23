package com.movie.watchList.service;

import com.movie.watchList.client.handler.MovieIdClientHandler;
import com.movie.watchList.model.controller.incoming.CreateItemRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateContentService {

    @Autowired
    private final MovieIdClientHandler movieIdClientHandler;

    public Mono<Boolean> addNewContents(CreateItemRequest newData) {
        // make http call to confirm and save it to db
        return movieIdClientHandler.getDetails(newData)
                .onErrorResume(e -> {
                    log.error("Suppressing error, sending back {}", e.getMessage());
                    return Mono.empty();
                })
                .flatMap(r -> validateServiceId(String.valueOf(r.getId()), newData))
                .defaultIfEmpty(false)
                .flatMap((a) -> {
                    log.info("Write info to db");
                    return Mono.just(a);
                });
    }

    public Mono<Boolean> validateServiceId(String id, CreateItemRequest data) {
        return Mono.fromCallable(() -> id.equalsIgnoreCase(data.getContentId()));
    }

}
