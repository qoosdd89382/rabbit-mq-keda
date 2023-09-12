package com.cherry.consumer.po;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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

    @Version
    private int version;

}
