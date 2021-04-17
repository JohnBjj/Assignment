package com.back.Backend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.lang.annotation.Documented;
import java.util.Date;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class File {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Long id;

    @Version
    int version;

    private String docType;

    private Integer companyId;

    private Date date;

    private Integer docId;

    private String sign;

    private Integer amount;

}
