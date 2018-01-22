package com.paioniu.cyclops.bean;

import java.util.List;

/**
 * Here be dragons
 * Created by Ezio on 2018/1/22 下午2:07
 */

public class ORCRep {

    /**
     * log_id : 3747237184022289204
     * direction : 0
     * words_result_num : 5
     * words_result : [{"words":"新年吸猫狂欢节"},{"words":"日期( Date)2017.12.15-2018.1.1510:30-2200"},{"words":"场馆( Venue)北京中驶世界城"},{"words":"票价( Price)普通票"},{"words":"地址(ADD)北京市朝阳区东大桥10号"}]
     */

    private long log_id;
    private int direction;
    private int words_result_num;
    private List<WordsResultBean> words_result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(int words_result_num) {
        this.words_result_num = words_result_num;
    }

    public List<WordsResultBean> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<WordsResultBean> words_result) {
        this.words_result = words_result;
    }

    public static class WordsResultBean {
        /**
         * words : 新年吸猫狂欢节
         */

        private String words;

        public String getWords() {
            return words;
        }

        public void setWords(String words) {
            this.words = words;
        }
    }
}
