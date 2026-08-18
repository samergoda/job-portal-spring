package com.jobportal.contact.service.impl;

import com.jobportal.contact.repository.ContactRepository;
import com.jobportal.contact.service.IContactsService;
import com.jobportal.contact.dto.ContactRequestDto;
import com.jobportal.contact.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ContactsServiceImpl implements IContactsService {
    private final ContactRepository contactRepository;
    @Override
    public void saveContact(ContactRequestDto contactRequestDto) {
     contactRepository.save(transformContactRequestDtoToContact(contactRequestDto));
    }

    private Contact transformContactRequestDtoToContact(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactRequestDto, contact);
//        contact.setCreatedAt(Instant.now());
//        contact.setCreatedBy("System");
        contact.setStatus("NEW");
        return contact;

    }
}
