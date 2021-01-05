package com.wideoapp.WideoAppDatabase.Web.Mapper;

import com.wideoapp.WideoAppDatabase.Entity.History;
import com.wideoapp.WideoAppDatabase.Web.Model.HistoryDto;
import org.mapstruct.Mapper;

@Mapper
public interface HistoryMapper {
    History historyDtoToHistory(HistoryDto historyDto);
    HistoryDto historyToHistoryDto(History history);
}
