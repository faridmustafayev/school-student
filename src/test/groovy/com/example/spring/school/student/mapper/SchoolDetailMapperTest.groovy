package com.example.spring.school.student.mapper

import com.example.spring.school.student.dao.entity.SchoolDetailEntity
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class SchoolDetailMapperTest extends Specification {
    EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "BuildSchoolDetailResponse"() {
        given:
        def schoolDetailEntity = random.nextObject(SchoolDetailEntity)

        when:
        def schoolDetailResponse = SchoolDetailMapper.SCHOOL_DETAIL_MAPPER.buildSchoolDetailResponse(schoolDetailEntity)

        then:
        schoolDetailResponse.principalName == schoolDetailEntity.principalName
        schoolDetailResponse.status == schoolDetailEntity.status
        schoolDetailResponse.createdAt == schoolDetailEntity.createdAt
        schoolDetailResponse.updatedAt == schoolDetailEntity.updatedAt
    }

}
