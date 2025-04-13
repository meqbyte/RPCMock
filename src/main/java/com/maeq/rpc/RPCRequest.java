package com.maeq.rpc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RPCRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String methodName;
    private Object[] paramsValue;
    private Class<?>[] paramsType;
}
