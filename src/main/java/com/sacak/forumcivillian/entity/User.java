package com.sacak.forumcivillian.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sacak.forumcivillian.entity.enums.EUserRank;
import com.sacak.forumcivillian.entity.enums.EUserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String firstName;
    String lastName;
    @Column(unique = true, nullable = false)
    String email;
    @Column(unique = true, nullable = false)
    String userName;
    @Column(unique = true, nullable = false)
    String password;
    @Enumerated(EnumType.STRING)
    EUserRole userRole;
    @Builder.Default
    Boolean isVerified = false;

    String avatar;
    LocalDate birthday;
    String phone;
    String address;
    String about;
    @Builder.Default
    @Enumerated(EnumType.STRING)
    EUserRank userRank = EUserRank.PEASANT;
    int totalComments;

}
