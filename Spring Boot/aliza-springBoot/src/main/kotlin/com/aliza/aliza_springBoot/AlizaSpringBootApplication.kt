package com.aliza.aliza_springBoot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class AlizaSpringBootApplication

fun main(args: Array<String>) {
	runApplication<AlizaSpringBootApplication>(*args)
}
