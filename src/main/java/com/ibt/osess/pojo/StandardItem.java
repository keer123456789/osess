package com.ibt.osess.pojo;

/**
 * @BelongsProject: osess
 * @BelongsPackage: com.ibt.osess.pojo
 * @Author: keer
 * @CreateTime: 2020-03-04 21:41
 * @Description: 评分和权重
 */
public class StandardItem {
    /**
     * 权重
     */
    private String weight;
    /**
     * 评分
     */
    private String score;

    public StandardItem() {
    }

    public String getWeight() {
        return weight;
    }

    public String getScore() {
        return score;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Standard{" +
                "weight='" + weight + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
