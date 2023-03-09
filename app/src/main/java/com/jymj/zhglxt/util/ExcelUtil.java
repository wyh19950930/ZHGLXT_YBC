package com.jymj.zhglxt.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jymj.zhglxt.api.AppMenus;
import com.jymj.zhglxt.zjd.bean.bcjc.BcTdlyEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BccgbqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BccyjgEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcfzyyEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcjcBbBean;
import com.jymj.zhglxt.zjd.bean.bcjc.BcjjzsrqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjsrqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtjjzcqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcjtzcEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcldlbdqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcrkbdqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BcwcjybdqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BczdqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BczhandqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.BczzdqkEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.OptionsEntity;
import com.jymj.zhglxt.zjd.bean.bcjc.ProjectBean;
import com.setsuna.common.baseapp.AppCache;
import com.setsuna.common.commonutils.ToastUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 */
public class ExcelUtil {

    /**
     * 后来找到的比较全的注释
     * https://blog.csdn.net/weixin_32154109/article/details/117544012
     * https://www.likecs.com/show-306892897.html?sc=800
     * https://jexcelapi.sourceforge.net/resources/javadocs/current/docs/
     */

    private static WritableFont arial14font = null;

    private static WritableCellFormat arial14format = null;
    private static WritableFont arial10font = null;
    private static WritableCellFormat arial10format = null;
    private static WritableFont arial12font = null;
    private static WritableFont arial13font = null;
    private static WritableFont arial15font = null;
    private static WritableCellFormat arial12format = null;
    private static WritableCellFormat arial13format = null;
    private static WritableCellFormat arial15format = null;
    private final static String UTF8_ENCODING = "UTF-8";

    /**
     * 单元格的格式设置 字体大小 颜色 对齐方式、背景颜色等...
     */
    private static void format() {
        try {
            arial14font = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD);
            arial14font.setColour(jxl.format.Colour.LIGHT_BLUE);
            arial14format = new WritableCellFormat(arial14font);
            arial14format.setAlignment(jxl.format.Alignment.CENTRE);
            arial14format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial14format.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);

            arial10font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            arial10format = new WritableCellFormat(arial10font);
            arial10format.setAlignment(jxl.format.Alignment.CENTRE);
            arial10format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
//            arial10format.setBackground(Colour.GRAY_25);

            arial12font = new WritableFont(WritableFont.ARIAL, 10);//ARIAL
            arial12format = new WritableCellFormat(arial12font);
            arial12format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
            arial12format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
            arial12format.setWrap(true);//可以设成自动换行，
            arial12format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);

            arial13font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);//ARIAL
            arial13format = new WritableCellFormat(arial13font);
            arial13format.setAlignment(jxl.format.Alignment.CENTRE);//左右居中
            arial13format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//上下居中
            arial13format.setWrap(true);//可以设成自动换行，
            arial13format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            //对齐格式
            arial10format.setAlignment(jxl.format.Alignment.LEFT);//CENTRE
            arial10format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            arial10format.setWrap(true);

            arial15font = new WritableFont(WritableFont.ARIAL, 10);//, WritableFont.BOLD
            arial15format = new WritableCellFormat(arial15font);
            arial15format.setAlignment(jxl.format.Alignment.CENTRE);
            arial15format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            arial15format.setAlignment(jxl.format.Alignment.LEFT);//CENTRE
            arial15format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
//            arial15format.setBackground(Colour.SKY_BLUE);
//            new Colour(0x4f,"automat",120,226,165)
            arial15format.setWrap(true);

        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化Excel
     *
     * @param fileName 导出excel存放的地址（目录）
     * @param colName excel中包含的列名（可以有多个）
     */
    public static void initExcel(Activity activity,String fileName, List<String> colName) {

        String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName();
//        File testFolder = new File(Environment.getExternalStorageDirectory() + "/test");
        copyAssetFilesToSDCard(activity,new File(filePath,"xiexian.png"),"xiexian.png");
        format();
        WritableWorkbook workbook = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            workbook = Workbook.createWorkbook(file);
            //设置表格的名字
            WritableSheet sheet = workbook.createSheet("demo", 0);
            //创建标题栏
            sheet.addCell((WritableCell) new Label(0, 0, fileName, arial14format));
            for (int col = 0; col < colName.size(); col++) {
                sheet.addCell(new Label(col, 0, colName.get(col), arial10format));
            }
            //设置行高
            sheet.setRowView(0, 340);
            workbook.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressWarnings("unchecked")//测试表格
    public static <T> void writeObjListToExcel(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,1,0,2);

                sheet.addCell(new Label(0, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(0,1,1,2,
                        new File(filePath));
                sheet.addImage(image);

                /*for (int j = 0; j < objList.size(); j++) {
                    ProjectBean projectBean = (ProjectBean) objList.get(j);
                    List<String> list = new ArrayList<>();
                    list.add(projectBean.getName());
                    list.add(projectBean.getProject());
                    list.add(projectBean.getMoney());
                    list.add(projectBean.getYear() + " "  + projectBean.getMonth()+" "+projectBean.getDay());
                    list.add(projectBean.getBeizhu());

                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, j + 1, list.get(i), arial12format));
                        if (list.get(i).length() <= 4) {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length() + 8);
                        } else {
                            //设置列宽
                            sheet.setColumnView(i, list.get(i).length() + 5);
                        }
                    }
                    //设置行高
                    sheet.setRowView(j + 1, 350);
                }*/

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//人口变动情况
    public static <T> void writeObjListToExcel1(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,10,0);
                sheet.addCell(new Label(0, 0, "人口变动情况", arial13format));//arial10format
                sheet.addCell(new Label(11, 0, "单位:户、人", arial12format));
                sheet.mergeCells(0,1,0,2);
                sheet.mergeCells(1,1,1,2);
                sheet.mergeCells(2,1,2,2);
                sheet.mergeCells(3,1,3,2);
//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,2,
                        new File(filePath));
                sheet.addImage(image);

                sheet.mergeCells(2,1,2,2);
                sheet.mergeCells(4,1,5,1);
                sheet.mergeCells(6,1,7,1);
                sheet.mergeCells(8,1,9,1);
//                sheet.mergeCells(9,1,11,1);
                sheet.addCell(new Label(0, 1, "区", arial13format));
                sheet.addCell(new Label(1, 1, "镇", arial13format));
                sheet.addCell(new Label(2, 1, "村", arial13format));
                sheet.addCell(new Label(4, 1, "全村户籍人口", arial13format));
                sheet.addCell(new Label(6, 1, "1.按户籍划分", arial13format));
                sheet.addCell(new Label(8, 1, "2.按年龄划分", arial13format));
                sheet.addCell(new Label(10, 1, "本村户籍常住人口", arial13format));
                sheet.addCell(new Label(11, 1, "外来常驻人口", arial13format));
//                sheet.addCell(new Label(0, 1, "行政区", arial13format));

                sheet.addCell(new Label(4, 2, "户数", arial12format));
                sheet.addCell(new Label(5, 2, "人数", arial12format));
                sheet.addCell(new Label(6, 2, "非农业户籍(人)", arial12format));
                sheet.addCell(new Label(7, 2, "农业户籍(人)", arial12format));
                sheet.addCell(new Label(8, 2, "≤30岁", arial12format));
                sheet.addCell(new Label(9, 2, "≥60岁", arial12format));
                sheet.addCell(new Label(10, 2, "人数", arial12format));
                sheet.addCell(new Label(11, 2, "人数", arial12format));
//                sheet.addCell(new Label(9, 2, "区", arial12format));
//                sheet.addCell(new Label(10, 2, "镇", arial12format));
//                sheet.addCell(new Label(11, 2, "村", arial12format));
                sheet.setColumnView(11, "单位:户、人".length() + 5);
                int count = 0;
                String s = new Gson().toJson(objList);
                List<BcrkbdqkEntity> stringList = new Gson().fromJson(s, new TypeToken<List<BcrkbdqkEntity>>() {}.getType());
                for (int i = 0; i < stringList.size(); i++) {
                        count++;
                        BcrkbdqkEntity projectBean = (BcrkbdqkEntity) stringList.get(i);
                        List<String> list = new ArrayList<>();
                        list.add(projectBean.getXzq());
                        list.add(projectBean.getZhen());
                        list.add(projectBean.getXzqmc());
                        list.add(projectBean.getYears());
                        list.add(projectBean.getQchs());
                        list.add(projectBean.getQcrk());
                        list.add(projectBean.getNyhjh());
                        list.add(projectBean.getNyhjr());
                        list.add(projectBean.getNlss());
                        list.add(projectBean.getNlls());
                        list.add(projectBean.getBcczrk());
                        list.add(projectBean.getWlczrk());


                        for (int f = 0; f < list.size(); f++) {
                            sheet.addCell(new Label(f, count + 2, list.get(f), arial12format));//list.get(i)
                            sheet.setColumnView(f, 15);
                        }
                        sheet.setRowView(count + 2, 350);
                    }
                    //设置行高

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//劳动力变化情况表
    public static <T> void writeObjListToExcelLdlbdqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,12,0);
                sheet.addCell(new Label(0, 0, "劳动力变动情况", arial13format));//arial10format
                sheet.addCell(new Label(13, 0, "单位:人", arial12format));
                sheet.mergeCells(0,1,0,2);
                sheet.mergeCells(1,1,1,2);
                sheet.mergeCells(2,1,2,2);
                sheet.mergeCells(3,1,3,2);
//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.addCell(new Label(0, 1, "区", arial12format));
                sheet.addCell(new Label(1, 1, "镇", arial12format));
                sheet.addCell(new Label(2, 1, "村", arial12format));
                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,2,
                        new File(filePath));
                sheet.addImage(image);



                sheet.mergeCells(4,1,4,2);
                sheet.addCell(new Label(4, 1, "实际户籍就业劳动力总数", arial13format));
                sheet.mergeCells(5,1,6,1);
                sheet.mergeCells(7,1,9,1);
                sheet.mergeCells(10,1,13,1);
                sheet.mergeCells(14,1,16,1);
                sheet.addCell(new Label(5, 1, "1.按年龄划分", arial13format));
                sheet.addCell(new Label(7, 1, "2.按产业划分", arial13format));
                sheet.addCell(new Label(10, 1, "3.按就业渠道划分", arial13format));
