package edu.restaurant.datasource.utils;

import edu.restaurant.datasource.entities.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CustomOrderStatusEnumConverter implements AttributeConverter<OrderStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderStatus customEnum) {
        return customEnum.getCode();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Integer code) {
        return OrderStatus.fromCode(code);
    }
}
