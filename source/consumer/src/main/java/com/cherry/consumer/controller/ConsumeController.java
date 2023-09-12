package com.cherry.consumer.controller;

import com.cherry.consumer.dao.QFailOverDao;
import com.cherry.consumer.enumeration.QQqueueStatus;
import com.cherry.consumer.feignclient.CollectorClient;
import com.cherry.consumer.po.QFailOverPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(path = "/consume")
@RestController
public class ConsumeController {

    @Autowired
    private QFailOverDao qFailOverDao;

    @Autowired
    private CollectorClient collectorClient;

    @GetMapping(path = "/notifier")
    public void notifier() {
        List<QFailOverPo> finList = qFailOverDao.findAllByIsConsumedFalse().stream()
                .peek(f -> System.out.println("receive from db"))
                .peek(f -> collectorClient.printer(f.getValue(), QQqueueStatus.FAIL_OVER.name()))
                .map(f -> new QFailOverPo(f.getSeq(), f.getValue(), true))
                .collect(Collectors.toList());

        qFailOverDao.saveAll(finList);
    }


}
