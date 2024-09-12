package com.aliza.alizaShop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
@SpringBootApplication
class AlizaShopApplication

fun main(args: Array<String>) {
	runApplication<AlizaShopApplication>(*args)
}
