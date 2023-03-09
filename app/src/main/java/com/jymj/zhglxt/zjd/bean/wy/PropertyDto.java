package com.jymj.zhglxt.zjd.bean.wy;

import com.jymj.zhglxt.ldrkgl.home.bean.QRCodeFileEntity;
import com.jymj.zhglxt.rjhj.bean.SysUserEntity;
import com.jymj.zhglxt.rjhj.bean.YLEntity;

import java.io.Serializable;


/**
 * Created by lc on 2022/5/26. 物业管理参数实体
 */
public class PropertyDto implements Serializable {
    /*private String code;
    private SysUserEntity sysUser;
    private String cqr;//产权人
    private String mph;//门牌号

    private String point;*/

    private YLEntity yl;
    private QRCodeFileEntity qrCode;
    private HousingEntity housing;

    public YLEntity getYl() {
        return yl;
    }

    public void setYl(YLEntity yl) {
        this.yl = yl;
    }

    public QRCodeFileEntity getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCodeFileEntity qrCode) {
        this.qrCode = qrCode;
    }

    public HousingEntity getHousing() {
        return housing;
    }

    public void setHousing(HousingEntity housing) {
        this.housing = housing;
    }
}
