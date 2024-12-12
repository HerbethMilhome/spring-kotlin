package com.spring_kotlin.spring_kotlin.services

import com.spring_kotlin.spring_kotlin.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("find one person.")
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Herbeth"
        person.lastName = "Milhome"
        person.address = "Bem ali assim"
        person.gender = "M"
        return person
    }

    fun findAll(): MutableList<Person> {
        logger.info("find all person.")

        val persons: MutableList<Person> = ArrayList()

        for (i in 0 .. 7) {
            val person = mockPerson(i)
            persons.add(person)
        }

        return persons
    }

    fun create(person: Person) = person

    fun update(person: Person) = person

    fun delete(id: Long) {

    }

    private fun mockPerson(i: Int): Person {

        val person = Person()

        person.id = counter.incrementAndGet()
        person.firstName = "Herbeth $i"
        person.lastName = "Milhome $i"
        person.address = "Bem ali assim"
        person.gender = "M"

        return person

    }
}

