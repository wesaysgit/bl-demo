package com.zld.dao.mapper.base;

import com.zld.dao.models.LsNosenceTransferDetailsTb20241231;

public interface LsNosenceTransferDetailsTb20241231Mapper {
    int deleteByPrimaryKey(Long id);

    int insert(LsNosenceTransferDetailsTb20241231 record);

    int insertSelective(LsNosenceTransferDetailsTb20241231 record);

    LsNosenceTransferDetailsTb20241231 selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LsNosenceTransferDetailsTb20241231 record);

    int updateByPrimaryKey(LsNosenceTransferDetailsTb20241231 record);
}