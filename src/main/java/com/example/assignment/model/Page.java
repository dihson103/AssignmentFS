package com.example.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Page {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalItems;

    public Page(Integer pageIndex, Integer pageSize, Integer totalItems){
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.pageIndex = pageIndex;
        totalPage = (int) Math.ceil((double) totalItems / pageSize);
    }

    public Integer getStartIndex(){
        return (pageIndex - 1) * pageSize;
    }

}
