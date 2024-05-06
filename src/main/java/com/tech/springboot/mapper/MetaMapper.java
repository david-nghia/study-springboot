package com.tech.springboot.mapper;

import com.tech.springboot.lending.model.Meta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MetaMapper {
    MetaMapper INSTANCE = Mappers.getMapper(MetaMapper.class);

    Meta toMeta(Integer count, Integer limit, Integer offset, Integer totalItem);
}
