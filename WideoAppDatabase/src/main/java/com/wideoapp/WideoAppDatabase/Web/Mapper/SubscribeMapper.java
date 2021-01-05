package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.Subscribe;
import com.wideoapp.WideoAppDatabase.Web.Model.SubscribeDto;
import org.mapstruct.Mapper;

@Mapper
public interface SubscribeMapper {
    Subscribe subscribeDtoToSubscribe(SubscribeDto subscribeDto);
    SubscribeDto subscribeToSubscribeDto(Subscribe subscribe);
}
