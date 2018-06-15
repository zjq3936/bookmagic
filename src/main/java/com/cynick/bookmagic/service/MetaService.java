package com.cynick.bookmagic.service;

/**
 * 分类信息service接口
 * Created by BlueT on 2017/3/17.
 */
public interface MetaService {
    /**
     * 保存多个项目
     * @param cid
     * @param names
     * @param type
     */
    void saveMetas(Integer cid, String names, String type);

}