//                sheet.addCell(new Label(12, 1, "行政区", arial13format));

                sheet.addCell(new Label(5, 2, "≤30岁", arial12format));
                sheet.addCell(new Label(6, 2, "≥60岁", arial12format));
                sheet.addCell(new Label(7, 2, "一产", arial12format));
                sheet.addCell(new Label(8, 2, "二产", arial12format));
                sheet.addCell(new Label(9, 2, "三产", arial12format));
                sheet.addCell(new Label(10, 2, "(1)家庭经营", arial12format));
                sheet.addCell(new Label(11, 2, "(2)村集体经营", arial12format));
                sheet.addCell(new Label(12, 2, "(3)村内社会企业", arial12format));
                sheet.addCell(new Label(13, 2, "(4)外出务工(1年中≥6个月)", arial12format));

                sheet.setColumnView(13, "单位:人".length() + 5);

                int count = 0;
                /*for (int j = 0; j < objList.size(); j++) {
                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcldlbdqkEntity> bcldlbdqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcldlbdqkEntity>>() {}.getType());
                    if (count+3!=count+3+bcldlbdqkEntities.size()-1){
                        sheet.mergeCells(0,count+3,0,count+3+bcldlbdqkEntities.size()-1);
                    }*/
                String toJson1 = new Gson().toJson(objList);
                List<BcldlbdqkEntity> bcldlbdqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcldlbdqkEntity>>() {}.getType());
                    for (int i = 0; i < bcldlbdqkEntities.size(); i++) {
                        count++;
                        BcldlbdqkEntity projectBean = (BcldlbdqkEntity) bcldlbdqkEntities.get(i);
                        List<String> list = new ArrayList<>();
                        list.add(projectBean.getXzq());
                        list.add(projectBean.getZhen());
                        list.add(projectBean.getXzqmc());
                        list.add(projectBean.getYears());
                        list.add(projectBean.getLdlzs());
                        list.add(projectBean.getNlss());
                        list.add(projectBean.getNlls());
                        list.add(projectBean.getCyyc());
                        list.add(projectBean.getCyec());
                        list.add(projectBean.getSysc());
                        list.add(projectBean.getJtjy());
                        list.add(projectBean.getCjtjy());
                        list.add(projectBean.getCjtjjzz());
                        list.add(projectBean.getWclg());

                        for (int f = 0; f < list.size(); f++) {
                            sheet.addCell(new Label(f, count + 2, list.get(f), arial12format));//list.get(i)
                            sheet.setColumnView(f, 15);
                        }
                        //设置行高
                        sheet.setRowView(count + 2, 350);
                    }
//                }

                sheet.setColumnView(3,  15);
                sheet.setColumnView(11, "(3)村内社会企业".length() + 10);
                sheet.setColumnView(12, "(4)外出务工(1年中≥6个月)".length() + 10);

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//外出务工人员就业结构变动情况表
    public static <T> void writeObjListToExcelWcwgryjyjgbdqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,6,0);
                sheet.addCell(new Label(0, 0, "外出务工人员就业结构变动情况", arial13format));//arial10format
                sheet.addCell(new Label(7, 0, "单位:人", arial12format));
                sheet.mergeCells(0,1,0,2);
                sheet.mergeCells(1,1,1,2);
                sheet.mergeCells(2,1,2,2);
                sheet.mergeCells(3,1,3,2);

//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.addCell(new Label(0, 1, "区", arial12format));
                sheet.addCell(new Label(1, 1, "镇", arial12format));
                sheet.addCell(new Label(2, 1, "村", arial12format));
                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,2,
                        new File(filePath));
                sheet.addImage(image);

                sheet.mergeCells(4,1,4,2);
                sheet.addCell(new Label(4, 1, "长期外出务工人员总数", arial13format));
                sheet.mergeCells(5,1,7,1);
//                sheet.mergeCells(6,1,8,1);
                sheet.addCell(new Label(5, 1, "其中:按就业区域划分", arial13format));
//                sheet.addCell(new Label(6, 1, "行政区", arial13format));

                sheet.addCell(new Label(5, 2, "乡外区内", arial12format));
                sheet.addCell(new Label(6, 2, "区外市内", arial12format));
                sheet.addCell(new Label(7, 2, "市外", arial12format));



                sheet.setColumnView(7, "单位:人".length() + 5);

                int count = 0;
                /*for (int j = 0; j < objList.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcwcjybdqkEntity> bcwcjybdqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcwcjybdqkEntity>>() {}.getType());
                    sheet.mergeCells(0,count+3,0,count+3+bcwcjybdqkEntities.size()-1);*/

                String toJson1 = new Gson().toJson(objList);
                List<BcwcjybdqkEntity> bcwcjybdqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcwcjybdqkEntity>>() {}.getType());
                    for (int i = 0; i < bcwcjybdqkEntities.size(); i++) {
                        count++;
                        BcwcjybdqkEntity projectBean = (BcwcjybdqkEntity) bcwcjybdqkEntities.get(i);
                        List<String> list = new ArrayList<>();
                        list.add(projectBean.getXzq());
                        list.add(projectBean.getZhen());
                        list.add(projectBean.getXzqmc());
                        list.add(projectBean.getYears());
                        list.add(projectBean.getCqwgzs());
//                        list.add(projectBean.getBdjy());
                        list.add(projectBean.getXwqn());
                        list.add(projectBean.getQwsn());
                        list.add(projectBean.getWss());

                        for (int f = 0; f < list.size(); f++) {
                            sheet.addCell(new Label(f, count + 2, list.get(f), arial12format));//list.get(i)
                            sheet.setColumnView(f, 15);
                        }
                        //设置行高
                        sheet.setRowView(count + 2, 350);
                    }

