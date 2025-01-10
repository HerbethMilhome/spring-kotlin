package com.spring_kotlin.spring_kotlin.services

import com.spring_kotlin.spring_kotlin.data.vo.v1.PersonVO
import com.spring_kotlin.spring_kotlin.exception.ResourceNotFoundException
import com.spring_kotlin.spring_kotlin.mapper.DozerMapper
import com.spring_kotlin.spring_kotlin.model.Person
import com.spring_kotlin.spring_kotlin.repository.PersonRepository
import org.hibernate.engine.internal.ManagedTypeHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): PersonVO {
        logger.info("find one person.")
        val person = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun findAll(): List<PersonVO> {
        logger.info("find all person.")
        val persons = repository.findAll()
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("create new person. ${person.firstName}")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("update one person. ${person.firstName}")
        val entity = repository.findById(person.id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long) {
        logger.info("Delete one person with ID ${id}")
        val entity = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
        repository.delete(entity)
    }

}

