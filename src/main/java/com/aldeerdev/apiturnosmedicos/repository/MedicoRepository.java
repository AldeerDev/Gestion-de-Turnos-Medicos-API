package com.aldeerdev.apiturnosmedicos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeerdev.apiturnosmedicos.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}
