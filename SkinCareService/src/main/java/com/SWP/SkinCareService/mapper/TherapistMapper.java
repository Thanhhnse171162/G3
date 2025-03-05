package com.SWP.SkinCareService.mapper;

import com.SWP.SkinCareService.dto.request.Therapist.TherapistRequest;
import com.SWP.SkinCareService.dto.request.Therapist.TherapistUpdateRequest;
import com.SWP.SkinCareService.dto.response.TherapistResponse;
import com.SWP.SkinCareService.entity.Therapist;
import com.SWP.SkinCareService.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TherapistMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "experienceYears", source = "experienceYears")
    @Mapping(target = "bio", source = "bio")
    Therapist toTherapist(TherapistRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "dob", source = "dob")
    @Mapping(target = "isActive", constant = "true")
    User toUser(TherapistRequest request);

    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "dob", source = "user.dob")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "roles", source = "user.roles")
    TherapistResponse toTheRapistResponse(Therapist therapist);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "experienceYears", source = "experienceYears")
    @Mapping(target = "bio", source = "bio")
    void updateMapper(TherapistUpdateRequest request, @MappingTarget Therapist therapist);
}
