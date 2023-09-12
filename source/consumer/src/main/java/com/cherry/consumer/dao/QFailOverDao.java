package com.cherry.consumer.dao;

import com.cherry.consumer.po.QFailOverPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QFailOverDao extends JpaRepository<QFailOverPo, Long> {

    List<QFailOverPo> findAllByIsConsumedFalse();

}
