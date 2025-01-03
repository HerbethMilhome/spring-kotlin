package com.spring_kotlin.spring_kotlin.services

import com.spring_kotlin.spring_kotlin.exception.ResourceNotFoundException
import com.spring_kotlin.spring_kotlin.model.Person
import com.spring_kotlin.spring_kotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): Person {
        logger.info("find one person.")
        return repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
    }

    fun findAll(): List<Person> {
        logger.info("find all person.")
        return repository.findAll()
    }

    fun create(person: Person): Person {
        logger.info("create new person. ${person.firstName}")
        return repository.save(person)
    }

    fun update(person: Person): Person {
        logger.info("update one person. ${person.firstName}")
        val entity = repository.findById(person.id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    fun delete(id: Long) {
        logger.info("Delete one person with ID ${id}")
        val entity = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
        repository.delete(entity)
    }

}

