package com.baji.dom.excelEnum;

import com.baji.dom.entity.SysAgent;
import sun.management.resources.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代理用户excel枚举
 *
 * @author dongmin
 * @date 2022/08/18
 */
public enum AGENT_USER_EXCEL_ENUM {
    AGENT_USER_CODE("业务员代码", "agentCode"),
    AGENT_USER_NAME("业务员", "agentName"),
    AGENT_USER_MANAGE_CODE("机构代码", "manageCode"),
    AGENT_USER_MANAGE_NAME("机构名", "manageName"),
    AGENT_USER_MOBILE("手机号", "mobile"),
    AGENT_USER_BIRTHDAY("生日", "birthday"),
    AGENT_USER_TYPE("业务员类型", "agentType"),
    AGENT_USER_STATUS("业务员状态", "agentStatus");

    /**
     * 名字
     */
    private String name;

    /**
     * 文件名称
     */
    private String fileName;

    public String getName() {
        return name;
    }

    public String getFileName() {
        return fileName;
    }

    AGENT_USER_EXCEL_ENUM(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    public static List<String> getNameList() {
        List<String> list = new ArrayList<String>();
        for (AGENT_USER_EXCEL_ENUM value : AGENT_USER_EXCEL_ENUM.values()) {
            list.add(value.getName());
        }
        return list;
    }

    public static Map<String,String> getAllNameAndFileName() {
        Map<String, String> map = new HashMap<String, String>();
        for (AGENT_USER_EXCEL_ENUM value : AGENT_USER_EXCEL_ENUM.values()) {
            map.put(value.getName(), value.getFileName());
        }
        return map;
    }

    public static Map<String, String> getAllName(SysAgent agent) {
        Map<String, String> map = new HashMap<String, String>();
        for (AGENT_USER_EXCEL_ENUM value : AGENT_USER_EXCEL_ENUM.values()) {
            if (value.getName().equals("业务员代码")) {
                map.put(value.getName(), agent.getAgentCode());
            }
            if (value.getName().equals("业务员")) {
                map.put(value.getName(), agent.getAgentName());
            }
            if (value.getName().equals("机构代码")) {
                map.put(value.getName(), agent.getManageCode());
            }
            if (value.getName().equals("机构名")) {
                map.put(value.getName(), agent.getManageName());
            }
            if (value.getName().equals("手机号")) {
                map.put(value.getName(), agent.getMobile());
            }
            if (value.getName().equals("生日")) {
                map.put(value.getName(), agent.getBirthday());
            }
            if (value.getName().equals("业务员类型")) {
                map.put(value.getName(), agent.getAgentType().toString());
            }
            if (value.getName().equals("业务员状态")) {
                map.put(value.getName(), agent.getAgentStatus().toString());
            }
        }
        return map;
    }
}
