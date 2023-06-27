package com.example.springweb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsertypelinkRepository extends JpaRepository<Usertypelink, String> {

}