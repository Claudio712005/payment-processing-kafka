package com.clau.payment.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentSrvConsumerApplication

fun main(args: Array<String>) {
    runApplication<PaymentSrvConsumerApplication>(*args)
}
