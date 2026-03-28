package com.arturk.controller;

import com.arturk.model.entity.NoticeEntity;
import com.arturk.repository.NoticeRepository;
import com.arturk.service.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticesService noticesService;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeEntity>> getActiveNotices() {
        return noticesService.getActiveNotices();
    }

}
