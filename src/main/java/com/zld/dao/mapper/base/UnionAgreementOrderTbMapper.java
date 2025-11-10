package com.zld.dao.mapper.base;

import com.zld.dao.models.UnionAgreementOrderTb;

public interface UnionAgreementOrderTbMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UnionAgreementOrderTb record);

    int insertSelective(UnionAgreementOrderTb record);

    UnionAgreementOrderTb selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UnionAgreementOrderTb record);

    int updateByPrimaryKey(UnionAgreementOrderTb record);
}