package com.justin.system.entity.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReqUpdateCommentDTO {

    @NotNull
    private Long id;

    private int isCancel;
}
