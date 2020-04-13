package com.space.controller;

import com.space.model.ShipType;

@org.springframework.stereotype.Component
public class ShipDisplayOptions {
    private ShipOrder order;
    private Integer pageNumber;
    private Integer pageSize;



    public ShipOrder getOrder() {
        return order;
    }

    public void setOrder(ShipOrder order) {
        this.order = order;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
