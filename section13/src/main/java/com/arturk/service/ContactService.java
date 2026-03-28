package com.arturk.service;

import com.arturk.model.entity.ContactEntity;
import com.arturk.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactEntity saveContactInquiryDetails(ContactEntity contactEntity) {
        contactEntity.setContactId(getServiceReqNumber());
        contactEntity.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contactEntity);
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}
