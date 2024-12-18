package com.sacak.forumcivillian.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VwUser {
    Long id;
    String firstName;
    String lastName;
    String email;
    String userName;
    String avatar;
    LocalDate birthday;
    String phone;
    String address;
    String about;
}
