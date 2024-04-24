package com.tech.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_courses")
public class Course extends BaseEntity<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description", length = 2000)
    private String description;
    @Column(name = "start_date")
    private OffsetDateTime startDate;
    @Column(name = "end_date")

    private OffsetDateTime endDate;
    @Column(name = "duration")
    private Duration duration;
    @Column(name = "tuition_fee")
    private BigDecimal tuitionFee;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments = new HashSet<>();
}
