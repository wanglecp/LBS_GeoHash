package com.texaspoker.locale;

import com.texaspoker.logger.LocaleDebugLogService;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本类用于维护玩家ID与对应地理信息的数据容器
 * Created by eric.x on 16/10/24.
 */
public class LocaleManager {

    /**
     * 用于存放玩家ID与设备IP的对应关系
     */
    private ConcurrentHashMap<Long, String> mUserIPInfos;

    /**
     * 用于存放玩家ID与Geohash字符串的对应关系
     */
    private ConcurrentHashMap<Long, String> mUserGeohash;

    /**
     * 本类实例对象
     */
    private static LocaleManager instance;

    /**
     * 私有构造方法
     */
    private LocaleManager() {
        mUserIPInfos = new ConcurrentHashMap<>();
        mUserGeohash = new ConcurrentHashMap<>();
    }

    /**
     * 获取本类唯一实例对象
     *
     * @return 本类实例对象
     */
    public static synchronized LocaleManager getInstance() {
        if (instance == null)
            instance = new LocaleManager();
        return instance;
    }

    /**
     * 将指定玩家ID与设备IP的对应关系放入容器
     *
     * @param uuid   玩家ID
     * @param ipInfo 设备IP
     */
    public void addmUserIPInfos(Long uuid, String ipInfo) {
        mUserIPInfos.put(uuid, ipInfo);
        LocaleDebugLogService.getInstance().debug("addIP " + uuid + " " + ipInfo);
    }

    /**
     * 从容器中获取指定玩家ID对应的设备IP
     *
     * @param uuid 玩家ID
     * @return 对应的设备IP
     */
    public String getmUserIPInfos(Long uuid) {
        // TODO: get方法会在实际运行中每次查询连续调用两次，该类日志已定义为debug级别，上线后可随时调节打印级别
        LocaleDebugLogService.getInstance().debug("getIP " + uuid + " " + (mUserIPInfos.containsKey(uuid) ? mUserIPInfos.get(uuid) : "null"));
        if (mUserIPInfos.containsKey(uuid))
            return mUserIPInfos.get(uuid);
        else
            return null;
    }

    /**
     * 将指定玩家ID与Geohash字符串的对应关系放入容器
     *
     * @param uuid    玩家ID
     * @param geohash Geohash字符串
     */
    public void addmUserGeohash(Long uuid, String geohash) {
        mUserGeohash.put(uuid, geohash);
        LocaleDebugLogService.getInstance().debug("addGeo " + uuid + " " + geohash);
    }

    /**
     * 从容器中获取指定玩家ID对应的Geohash字符串
     *
     * @param uuid 玩家ID
     * @return Geohash字符串
     */
    public String getmUserGeohash(Long uuid) {
        LocaleDebugLogService.getInstance().debug("getGeo " + uuid + " " + (mUserGeohash.containsKey(uuid) ? mUserGeohash.get(uuid) : "null"));
        if (mUserGeohash.containsKey(uuid)) {
            return mUserGeohash.get(uuid);
        } else {
            LocaleDebugLogService.getInstance().debug("not_exist in mUserGeohash uid: " + uuid);
            return null;
        }
    }
}
