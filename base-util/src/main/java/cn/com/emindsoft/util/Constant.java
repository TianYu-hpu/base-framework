package cn.com.emindsoft.util;

/**
 * 接口常量
 *
 */
public interface Constant {

    /**
     * 超级管理员ID
     */
    int SUPER_ADMIN = 2;

    /**
     * 数据权限过滤
     *
     */
    String SQL_FILTER = "sql_filter";

    /**
     * 云存储配置KEY
     */
    String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    /**
     * 菜单类型
     */
    enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */
    enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * session 用户名
     */
    String SESSION_USERNAME = "session_username";

    /**
     * 系统级别登录用户
     */
    String SESSION_SYS_USERNAME = "session_sys_username";

}
