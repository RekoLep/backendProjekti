package com.backendprojekti.yolento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AsuntoRepository extends JpaRepository<Asunto, Long> {
    
}
