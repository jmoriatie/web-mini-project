package com.api.miniproject.controller.item;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.dto.item.ItemUpdateAPIDto;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.exception.exceptions.ItemAPIBindException;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/item-api")
public class ItemRESTController {

    private final ItemService service;
    private final ConversionService conversionService;

    @PostMapping(value = "/save")
    ResponseEntity<Object> saveItem(@Validated @RequestBody ItemSaveDto itemSaveDto) {

        Item item = conversionService.convert(itemSaveDto, Item.class);
        Item saveItem = service.saveItem(item);

        return new ResponseEntity<>(saveItem, HttpStatus.OK);
    }

    @GetMapping( "/items")
    ResponseEntity<Object> findAll(){
        List<Item> allItemList = service.findAll();
        for (Item item : allItemList) {
            System.out.println("item = " + item);
        }
        return ResponseEntity.ok(allItemList);
    }

    @GetMapping("/item")
    ResponseEntity<Object> findById(@RequestParam Long id){
        Item findItem = service.findById(id);
        return new ResponseEntity<>(findItem, HttpStatus.OK);
    }

    @PatchMapping("/update")
    ResponseEntity<Object> updateItem(@RequestParam Long id,
                                      @Validated @RequestBody ItemUpdateAPIDto itemUpdateAPIDto, BindingResult bindingResult) throws ItemAPIBindException {

        if(bindingResult.hasErrors()){ // bindingException 에러들 받아서 message source 에서 설정해놓은 메세지 출력
            throw new ItemAPIBindException(bindingResult);
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

        service.deleteItem(id);
        return new ResponseEntity<>(findItem, HttpStatus.OK);
    }
}
