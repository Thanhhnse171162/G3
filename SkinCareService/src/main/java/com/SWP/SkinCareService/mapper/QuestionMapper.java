package com.SWP.SkinCareService.mapper;

import com.SWP.SkinCareService.dto.request.Quiz.QuestionRequest;
import com.SWP.SkinCareService.dto.response.Quiz.QuestionResponse;
import com.SWP.SkinCareService.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {
    @Mapping(target = "quiz", ignore = true)
    @Mapping(target = "text", source = "text")
    @Mapping(target = "type", source = "type")
    Question toQuestion(QuestionRequest request);
    QuestionResponse toResponse(Question Question);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "answers", ignore = true)
    @Mapping(target = "text", source = "text")
    @Mapping(target = "type", source = "type")
    void updateQuestion(@MappingTarget Question question, QuestionRequest request);

}
