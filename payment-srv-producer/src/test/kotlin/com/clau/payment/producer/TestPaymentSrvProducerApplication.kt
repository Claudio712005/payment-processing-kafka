package com.clau.payment.producer

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<PaymentSrvProducerApplication>().with(TestcontainersConfiguration::class).run(*args)
}
