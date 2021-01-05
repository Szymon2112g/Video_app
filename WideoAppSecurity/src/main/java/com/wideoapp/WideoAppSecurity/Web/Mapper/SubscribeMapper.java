package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import com.wideoapp.WideoAppSecurity.Web.Model.SubscribeDto;
import org.mapstruct.Mapper;

@Mapper
public interface SubscribeMapper {
    Subscribe subscribeDtoToSubscribe(SubscribeDto subscribeDto);
    SubscribeDto subscribeToSubscribeDto(Subscribe subscribe);
}
