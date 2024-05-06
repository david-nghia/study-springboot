package com.tech.springboot.model.entity;

import com.tech.springboot.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> {
    @Column(name = "createdDate", nullable = false, updatable = false)
    @CreatedDate
    protected OffsetDateTime createdDate;

    @Column(name = "modifiedDate")
    @LastModifiedDate
    protected OffsetDateTime modifiedDate;

    @Column(name = "created_by", nullable = false, updatable = false)
    @CreatedBy
    protected T createdBy;

    @Column(name = "modified_by", nullable = false)
    @LastModifiedBy
    protected T modifiedBy;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    protected StatusEnum status = StatusEnum.ACTIVE;

    public void updateAudit(T author) {
        if (author == null) {
            author = (T) "SYSTEM";
        }

        this.createdBy = author;
        this.modifiedBy = author;
        this.createdDate = OffsetDateTime.now();
        this.modifiedDate = OffsetDateTime.now();
        this.status = StatusEnum.ACTIVE;
    }

}
