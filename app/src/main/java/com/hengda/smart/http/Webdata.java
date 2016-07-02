package http;

import java.util.List;

/**
 * @author WZQ
 * @version V1.0
 * @Description ${todo}
 * @Email :wzq_steady@126.com
 * @date 2016/6/29 11:22
 * @update (date)
 */
public class Webdata {


    /**
     * code : 001
     * data : [{"id":"1","app_id":"4","app_name":"V2.0.2","content":"更新了地图定位图标","url":"http://hengdawb-update.oss-cn-hangzhou.aliyuncs.com/KZG_1024%2FHD_Helper1024_V2.0.2.apk","datetime":"1466733813"}]
     */

    private String code;
    /**
     * id : 1
     * app_id : 4
     * app_name : V2.0.2
     * content : 更新了地图定位图标
     * url : http://hengdawb-update.oss-cn-hangzhou.aliyuncs.com/KZG_1024%2FHD_Helper1024_V2.0.2.apk
     * datetime : 1466733813
     */

    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String app_id;
        private String app_name;
        private String content;
        private String url;
        private String datetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", app_id='" + app_id + '\'' +
                    ", app_name='" + app_name + '\'' +
                    ", content='" + content + '\'' +
                    ", url='" + url + '\'' +
                    ", datetime='" + datetime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Webdata{" +
                "code='" + code + '\'' +
                ", data=" + data +
                '}';
    }
}
