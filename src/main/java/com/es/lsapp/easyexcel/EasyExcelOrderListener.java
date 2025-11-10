package com.es.lsapp.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelOrderListener extends AnalysisEventListener<LsChannelSwitchDto> {

    private final List<LsChannelSwitchDto> list = new ArrayList<>();

    @Override
    public void invoke(LsChannelSwitchDto dto, AnalysisContext analysisContext) {
        list.add(dto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<LsChannelSwitchDto> getList() {
        return list;
    }
}
