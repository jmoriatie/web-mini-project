package com.api.miniproject.controller;

import com.api.miniproject.domain.Item;
import com.api.miniproject.dto.item.ItemSaveDto;
import com.api.miniproject.service.item.ItemService;
import com.api.miniproject.util.StatusEnum;
import com.api.miniproject.util.StatusMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
            log.error("bindingResult error={}", bindingResult.getAllErrors());
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
//        return checkNullAndCreateResponseEntity(saveItem);
    }

    @GetMapping( "/items")
    ResponseEntity<StatusMessage> findAll(){
        return createResponseEntity(StatusEnum.OK, "전체 아이템 출력", service.findAll());
    }

    @GetMapping("/item")
    ResponseEntity<StatusMessage> findById(@RequestParam Long id){
        Item findItem = service.findById(id);
        return checkNullAndCreateResponseEntity(findItem);
    }

    @PatchMapping("/update")
    ResponseEntity<StatusMessage> updateItem(@RequestParam Long id, @RequestBody Item item){
        Item findItem = service.findById(id);
        if(findItem != null){
            service.updateItem(id, item);
            Item updateItem = service.findById(id);
            log.debug("업데이트 아이템={}", updateItem);
            return createResponseEntity(StatusEnum.OK, "정상 업데이트", updateItem);
        }else{
            return createResponseEntity(StatusEnum.BAD_REQUEST, "비정상 요청", null);
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<StatusMessage> deleteItem(Long id){
        Item findItem = service.findById(id);
        log.debug("삭제할 아이템={}", findItem);
        if(findItem != null){
            service.deleteItem(id);
            return createResponseEntity(StatusEnum.OK, "정상 삭제", findItem);
        }else{
            return createResponseEntity(StatusEnum.BAD_REQUEST, "비정상 요청", null);
        }
    }

    /***
     * StatusMessage 에 넣어서 responseEntity 만들어 내는 객체
     * @param statusCode
     * @param message
     * @return
     */
    private ResponseEntity<StatusMessage> createResponseEntity(StatusEnum statusCode, String message, Object data) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType( new MediaType("application","json") );

        StatusMessage statusMessage = new StatusMessage();
        statusMessage.setStatus(statusCode);
        statusMessage.setMessage(message);
        statusMessage.setData(data);

        return new ResponseEntity<>(statusMessage, httpHeaders, statusCode.statusCode);
    }

    /***
     * 객체를 받아 createResponseEntity 전 예외처리
     * @param object
     * @return
     */
    private ResponseEntity<StatusMessage> checkNullAndCreateResponseEntity(Object object) {
        ResponseEntity<StatusMessage> result;
        if(object != null) {
            result = createResponseEntity(StatusEnum.OK, "정상 요청", object);
        }else {
            result = createResponseEntity(StatusEnum.BAD_REQUEST, "비정상 요청", null);
        }
        return result;
    }
}
