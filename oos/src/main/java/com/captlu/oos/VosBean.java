package com.captlu.oos;

import java.util.List;

public class VosBean {

    private Integer code;
    private Object msg;
    private List<DataDTO> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private Integer id;
        private String uri;
        private Integer flagdelete;
        private String means;
        private String imguri;
        private String md5;
        private String createtime;
        private Integer isnew;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public Integer getFlagdelete() {
            return flagdelete;
        }

        public void setFlagdelete(Integer flagdelete) {
            this.flagdelete = flagdelete;
        }

        public String getMeans() {
            return means;
        }

        public void setMeans(String means) {
            this.means = means;
        }

        public String getImguri() {
            return imguri;
        }

        public void setImguri(String imguri) {
            this.imguri = imguri;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public Integer getIsnew() {
            return isnew;
        }

        public void setIsnew(Integer isnew) {
            this.isnew = isnew;
        }
    }
}
