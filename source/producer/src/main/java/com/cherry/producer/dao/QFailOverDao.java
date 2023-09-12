package com.cherry.producer.dao;

import com.cherry.producer.po.QFailOverPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QFailOverDao extends JpaRepository<QFailOverPo, Long> {
}
