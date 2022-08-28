package com.api.miniproject.util.validation;

import com.api.miniproject.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        // item == clazz    해당 클래스가 item 클래스인지
        // item = subItem   Item 자식 클래스인지
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;

        // Errors : bindingResult의 부모 클래스
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName", "required");
        }

        if(item.getPrice() == null || item.getPrice() <= 10){
            errors.rejectValue("price", "range", new Object[]{10}, null);
        }

        if(item.getQuantity() == null || item.getQuantity() <= 0){
            errors.rejectValue("quantity", "min");
        }
        if(errors.hasErrors()){
            log.info("item validation has Error = {}", item);
        }
    }
}
