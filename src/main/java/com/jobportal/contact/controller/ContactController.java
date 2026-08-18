package com.jobportal.contact.controller;

import com.jobportal.contact.service.IContactsService;
import com.jobportal.contact.dto.ContactRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final IContactsService contactsService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid ContactRequestDto contact) {
        contactsService.saveContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Request processed successfully");

    }
}
