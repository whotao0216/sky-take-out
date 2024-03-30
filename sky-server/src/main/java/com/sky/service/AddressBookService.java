package com.sky.service;

import com.sky.entity.AddressBook;

import java.util.List;

public interface AddressBookService {
    List<AddressBook> list();

    void addAddressBook(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    void deleteById(Integer id);

    AddressBook getDefaultAddress();
}
