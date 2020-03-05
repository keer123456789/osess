package com.ibt.osess.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;
import java.util.TreeMap;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.pojo
 * @Author: keer
 * @CreateTime: 2020-03-05 09:20
 * @Description: 修改BigChainDB driver的metadata类
 */
public class MetaData {

    /**
     * The id.
     */
    @SerializedName("id")
    private String id;

    /**
     * The metadata.
     */
    @SerializedName("metadata")
    private Map<String, Object> metadata = new TreeMap<String, Object>();

    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the metadata.
     *
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetaData(String key, Object value) {
        this.metadata.put(key, value);
    }

    /**
     * 新增方法，用于设置metadata数据
     *
     * @param map
     */
    public void setMetadata(Map map) {
        this.metadata = map;
    }
}
