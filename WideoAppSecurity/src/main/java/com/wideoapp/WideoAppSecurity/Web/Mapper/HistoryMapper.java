package com.wideoapp.WideoAppSecurity.Web.Mapper;

import com.wideoapp.WideoAppSecurity.Entity.History;
import com.wideoapp.WideoAppSecurity.Web.Model.HistoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface HistoryMapper {
    History historyDtoToHistory(HistoryDto historyDto);
    HistoryDto historyToHistoryDto(History history);
}
