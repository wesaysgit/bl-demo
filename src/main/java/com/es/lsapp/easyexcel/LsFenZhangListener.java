package com.es.lsapp.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class LsFenZhangListener extends AnalysisEventListener<LsFenZhangDto> {

    private final List<LsFenZhangDto> vos = new ArrayList<>();

    @Override
    public void invoke(LsFenZhangDto dto, AnalysisContext analysisContext) {
        vos.add(dto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<LsFenZhangDto> getVos() {
        return vos;
    }
}
