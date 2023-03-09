package com.jymj.zhglxt.rjhj.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:55
 */
public class SysUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;
//@NotBlank(message = "姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    private String idcard;//证件号
    private String position;//职务
    //其他人可见  默认false
    private Boolean enable;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    /*@NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})*/
    private String email;
    /**
     * 设备码
     */
    private String deviceCode;
    /**
     * menuList
     */
//    private List<SysMenuEntity> menuList;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态 0：禁用 1：正常
     */
    private Integer status;
    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;
    private List<PjProjEntity> pjProjEntityList;
    /**
     * 创建者ID
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 类型
     */
    private Integer type;
    private String xzqmc;
    private String code;
    private String center;
    private Integer duties;//  是否可以修改  0 可以修改  1不可以修改
    private Integer rz;//

    private List<String> codeList;
    private List<SysXzqFhEntity> xzqs;//SysXzqFhEntity
    private List<Long> xzqIds;//SysXzqFhEntity

    public Integer getRz() {
        return rz==null?0:rz;
    }

    public void setRz(Integer rz) {
        this.rz = rz;
    }

    public List<Long> getXzqIds() {
        return xzqIds==null?new ArrayList<>():xzqIds;
    }

    public void setXzqIds(List<Long> xzqIds) {
        this.xzqIds = xzqIds;
    }

    public Integer getDuties() {
        return duties;
    }

    public void setDuties(Integer duties) {
        this.duties = duties;
    }

    public List<SysXzqFhEntity> getXzqs() {
        return xzqs==null?new ArrayList<>():xzqs;
    }

    public void setXzqs(List<SysXzqFhEntity> xzqs) {
        this.xzqs = xzqs;
    }

    public String getIdcard() {
        return idcard==null?"":idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getXzqmc() {
        return xzqmc==null?"":xzqmc;
    }

    public String getPosition() {
        return position==null?"":position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setXzqmc(String xzqmc) {
        this.xzqmc = xzqmc;
    }

    public String getCode() {
        return code==null?"":code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCenter() {
        return center==null?"":center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnable() {
        if (enable == null) {
            return false;
        }
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<PjProjEntity> getPjProjEntityList() {
        return pjProjEntityList;
    }

    public void setPjProjEntityList(List<PjProjEntity> pjProjEntityList) {
        this.pjProjEntityList = pjProjEntityList;
    }

    /**
     *
     * 设置：用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUsername() {
        return username==null?"":username;
    }

    /**
     * 设置：密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     *
     * @return String
     */
    public String getPassword() {
        return password==null?"":password;
    }

    /**
     * 设置：邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     *
     * @return String
     */
    public String getEmail() {
        return email==null?"":email;
    }

    /**
     * 设置：手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     *
     * @return String
     */
    public String getMobile() {
        return mobile==null?"":mobile;
    }

    /**
     * 设置：状态 0：禁用 1：正常
     *
     * @param status 状态 0：禁用 1：正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态 0：禁用 1：正常
     *
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     *
     * @return Date
     */
    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * Aoo菜单权限
     */
  /*  private List<SysMenuEntity> menus;

    public List<SysMenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenuEntity> menus) {
        this.menus = menus;
    }*/

    public String getDeviceCode() {
        return deviceCode==null?"":deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

  /*  public List<SysMenuEntity> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<SysMenuEntity> menuList) {
        this.menuList = menuList;
    }*/
}
