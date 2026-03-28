package com.arturk.service;

import com.arturk.model.entity.NoticeEntity;
import com.arturk.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NoticesService {

    private final NoticeRepository noticeRepository;

    public ResponseEntity<List<NoticeEntity>> getActiveNotices() {
        List<NoticeEntity> notices = noticeRepository.findAllActiveNotices();

        if (notices != null) {
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                    .body(notices);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
