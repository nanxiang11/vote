package com.nx.longtm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommonResult<T> {
    private int code;
    private T data;
    private int total;

    public CommonResult(int code, T data){
        this.code = code;
        this.data = data;
    }

    public CommonResult(int code, T data, int total){
        this.code = code;
        this.data = data;
        this.total = total;
    }
}
