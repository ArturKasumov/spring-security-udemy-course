package com.arturk.controller;

import com.arturk.model.entity.NoticeEntity;
import com.arturk.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticesService noticesService;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeEntity>> getNotices() {
        return noticesService.getActiveNotices();
    }

}
