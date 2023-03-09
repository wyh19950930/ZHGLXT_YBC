package com.jymj.zhglxt.zjd.bean.yzt;

import android.view.Menu;

import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class User1 {

    /**
     * msg : null
     * code : 0
     * user : {"createTime":"2016-11-11 00:00:00","createUserId":null,"email":"root@renren.io","mobile":"13612345678","roleIdList":null,"status":1,"userId":1,"username":"admin"}
     */

    private UserBean user;
    private String token;
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean implements Serializable {
        /**
         * createTime : 2016-11-11 00:00:00
         * createUserId : null
         * email : root@renren.io
         * mobile : 13612345678
         * roleIdList : null
         * status : 1
         * userId : 1
         * username : admin
         */

        private String createTime;
        private Object createUserId;
        private String email;
        private String mobile;
        private Object roleIdList;
        private int status;
        private int userId;
        private String username;
        private String xzqmc;
        private String code;
        private String center;
        private String typeText;
        private int type;
        private List<XzqBean> xzqs;
        private List<Menu> menuList;

        public List<Menu> getMenuList() {
            return menuList==null?new ArrayList<>():menuList;
        }

        public void setMenuList(List<Menu> menuList) {
            this.menuList = menuList;
        }

        public List<XzqBean> getXzqs() {
            return xzqs==null?new ArrayList<>():xzqs;
        }

        public void setXzqs(List<XzqBean> xzqs) {
            this.xzqs = xzqs;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        private ArrayList<Menu> menus=new ArrayList<>();
    
        public ArrayList<Menu> getMenus() {
            return menus;
        }
    
        public void setMenus(ArrayList<Menu> menus) {
            this.menus = menus;
        }
    
        public String getXzqmc() {
            return xzqmc;
        }

        public void setXzqmc(String xzqmc) {
            this.xzqmc = xzqmc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTypeText() {
            return typeText==null?"":typeText;
        }

        public void setTypeText(String typeText) {
            this.typeText = typeText;
        }
        public String getStringCenter(){
            return center==null?"":center;
        }
        public LatLng getCenter() {
            if (center != null) {
                String[] points = center.substring(6, center.length() - 1).split(" ");
                if (points != null && points.length > 1) {
                    CoordinateConverter converter = new CoordinateConverter();
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.GPS);
                    LatLng sl=new LatLng(Double.parseDouble(points[1]), Double.parseDouble(points[0]));
                    // sourceLatLng待转换坐标点 LatLng类型
                    converter.coord(sl);
                    LatLng latLng = converter.convert();
                    return sl;
                } else {
                    return new LatLng(0, 0);
                }
            }

            return new LatLng(0, 0);

        }

        public void setCenter(String center) {
            this.center = center;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(Object createUserId) {
            this.createUserId = createUserId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public Object getRoleIdList() {
            return roleIdList;
        }

        public void setRoleIdList(Object roleIdList) {
            this.roleIdList = roleIdList;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
        public static class XzqBean  implements Serializable{

            /**
             * xzqId : 176
             * name : 高家铺村
             * code : 110115015002
             */

            private int xzqId;
            private String name;
            private String code;
            private String geometry;
            private String center;
            private boolean isChecked;
            private boolean isCheck;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public void XzqBean() {
            }

            public String getGeometry() {
                return geometry==null?"":geometry;
            }

            public void setGeometry(String geometry) {
                this.geometry = geometry;
            }

            public String getCenter() {
                return center==null?"":center;
            }

            public void setCenter(String center) {
                this.center = center;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public int getXzqId() {
                return xzqId;
            }

            public void setXzqId(int xzqId) {
                this.xzqId = xzqId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
        public static class MenuBean{
            private String name;
    
            public String getName() {
                return name;
            }
    
            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
