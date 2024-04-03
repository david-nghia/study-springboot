package com.tech.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data

@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_description")
    private String courseDescription;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;
}
