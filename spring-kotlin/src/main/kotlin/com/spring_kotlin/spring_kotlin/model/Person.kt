package com.spring_kotlin.spring_kotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "person")
data class Person (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String = "",

    @Column(name = "last_name")
    var lastName: String = "",

    @Column
    var address: String = "",

    @Column(nullable = false)
    var gender: String = "",

)