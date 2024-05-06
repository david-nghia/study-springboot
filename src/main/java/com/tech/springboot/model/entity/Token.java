package com.tech.springboot.model.entity;

import com.tech.springboot.enums.TokenTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_tokens")
public class Token extends BaseEntity<String> {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "access_token", unique = true)
    private String accessToken;
    @Column(name = "refresh_token", unique = true)
    private String refreshToken;
    @Column(name = "token_type")
    @Enumerated(EnumType.STRING)
    private TokenTypeEnum tokenTypeEnum = TokenTypeEnum.BEARER;
    @Column(name = "revoked")
    private Boolean revoked = Boolean.FALSE;
    @Column(name = "expired")
    private Boolean expired = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
