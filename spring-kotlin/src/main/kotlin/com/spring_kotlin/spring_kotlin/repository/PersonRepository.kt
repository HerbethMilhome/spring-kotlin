package com.spring_kotlin.spring_kotlin.repository

import com.spring_kotlin.spring_kotlin.model.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long?> {
}