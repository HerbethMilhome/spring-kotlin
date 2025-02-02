package com.spring_kotlin.spring_kotlin.services

import com.spring_kotlin.spring_kotlin.controller.PersonController
import com.spring_kotlin.spring_kotlin.data.vo.v1.PersonVO
import com.spring_kotlin.spring_kotlin.data.vo.v2.PersonVO as PersonVOV2
import com.spring_kotlin.spring_kotlin.exception.ResourceNotFoundException
import com.spring_kotlin.spring_kotlin.mapper.DozerMapper
import com.spring_kotlin.spring_kotlin.mapper.custom.PersonMapper
import com.spring_kotlin.spring_kotlin.model.Person
import com.spring_kotlin.spring_kotlin.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger



@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var mapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findById(id: Long): PersonVO {
        logger.info("find one person woth ID $id")
        val person = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
        val persolVo: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(persolVo.key).withSelfRel()
        persolVo.add(withSelfRel)
        return persolVo
    }

    fun findAll(): List<PersonVO> {
        logger.info("Find all person.")
        val persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO::class.java)
        for (person in persons) {
            val withSelfRel = linkTo(PersonController::class.java).slash(person.key).withSelfRel()
            person.add(withSelfRel)
        }

        return persons
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("create new person. ${person.firstName}")
        val entity: Person = DozerMapper.parseObject(person, Person::class.java)
        val persolVo: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(persolVo.key).withSelfRel()
        persolVo.add(withSelfRel)
        return persolVo
    }

    fun createV2(person: PersonVOV2): PersonVOV2 {
        logger.info("create new person. ${person.firstName}")
        val entity: Person = mapper.mapVOToEntity(person)
        return mapper.mapEntityToVO(repository.save(entity))
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("update one person. ${person.firstName}")
        val entity = repository.findById(person.key)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val persolVo: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        val withSelfRel = linkTo(PersonController::class.java).slash(persolVo.key).withSelfRel()
        persolVo.add(withSelfRel)
        return persolVo
    }

    fun delete(id: Long) {
        logger.info("Delete one person with ID ${id}")
        val entity = repository.findById(id)
            .orElseThrow({ResourceNotFoundException("No records found for this ID!")})
        repository.delete(entity)
    }

}

