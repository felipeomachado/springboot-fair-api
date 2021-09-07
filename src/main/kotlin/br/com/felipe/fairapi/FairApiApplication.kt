package br.com.felipe.fairapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FairApiApplication

fun main(args: Array<String>) {
	runApplication<FairApiApplication>(*args)
}
