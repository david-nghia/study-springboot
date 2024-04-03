package com.tech.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Data

@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "status")
    private String status;

    @Column(name = "enrollment_date")
    private Date enrollmentDate;

    @Column(name = "completion_date")
    private Date completionDate;

    @Column(name = "note")
    private String note;
}
