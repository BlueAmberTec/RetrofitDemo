package com.hengda.smart.versioncheck;

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
     * versionInfo : {"versionNo":"2.0","versionName":"第二版","versionUrl":"http:/www.baidu.com","versionLog":"爱更不更"}
     */

    private String status;
    private String msg;
    /**
     * versionNo : 2.0
     * versionName : 第二版
     * versionUrl : http:/www.baidu.com
     * versionLog : 爱更不更
     */

    private VersionInfoBean versionInfo;

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

    public VersionInfoBean getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfoBean versionInfo) {
        this.versionInfo = versionInfo;
    }

    public static class VersionInfoBean {
        private String versionNo;
        private String versionName;
        private String versionUrl;
        private String versionLog;

        public String getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(String versionNo) {
            this.versionNo = versionNo;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionUrl() {
            return versionUrl;
        }

        public void setVersionUrl(String versionUrl) {
            this.versionUrl = versionUrl;
        }

        public String getVersionLog() {
            return versionLog;
        }

        public void setVersionLog(String versionLog) {
            this.versionLog = versionLog;
        }

        @Override
        public String toString() {
            return "VersionInfoBean{" +
                    "versionNo='" + versionNo + '\'' +
                    ", versionName='" + versionName + '\'' +
                    ", versionUrl='" + versionUrl + '\'' +
                    ", versionLog='" + versionLog + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "VersionInfo{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                ", versionInfo=" + versionInfo.toString() +
                '}';
    }
}
