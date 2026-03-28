package com.arturk.controller;

import com.arturk.model.entity.ContactEntity;
import com.arturk.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/contact")
    public ContactEntity saveContactInquiryDetails(@RequestBody ContactEntity contact) {
        return contactService.saveContactInquiryDetails(contact);
    }
}
