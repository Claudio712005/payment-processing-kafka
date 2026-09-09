package com.clau.payment.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaymentSrvProducerApplication

fun main(args: Array<String>) {
    runApplication<PaymentSrvProducerApplication>(*args)
}
