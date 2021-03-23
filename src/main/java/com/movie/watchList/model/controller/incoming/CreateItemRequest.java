package com.movie.watchList.model.controller.incoming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class CreateItemRequest {

    public String contentId;
    public String itemType;
    public Long createTime;
    public String name;
}
