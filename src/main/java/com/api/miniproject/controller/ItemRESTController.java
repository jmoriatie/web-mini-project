package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.dto.item.ItemUpdateAPIDto;
import com.api.miniproject.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/item-api")
public class ItemRESTController {

    //TODO: 추후 리펙토링 필요 1) 검증

    private final ItemService service;
    private final ConversionService conversionService;

    @PostMapping("/save")
    ResponseEntity<Object> saveItem(@Validated @RequestBody ItemSaveDto itemSaveDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("bindingResult error={}", bindingResult);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        if(itemSaveDto.getUserId() == null){
            //error 리턴하도록
            throw new IllegalArgumentException();
//            return new ResponseEntity<>(new , HttpStatus.BAD_REQUEST);
        }

        Item item = conversionService.convert(itemSaveDto, Item.class);
        Item saveItem = service.saveItem(item);

        return new ResponseEntity<>(saveItem, HttpStatus.OK);
    }

    @GetMapping( "/items")
    ResponseEntity<Object> findAll(){
        List<Item> allItemList = service.findAll();
        return new ResponseEntity<>(allItemList, HttpStatus.OK);
    }

    @GetMapping("/item")
    ResponseEntity<Object> findById(@RequestParam Long id){
        Item findItem = service.findById(id);
        return new ResponseEntity<>(findItem, HttpStatus.OK);
    }

    @PatchMapping("/update")
    ResponseEntity<Object> updateItem(@RequestParam Long id, @Validated @RequestBody ItemUpdateAPIDto itemUpdateAPIDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.error("bindingResult error={}", bindingResult);
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        Item item = conversionService.convert(itemUpdateAPIDto, Item.class);
        service.updateItem(id, item);

        Item updateItem = service.findById(id);
        log.debug("업데이트 아이템={}", updateItem);
        return new ResponseEntity<>(updateItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    ResponseEntity<Object> deleteItem(Long id){
        Item findItem = service.findById(id);
        log.debug("삭제할 아이템={}", findItem);
        if(findItem != null){
            service.deleteItem(id);
            return new ResponseEntity<>(findItem, HttpStatus.OK);
        }else{
            throw new IllegalArgumentException("잘못된 요청");
        }
    }
}
