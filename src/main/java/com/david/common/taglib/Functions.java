package com.david.common.taglib;

import com.david.common.JsonMapper;
import com.david.common.config.JConfig;
import com.david.common.spring.SpringUtils;
import com.david.common.utils.HttpClientUtil;
import com.david.common.utils.RelativeDateFormat;
import com.david.common.utils.UserUtils;
import com.david.sys.entity.Config;
import com.david.sys.entity.User;
import com.david.sys.entity.Weather;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

/**
 * System tag function
 *
 * @author David
 */
public class Functions {

    private static SessionDAO sessionDAO = SpringUtils.getBean(SessionDAO.class);


    public static Weather getWeather() {
        final String url = "http://api.openweathermap.org/data/2.5/weather?id=2147714&appid=43aacfb6fa831b24200a39d2d4351449";
        Weather weather = new Weather();

        String result = HttpClientUtil.doGet(url);
        try {
            JsonNode root = JsonMapper.getInstance().readTree(result);
            weather.setTemp(root.get("main").get("temp").asDouble() - 273.15);
            weather.setTemp_min(root.get("main").get("temp_min").asDouble() - 273.15);
            weather.setTemp_max(root.get("main").get("temp_max").asDouble() - 273.15);
            weather.setHumidity(root.get("main").get("humidity").asInt());
            weather.setPressure(root.get("main").get("pressure").asInt());
            weather.setSpeed(root.get("wind").get("speed").asDouble());
            weather.setAll(root.get("clouds").get("all").asInt());
            weather.setSunrise(root.get("sys").get("sunrise").asLong());
            weather.setSunset(root.get("sys").get("sunset").asLong());
            weather.setSunset(root.get("dt").asLong());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weather;
    }


    /**
     * To determine whether it is in the collection
     *
     * @param iterable
     * @param element
     * @return
     */
    public static boolean in(Iterable<?> iterable, Object element) {
        if (iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    /**
     * Get the online user list
     *
     * @return
     */
    public static Collection<Session> getOnlineUser() {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return sessions;
    }

    /**
     * Get account account
     *
     * @param session
     * @return
     */
    public static String principal(Session session) {
        PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (principalCollection == null)
            return "";
        else
            return (String) principalCollection.getPrimaryPrincipal();
    }

    /**
     * To determine whether to force the withdrawal
     *
     * @param session
     * @return
     */
    public static boolean isForceLogout(Session session) {
        return session.getAttribute(JConfig.SESSION_FORCE_LOGOUT_KEY) != null;
    }

    /**
     * Get the current login user
     *
     * @return
     */
    public static User getLoginUser() {
        return UserUtils.getLoginUser();
    }

    /**
     * Get the config.properties configuration file
     *
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        return JConfig.getConfig(key);
    }


    /**
     * Get the data configuration
     *
     * @param sysName
     * @param moduleName
     * @param configName
     * @return
     */
    public static Config getDBConfig(String sysName, String moduleName, String configName) {
        return JConfig.getConfig(sysName, moduleName, configName);
    }

    /**
     * Chinese distortion decoding
     *
     * @param key
     * @return
     */
    public static String urlDecode(String key) {
        try {
            key = new String(key.getBytes("iso8859-1"), "utf-8");
        } catch (Exception e) {
        }
        return key;
    }

    /**
     * Time formatting
     *
     * @param date
     * @return
     */
    public static String relativeDate(Date date) {
        return RelativeDateFormat.format(date);
    }

}
