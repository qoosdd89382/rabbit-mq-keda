package com.cherry.producer.po;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Q_FAIL_OVER")
public class QFailOverPo {

    @Id
    @Column(name = "SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "IS_CONSUMED")
    private Boolean isConsumed;

}
