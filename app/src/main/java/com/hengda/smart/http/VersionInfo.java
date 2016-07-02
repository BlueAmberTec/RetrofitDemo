package http;

/**
 * @author WZQ
 * @version V1.0
 * @Description ${todo}
 * @Email :wzq_steady@126.com
 * @date 2016/7/1 16:31
 * @update (date)
 */
public class VersionInfo {

    /**
     * status : 2002
     * msg : 有新的版本
     * versioninfo : {"version_no":"2.0","version_name":"第二版","version_url":"http:/www.baidu.com","version_logo":"爱更不更"}
     */

    private String status;
    private String msg;
    /**
     * version_no : 2.0
     * version_name : 第二版
     * version_url : http:/www.baidu.com
     * version_logo : 爱更不更
     */

    private VersioninfoBean versioninfo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public VersioninfoBean getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(VersioninfoBean versioninfo) {
        this.versioninfo = versioninfo;
    }

    public static class VersioninfoBean {
        private String version_no;
        private String version_name;
        private String version_url;
        private String version_logo;

        public String getVersion_no() {
            return version_no;
        }

        public void setVersion_no(String version_no) {
            this.version_no = version_no;
        }

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public String getVersion_url() {
            return version_url;
        }

        public void setVersion_url(String version_url) {
            this.version_url = version_url;
        }

        public String getVersion_logo() {
            return version_logo;
        }

        public void setVersion_logo(String version_logo) {
            this.version_logo = version_logo;
        }

        @Override
        public String toString() {
            return "VersioninfoBean{" +
                    "version_no='" + version_no + '\'' +
                    ", version_name='" + version_name + '\'' +
                    ", version_url='" + version_url + '\'' +
                    ", version_logo='" + version_logo + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "VersionInfo{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", versioninfo=" + versioninfo.toString() +
                '}';
    }
}