//                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村干部基本情况
    public static <T> void writeObjListToExcelCgbjbqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,8,0);
                sheet.addCell(new Label(0, 0, "村干部基本情况", arial13format));//arial10format

                sheet.setColumnView(2, 12);
                sheet.setColumnView(3, "村股份合作社社长".length() + 10);
                sheet.setColumnView(4, "村股份合作社社长".length() + 5);//姓名
                sheet.setColumnView(5, "11111111111".length() + 5);//手机号长度
                sheet.setColumnView(6, "文化程度".length() + 5);
                sheet.setColumnView(7, "文化程度".length() + 5);
                sheet.setColumnView(8, "其实连续任现职时间(年/月)".length() + 10);//年龄

                int count = 0;

                /*for (int j = 0; j < objList.size(); j++) {


                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BccgbqkEntity> bccgbqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BccgbqkEntity>>() {}.getType());*/

                String toJson1 = new Gson().toJson(objList);
                List<BccgbqkEntity> bccgbqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BccgbqkEntity>>() {}.getType());
                    for (int i = 0; i < bccgbqkEntities.size(); i++) {
                        count = count +5;
                        BccgbqkEntity bccgbqkEntity = bccgbqkEntities.get(i);
                        sheet.mergeCells(0,(i*6) + 2,0,(i*6)+4);
                        sheet.mergeCells(1,(i*6) + 2,1,(i*6)+4);
                        sheet.mergeCells(2,(i*6) + 2,2,(i*6)+4);
                        sheet.mergeCells(0,(i*6)+5,8,(i*6)+5);
                        sheet.mergeCells(0,(i*6)+6,8,(i*6)+6);
                        sheet.addCell(new Label(0, (i*6)+1, "区", arial13format));
                        sheet.addCell(new Label(1, (i*6)+1, "镇", arial13format));
                        sheet.addCell(new Label(2, (i*6)+1, "村", arial13format));
                        sheet.addCell(new Label(3, (i*6)+1, "", arial13format));//职务
                        sheet.addCell(new Label(4, (i*6)+1, "姓名", arial13format));
                        sheet.addCell(new Label(5, (i*6)+1, "手机号码", arial13format));
                        sheet.addCell(new Label(6, (i*6)+1, "文化程度", arial13format));
                        sheet.addCell(new Label(7, (i*6)+1, "年龄", arial13format));
                        sheet.addCell(new Label(8, (i*6)+1, "起始连续任现职时间(年/月)", arial13format));

                        sheet.addCell(new Label(0, (i*6)+2, bccgbqkEntity.getXzq(), arial12format));//list.get(i)
                        sheet.addCell(new Label(1, (i*6)+2, bccgbqkEntity.getZhen(), arial12format));//list.get(i)
                        sheet.addCell(new Label(2, (i*6)+2, bccgbqkEntity.getXzqmc(), arial12format));//list.get(i)
                        sheet.addCell(new Label(3, (i*6)+2, "村书记", arial12format));//list.get(i)
                        sheet.addCell(new Label(4, (i*6)+2, bccgbqkEntity.getCsjmc(), arial12format));//list.get(i)
                        sheet.addCell(new Label(5, (i*6)+2, bccgbqkEntity.getCsjsjh(), arial12format));//list.get(i)
                        sheet.addCell(new Label(6, (i*6)+2, bccgbqkEntity.getCsjwhcd().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(7, (i*6)+2, bccgbqkEntity.getCsjnl().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(8, (i*6)+2, bccgbqkEntity.getCsjrz(), arial12format));//list.get(i)
///////////////////////**************************************
                        sheet.addCell(new Label(3, (i*6)+3, "村主任", arial12format));//list.get(i)
                        sheet.addCell(new Label(4, (i*6)+3, bccgbqkEntity.getCzrmc(), arial12format));//list.get(i)
                        sheet.addCell(new Label(5, (i*6)+3, bccgbqkEntity.getCzrsjh(), arial12format));//list.get(i)
                        sheet.addCell(new Label(6, (i*6)+3, bccgbqkEntity.getCzrwhcd().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(7, (i*6)+3, bccgbqkEntity.getCzrnl().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(8, (i*6)+3, bccgbqkEntity.getCzrrz(), arial12format));//list.get(i)

                        sheet.addCell(new Label(3, (i*6)+4, "村股份合作社社长", arial12format));//list.get(i)
                        sheet.addCell(new Label(4, (i*6)+4, bccgbqkEntity.getCszmc(), arial12format));//list.get(i)
                        sheet.addCell(new Label(5, (i*6)+4, bccgbqkEntity.getCszsjh(), arial12format));//list.get(i)
                        sheet.addCell(new Label(6, (i*6)+4, bccgbqkEntity.getCszwhcd().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(7, (i*6)+4, bccgbqkEntity.getCsznl().toString(), arial12format));//list.get(i)
                        sheet.addCell(new Label(8, (i*6)+4, bccgbqkEntity.getCszrz(), arial12format));//list.get(i)
                        if (bccgbqkEntity.getSfydysj().equals("B")){
                            sheet.addCell(new Label(0, (i*6)+5, "附：本村户籍人口中有中共党员"+bccgbqkEntity.getDyrs()+"人，" +
                                    "60 岁（含）以上党员"+bccgbqkEntity.getDyrs50()+"人。 " +
                                    "是否有“第一书记”"+bccgbqkEntity.getSfydysj()+"（A 是 B 否）。", arial10format));//list.get(i)
                        }else {

                            sheet.addCell(new Label(0, (i*6)+5, "附：本村户籍人口中有中共党员"+bccgbqkEntity.getDyrs()+"人，" +
                                    "60 岁（含）以上党员"+bccgbqkEntity.getDyrs50()+"人。 " +
                                    "是否有“第一书记”"+bccgbqkEntity.getSfydysj()+"（A 是 B 否）。" +
                                    "如有，来自（单位名称"+bccgbqkEntity.getDwmc()+" 。", arial10format));//list.get(i)
                        }
                        sheet.addCell(new Label(0, (i*6)+6, "注：[1]已撤村的，表 1-1、1-2、1-3 以集体经济组织成员为口径进行填写，表 1-4 仅填写村股份合作社支部书记及社长信息。 " +
                                "\n[2]农村集体经济组织负责人一般为村股份经济合作社社长，一些地区为股份合作制公司董事长。 " +
                                "\n[3]文化程度：1=小学及以下；2=初中；3=高中（中专）；4=大专及以上。 " +
                                "\n[4]表 1-4 数据均按现状填写。", arial15format));//list.get(i)

                        sheet.setRowView((i*6)+2, 350);
                        sheet.setRowView((i*6)+3, 350);
                        sheet.setRowView((i*6)+4, 350);
                        sheet.setRowView((i*6)+5, 700);
                        sheet.setRowView((i*6)+6, 1400);

                    }

                    //设置行高
//                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村集体土地资源情况  |  用地类型情况
    public static <T> void writeObjListToExcelCjttdzyqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,9,0);
                sheet.addCell(new Label(0, 0, "村集体土地资源情况", arial12format));//arial10format

                sheet.addCell(new Label(0, 1, "区", arial12format));//arial10format
                sheet.addCell(new Label(1, 1, "镇", arial12format));//arial10format
                sheet.addCell(new Label(2, 1, "村", arial12format));//arial10format
                sheet.addCell(new Label(3, 1, "年份", arial12format));//arial10format
                sheet.addCell(new Label(4, 1, "编号", arial12format));//arial10format
                sheet.addCell(new Label(5, 1, "用地类型", arial12format));//arial10format
                sheet.addCell(new Label(6, 1, "占地面积(亩)", arial12format));//arial10format
                sheet.addCell(new Label(7, 1, "使用情况", arial12format));//arial10format
                sheet.addCell(new Label(8, 1, "平均流转价格(万元/亩、万元/宗)", arial12format));//arial10format
                sheet.addCell(new Label(9, 1, "备注", arial12format));//arial10format


                sheet.setColumnView(0, 15);
                sheet.setColumnView(1, 15);
                sheet.setColumnView(2, 15);
                sheet.setColumnView(3, 10);
                sheet.setColumnView(4, 8);
                sheet.setColumnView(5, 15);
                sheet.setColumnView(6, 12);
                sheet.setColumnView(7, 20);
                sheet.setColumnView(8, 20);
                sheet.setColumnView(9, 30);

                String toJson1 = new Gson().toJson(objList);
                List<BcTdlyEntity> bcTdlyEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcTdlyEntity>>() {}.getType());

                for (int j = 0; j < bcTdlyEntities.size(); j++) {

                    BcTdlyEntity bcTdlyEntity = bcTdlyEntities.get(j);
                    sheet.mergeCells(0,(j*12)+2,0,(j*12)+12);
                    sheet.mergeCells(1,(j*12)+2,1,(j*12)+12);
                    sheet.mergeCells(2,(j*12)+2,2,(j*12)+12);
                    sheet.mergeCells(3,(j*12)+2,3,(j*12)+12);


                    //设置行高
                    sheet.setRowView((j*12)+1, 600);
                    sheet.setRowView((j*12)+2, 900);
                    sheet.setRowView((j*12)+3, 900);
                    sheet.setRowView((j*12)+4, 900);
                    sheet.setRowView((j*12)+5, 600);
                    sheet.setRowView((j*12)+6, 350);
                    sheet.setRowView((j*12)+7, 350);
                    sheet.setRowView((j*12)+8, 350);
                    sheet.setRowView((j*12)+9, 1200);
                    sheet.setRowView((j*12)+10, 900);
                    sheet.setRowView((j*12)+11, 900);
                    sheet.setRowView((j*12)+12, 1600);


                    sheet.addCell(new Label(0, (j*12)+2, bcTdlyEntity.getXzq(), arial12format));//arial10format
                    sheet.addCell(new Label(1, (j*12)+2, bcTdlyEntity.getZhen(), arial12format));//arial10format
                    sheet.addCell(new Label(2, (j*12)+2, bcTdlyEntity.getXzqmc(), arial12format));//arial10format
                    sheet.addCell(new Label(3, (j*12)+2, bcTdlyEntity.getYears(), arial13format));//arial10format
                    sheet.addCell(new Label(4, (j*12)+2, "一", arial13format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+2, "农用地", arial13format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+2, bcTdlyEntity.getNydmj(), arial13format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+2, "--", arial13format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+2, "--", arial13format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+2, "确权确地"+bcTdlyEntity.getNydqd()+"亩\n确权确利"+bcTdlyEntity.getNydql()
                            +"亩\n确权确股"+bcTdlyEntity.getNydqg()+"亩", arial12format));//arial10format


                    sheet.addCell(new Label(4, (j*12)+3, "1", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+3, "耕地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+3, bcTdlyEntity.getGdmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+3, "✧ 自种"+bcTdlyEntity.getGdzzmj()
                            +"亩\n✧ 流转"+bcTdlyEntity.getGdlzmj()+"亩\n✧ 其他"+bcTdlyEntity.getGdqtmj()+"亩", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+3, bcTdlyEntity.getGdjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+3, bcTdlyEntity.getGdbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+4, "2", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+4, "园地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+4, bcTdlyEntity.getYdmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+4, "✧ 自种"+bcTdlyEntity.getYdzzmj()
                            +"亩\n✧ 流转"+bcTdlyEntity.getYdlzmj()+"亩\n✧ 其他"+bcTdlyEntity.getYdqtmj()+"亩", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+4, bcTdlyEntity.getYdjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+4, bcTdlyEntity.getYdbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+5, "3", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+5, "林地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+5, bcTdlyEntity.getLdmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+5, "✧ 无经营"+bcTdlyEntity.getLdwjy()
                            +"亩\n✧ 林下种养殖"+bcTdlyEntity.getLdlxzyz()+"亩", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+5, bcTdlyEntity.getLdjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+5, bcTdlyEntity.getLdbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+6, "4", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+6, "设施农用地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+6, bcTdlyEntity.getNyjsmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+6, "--", arial12format));//arial10format bcTdlyEntity.getNyjssyqk()
                    sheet.addCell(new Label(8, (j*12)+6, "--", arial12format));//arial10format bcTdlyEntity.getNyjsjg()
                    sheet.addCell(new Label(9, (j*12)+6, bcTdlyEntity.getNyjsbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+7, "5", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+7, "其他农用地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+7, bcTdlyEntity.getQtnydmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+7, "--", arial12format));//arial10format bcTdlyEntity.getQtnydsyqk()
                    sheet.addCell(new Label(8, (j*12)+7, "--", arial12format));//arial10format bcTdlyEntity.getQtnydjg()
                    sheet.addCell(new Label(9, (j*12)+7, bcTdlyEntity.getQtnydbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+8, "二", arial13format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+8, "建设用地", arial13format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+8, bcTdlyEntity.getJsydmj(), arial13format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+8, "--", arial13format));//arial10format bcTdlyEntity.getJsydsyqk()
                    sheet.addCell(new Label(8, (j*12)+8, "--", arial13format));//arial10format bcTdlyEntity.getJsydjg()
                    sheet.addCell(new Label(9, (j*12)+8, "其中，规划批复使用面积"+bcTdlyEntity.getJsydpf()+"亩", arial13format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+9, "6", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+9, "居住用地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+9, bcTdlyEntity.getJzydmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+9, " 其中：农村住宅用地\n✧ 自住"+bcTdlyEntity.getJzydzz()
                            +"宗\n✧ 出租"+bcTdlyEntity.getJzydcz()+"宗\n✧ 其他"+bcTdlyEntity.getJzydqt()+"宗", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+9, bcTdlyEntity.getJzydjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+9, "其中，改建为民宿"+bcTdlyEntity.getJzydms()+"宗", arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+10, "7", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+10, "公共管理与公共服务用地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+10, bcTdlyEntity.getGgydmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+10, "✧ 自用"+bcTdlyEntity.getGgydzz()
                            +"亩\n✧ 出租"+bcTdlyEntity.getGgydcz()+"亩\n✧ 其他"+bcTdlyEntity.getGgydqt()+"亩", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+10, bcTdlyEntity.getGgydlzjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+10, bcTdlyEntity.getGgydbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+11, "8", arial12format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+11, "集体经营性建设用地", arial12format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+11, bcTdlyEntity.getJtydmj(), arial12format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+11, "✧ 自用"+bcTdlyEntity.getJtydzz()
                            +"亩\n✧ 出租"+bcTdlyEntity.getJtydcz()+"亩\n✧ 其他"+bcTdlyEntity.getJtydqt()+"亩", arial12format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+11, bcTdlyEntity.getJtydjg(), arial12format));//arial10format
                    sheet.addCell(new Label(9, (j*12)+11, bcTdlyEntity.getJtydbz(), arial12format));//arial10format

                    sheet.addCell(new Label(4, (j*12)+12, "三", arial13format));//arial10format
                    sheet.addCell(new Label(5, (j*12)+12, "土地总面积", arial13format));//arial10format
                    sheet.addCell(new Label(6, (j*12)+12, bcTdlyEntity.getTdzmj(), arial13format));//arial10format
                    sheet.addCell(new Label(7, (j*12)+12, "--", arial13format));//arial10format
                    sheet.addCell(new Label(8, (j*12)+12, "--", arial13format));//arial10format
                    //2.永久基本农田"+bcTdlyEntity.getYjjbnt()+";"
                    sheet.addCell(new Label(9, (j*12)+12, "其中:1.国有地"+bcTdlyEntity.getGymj()+"亩;"+
                            "2.平原造林(包括山区台地)"+bcTdlyEntity.getPyzl()+"亩;3.从经营方式看,农用地中，家庭经营"+bcTdlyEntity.getJtjy()
                            +"亩;集体经营"+bcTdlyEntity.getJtjy1()+"亩;" +
                            "大户承包"+bcTdlyEntity.getDhcb()+"亩;对外租赁"+bcTdlyEntity.getDwzl()+"亩;其他"+bcTdlyEntity.getQtmj()+"亩。", arial12format));//arial10format



                    //设置行高
                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//土地征占情况
    public static <T> void writeObjListToExcelTdzzqk(List<T> objList1, String fileName,
                                                     Context c,OnOpenFileLinear onOpenFileLinear) {//List<T> objList,
        if (objList1 != null && objList1.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,21,0);
                sheet.addCell(new Label(0, 0, "村域内2000年以来土地征占情况", arial13format));//arial10format
                sheet.addCell(new Label(22, 0, "单位:亩、万元", arial12format));
//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.mergeCells(0,1,0,3);
                sheet.mergeCells(1,1,1,3);
                sheet.mergeCells(2,1,2,3);
                sheet.mergeCells(3,1,3,3);
                sheet.mergeCells(4,1,4,3);
                sheet.mergeCells(5,1,5,3);
                sheet.mergeCells(6,1,11,1);
                sheet.mergeCells(12,1,12,3);
                sheet.mergeCells(13,1,22,1);
                sheet.mergeCells(6,2,7,2);
                sheet.mergeCells(8,2,9,2);
                sheet.mergeCells(10,2,11,2);
                sheet.mergeCells(13,2,14,2);
                sheet.mergeCells(15,2,16,2);
                sheet.mergeCells(17,2,18,2);
                sheet.mergeCells(19,2,20,2);
                sheet.mergeCells(21,2,22,2);

                /*sheet.mergeCells(20,1,22,1);
                sheet.mergeCells(20,2,20,3);
                sheet.mergeCells(21,2,21,3);
                sheet.mergeCells(22,2,22,3);*/


                sheet.addCell(new Label(0, 1, "区", arial12format));
                sheet.addCell(new Label(1, 1, "镇", arial12format));
                sheet.addCell(new Label(2, 1, "村", arial12format));
                sheet.addCell(new Label(3, 1, "年份", arial12format));
                sheet.addCell(new Label(4, 1, "征、占地时间(年)", arial12format));
                sheet.addCell(new Label(5, 1, "征地面积", arial13format));
                sheet.addCell(new Label(6, 1, "其中：", arial10format));
                sheet.addCell(new Label(12, 1, "占地面积", arial13format));
                sheet.addCell(new Label(13, 1, "其中：", arial10format));
//                sheet.addCell(new Label(19, 1, "行政区", arial12format));

                sheet.addCell(new Label(6, 2, "1.公益性征地", arial10format));
                sheet.addCell(new Label(8, 2, "2.开发建设征地", arial10format));
                sheet.addCell(new Label(10, 2, "3.其他征地", arial10format));
                sheet.addCell(new Label(13, 2, "1.基础设施占地", arial10format));
                sheet.addCell(new Label(15, 2, "2.军队、政府占地", arial10format));
                sheet.addCell(new Label(17, 2, "3.开发区占地", arial10format));
                sheet.addCell(new Label(19, 2, "4.公益事业占地", arial10format));
                sheet.addCell(new Label(21, 2, "5.其他占地", arial10format));
//                sheet.addCell(new Label(20, 2, "区", arial12format));
//                sheet.addCell(new Label(21, 2, "镇", arial12format));
//                sheet.addCell(new Label(22, 2, "村", arial12format));

                sheet.addCell(new Label(6, 3, "面积", arial12format));
                sheet.addCell(new Label(7, 3, "补偿费", arial12format));
                sheet.addCell(new Label(8, 3, "面积", arial12format));
                sheet.addCell(new Label(9, 3, "补偿费", arial12format));
                sheet.addCell(new Label(10, 3, "面积", arial12format));
                sheet.addCell(new Label(11, 3, "补偿费", arial12format));
                sheet.addCell(new Label(13, 3, "面积", arial12format));
                sheet.addCell(new Label(14, 3, "补偿费", arial12format));

                sheet.addCell(new Label(15, 3, "面积", arial12format));
                sheet.addCell(new Label(16, 3, "补偿费", arial12format));
                sheet.addCell(new Label(17, 3, "面积", arial12format));
                sheet.addCell(new Label(18, 3, "补偿费", arial12format));
                sheet.addCell(new Label(19, 3, "面积", arial12format));
                sheet.addCell(new Label(20, 3, "补偿费", arial12format));
                sheet.addCell(new Label(21, 3, "面积", arial12format));
                sheet.addCell(new Label(22, 3, "补偿费", arial12format));

                sheet.setColumnView(22, "单位:亩、万元".length() + 8);

                List<BczzdqkEntity> bczdqkEntities = new ArrayList<>();
//                List<BczhandqkEntity> bczhandqkEntities = new ArrayList<>();

               /* int count = 4;
                for (int i = 0; i < objList1.size(); i++) {
                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList1.get(i);
                    String toJson = new Gson().toJson(bcjcBbBean.getList());
                    bczdqkEntities.addAll(new Gson().fromJson(toJson, new TypeToken<List<BczzdqkEntity>>() {}.getType()));
                    sheet.mergeCells(0,count,0,count+bcjcBbBean.getList().size()-1);
                    count = count+bcjcBbBean.getList().size();
                }*/
                String toJson = new Gson().toJson(objList1);
                bczdqkEntities = new Gson().fromJson(toJson, new TypeToken<List<BczzdqkEntity>>() {}.getType());


                for (int j = 0; j < bczdqkEntities.size(); j++) {
                    BczzdqkEntity bczdqkEntity = (BczzdqkEntity) bczdqkEntities.get(j);
//                    BczhandqkEntity bczhandqkEntity = (BczhandqkEntity) bczhandqkEntities.get(j);
                    List<String> list = new ArrayList<>();
                    list.add(bczdqkEntity.getXzq());
                    list.add(bczdqkEntity.getZhen());
                    list.add(bczdqkEntity.getXzqmc());
                    list.add(bczdqkEntity.getYears());
                    list.add(bczdqkEntity.getZdsj());//+"、"+bczhandqkEntity.getZdsj()
                    list.add(bczdqkEntity.getZdmj());
                    list.add(bczdqkEntity.getGyzdmj());
                    list.add(bczdqkEntity.getGybcf());
                    list.add(bczdqkEntity.getKfjsmj());
                    list.add(bczdqkEntity.getKfjsbcf());
                    list.add(bczdqkEntity.getQtzdmj());
                    list.add(bczdqkEntity.getQtzdbcf());
                    list.add(bczdqkEntity.getZhandmj());
                    list.add(bczdqkEntity.getJcmj());
                    list.add(bczdqkEntity.getJcbcf());
                    list.add(bczdqkEntity.getJdmj());
                    list.add(bczdqkEntity.getJdbcf());
                    list.add(bczdqkEntity.getKfqmj());
                    list.add(bczdqkEntity.getKfqbcf());
                    list.add(bczdqkEntity.getGysymj());
                    list.add(bczdqkEntity.getGysybcf());
                    list.add(bczdqkEntity.getQtmj());
                    list.add(bczdqkEntity.getQtbcf());


                    for (int i = 0; i < list.size(); i++) {
                        sheet.addCell(new Label(i, j + 4, list.get(i), arial12format));//list.get(i)
                        sheet.setColumnView(i, 15);

                    }
                    //设置行高
                    sheet.setRowView(j + 4, 350);
                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村集体   产业变动情况
    public static <T> void writeObjListToExcelCjtcybd(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);

                sheet.setColumnView(0, 15);
                sheet.setColumnView(1, 20);
                sheet.setColumnView(2, 20);
                sheet.setColumnView(3, 20);


                String toJs = new Gson().toJson(objList);
                List<BcjcBbBean> bccgbqkBeans = new Gson().fromJson(toJs, new TypeToken<List<BcjcBbBean>>() {}.getType());
                for (int j = 0; j < bccgbqkBeans.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) bccgbqkBeans.get(j);
                    ArrayList<BccyjgEntity> zycp = new ArrayList<>();//stringArrayListHashMap.get("zycp");
                    ArrayList<BccyjgEntity> cz = new ArrayList<>();//stringArrayListHashMap.get("cz");
                    ArrayList<BccyjgEntity> xnbcjy = new ArrayList<>();//stringArrayListHashMap.get("xnbcjy");
                    ArrayList<BccyjgEntity> xnwljy = new ArrayList<>();//stringArrayListHashMap.get("xnwljy");
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BccyjgEntity> bccgbqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BccyjgEntity>>() {}.getType());

                    for (int f = 0; f < bccgbqkEntities.size(); f++) {
                        BccyjgEntity bccyjgEntity = bccgbqkEntities.get(f);
                        if (bccyjgEntity.getLx()==1){
                            zycp.add(bccyjgEntity);
                        }else
                            if (bccyjgEntity.getLx()==2){
                            cz.add(bccyjgEntity);
                        }else
                            if (bccyjgEntity.getLx()==3){
                            xnbcjy.add(bccyjgEntity);
                        }else
                            if (bccyjgEntity.getLx()==4){
                            xnwljy.add(bccyjgEntity);
                        }
                    }

                    int years = zycp.size();

                    //年限的数量  2
                    sheet.mergeCells(0,(j*17)+0,years*4+2,(j*17)+0);
                    sheet.addCell(new Label(0, (j*17)+0, "产业变动情况", arial13format));//arial10format
                    sheet.addCell(new Label((years*4)+3, (j*17)+0, "单位:万元、人", arial12format));



                    //合并
                    sheet.mergeCells(0,(j*17)+1,0,(j*17)+2);
                    sheet.mergeCells(1,(j*17)+1,1,(j*17)+2);
                    sheet.mergeCells(2,(j*17)+1,2,(j*17)+2);
                    sheet.mergeCells(3,(j*17)+1,3,(j*17)+2);
                    //年限的数量  2
                    sheet.mergeCells(0,(j*17)+3,0,(j*17)+15);//区
                    sheet.mergeCells(1,(j*17)+3,1,(j*17)+15);//镇
                    sheet.mergeCells(2,(j*17)+3,2,(j*17)+15);//村
                    sheet.mergeCells(4,(j*17)+1,(1*years)+3,(j*17)+1);//主要产品
                    sheet.mergeCells((1*years+4),(j*17)+1,(2*years+3),(j*17)+1);//收入
                    sheet.mergeCells((2*years+4),(j*17)+1,(3*years+3),(j*17)+1);//吸纳本村农民就业
                    sheet.mergeCells((3*years+4),(j*17)+1,(4*years+3),(j*17)+1);//吸纳外来农民就业
//                    sheet.mergeCells((4*years+2),(j*13)+1,(4*years+3+1),(j*13)+1);//行政区
//                    sheet.mergeCells((4*years+2),(j*13)+3,(4*years+1+1),(j*13)+13);//行政区
//                    sheet.mergeCells((4*years+2),(j*13)+3,(4*years+2),(j*13)+13);//行政区
//                    sheet.mergeCells((4*years+3),(j*13)+3,(4*years+3),(j*13)+13);//行政区

                    //赋值
                    sheet.addCell(new Label(3, (j*17)+1, "产业名称", arial13format));
                    sheet.addCell(new Label(4, (j*17)+1, "主要产品", arial13format));
                    sheet.addCell(new Label((1*years+4), (j*17)+1, "收入", arial13format));
                    sheet.addCell(new Label((2*years+4), (j*17)+1, "吸纳本村农民就业", arial13format));
                    sheet.addCell(new Label((3*years+4), (j*17)+1, "吸纳外村农民就业", arial13format));
                    sheet.addCell(new Label(2, (j*17)+1, "行政区", arial13format));
                    sheet.addCell(new Label(0, (j*17)+1, "区", arial12format));
                    sheet.addCell(new Label(1, (j*17)+1, "镇", arial12format));
                    sheet.addCell(new Label(2, (j*17)+1, "村", arial12format));

                    for (int f = 0; f < zycp.size(); f++) {
                        sheet.setColumnView(f+4, 15);
                        BccyjgEntity bccyjgEntity = zycp.get(f);
                        //1  2
                        sheet.addCell(new Label(f+4, (j*17)+2, bccyjgEntity.getYears(), arial12format));



                        sheet.addCell(new Label(f+4, (j*17)+3, "--", arial13format));//bccyjgEntity.getDecy()
                        sheet.addCell(new Label(f+4, (j*17)+4, "--", arial12format));//bccyjgEntity.getZzy()
                        sheet.addCell(new Label(f+4, (j*17)+5, bccyjgEntity.getZzyls(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+6, bccyjgEntity.getZzysc(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+7, "--", arial12format));//bccyjgEntity.getLy()
                        sheet.addCell(new Label(f+4, (j*17)+8, bccyjgEntity.getLylg(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+9, bccyjgEntity.getLysg(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+10, bccyjgEntity.getMy(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+11, bccyjgEntity.getYy(), arial12format));
                        sheet.addCell(new Label(f+4, (j*17)+12, bccyjgEntity.getDecy(), arial13format));
                        sheet.addCell(new Label(f+4, (j*17)+13, "--", arial13format));//bccyjgEntity.getDscy()
                        sheet.addCell(new Label(f+4, (j*17)+14, bccyjgEntity.getDscms(), arial13format));
                        sheet.addCell(new Label(f+4, (j*17)+15, bccyjgEntity.getDscfd(), arial13format));
//                        sheet.addCell(new Label(f+2, (j*17)+16, bccyjgEntity.getDscqt(), arial13format));
                    }
                    for (int f = 0; f < cz.size(); f++) {
                        sheet.setColumnView(years+f+4, 15);
                        BccyjgEntity bccyjgEntity = cz.get(f);
                        //1  2
                        sheet.addCell(new Label(years+f+4, (j*17)+2, bccyjgEntity.getYears(), arial12format));

                        sheet.addCell(new Label(years+f+4, (j*17)+3, bccyjgEntity.getDycy(), arial13format));
                        sheet.addCell(new Label(years+f+4, (j*17)+4, bccyjgEntity.getZzy(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+5, bccyjgEntity.getZzyls(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+6, bccyjgEntity.getZzysc(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+7, bccyjgEntity.getLy(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+8, bccyjgEntity.getLylg(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+9, bccyjgEntity.getLysg(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+10, bccyjgEntity.getMy(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+11, bccyjgEntity.getYy(), arial12format));
                        sheet.addCell(new Label(years+f+4, (j*17)+12, bccyjgEntity.getDecy(), arial13format));
                        sheet.addCell(new Label(years+f+4, (j*17)+13, bccyjgEntity.getDscy(), arial13format));
                        sheet.addCell(new Label(years+f+4, (j*17)+14, bccyjgEntity.getDscms(), arial13format));
                        sheet.addCell(new Label(years+f+4, (j*17)+15, bccyjgEntity.getDscfd(), arial13format));
//                        sheet.addCell(new Label(years+f+2, (j*17)+16, bccyjgEntity.getDscqt(), arial13format));
                    }
                    for (int f = 0; f < xnbcjy.size(); f++) {
                        sheet.setColumnView(years*2+f+4, 15);
                        BccyjgEntity bccyjgEntity = xnbcjy.get(f);
                        //1  2
                        sheet.addCell(new Label(years*2+f+4, (j*17)+2, bccyjgEntity.getYears(), arial12format));

                        sheet.addCell(new Label(years*2+f+4, (j*17)+3, bccyjgEntity.getDycy(), arial13format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+4, bccyjgEntity.getZzy(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+5, bccyjgEntity.getZzyls(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+6, bccyjgEntity.getZzysc(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+7, bccyjgEntity.getLy(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+8, bccyjgEntity.getLylg(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+9, bccyjgEntity.getLysg(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+10, bccyjgEntity.getMy(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+11, bccyjgEntity.getYy(), arial12format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+12, bccyjgEntity.getDecy(), arial13format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+13, bccyjgEntity.getDscy(), arial13format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+14, bccyjgEntity.getDscms(), arial13format));
                        sheet.addCell(new Label(years*2+f+4, (j*17)+15, bccyjgEntity.getDscfd(), arial13format));
//                        sheet.addCell(new Label(years*2+f+2, (j*17)+16, bccyjgEntity.getDscqt(), arial13format));
                    }
                    for (int f = 0; f < xnwljy.size(); f++) {
                        BccyjgEntity bccyjgEntity = xnwljy.get(f);
                        sheet.setColumnView(years*3+f+4, 15);
                        sheet.addCell(new Label(0, (j*17)+3, bccyjgEntity.getXzq(), arial13format));
                        sheet.addCell(new Label(1, (j*17)+3, bccyjgEntity.getZhen(), arial13format));
                        sheet.addCell(new Label(2, (j*17)+3, bccyjgEntity.getXzqmc(), arial13format));
                        //1  2
                        sheet.addCell(new Label(years*3+f+4, (j*17)+2, bccyjgEntity.getYears(), arial12format));

                        sheet.addCell(new Label(years*3+f+4, (j*17)+3, bccyjgEntity.getDycy(), arial13format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+4, bccyjgEntity.getZzy(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+5, bccyjgEntity.getZzyls(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+6, bccyjgEntity.getZzysc(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+7, bccyjgEntity.getLy(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+8, bccyjgEntity.getLylg(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+9, bccyjgEntity.getLysg(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+10, bccyjgEntity.getMy(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+11, bccyjgEntity.getYy(), arial12format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+12, bccyjgEntity.getDecy(), arial13format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+13, bccyjgEntity.getDscy(), arial13format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+14, bccyjgEntity.getDscms(), arial13format));
                        sheet.addCell(new Label(years*3+f+4, (j*17)+15, bccyjgEntity.getDscfd(), arial13format));
//                        sheet.addCell(new Label(years*3+f+2, (j*17)+16, bccyjgEntity.getDscqt(), arial13format));


                        //设置行高
                        sheet.setRowView((j*17)+0, 350);
                        sheet.setRowView((j*17)+1, 350);
                        sheet.setRowView((j*17)+2, 350);
                        sheet.setRowView((j*17)+3, 350);
                        sheet.setRowView((j*17)+4, 350);
                        sheet.setRowView((j*17)+5, 350);
                        sheet.setRowView((j*17)+6, 350);
                        sheet.setRowView((j*17)+7, 350);
                        sheet.setRowView((j*17)+8, 350);
                        sheet.setRowView((j*17)+9, 350);
                        sheet.setRowView((j*17)+10, 350);
                        sheet.setRowView((j*17)+11, 350);
                        sheet.setRowView((j*17)+12, 350);
                        sheet.setRowView((j*17)+13, 350);
                        sheet.setRowView((j*17)+14, 350);
                        sheet.setRowView((j*17)+15, 350);
//                        sheet.setRowView((j*17)+16, 350);
                    }
                    /*for (int i = 0; i < 4; i++) {//3

                        for (int f = 0; f < years; f++) {

                            sheet.setColumnView((i*f+f+1), 20);

                            //1  2
                            sheet.addCell(new Label((i*f+f+1), (j*13)+2, (i*f+f)+"年", arial12format));




                        }
                    }*/
//                    sheet.addCell(new Label((4*years+2), (j*13)+2, "镇", arial12format));
//                    sheet.addCell(new Label((4*years+3), (j*13)+2, "村", arial12format));
                    sheet.addCell(new Label(3, (j*17)+1, "产业名称", arial10format));
                    sheet.addCell(new Label(3, (j*17)+3, "一、第一产业", arial10format));
                    sheet.addCell(new Label(3, (j*17)+4, "1.种植业", arial12format));
                    sheet.addCell(new Label(3, (j*17)+5, "  其中：粮食", arial12format));
                    sheet.addCell(new Label(3, (j*17)+6, "        蔬菜", arial12format));
                    sheet.addCell(new Label(3, (j*17)+7, "2.林果业", arial12format));
                    sheet.addCell(new Label(3, (j*17)+8, "  其中：干果", arial12format));
                    sheet.addCell(new Label(3, (j*17)+9, "        鲜果", arial12format));
                    sheet.addCell(new Label(3, (j*17)+10, "3.畜牧业", arial12format));
                    sheet.addCell(new Label(3, (j*17)+11, "4.渔业", arial12format));
                    sheet.addCell(new Label(3, (j*17)+12, "二、第二产业", arial10format));
                    sheet.addCell(new Label(3, (j*17)+13, "三、第三产业", arial10format));
                    sheet.addCell(new Label(3, (j*17)+14, "1.民宿", arial12format));
                    sheet.addCell(new Label(3, (j*17)+15, "2.餐饮", arial12format));
//                    sheet.addCell(new Label(1, (j*17)+16, "3.其他", arial10format));


//                    sheet.setColumnView((years*4)+1, "单位:万元、人".length() + 7);
                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村集体经济组织治理及资产经营情况            集体资产情况
    public static <T> void writeObjListToExcelJtzcqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);

                sheet.setColumnView(3, 25);

                //年限的数量  2
//                sheet.setColumnView((years*2)+2, "单位:万元、人、次".length() + 7);


                String toJ = new Gson().toJson(objList);
                List<BcjcBbBean> bcjtzcBeans = new Gson().fromJson(toJ, new TypeToken<List<BcjcBbBean>>() {}.getType());
                for (int j = 0; j < bcjtzcBeans.size(); j++) {

                    int years = 0;
                    BcjcBbBean bcjcBbBean = (BcjcBbBean) bcjtzcBeans.get(j);
//                    List<BcrkbdqkEntity> bcrkbdqkEntities = projectBean.getList();
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcjtzcEntity> bcjtzcEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjtzcEntity>>() {}.getType());



                    years = bcjtzcEntities.size();
                    sheet.setColumnView(years+4, 25);
                    /*List<BcjtzcEntity> bcjtzcEntities = new ArrayList<>();
                    bcjtzcEntities.add(new BcjtzcEntity());
                    bcjtzcEntities.add(new BcjtzcEntity());
                    bcjtzcEntities.add(new BcjtzcEntity());
                    bcjtzcEntities.add(new BcjtzcEntity());*/

                    sheet.mergeCells(0,j*23+0,years*2+3,j*23+0);
                    sheet.addCell(new Label(0, j*23+0, "产业变动情况", arial13format));//arial10format
                    sheet.addCell(new Label((years*2)+4, j*23+0, "单位:万元、人、次", arial12format));

                    /*sheet.mergeCells(4,j*23+2,years+3,j*23+2);
                    sheet.mergeCells(4,j*23+3,years+3,j*23+3);
                    sheet.mergeCells(3,j*23+4,3,j*23+6);
                    sheet.mergeCells(4,j*23+4,years+3,j*23+6);
                    sheet.mergeCells(4,j*23+7,years+3,j*23+7);
                    sheet.mergeCells(4,j*23+8,years+3,j*23+8);*/
                    sheet.mergeCells(3,j*23+4,3,j*23+6);

                    sheet.mergeCells(0,j*23+2,0,j*23+21);//区
                    sheet.mergeCells(1,j*23+2,1,j*23+21);//镇
                    sheet.mergeCells(2,j*23+2,2,j*23+21);//村
//                    sheet.mergeCells((years*2)+4,j*23+2,(years*2)+4,j*23+21);
//                    sheet.mergeCells((years*2)+5,j*23+2,(years*2)+5,j*23+21);

                    sheet.addCell(new Label(3, j*23+1, "项目", arial13format));
                    sheet.addCell(new Label(years+4, j*23+1, "项目", arial13format));


                    sheet.addCell(new Label(0, j*23+1, "区", arial13format));
                    sheet.addCell(new Label(1, j*23+1, "镇", arial13format));
                    sheet.addCell(new Label(2, j*23+1, "村", arial13format));
//                    sheet.addCell(new Label(years*2+4, j*23+1, "镇", arial13format));
//                    sheet.addCell(new Label(years*2+5, j*23+1, "村", arial13format));


                    sheet.addCell(new Label(3, j*23+2, "一、集体经济组织(名称)", arial13format));
                    sheet.addCell(new Label(3, j*23+3, "1.成立时间", arial12format));
                    sheet.addCell(new Label(3, j*23+4, "2.是否进行法人主体登记注册?(A 是,在农业经营部门登记;B 是,在市场主管部门登记;C 否)", arial12format));
                    sheet.addCell(new Label(3, j*23+7, "3.改革前一年资产总额", arial12format));
                    sheet.addCell(new Label(3, j*23+8, " 其中:净资产总额", arial12format));
                    sheet.addCell(new Label(3, j*23+9, "二、股东总数", arial13format));
                    sheet.addCell(new Label(3, j*23+10, "1.集体股东总数", arial12format));
                    sheet.addCell(new Label(3, j*23+11, "2.成员个人股东人数", arial12format));
                    sheet.addCell(new Label(3, j*23+12, " 其中:[30,60]岁股东人数", arial12format));
                    sheet.addCell(new Label(3, j*23+13, "      在集体工作股东人数", arial12format));
                    sheet.addCell(new Label(3, j*23+14, "3.非成员个人股东人数", arial12format));
                    sheet.addCell(new Label(3, j*23+15, "三、股东总额", arial13format));
                    sheet.addCell(new Label(3, j*23+16, "1.集体股东股本", arial12format));
                    sheet.addCell(new Label(3, j*23+17, "2.成员个人股东股本", arial12format));
                    sheet.addCell(new Label(3, j*23+18, "3.非成员个人股东股本", arial12format));
                    sheet.addCell(new Label(3, j*23+19, "4.其他股东股本", arial12format));
                    sheet.addCell(new Label(3, j*23+20, "四、经营管理绩效", arial13format));
                    sheet.addCell(new Label(3, j*23+21, "1.总资产", arial12format));

                    sheet.addCell(new Label(years+4, j*23+2, "其中,经营性资产", arial12format));
//                    sheet.addCell(new Label(years+2, j*23+3, " (1)经营性物业面积", arial12format));
//                    sheet.addCell(new Label(years+2, j*23+4, " (2)出租物业面积", arial12format));
                    sheet.addCell(new Label(years+4, j*23+3, "2.净资产", arial12format));
                    sheet.addCell(new Label(years+4, j*23+4, "3.经营性收入", arial12format));
                    sheet.addCell(new Label(years+4, j*23+5, "4.利润总额", arial12format));
                    sheet.addCell(new Label(years+4, j*23+6, "5.净利润", arial12format));
                    sheet.addCell(new Label(years+4, j*23+7, "6.股金分红总额", arial12format));
                    sheet.addCell(new Label(years+4, j*23+8, "其中:成员个人股东分红金额", arial12format));
                    sheet.addCell(new Label(years+4, j*23+9, "     非成员个人股东分红金额", arial12format));
                    sheet.addCell(new Label(years+4, j*23+10, "7.福利分配总额", arial12format));
                    sheet.addCell(new Label(years+4, j*23+11, "补充材料", arial13format));
                    sheet.addCell(new Label(years+4, j*23+12, "1.应交税费", arial12format));
                    sheet.addCell(new Label(years+4, j*23+13, "(1)应交增值税", arial12format));
                    sheet.addCell(new Label(years+4, j*23+14, "(2)应交土地增值税", arial12format));
                    sheet.addCell(new Label(years+4, j*23+15, "(3)应交房产税", arial12format));
                    sheet.addCell(new Label(years+4, j*23+16, "(4)应交所得税", arial12format));
                    sheet.addCell(new Label(years+4, j*23+17, "(5)其他税费", arial12format));
                    sheet.addCell(new Label(years+4, j*23+18, "2.扩大再生产投入", arial12format));
                    sheet.addCell(new Label(years+4, j*23+19, "公益事业支出", arial12format));
                    sheet.addCell(new Label(years+4, j*23+20, "", arial12format));
                    sheet.addCell(new Label(years+4, j*23+21, "", arial12format));
//                    sheet.addCell(new Label(years+2, j*23+22, "", arial12format));
//                    sheet.addCell(new Label(years+2, j*23+23, "", arial12format));

                    for (int i = 0; i < years; i++) {
                        sheet.mergeCells(i+4,j*23+4,i+4,j*23+6);
                        BcjtzcEntity bcjtzcEntity = bcjtzcEntities.get(i);
                        sheet.addCell(new Label(i+4, j*23+1, bcjtzcEntity.getYears(), arial13format));
                        sheet.addCell(new Label(years+i+5, j*23+1, bcjtzcEntity.getYears(), arial13format));
                        sheet.addCell(new Label(0, j*23+2, bcjtzcEntity.getXzq(), arial13format));
                        sheet.addCell(new Label(1, j*23+2, bcjtzcEntity.getZhen(), arial13format));
                        sheet.addCell(new Label(2, j*23+2, bcjtzcEntity.getXzqmc(), arial13format));

                        sheet.addCell(new Label(i+4, j*23+2, bcjtzcEntity.getJjzzmc(), arial13format));
                        sheet.addCell(new Label(i+4, j*23+3, bcjtzcEntity.getClsj(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+4, bcjtzcEntity.getZtdjzc(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+7, bcjtzcEntity.getGgqzcze(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+8, bcjtzcEntity.getGgqjzcze(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+9, bcjtzcEntity.getGdzs(), arial13format));
                        sheet.addCell(new Label(i+4, j*23+10, bcjtzcEntity.getJtgdzs(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+11, bcjtzcEntity.getGrgdrs(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+12, bcjtzcEntity.getLsgdrs(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+13, bcjtzcEntity.getJtgzgdrs(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+14, bcjtzcEntity.getFcygrgdrs(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+15, bcjtzcEntity.getGbze(), arial13format));
                        sheet.addCell(new Label(i+4, j*23+16, bcjtzcEntity.getJtgdgb(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+17, bcjtzcEntity.getGrgdgb(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+18, bcjtzcEntity.getFcygrgdgb(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+19, bcjtzcEntity.getQtgdgb(), arial12format));
                        sheet.addCell(new Label(i+4, j*23+20, bcjtzcEntity.getJygljx(), arial13format));
                        sheet.addCell(new Label(i+4, j*23+21, bcjtzcEntity.getZzc(), arial12format));


                        sheet.addCell(new Label(years+5+i, j*23+2, bcjtzcEntity.getJyxzc(), arial12format));
//                        sheet.addCell(new Label(years+3+i, j*23+3, bcjtzcEntity.getJyxwymj(), arial12format));
//                        sheet.addCell(new Label(years+3+i, j*23+4, bcjtzcEntity.getCzwymj(), arial12format));

                        sheet.addCell(new Label(years+5+i, j*23+3, bcjtzcEntity.getJzc(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+4, bcjtzcEntity.getJyxsr(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+5, bcjtzcEntity.getLrze(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+6, bcjtzcEntity.getJlr(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+7, bcjtzcEntity.getGjfhze(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+8,  bcjtzcEntity.getGrgdfh(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+9,  bcjtzcEntity.getFcygrgdfh(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+10,  bcjtzcEntity.getFlfp(), arial12format));//getTzsy
                        sheet.addCell(new Label(years+5+i, j*23+11, bcjtzcEntity.getBccl(), arial13format));
                        sheet.addCell(new Label(years+5+i, j*23+12, bcjtzcEntity.getYjsf(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+13, bcjtzcEntity.getYjzzs(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+14, bcjtzcEntity.getYjtdzzs(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+15, bcjtzcEntity.getYjfcs(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+16, bcjtzcEntity.getYjsds(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+17, bcjtzcEntity.getQtsf(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+18, bcjtzcEntity.getKdzsczc(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+19, bcjtzcEntity.getGysyzc(), arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+20, "", arial12format));
                        sheet.addCell(new Label(years+5+i, j*23+21, "", arial12format));


                    }


                    //设置行高
                    sheet.setRowView(j*23+1, 350);
                    sheet.setRowView(j*23+2, 450);
                    sheet.setRowView(j*23+3, 350);
                    sheet.setRowView(j*23+4, 350);
                    sheet.setRowView(j*23+5, 350);
                    sheet.setRowView(j*23+6, 350);
                    sheet.setRowView(j*23+7, 350);
                    sheet.setRowView(j*23+8, 350);
                    sheet.setRowView(j*23+9, 350);
                    sheet.setRowView(j*23+10, 350);
                    sheet.setRowView(j*23+11, 350);
                    sheet.setRowView(j*23+12, 350);
                    sheet.setRowView(j*23+13, 350);
                    sheet.setRowView(j*23+14, 350);
                    sheet.setRowView(j*23+15, 350);
                    sheet.setRowView(j*23+16, 350);
                    sheet.setRowView(j*23+17, 350);
                    sheet.setRowView(j*23+18, 350);
                    sheet.setRowView(j*23+19, 350);
                    sheet.setRowView(j*23+20, 350);
                    sheet.setRowView(j*23+21, 350);
                }
                for (int i = 0; i < 1000; i++) {
                    int columnWidth = sheet.getColumnWidth(i);
                    if (columnWidth<15){
                        sheet.setColumnView(i, 15);
                    }
                }
                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村经济总收入情况
    public static <T> void writeObjListToExcelCjjzsrqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,14,0);
                sheet.addCell(new Label(0, 0, "村经济总收入情况", arial13format));//arial10format
                sheet.addCell(new Label(15, 0, "单位:万元", arial12format));

                sheet.mergeCells(0,1,0,3);
                sheet.mergeCells(1,1,1,3);
                sheet.mergeCells(2,1,2,3);
                sheet.mergeCells(3,1,3,3);
                sheet.mergeCells(4,1,4,3);
                sheet.mergeCells(5,1,12,1);
                sheet.mergeCells(13,1,15,1);
//                sheet.mergeCells(13,1,15,1);

                sheet.mergeCells(5,2,5,3);
                sheet.mergeCells(6,2,7,2);
                sheet.mergeCells(8,2,8,3);
                sheet.mergeCells(9,2,12,2);

                sheet.mergeCells(13,2,13,3);
                sheet.mergeCells(14,2,14,3);
                sheet.mergeCells(15,2,15,3);
//                sheet.mergeCells(13,2,13,3);
//                sheet.mergeCells(14,2,14,3);
//                sheet.mergeCells(15,2,15,3);

//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,3,
                        new File(filePath));
                sheet.addImage(image);

                sheet.addCell(new Label(4, 1, "村经济总收入", arial13format));
                sheet.addCell(new Label(5, 1, "1.按经营层次分", arial13format));
                sheet.addCell(new Label(13, 1, "2.按产业分", arial13format));
//                sheet.addCell(new Label(13, 1, "行政区", arial13format));
                sheet.addCell(new Label(0, 1, "区", arial13format));
                sheet.addCell(new Label(1, 1, "镇", arial13format));
                sheet.addCell(new Label(2, 1, "村", arial13format));
                sheet.addCell(new Label(5, 2, "1.1 公有经济", arial13format));
                sheet.addCell(new Label(6, 2, "其中：", arial10format));
                sheet.addCell(new Label(6, 3, "村集体组织收入", arial12format));
                sheet.addCell(new Label(7, 3, "村集体企业收入", arial12format));
                sheet.addCell(new Label(8, 2, "1.2 非公有经济", arial13format));
                sheet.addCell(new Label(9, 2, "其中：", arial10format));
                sheet.addCell(new Label(9, 3, "农户", arial12format));
                sheet.addCell(new Label(10, 3, "农民合作社", arial12format));
                sheet.addCell(new Label(11, 3, "私营企业", arial12format));
                sheet.addCell(new Label(12, 3, "个体户", arial12format));
                sheet.addCell(new Label(13, 2, "一产收入", arial13format));
                sheet.addCell(new Label(14, 2, "二产收入", arial13format));
                sheet.addCell(new Label(15, 2, "三产收入", arial13format));

                sheet.setRowView(0, 350);
                sheet.setRowView(1, 350);
                sheet.setRowView(2, 350);
                sheet.setRowView(3, 350);
                sheet.setColumnView(13, "单位:万元".length() + 5);
                int count = 0;
                /*for (int j = 0; j < objList.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);

                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcjjzsrqkEntity> bcjjzsrqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjjzsrqkEntity>>() {}.getType());

                    sheet.mergeCells(0,count+4,0,count+4+bcjjzsrqkEntities.size()-1);*/

                String toJson1 = new Gson().toJson(objList);
                List<BcjjzsrqkEntity> bcjjzsrqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjjzsrqkEntity>>() {}.getType());
                    for (int f = 0; f < bcjjzsrqkEntities.size(); f++) {
                        sheet.setRowView(count + 4, 350);
                        count++;
                        BcjjzsrqkEntity bcjjzsrqkEntity = bcjjzsrqkEntities.get(f);
                        List<String> list = new ArrayList<>();
                        list.add(bcjjzsrqkEntity.getXzq());
                        list.add(bcjjzsrqkEntity.getZhen());
                        list.add(bcjjzsrqkEntity.getXzqmc());
                        list.add(bcjjzsrqkEntity.getYears());
                        list.add(bcjjzsrqkEntity.getCjjzsr());
                        list.add(bcjjzsrqkEntity.getGyjj());
                        list.add(bcjjzsrqkEntity.getCjtzzsr());
                        list.add(bcjjzsrqkEntity.getCjtqysr());

                        list.add(bcjjzsrqkEntity.getFgysr());
                        list.add(bcjjzsrqkEntity.getNhsr());
                        list.add(bcjjzsrqkEntity.getNmhzs());
                        list.add(bcjjzsrqkEntity.getSyqysr());
                        list.add(bcjjzsrqkEntity.getGtjj());
                        list.add(bcjjzsrqkEntity.getYcsr());
                        list.add(bcjjzsrqkEntity.getEcsr());
                        list.add(bcjjzsrqkEntity.getScsr());
                        for (int i = 0; i < list.size(); i++) {
                            sheet.addCell(new Label(i, count + 3, list.get(i), arial12format));//list.get(i)

                            sheet.setColumnView(i, 15);
                        }
                        //设置行高
                    }
//                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }
    @SuppressWarnings("unchecked")//村集体经济主要收入情况
    public static <T> void writeObjListToExcelCjtjjzzzysrqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,12,0);
                sheet.addCell(new Label(0, 0, "村经济总收入情况", arial13format));//arial10format
                sheet.addCell(new Label(13, 0, "单位:万元", arial12format));

                sheet.mergeCells(0,1,0,2);
                sheet.mergeCells(1,1,1,2);
                sheet.mergeCells(2,1,2,2);
                sheet.mergeCells(3,1,3,2);
                sheet.mergeCells(4,1,4,2);
                sheet.mergeCells(5,1,5,2);
                sheet.mergeCells(6,1,6,2);
                sheet.mergeCells(7,1,7,2);

                sheet.mergeCells(8,1,11,1);
                sheet.mergeCells(12,1,12,2);
                sheet.mergeCells(13,1,13,2);
//                sheet.mergeCells(12,1,12,2);
//                sheet.mergeCells(13,1,13,2);
//                sheet.mergeCells(14,1,14,2);


//                sheet.addCell(new Label(0, 1, "年份\\指标", arial12format));

                sheet.addCell(new Label(2, 1, "", arial12format));
                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,2,
                        new File(filePath));
                sheet.addImage(image);

                sheet.addCell(new Label(4, 1, "1.主营业务收入", arial13format));
                sheet.addCell(new Label(5, 1, "2.其他业务收入(地、山、林、房等出租)", arial13format));
                sheet.addCell(new Label(6, 1, "3.投资收益(利息、股权收益等)", arial13format));
                sheet.addCell(new Label(7, 1, "4.营业外收入", arial13format));
                sheet.addCell(new Label(8, 1, "其中，补贴收入", arial13format));
                sheet.addCell(new Label(8, 2, "村级公益事业专项补助经费", arial12format));
                sheet.addCell(new Label(9, 2, "村党组织服务群众经费", arial12format));
                sheet.addCell(new Label(10, 2, "美丽乡村建设与管护资金", arial12format));
                sheet.addCell(new Label(11, 2, "其他政策性补助", arial12format));
                sheet.addCell(new Label(12, 1, "其他(注明来源)", arial13format));
                sheet.addCell(new Label(13, 1, "合计", arial13format));
                sheet.addCell(new Label(0, 1, "区", arial13format));
                sheet.addCell(new Label(1, 1, "镇", arial13format));
                sheet.addCell(new Label(2, 1, "村", arial13format));
//                sheet.addCell(new Label(13, 1, "镇", arial13format));
//                sheet.addCell(new Label(14, 1, "村", arial13format));

                sheet.setRowView(0, 350);
                sheet.setRowView(1, 350);
                sheet.setRowView(2, 700);//设置行高
                sheet.setColumnView(13, "单位:万元".length() + 5);


                int count = 0;

               /* for (int j = 0; j < objList.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);
//                    List<BcrkbdqkEntity> bcrkbdqkEntities = projectBean.getList();
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcjtjjsrqkEntity> bcjtjjsrqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjtjjsrqkEntity>>() {}.getType());

                    sheet.mergeCells(0,count+3,0,count+3+bcjtjjsrqkEntities.size()-1);*/

                String toJson1 = new Gson().toJson(objList);
                List<BcjtjjsrqkEntity> bcjtjjsrqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjtjjsrqkEntity>>() {}.getType());
                    for (int f = 0; f < bcjtjjsrqkEntities.size(); f++) {
                        //设置行高
                        sheet.setRowView(count + 3, 350);
                        count++;
                        BcjtjjsrqkEntity bcjtjjsrqkEntity = bcjtjjsrqkEntities.get(f);

                        List<String> list = new ArrayList<>();
                        list.add(bcjtjjsrqkEntity.getXzq());
                        list.add(bcjtjjsrqkEntity.getZhen());
                        list.add(bcjtjjsrqkEntity.getXzqmc());
                        list.add(bcjtjjsrqkEntity.getYears());
                        list.add(bcjtjjsrqkEntity.getZyywsr());
                        list.add(bcjtjjsrqkEntity.getQtywsr());
                        list.add(bcjtjjsrqkEntity.getTzsy());
                        list.add(bcjtjjsrqkEntity.getYywsr());
                        list.add(bcjtjjsrqkEntity.getGybzjf());
                        list.add(bcjtjjsrqkEntity.getDzzfwjf());
                        list.add(bcjtjjsrqkEntity.getMlxcjswh());
                        list.add(bcjtjjsrqkEntity.getZcbz());
                        list.add(bcjtjjsrqkEntity.getQt());
                        list.add(bcjtjjsrqkEntity.getHj());
                        for (int i = 0; i < list.size(); i++) {
                            sheet.addCell(new Label(i, count + 2, list.get(i), arial12format));//list.get(i)

                            sheet.setColumnView(i, 17);
                        }

                    }

//                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }

    @SuppressWarnings("unchecked")//村集体经济主要支出情况
    public static <T> void writeObjListToExcelCjtjjzzzyzcqk(List<T> objList, String fileName, Context c,OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
                sheet.mergeCells(0,0,10,0);
                sheet.addCell(new Label(0, 0, "村集体经济组织主要支出情况", arial13format));//arial10format
                sheet.addCell(new Label(11, 0, "单位:万元", arial12format));
                sheet.setRowView(1, 700);//设置行高

                sheet.addCell(new Label(3, 1, "      指标\n年份  ", arial12format));
                String filePath = AppMenus.getInstance().getCardPath() + "/AndroidExcelDemo/"+ AppCache.getInstance().getName()+"/xiexian.png";
                WritableImage image = new WritableImage(3,1,1,1,
                        new File(filePath));
                sheet.addImage(image);

                sheet.addCell(new Label(0, 1, "区", arial13format));
                sheet.addCell(new Label(1, 1, "镇", arial13format));
                sheet.addCell(new Label(2, 1, "村", arial13format));
                sheet.addCell(new Label(4, 1, "村干部补贴", arial13format));
                sheet.addCell(new Label(5, 1, "村级组织办公经费", arial13format));
                sheet.addCell(new Label(6, 1, "公共服务运行维护费", arial13format));
                sheet.addCell(new Label(7, 1, "公益性基础设施建设投入", arial13format));
                sheet.addCell(new Label(8, 1, "村级负担的其他水电费", arial13format));
                sheet.addCell(new Label(9, 1, "生产性(投资)支出", arial13format));
                sheet.addCell(new Label(10, 1, "其他(注明渠道)", arial13format));
                sheet.addCell(new Label(11, 1, "合计", arial13format));

                sheet.setColumnView(11, "单位:万元".length() + 5);

                int count = 0;

                /*for (int j = 0; j < objList.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) objList.get(j);
//                    List<BcrkbdqkEntity> bcrkbdqkEntities = projectBean.getList();
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcjtjjzcqkEntity> bcjtjjzcqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjtjjzcqkEntity>>() {}.getType());

                    sheet.mergeCells(0,count+2,0,count+2+bcjtjjzcqkEntities.size()-1);*/

                String toJson1 = new Gson().toJson(objList);
                List<BcjtjjzcqkEntity> bcjtjjzcqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcjtjjzcqkEntity>>() {}.getType());
                    for (int f = 0; f < bcjtjjzcqkEntities.size(); f++) {
                        //设置行高
                        sheet.setRowView(count + 2, 350);
                        count++;
                        BcjtjjzcqkEntity bcjtjjzcqkEntity = bcjtjjzcqkEntities.get(f);

                        List<String> list = new ArrayList<>();
                        list.add(bcjtjjzcqkEntity.getXzq());
                        list.add(bcjtjjzcqkEntity.getZhen());
                        list.add(bcjtjjzcqkEntity.getXzqmc());
                        list.add(bcjtjjzcqkEntity.getYears());
                        list.add(bcjtjjzcqkEntity.getCgbbt());
                        list.add(bcjtjjzcqkEntity.getCzzbgjf());
                        list.add(bcjtjjzcqkEntity.getGgyxwhf());
                        list.add(bcjtjjzcqkEntity.getGysstr());
                        list.add(bcjtjjzcqkEntity.getCjqtsdf());
                        list.add(bcjtjjzcqkEntity.getFl());
                        list.add(bcjtjjzcqkEntity.getQt());
                        list.add(bcjtjjzcqkEntity.getHj());

                        for (int i = 0; i < list.size(); i++) {
                            sheet.addCell(new Label(i, count + 1, list.get(i), arial12format));//list.get(i)

                            sheet.setColumnView(i, 15);
                        }
                    }
//                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }

    @SuppressWarnings("unchecked")//村集体经济运行情况监测问卷
    public static <T> void writeObjListToExcelFzyy(List<T> objList, ArrayList<OptionsEntity> optionsEntities, ArrayList<String> years,
                                                   String fileName, Context c, OnOpenFileLinear onOpenFileLinear) {
        if (objList != null && objList.size() > 0) {
            WritableWorkbook writebook = null;
            InputStream in = null;
            try {
                WorkbookSettings setEncode = new WorkbookSettings();
                setEncode.setEncoding(UTF8_ENCODING);
                in = new FileInputStream(new File(fileName));
                Workbook workbook = Workbook.getWorkbook(in);
                writebook = Workbook.createWorkbook(new File(fileName), workbook);
                WritableSheet sheet = writebook.getSheet(0);
//                sheet.mergeCells(0,0,10,0);
                sheet.addCell(new Label(0, 0, "区", arial13format));//arial10format
                sheet.addCell(new Label(1, 0, "镇", arial13format));//arial10format
                sheet.addCell(new Label(2, 0, "村", arial13format));//arial10format
                sheet.addCell(new Label(3, 0, "年份", arial13format));//arial10format
                sheet.addCell(new Label(4, 0, "村集体经济运行情况监测问卷", arial13format));//arial10format

                sheet.setColumnView(4, 90);


               /* List<BcfzyyEntity> bcfzyyEntities = new ArrayList<>();// type 1 2 3
                bcfzyyEntities.add(new BcfzyyEntity());
                bcfzyyEntities.add(new BcfzyyEntity());
                bcfzyyEntities.add(new BcfzyyEntity());
                bcfzyyEntities.add(new BcfzyyEntity());*/

               int count = 1;
                String toJn1 = new Gson().toJson(objList);
                List<BcjcBbBean> bcjtjjzcqkBeans = new Gson().fromJson(toJn1, new TypeToken<List<BcjcBbBean>>() {}.getType());
                for (int j = 0; j < bcjtjjzcqkBeans.size(); j++) {

                    BcjcBbBean bcjcBbBean = (BcjcBbBean) bcjtjjzcqkBeans.get(j);
                    String toJson1 = new Gson().toJson(bcjcBbBean.getList());
                    List<BcfzyyEntity> bcjtjjzcqkEntities = new Gson().fromJson(toJson1, new TypeToken<List<BcfzyyEntity>>() {}.getType());

                    HashMap<String,List<BcfzyyEntity>> bcjtjjzcqkEntities1 = new HashMap<>();
                    for (int i = 0; i < bcjtjjzcqkEntities.size(); i++) {
                        List<BcfzyyEntity> bcjtjjzcqkEntities2 = new ArrayList<>();
                        if (bcjtjjzcqkEntities1.get(bcjtjjzcqkEntities.get(i).getYears())!=null){
                            bcjtjjzcqkEntities2.addAll(bcjtjjzcqkEntities1.get(bcjtjjzcqkEntities.get(i).getYears()));
                        }
                        bcjtjjzcqkEntities2.add(bcjtjjzcqkEntities.get(i));
                        bcjtjjzcqkEntities1.put(bcjtjjzcqkEntities.get(i).getYears(),bcjtjjzcqkEntities2);
                    }

                    sheet.mergeCells(0,count,0,count+bcjtjjzcqkEntities1.size()*3-1);
                    sheet.mergeCells(1,count,1,count+bcjtjjzcqkEntities1.size()*3-1);
                    sheet.mergeCells(2,count,2,count+bcjtjjzcqkEntities1.size()*3-1);
                    for (int f = 0; f < years.size(); f++) {

                        List<BcfzyyEntity> bcfzyyEntities = bcjtjjzcqkEntities1.get(years.get(f));
                        if (bcfzyyEntities!=null&&bcfzyyEntities.size()>0){
                            sheet.addCell(new Label(0, count, bcfzyyEntities.get(0).getXzq(), arial13format));//arial10format
                            sheet.addCell(new Label(1, count, bcfzyyEntities.get(0).getZhen(), arial13format));//arial10format
                            sheet.addCell(new Label(2, count, bcfzyyEntities.get(0).getXzqmc(), arial13format));//arial10format
                            sheet.addCell(new Label(3, count,bcfzyyEntities.get(0).getYears(), arial13format));//arial10format
                            sheet.mergeCells(3,count,3,count+3-1);
                            String option1 = "";
                            String option2 = "";
                            String option3 = "";
                            for (int i = 0; i < bcfzyyEntities.size(); i++) {
                                BcfzyyEntity bcfzyyEntity = bcfzyyEntities.get(i);
                                String remark = "";
                                if (!bcfzyyEntity.getRemark().equals("")){
                                    remark = "("+bcfzyyEntity.getRemark()+")";
                                }
                                if (bcfzyyEntity.getType()==1){
                                    option1 = option1 + getWjName(optionsEntities,bcfzyyEntity.getOptions())+remark+"、";
                                }else if (bcfzyyEntity.getType()==2){
                                    option2 = option2 + getWjName(optionsEntities,bcfzyyEntity.getOptions())+remark+"、";
                                }else if (bcfzyyEntity.getType()==3){
                                    if (bcfzyyEntity.getOptions() == 26){
                                        option3 = option3 + getWjName(optionsEntities,bcfzyyEntity.getOptions())+
                                                "(需"+bcfzyyEntity.getArea()+"亩，用途"+bcfzyyEntity.getRemark()+")"+"、";
                                    }else {
                                        option3 = option3 + getWjName(optionsEntities,bcfzyyEntity.getOptions())+remark+"、";
                                    }
                                }
                            }

                            if (!option1.equals(""))
                            sheet.addCell(new Label(4, count, " 6.1 目前，本村集体产业发展的主要制约因素是："+
                                    option1.substring(0,option1.length()-1), arial15format));//arial10format
                            if (!option2.equals(""))
                            sheet.addCell(new Label(4, count+1, " 6.2 村庄未来计划发展的产业是："+
                                    option2.substring(0,option2.length()-1), arial15format));//arial10format
                            if (!option3.equals(""))
                            sheet.addCell(new Label(4, count+2, " 6.3 村庄产业发展，最需要哪些方面的支持："+
                                    option3.substring(0,option3.length()-1), arial15format));//arial10format

                            sheet.setRowView(count, 700);//设置行高
                            sheet.setRowView(count+1, 700);//设置行高
                            sheet.setRowView(count+2, 700);//设置行高
                            count = count+3;
                        }

                    }

                }

                writebook.write();
                ToastUtils.showShort("导出Excel成功");
            } catch (Exception e) {
//                ToastUtils.showShort("导出Excel失败");//+e.getMessage()
                e.printStackTrace();
            } finally {
                if (writebook != null) {
                    try {
                        writebook.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                onOpenFileLinear.onOpenFile();
            }

        }
    }

    private static byte[] InputStreamToByte(InputStream is) throws IOException {

        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();

        int ch;

        while ((ch = is.read()) != -1) {

            bytestream.write(ch);

        }

        byte imgdata[] = bytestream.toByteArray();

        bytestream.close();

        return imgdata;

    }

    private static String getWjName(ArrayList<OptionsEntity> optionsEntities,int options){
        String whName = "";
        for (int i = 0; i < optionsEntities.size(); i++) {
            OptionsEntity optionsEntity = optionsEntities.get(i);
            if (optionsEntity.getId()==options){
                whName = optionsEntity.getOptions();
            }
        }
        return whName;
    }



    public interface OnOpenFileLinear{
        void onOpenFile();
    }

    private static void copyAssetFilesToSDCard(Activity activity,File testFileOnSdCard, String FileToCopy) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = activity.getAssets().open(FileToCopy);
                    FileOutputStream fos = new FileOutputStream(testFileOnSdCard);
                    byte[] buffer = new byte[8192];
                    int read;
                    try {
                        while ((read = is.read(buffer)) != -1) {
                            fos.write(buffer, 0, read);
                        }
                    } finally {
                        fos.flush();
                        fos.close();
                        is.close();
                    }
                } catch (IOException e) {
                    Log.d("", "Can't copy test file onto SD card");
                }
            }
        }).start();
    }


}
