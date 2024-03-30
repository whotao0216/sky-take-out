package com.sky.controller.user;

import com.sky.entity.AddressBook;
import com.sky.result.Result;
import com.sky.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api(tags = "地址簿接口")
@RequestMapping("/user/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @GetMapping("/list")
    @ApiOperation("查找用户所有地址")
    public Result<List<AddressBook>> list(){
       List<AddressBook> list= addressBookService.list();
        return Result.success(list);
    }

    @PostMapping
    @ApiOperation("增加地址")
    public Result addAddressBook(@RequestBody AddressBook addressBook) {
        addressBookService.addAddressBook(addressBook);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查找地址")
    public Result<AddressBook> getAddressById(@PathVariable Long id) {
        AddressBook addressBook=addressBookService.getById(id);
        return Result.success(addressBook);
    }

    @PutMapping
    @ApiOperation("修改地址信息")
    public Result updateAddress(@RequestBody AddressBook addressBook) {
        addressBookService.update(addressBook);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("根据主键id删除地址")
    public Result deleteById(@RequestParam Integer id) {
        addressBookService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/default")
    @ApiOperation("设置默认地址")
    public Result setDefaultAddress(@RequestBody AddressBook addressBook) {
        addressBook.setIsDefault(1);
        addressBookService.update(addressBook);
        return Result.success();
    }

    @GetMapping("/default")
    @ApiOperation("查询默认地址")
    public Result<AddressBook> getDefaultAddress() {
        AddressBook addressBook = addressBookService.getDefaultAddress();
        if(addressBook!=null)
        return Result.success(addressBook);
        else
            return Result.error("没找到默认地址");
    }
}
