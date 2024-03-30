package com.sky.mapper;

import com.sky.entity.AddressBook;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    @Select("select *from sky_take_out.address_book where address_book.user_id=#{userId}")
    List<AddressBook> list(Long userId);

    void insert(AddressBook addressBook);

    AddressBook getById(Long id);

    void update(AddressBook addressBook);

    @Delete("delete from sky_take_out.address_book where address_book.id=#{id}")
    void delete(Integer id);

    @Select("select *from sky_take_out.address_book where address_book.user_id=#{userId} and address_book.is_default=1")
    AddressBook getDefaultAddress(Long userId);
}
