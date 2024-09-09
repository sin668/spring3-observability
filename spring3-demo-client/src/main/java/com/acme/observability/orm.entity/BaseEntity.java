package com.acme.observability.orm.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 和租户相关的基类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "date_created", fill = FieldFill.INSERT)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateCreated;

    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Long creatorId;

    @TableField(value = "date_updated", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateUpdated;

    @TableField(value = "updator_id", fill = FieldFill.INSERT_UPDATE)
    private Long updatorId;

    @TableField(value = "date_deleted")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date dateDeleted;

    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

    @TableField(value = "tenant_code", fill = FieldFill.INSERT)
    private String tenantCode;
}