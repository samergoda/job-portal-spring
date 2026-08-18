package com.jobportal.contact.service;

import com.jobportal.contact.dto.ContactRequestDto;

public interface IContactsService {
    void saveContact(ContactRequestDto contactRequestDto);

}
