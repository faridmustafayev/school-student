package com.example.spring.school.student.service

import com.example.spring.school.student.service.abstraction.MathService
import com.example.spring.school.student.service.concrete.MathServiceHandler
import spock.lang.Specification

class MathServiceTest extends Specification {

    def "TestSum"() {
        given:
        def firstNumber = a
        def secondNumber = b
        def sum = expected

        when:
        MathService mathService = new MathServiceHandler()
        def actual = mathService.sum(firstNumber, secondNumber)

        then:
        actual == sum

        where:
        a|b|expected
        3 | 4 | 7
        -2| -3 | -5
        3 | -4 | -1
    }

}
