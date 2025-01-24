package com.spring_kotlin.spring_kotlin.mapper.custom

import com.spring_kotlin.spring_kotlin.data.vo.v2.PersonVO
import com.spring_kotlin.spring_kotlin.model.Person
import org.springframework.stereotype.Service
import java.util.Date

@Service
class PersonMapper {

    fun mapEntityToVO(person: Person): PersonVO {
        val vo  = PersonVO()
        vo.id = person.id
        vo.firstName = person.firstName
        vo.birthDay = Date()
        vo.lastName = person.lastName
        vo.address = person.address
        vo.gender = person.gender
        return vo
    }

    fun mapVOToEntity(person: PersonVO): Person {
        val entity  = Person()
        entity.id = person.id
        entity.firstName = person.firstName
//        entity.birthDay = person.birthDay
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender
        return entity
    }

}