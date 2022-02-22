package com.ratepay.challenge.entity;

import com.ratepay.challenge.model.enums.Priority;
import com.ratepay.challenge.model.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Bugs")
@Getter
@Setter
public class Bug {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "issueId", columnDefinition = "VARCHAR(255)")
    private UUID issueId;

    private String title;

    private String description;


    private UUID developerId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;
}
