package com.jymj.zhglxt.zjd.fragment.yzt;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jymj.zhglxt.R;
import com.jymj.zhglxt.api.ApiConstants;
import com.jymj.zhglxt.bean.SuccessBean;
import com.jymj.zhglxt.ldrkgl.base.BaseNet;
import com.jymj.zhglxt.ldrkgl.home.adapter.PhotoAdapter;
import com.jymj.zhglxt.ldrkgl.home.adapter.RecyclerItemClickListener;
import com.jymj.zhglxt.util.AesEncryptUtils;
import com.jymj.zhglxt.util.CalendarUtil;
import com.jymj.zhglxt.util.CommenPop;
import com.jymj.zhglxt.util.OnCtDetailClickLinear;
import com.jymj.zhglxt.widget.SpinnerAdapter;
import com.jymj.zhglxt.zjd.activity.yzt.CtDetailActivity;
import com.jymj.zhglxt.zjd.adapter.yzt.CtglXRlvAdapter;
import com.jymj.zhglxt.zjd.bean.PQListBean;
import com.jymj.zhglxt.zjd.bean.yzt.CTGLPOINTBean;
import com.jymj.zhglxt.zjd.bean.yzt.CTPQLCBean;
import com.jymj.zhglxt.zjd.bean.yzt.FlagStatic;
import com.jymj.zhglxt.zjd.bean.yzt.PjProBean;
import com.jymj.zhglxt.zjd.bean.yzt.PjProjEntitys;
import com.jymj.zhglxt.zjd.bean.yzt.ProjspeedEnum;
import com.lwy.paginationlib.PaginationRecycleView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.setsuna.common.basebean.BaseRespose;
import com.setsuna.common.commonutils.ToastUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.json.JSONObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.PhotoPreview;

/**
 * A simple {@link Fragment} subclass.
 */
public class CtglFragment extends Fragment {

    private View mView;
    private MZBannerView mBanner;
    private LinearLayout mYewuctgl_ll;
    private LinearLayout yewuctgl_top;
    private TextView mCtglpq_fanhui;
    private PieChart ctglpq_zf_pct;
    private PieChart ctglpq_lc_pct;
    private RecyclerView ctglpq_lc_rlv;
    private XRecyclerView ctglpq_xm_rlv;
    private PaginationRecycleView pagination_rcv;
    private Button ctglpq_add;
    private CommenPop mAddPop;
    private ArrayList<String> selectedPhotos = new ArrayList<>();
    private PhotoAdapter mPhotoAdapter;
    private OnCtDetailClickLinear onCtDetailClickLinear;
    private int limit=20;
    private int page=1;

    public CtglFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_ctgl, container, false);
        }
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mBanner = mView.findViewById(R.id.yewuctgl_banner);
        mYewuctgl_ll = mView.findViewById(R.id.yewuctgl_ll);
        mCtglpq_fanhui = mView.findViewById(R.id.ctglpq_fanhui);
        ctglpq_zf_pct = mView.findViewById(R.id.ctglpq_zf_pct);
        ctglpq_lc_pct = mView.findViewById(R.id.ctglpq_lc_pct);
        ctglpq_lc_rlv = mView.findViewById(R.id.ctglpq_lc_rlv);
        ctglpq_xm_rlv = mView.findViewById(R.id.ctglpq_xm_rlv);
        pagination_rcv = mView.findViewById(R.id.pagination_rcv);
        yewuctgl_top = mView.findViewById(R.id.yewuctgl_top);
        ctglpq_add = mView.findViewById(R.id.ctglpq_add);

        mCtglpq_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FlagStatic.setGid(-1);
                onCtDetailClickLinear.onClick("");
                mYewuctgl_ll.setVisibility(View.GONE);
                mBanner.setVisibility(View.VISIBLE);
            }
        });
        ctglpq_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<CTGLPOINTBean.DataBean> dataBeanList = FlagStatic.getDataBeanList();
                if (dataBeanList.size() < 1) {
                    ToastUtils.showShort("请点击一个片区！");
                } else {
                    addPop(dataBeanList);
                    CommenPop.backgroundAlpha(0.5f, getActivity());
                    mAddPop.showAtLocation(yewuctgl_top, Gravity.CENTER, 0, 0);
                   /* String sss = null;
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("points", point);
                        String encrypt = AesEncryptUtils.encrypt(jsonObject.toString());//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                        sss = "{\"requestData\":\"" + encrypt + "\"}";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    OkGo.<String>post(ApiConstants.URL_QUERYPRPQ).upJson(sss).execute(new BaseCallBack<String>() {
                        @Override
                        public void onStart(Request<String, ? extends Request> request) {
                            super.onStart(request);
                        }

                        @Override
                        public void onSuccess(Response<String> response) {
                            super.onSuccess(response);
                            String body = response.body();
                            if (body != null) {
                                String decrypt = null;
                                try {
                                    decrypt = AesEncryptUtils.decrypt(response.body());

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                CTGLPOINTBean json = new Gson().fromJson(decrypt, new TypeToken<CTGLPOINTBean>() {
                                }.getType());

                                if (json.getCode() == 0) {

                                    if (json.getData() != null) {
                                        addPop(json.getData());
                                        CommenPop.backgroundAlpha(0.5f, getActivity());
                                        mAddPop.showAtLocation(yewuctgl_top, Gravity.CENTER, 0, 0);
                                    }
                                }else if (json.getCode() == 500){
                                    ToastUtils.showShort(json.getMsg());
                                }
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                        }
                    });*/


                }
            }
        });

    }

    public void setOnCtDetailClickLinear(OnCtDetailClickLinear onCtDetailClickLinear) {
        this.onCtDetailClickLinear = onCtDetailClickLinear;
    }

    public void getData(List<PQListBean.DataBean> dataBeans) {
        if (dataBeans.size() > 0) {

            mBanner.setDelayedTime(1000000);
            mBanner.setPages(dataBeans, new MZHolderCreator<BannerViewHolder>() {
                @Override
                public CtglFragment.BannerViewHolder createViewHolder() {
                    return new CtglFragment.BannerViewHolder();
                }
            });
        }
    }

    public class BannerViewHolder implements MZViewHolder<PQListBean.DataBean> {
        private PieChart item_ctgl_pct;
        private TextView banner_title;
        private TextView item_ctgl_zcbje;
        private TextView item_ctgl_zj;
        private TextView item_ctgl_cddw;
        private TextView item_ctgl_yc;
        private TextView item_ctgl_yzfje;
        private TextView item_ctgl_wc;
        private Button item_ctgl_btn;


        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_ctgllist, null);
            //mImageView = (ImageView) view.findViewById(R.id.banner_image);
            item_ctgl_pct = view.findViewById(R.id.item_ctgl_pct);
            banner_title = view.findViewById(R.id.item_ctgl_name);
            item_ctgl_zcbje = view.findViewById(R.id.item_ctgl_zcbje);
            item_ctgl_zj = view.findViewById(R.id.item_ctgl_zj);
            item_ctgl_cddw = view.findViewById(R.id.item_ctgl_cddw);
            item_ctgl_yc = view.findViewById(R.id.item_ctgl_yc);
            item_ctgl_yzfje = view.findViewById(R.id.item_ctgl_yzfje);
            item_ctgl_wc = view.findViewById(R.id.item_ctgl_wc);
            item_ctgl_btn = view.findViewById(R.id.item_ctgl_btn);

            return view;
        }

        @Override
        public void onBind(Context context, int position, PQListBean.DataBean data) {
            // 数据绑定
            // mImageView.setImageResource(data);
            banner_title.setText("片区："+data.getXmmc());
            item_ctgl_zcbje.setText(data.getCost().toString());
            item_ctgl_zj.setText(data.getKeyValueEntity().getObject4() + "");
            item_ctgl_cddw.setText(data.getCompany());
            item_ctgl_yc.setText(data.getKeyValueEntity().getObject3() + "");
            item_ctgl_yzfje.setText(data.getProjAmount().toString());
            item_ctgl_wc.setText(data.getKeyValueEntity().getObject1() + "");

            double chai = 0.0;
            double ycc = data.getKeyValueEntity().getObject1();
            double wcc = data.getKeyValueEntity().getObject3();
            chai = ycc + wcc;
            ArrayList<Float> count = new ArrayList<Float>();
            ArrayList<String> countName = new ArrayList<String>();
            count.add((float) (ycc / chai));
            count.add((float) (wcc / chai));
            countName.add("已拆除" + " " + ycc);
            countName.add("未拆除" + " " + wcc);
            pqPie(item_ctgl_pct, "", count, countName);

            //进入项目
            item_ctgl_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double chai = 0.0;
                    /*Intent intent = new Intent(getActivity(), CTGLPQActivity.class);
                    intent.putExtra("ctglpq", (Serializable) data);
                    startActivity(intent);*/
                    double ycc = data.getKeyValueEntity().getObject1();
                    double wcc = data.getKeyValueEntity().getObject3();
                    chai = ycc + wcc;
                    ArrayList<Float> count = new ArrayList<Float>();
                    ArrayList<String> countName = new ArrayList<String>();
                    count.add((float) (ycc / chai));
                    count.add((float) (wcc / chai));
                    countName.add("已拆除" + " " + ycc);
                    countName.add("未拆除" + " " + wcc);

                    zfinitPie(ctglpq_zf_pct, "支付情况图", count, countName);
                    getLCList(data.getId());
                    pqId = data.getId();
                    FlagStatic.setGid(data.getGid());
                    onCtDetailClickLinear.onClick(data.getGeometry());
                    //List<PjProBean.DataBean.ListBean> list = getList(data.getId(), 1, 10);
                    mBanner.setVisibility(View.GONE);
                    mYewuctgl_ll.setVisibility(View.VISIBLE);
                    pagination_rcv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    limit=20;
                    getList(data.getId(), 1, limit);


                    /*pagination_rcv.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
                                mAdapter = new CtglPqListAdapter(getActivity(), totalCount);
                                pagination_rcv.setAdapter(mAdapter);

                                pagination_rcv.setListener(new PaginationRecycleView.Listener() {
                                    @Override
                                    public void loadMore(int currentPagePosition, int nextPagePosition, int perPageCount, int dataTotalCount) {
                                        // nextPagePosition为将要加载的页码，即需要加载数据的页
                                        // perPageCount 每页展示的数量
                                        if (pqId != -1) {
                                            List<PjProBean.DataBean.ListBean> list = getList(pqId, nextPagePosition, 7);

                                            mAdapter.setDatas(nextPagePosition, list);
                                            pagination_rcv.setState(PaginationRecycleView.SUCCESS);
                                        }
                                *//*mAdapter.setDatas(nextPagePosition, data.getList());
                                pagination_rcv.setState(PaginationRecycleView.SUCCESS);*//*                            */
                    /*if (pqId != -1) {
                                final int loadPos = nextPagePosition;
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(800);
                                            Message msg = Message.obtain();
                                            msg.obj = getList(pqId, loadPos, 7);
                                            msg.arg1 = loadPos;
                                            mHandler.sendMessage(msg);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();
                            }*//*
                                    }

                                    @Override
                                    public void onPerPageCountChanged(int perPageCount) {
                                        // "x条/每页"Spinner选中值改变时触发
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });*/

                }
            });

        }
    }

    private void addPop(List<CTGLPOINTBean.DataBean> dataBean) {
        selectedPhotos.clear();
        mAddPop = new CommenPop().getNormalPopu(getActivity(), R.layout.pop_ctglpq_add, yewuctgl_top);
        View contentView = mAddPop.getContentView();
        EditText xmmc_et = contentView.findViewById(R.id.et_ctglpq_add_xmmc);
        EditText cddw_et = contentView.findViewById(R.id.et_ctglpq_add_cddw);
        EditText jsgm_et = contentView.findViewById(R.id.et_ctglpq_add_jsgm);
        Spinner ttzt_spr = contentView.findViewById(R.id.et_ctglpq_add_ttzt);
        Spinner zfzt_spr = contentView.findViewById(R.id.et_ctglpq_add_zfzt);
        EditText zfje_et = contentView.findViewById(R.id.et_ctglpq_add_zfje);
        EditText et_ctglpq_add_dabh = contentView.findViewById(R.id.et_ctglpq_add_dabh);//档案编号
        EditText et_ctglpq_add_bdzb = contentView.findViewById(R.id.et_ctglpq_add_bdzb);//标段组别
        EditText et_ctglpq_add_qs = contentView.findViewById(R.id.et_ctglpq_add_qs);//权属
        EditText et_ctglpq_add_cqrhczr = contentView.findViewById(R.id.et_ctglpq_add_cqrhczr);//产权人或承租人
        EditText et_ctglpq_add_qymc = contentView.findViewById(R.id.et_ctglpq_add_qymc);//企业名称
        TextView tv_ctglpq_add_htqx = contentView.findViewById(R.id.tv_ctglpq_add_htqx);//合同期限开始
        TextView tv_ctglpq_add_htqx1 = contentView.findViewById(R.id.tv_ctglpq_add_htqx1);//合同期限结束
        EditText et_ctglpq_add_htzdmj = contentView.findViewById(R.id.et_ctglpq_add_htzdmj);//合同租地面积
        EditText et_ctglpq_add_scjzmj = contentView.findViewById(R.id.et_ctglpq_add_scjzmj);//合同建筑面积
        Spinner et_ctglpq_add_ywcq = contentView.findViewById(R.id.et_ctglpq_add_ywcq);//有无产权
        EditText et_ctglpq_add_lxdh = contentView.findViewById(R.id.et_ctglpq_add_lxdh);//联系电话
        Button add_btn = contentView.findViewById(R.id.but_ctglpq_add_sure);
        RecyclerView rlv_add = contentView.findViewById(R.id.rlv_ctglpq_add);

        String uploadDate = CalendarUtil.DEFAULT_DATE_FORMAT.format(new Date());
        tv_ctglpq_add_htqx.setText(uploadDate);
        tv_ctglpq_add_htqx1.setText(uploadDate);
        tv_ctglpq_add_htqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(tv_ctglpq_add_htqx);
            }
        });
        tv_ctglpq_add_htqx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(tv_ctglpq_add_htqx1);
            }
        });

        mPhotoAdapter = new PhotoAdapter(getActivity(), selectedPhotos);
        rlv_add.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));

        rlv_add.setAdapter(mPhotoAdapter);
        rlv_add.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mPhotoAdapter.getItemViewType(position) == PhotoAdapter.TYPE_ADD) {
                    PhotoPicker.builder()
                            .setPhotoCount(PhotoAdapter.MAX)
                            .setShowCamera(true)
                            .setPreviewEnabled(false)
                            .setSelected(selectedPhotos)
                            .start(getActivity(),CtglFragment.this, Activity.RESULT_FIRST_USER);
                } else {
                    PhotoPreview.builder()
                            .setPhotos(selectedPhotos)
                            .setCurrentItem(position)
                            .start(getActivity(),CtglFragment.this, Activity.RESULT_FIRST_USER);
                }
            }
        }));
        SpinnerAdapter ttztAdapter = new SpinnerAdapter(getActivity());
        SpinnerAdapter zfztAdapter = new SpinnerAdapter(getActivity());
        SpinnerAdapter ywcqAdapter = new SpinnerAdapter(getActivity());

        ArrayList<String> ttztList = new ArrayList<String>();
        ArrayList<String> zfztList = new ArrayList<String>();
        ArrayList<String> ywcqList = new ArrayList<String>();

        ttztList.add("未动工");
        ttztList.add("入户清登");
        ttztList.add("交房");
        ttztList.add("挑顶");
        ttztList.add("拆腾");
        ttztList.add("场地清平");
        ttztList.add("销账");

        zfztList.add("已支付");
        zfztList.add("未支付");

        ywcqList.add("无");
        ywcqList.add("有");
        ttzt_spr.setAdapter(ttztAdapter);
        zfzt_spr.setAdapter(zfztAdapter);
        et_ctglpq_add_ywcq.setAdapter(ywcqAdapter);

        ttztAdapter.setDatas(ttztList);
        zfztAdapter.setDatas(zfztList);
        ywcqAdapter.setDatas(ywcqList);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tv_ctglpq_add_htqx.getText().toString().equals("开始时间")&&!tv_ctglpq_add_htqx1.getText().toString().equals("结束时间")){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date1 = null;//开始时间
                    Date date2 = null;//结束时间
                    try {
                        date1 = dateFormat.parse(tv_ctglpq_add_htqx.getText().toString());
                        date2 = dateFormat.parse(tv_ctglpq_add_htqx1.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if (date1.getTime()>date2.getTime()){
                        ToastUtils.showShort("开始时间不能大于结束时间");
                        return;
                    }
                }
                if (!xmmc_et.getText().toString().equals("")
                        && !cddw_et.getText().toString().equals("")
                        && !jsgm_et.getText().toString().equals("")
                        && !zfje_et.getText().toString().equals("")
                        && !et_ctglpq_add_bdzb.getText().toString().equals("")
                        && !et_ctglpq_add_scjzmj.getText().toString().equals("")
                        && !et_ctglpq_add_htzdmj.getText().toString().equals("")) {


                    int[] i2 = new int[dataBean.size()];//{dataBean.getCtQyEntity().getId()}

                    PjProjEntitys pjProBean = new PjProjEntitys();
                    //pjProBean.setId(dataBean.getCtQyEntity().getId());
                    pjProBean.setIds(i2);
                    ArrayList<String> cunList = new ArrayList<>();
                    for (int i = 0; i < dataBean.size(); i++) {
                        i2[i] = dataBean.get(i).getCtQyEntity().getId();
                        cunList.add(dataBean.get(i).getCtQyEntity().getXzqmc());

                    }
                    LinkedHashSet<String> hashSet = new LinkedHashSet<>(cunList);

                    ArrayList<String> listWithoutDuplicates = new ArrayList<>(hashSet);
                    String replace = listWithoutDuplicates.toString().replace("[", "").replace("]", "");
                    pjProBean.setPlanFw(replace);
                    pjProBean.setMsid(dataBean.get(0).getCtPqEntitie().getId());
                    pjProBean.setName(xmmc_et.getText().toString());
                    pjProBean.setImpUnit(cddw_et.getText().toString());
                    pjProBean.setProjScale(new BigDecimal(jsgm_et.getText().toString()));
                    String s = ttztList.get(ttzt_spr.getSelectedItemPosition());
                    pjProBean.setProjSpeed(ProjspeedEnum.getIndex(s));
                    pjProBean.setDabh(et_ctglpq_add_dabh.getText().toString());
                    pjProBean.setProjGeo(Integer.parseInt(et_ctglpq_add_bdzb.getText().toString()));
                    pjProBean.setProjCoor(et_ctglpq_add_qs.getText().toString());
                    pjProBean.setCqr(et_ctglpq_add_cqrhczr.getText().toString());
                    pjProBean.setQyName(et_ctglpq_add_qymc.getText().toString());
                    if (!tv_ctglpq_add_htqx.getText().toString().equals("开始时间")){
                        pjProBean.setScalestartdate(tv_ctglpq_add_htqx.getText().toString());
                    }
                    if (!tv_ctglpq_add_htqx1.getText().toString().equals("结束时间")){
                        pjProBean.setScaleenddate(tv_ctglpq_add_htqx1.getText().toString());
                    }
//                    pjProBean.setScalestartdate(tv_ctglpq_add_htqx.getText().toString());
//                    pjProBean.setScaleenddate(tv_ctglpq_add_htqx1.getText().toString());
                    pjProBean.setHtmj(new BigDecimal(et_ctglpq_add_htzdmj.getText().toString()));
                    pjProBean.setJzmj(new BigDecimal(et_ctglpq_add_scjzmj.getText().toString()));
                    pjProBean.setCqz(et_ctglpq_add_ywcq.getSelectedItemPosition());
                    pjProBean.setMobile(et_ctglpq_add_lxdh.getText().toString());
                    if (zfztList.get(zfzt_spr.getSelectedItemPosition()).equals("已支付")) {
                        pjProBean.setProjPay(1);
                    } else {
                        pjProBean.setProjPay(0);
                    }
                    pjProBean.setProjAmount(new BigDecimal(zfje_et.getText().toString()));


                    if (selectedPhotos.size() > 0) {
                        int index = ProjspeedEnum.getIndex(s);
                        for (int i = 0; i < selectedPhotos.size(); i++) {
                            uplodeUpdate(new File(selectedPhotos.get(i)), index, pjProBean);
                        }
                    } else {
                        String sss = null;
                        try {

                            String encrypt = AesEncryptUtils.encrypt(new Gson().toJson(pjProBean));//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                            sss = "{\"requestData\":\"" + encrypt + "\"}";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        /*OkGo.<String>post(ApiConstants.URL_PJPROJSAVE).upJson(new Gson().toJson(pjProBean)).execute(new BaseNet<String>() {
                            @Override
                            public void onStart(Request<String, ? extends Request> request) {
                                super.onStart(request);
                            }

                            @Override
                            public void onSuccess(Response<String> response) {
                                String body = response.body();
                                if (body != null) {
                                    String decrypt = null;
                                    try {
                                        decrypt = AesEncryptUtils.decrypt(response.body());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                    SuccessBean json = new Gson().fromJson(body, new TypeToken<SuccessBean>() {
                                    }.getType());

                                    if (json.getCode() == 0) {
                                        ToastUtils.showShort("添加成功！");
                                        FlagStatic.getDataBeanList().clear();
                                        mAddPop.dismiss();
                                    } else {
                                        ToastUtils.showShort("添加成功！");
                                    }

                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                            }
                        });
*/
                    }

                } else {
                    ToastUtils.showShort("请填写内容");
                }
            }
        });
    }
    private void setTime(TextView editText) {
        int year = Calendar.getInstance().get(Calendar.YEAR);//[Calendar.YEAR];
        int month = Calendar.getInstance().get(Calendar.MONTH);//[Calendar.MONTH];
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);//[Calendar.DAY_OF_MONTH];
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String nian = "";
                String yue = "";
                String ri = "";
                nian = year + "";
                if (month<9){
                    yue="0" + (month + 1);
                }else {
                    yue="" + (month + 1);
                }
                if(dayOfMonth < 10){
                    ri="0"+dayOfMonth;
                }else {
                    ri=  "" + dayOfMonth;
                }

//            val format: String = CalendarUtil.DATE_FORMAT_HOUR_SE.format(Date()) //$format"  $format"
//                editText.setText("$i-$yue-$ri");
                editText.setText(nian+"-"+yue+"-"+ri);
//            uploadDate = "$i-$yue-$ri"
            }
        }, year, month, day);
        datePicker.show();
    }
    //上传图片
    public void uplodeUpdate(File file, int speed, PjProjEntitys pjProBean) {
        HttpParams httpParams = new HttpParams();

        httpParams.put(file.getName(), file);
        httpParams.put("projSpeed", speed);
        OkGo.<BaseRespose<String>>post(ApiConstants.URL_PJPROJFILE_UPLOAD)
                .params(httpParams)
                .execute(new BaseNet<BaseRespose<String>>() {
                    @Override
                    public void onSuccess(Response<BaseRespose<String>> response) {
                        BaseRespose<String> body = response.body();
                        if (body != null) {
                            if (body.code == 0) {
                                String data = body.data;
                                int i = Integer.parseInt(data);
                                int[] i2 = new int[]{i};
                                pjProBean.setPids(i2);
                                String sss = null;

                                try {

                                    String encrypt = AesEncryptUtils.encrypt(new Gson().toJson(pjProBean));//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                                    sss = "{\"requestData\":\"" + encrypt + "\"}";
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                               /* OkGo.<String>post(ApiConstants.URL_PJPROJSAVE).upJson(new Gson().toJson(pjProBean)).execute(new BaseNet<String>() {
                                    @Override
                                    public void onStart(Request<String, ? extends Request> request) {
                                        super.onStart(request);
                                    }

                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        String body = response.body();
                                        if (body != null) {
                                            String decrypt = null;
                                            try {
                                                decrypt = AesEncryptUtils.decrypt(response.body());

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            SuccessBean json = new Gson().fromJson(body, new TypeToken<SuccessBean>() {
                                            }.getType());

                                            if (json.getCode() == 0) {
                                                ToastUtils.showShort("添加成功！");
                                                mAddPop.dismiss();
                                            } else {
                                                ToastUtils.showShort("添加成功！");
                                            }

                                        }
                                    }

                                    @Override
                                    public void onError(Response<String> response) {
                                        super.onError(response);
                                    }
                                });
*/
                            }
                        }
                    }

                    @Override
                    public void onError(Response<BaseRespose<String>> response) {
                        super.onError(response);
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ArrayList<String> photos = new ArrayList<>();
            selectedPhotos.clear();
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
            }
            if (photos != null) {
                selectedPhotos.addAll(photos);
            }
            if (mPhotoAdapter != null) mPhotoAdapter.notifyDataSetChanged();
        }
    }

    //流程列表
    private void getLCList(int id) {

        String sss = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pqid", id);
            String encrypt = AesEncryptUtils.encrypt(jsonObject.toString());//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
            sss = "{\"requestData\":\"" + encrypt + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkGo.<String>post(ApiConstants.URL_QUERYCTGK).upJson(sss).execute(new BaseNet<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    String decrypt = null;
                    try {
                        decrypt = AesEncryptUtils.decrypt(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    CTPQLCBean json = new Gson().fromJson(decrypt, new TypeToken<CTPQLCBean>() {
                    }.getType());
                    if (json.getData() != null) {

                        List<CTPQLCBean.DataBean> data = json.getData();

                        ArrayList<Float> count = new ArrayList<Float>();
                        ArrayList<String> countName = new ArrayList<String>();

                        for (int i = 0; i < data.size(); i++) {
                            count.add((float) data.get(i).getObject2());
                            countName.add(ProjspeedEnum.getName(data.get(i).getObject1()));
                        }

                        zfinitPie(ctglpq_lc_pct, "流程情况图", count, countName);

                        //ctglpq_lc_rlv.layoutManager= LinearLayoutManager(this@CTGLPQActivity,LinearLayoutManager.VERTICAL,false)
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        ctglpq_lc_rlv.setLayoutManager(linearLayoutManager);
                        ctglpq_lc_rlv.setAdapter(new BaseQuickAdapter<CTPQLCBean.DataBean, BaseViewHolder>(R.layout.item_ctglpq_lc, data) {

                            @Override
                            protected void convert(BaseViewHolder helper, CTPQLCBean.DataBean item) {
                                helper.setText(R.id.item_ctgl_lc_zt, ProjspeedEnum.getName(item.getObject1()));
                                helper.setText(R.id.item_ctgl_lc_num, item.getObject2() + "");
                            }
                        });

                    }

                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });

    }

    private List<PjProBean.DataBean.ListBean> pqList = new ArrayList<>();
    private int pqId = -1;
    private int totalCount = 0;

    //项目列表
    private List<PjProBean.DataBean.ListBean> getList(int id, int page, int limits) {

        String sss = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("pqid", id);
            jsonObject.put("page", page);
            jsonObject.put("limit", limits);
            String encrypt = AesEncryptUtils.encrypt(jsonObject.toString());//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
            sss = "{\"requestData\":\"" + encrypt + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* OkGo.<String>post(ApiConstants.URL_QUERYPROLIST).upJson(sss).execute(new BaseNet<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                if (body != null) {
                    String decrypt = null;
                    try {
                        decrypt = AesEncryptUtils.decrypt(response.body());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    PjProBean json = new Gson().fromJson(decrypt, new TypeToken<PjProBean>() {
                    }.getType());
                    if (json.getData() != null) {


                        PjProBean.DataBean data = json.getData();

                        pqList = data.getList();
                        totalCount = data.getTotalCount();
                        ctglpq_xm_rlv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


                       *//* mAdapter.setOnItemClickListener(new PaginationRecycleView.Adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                                PjProBean.DataBean.ListBean pageItem = mAdapter.getCurrentPageItem(position);
                                ToastUtils.showShort(pageItem.getName());
                            }

                            @Override
                            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                                return false;
                            }
                        });
*//*
                        CtglXRlvAdapter adapter = new CtglXRlvAdapter(getActivity(),data.getList());
                        ctglpq_xm_rlv.setAdapter(adapter);



                        adapter.setOnClickLister(new CtglXRlvAdapter.OnClickLister() {
                            @Override
                            public void onClick(PjProBean.DataBean.ListBean analysisEnty) {
                                //跳转详情页
                                Intent intent = new Intent(getActivity(), CtDetailActivity.class);
                                intent.putExtra("id", analysisEnty.getId());
                                startActivity(intent);
                            }
                        });
                        adapter.setOnDetClickLister(new CtglXRlvAdapter.OnDetClickLister() {
                            @Override
                            public void onDetClick(PjProBean.DataBean.ListBean item) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// 设置Title的图标
                                builder.setIcon(R.drawable.ic_launcher);
// 设置Title的内容
                                builder.setTitle("弹出警告框");
// 设置Content来显示一个信息
                                builder.setMessage("确定删除吗？");

                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String sss = null;
                                        JSONObject jsonObject = new JSONObject();
                                        try {
                                            int id1 = item.getId();
                                            int[] idss = new int[]{id1};
                                            List<Integer> ids = new ArrayList<>();
                                            ids.add(id1);
                                            jsonObject.put("ids", ids.get(0).toString());//"[" + ids.toString().substring(1, ids.toString().length - 1) + "]"
//        val toJson = Gson().toJson(jsonObject.toString())
                                            String toJson = new Gson().toJson(jsonObject).substring(18, new Gson().toJson(jsonObject).toString().length() - 1);
//                                                    jsonObject.put("ids", "[" + ids.toString().substring(1, ids.toString().length() - 1) + "]");
//                                                    String encrypt = AesEncryptUtils.encrypt("[" + ids.toString().substring(1, ids.toString().length() - 1) + "]");//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"

                                            jsonObject.put(" ids", idss);
                                            String encrypt = AesEncryptUtils.encrypt(toJson);//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                                            sss = "{\"requestData\":\"" + encrypt + "\"}";
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        OkGo.<String>post(ApiConstants.URL_PJPROJDELETE).upJson(jsonObject).execute(new BaseNet<String>() {
                                            @Override
                                            public void onStart(Request<String, ? extends Request> request) {
                                                super.onStart(request);
                                            }

                                            @Override
                                            public void onSuccess(Response<String> response) {
                                                String body = response.body();
                                                if (body != null) {
                                                    String decrypt = null;
                                                    try {
                                                        decrypt = AesEncryptUtils.decrypt(response.body());
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                    SuccessBean successBean = new Gson().fromJson(body, SuccessBean.class);
                                                    if (successBean.getCode() == 0) {
                                                        ToastUtils.showShort("删除成功");

                                                    }


                                                }
                                            }

                                            @Override
                                            public void onError(Response<String> response) {
                                                super.onError(response);
                                            }
                                        });

                                    }
                                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.show();
                            }
                        });


                        if (limit % 20 != 0 || data.getList().size() < limit) {
                            ctglpq_xm_rlv.setLoadingMoreEnabled(false);
                            ToastUtils.showShort("滑动到底部了");
                        }
                        if (limit != 20) {
                            ctglpq_xm_rlv.scrollToPosition(data.getList().size()-20);
                        }
                        ctglpq_xm_rlv.setLoadingListener(new XRecyclerView.LoadingListener() {
                            @Override
                            public void onRefresh() {
                                limit = 20;
                                if (pqId != -1) {
                                    getList(pqId, 1, limit);
                                    ctglpq_xm_rlv.setLoadingMoreEnabled(true);
                                }
                            }

                            @Override
                            public void onLoadMore() {
                                limit += 20;
                                getList(pqId, 1, limit);
                            }
                        });

                        ctglpq_xm_rlv.loadMoreComplete();
                        ctglpq_xm_rlv.refreshComplete();


                      *//* ctglpq_xm_rlv.setAdapter(new BaseQuickAdapter<PjProBean.DataBean.ListBean, BaseViewHolder>(R.layout.item_ctglpq_xm, data.getList()) {

                            @Override
                            protected void convert(BaseViewHolder helper, PjProBean.DataBean.ListBean item) {
                                helper.setText(R.id.item_ctglpq_xm_mc, item.getQyName());
                                helper.setText(R.id.item_ctglpq_xm_cddw, item.getImpUnit());
                                //helper.setText(R.id.item_ctglpq_xm_gm, item.getProjScale().toString());

                                if (item.getProjScale().compareTo(new BigDecimal(10000)) > 0) {
                                    helper.setText(R.id.item_ctglpq_xm_gm, item.getProjScale().divide(new BigDecimal(10000), 2, BigDecimal.ROUND_HALF_UP).toString() + "万㎡");
                                } else {
                                    helper.setText(R.id.item_ctglpq_xm_gm, item.getProjScale().toString() + "㎡");
                                }

                                helper.setText(R.id.item_ctglpq_xm_ttzt, item.getProjSpeedText());
                                if (item.getProjPay() == 1) {
                                    helper.setText(R.id.item_ctglpq_xm_zfqk, "已支付");
                                } else {
                                    helper.setText(R.id.item_ctglpq_xm_zfqk, "未支付");
                                }
                                helper.setText(R.id.item_ctglpq_xm_zfje, item.getProjAmount().toString());

                                helper.getView(R.id.item_ctglpq_xm_cz).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //跳转详情页
                                        Intent intent = new Intent(getActivity(), CtDetailActivity.class);
                                        intent.putExtra("id", item.getId());
                                        startActivity(intent);
                                    }
                                });
                                //删除
                                helper.getView(R.id.item_ctglpq_xm_sc).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
// 设置Title的图标
                                        builder.setIcon(R.drawable.ic_launcher);
// 设置Title的内容
                                        builder.setTitle("弹出警告框");
// 设置Content来显示一个信息
                                        builder.setMessage("确定删除吗？");

                                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                String sss = null;
                                                try {
                                                    JSONObject jsonObject = new JSONObject();
                                                    int id1 = item.getId();
                                                    int[] idss = new int[]{id1};
                                                    List<Integer> ids = new ArrayList<>();
                                                    ids.add(id1);
                                                    jsonObject.put("ids", ids.get(0).toString());//"[" + ids.toString().substring(1, ids.toString().length - 1) + "]"
//        val toJson = Gson().toJson(jsonObject.toString())
                                                    String toJson = new Gson().toJson(jsonObject).substring(18, new Gson().toJson(jsonObject).toString().length() - 1);
//                                                    jsonObject.put("ids", "[" + ids.toString().substring(1, ids.toString().length() - 1) + "]");
//                                                    String encrypt = AesEncryptUtils.encrypt("[" + ids.toString().substring(1, ids.toString().length() - 1) + "]");//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"

                                                    jsonObject.put(" ids", idss);
                                                    String encrypt = AesEncryptUtils.encrypt(toJson);//"{\"username\":\"hc"+uName+"\",\"password\":\""+uPwd+"\",\"deviceId\":\""+deviceId+"\"}"
                                                    sss = "{\"requestData\":\"" + encrypt + "\"}";
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }

                                                OkGo.<String>post(ApiConstants.URL_PJPROJDELETE).upJson(sss).execute(new BaseCallBack<String>() {
                                                    @Override
                                                    public void onStart(Request<String, ? extends Request> request) {
                                                        super.onStart(request);
                                                    }

                                                    @Override
                                                    public void onSuccess(Response<String> response) {
                                                        super.onSuccess(response);
                                                        String body = response.body();
                                                        if (body != null) {
                                                            String decrypt = null;
                                                            try {
                                                                decrypt = AesEncryptUtils.decrypt(response.body());
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                            }
                                                            SuccessBean successBean = new Gson().fromJson(decrypt, SuccessBean.class);
                                                            if (successBean.getCode() == 0) {
                                                                ToastUtils.showShort("删除成功");

                                                            }


                                                        }
                                                    }

                                                    @Override
                                                    public void onError(Response<String> response) {
                                                        super.onError(response);
                                                    }
                                                });

                                            }
                                        });
                                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        builder.show();
                                    }
                                });
                            }
                        });
                    *//*
                    }

                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }
        });*/



                /*execute(object : BaseCallBack<String>() {//SysXzqEntitys

            override fun onSuccess(response: Response<String>?) {
                val body = response?.body()
                if (body != null) {
                    val decrypt = AesEncryptUtils.decrypt(response.body())

                    val json: CTPQLCBean = Gson().fromJson(decrypt, object : TypeToken<CTPQLCBean?>() {}.type)
                    if (json!!.data != null) {

                        val data = json.data

                        val count = java.util.ArrayList<Float>()
                        val countName = java.util.ArrayList<String>()
                        for (i in 0..data.size - 1) {
                            count.add(data.get(i).object2.toFloat())

                            countName.add(ProjspeedEnum.getName(data.get(i).object1))
                        }

                        zfinitPie(ctglpq_lc_pct, "流程情况图", count, countName)

                        //ctglpq_lc_rlv.layoutManager= LinearLayoutManager(this@CTGLPQActivity,LinearLayoutManager.VERTICAL,false)
                        val linearLayoutManager = LinearLayoutManager(this@CTGLPQActivity)
                        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                        ctglpq_lc_rlv.layoutManager = linearLayoutManager
                        ctglpq_lc_rlv.adapter = object : BaseQuickAdapter<CTPQLCBean.DataBean, BaseViewHolder>(R.layout.item_ctglpq_lc, data) {
                            override fun convert(helper: BaseViewHolder?, item: CTPQLCBean.DataBean?) {
                                helper?.setText(R.id.item_ctgl_lc_zt, ProjspeedEnum.getName(item!!.object1))
                                helper?.setText(R.id.item_ctgl_lc_num, item!!.object2.toString())

                            }
                        }
                    }

                }
            }

            override fun onError(response: Response<String>?) {
                super.onError(response)

            }
        })*/

        return pqList;
    }


    //支付饼状图
    private void zfinitPie(PieChart pie, String title, ArrayList<Float> datas, ArrayList<String> lables) {
        pie.setUsePercentValues(false);
        pie.getDescription().setEnabled(false);
        pie.setExtraOffsets(5f, 10f, 5f, 5f);

        pie.setDragDecelerationFrictionCoef(0.95f);

        pie.setExtraOffsets(20f, 0f, 20f, 0f);
        pie.setDrawHoleEnabled(true);
        pie.setHoleColor(Color.WHITE);

        pie.setTransparentCircleColor(Color.WHITE);
        pie.setTransparentCircleAlpha(110);

        pie.setHoleRadius(58f);
        pie.setTransparentCircleRadius(61f);

        pie.setDrawCenterText(true);

        pie.setCenterText(title);
        pie.setCenterTextSize(16f);
        pie.setRotationAngle(0f);
        // enable rotation of the chart by touch
        pie.setRotationEnabled(true);
        pie.setHighlightPerTapEnabled(true);
        pie.setEntryLabelColor(Color.BLACK);
        setRKData(pie, datas, lables);

        pie.animateY(1400, Easing.EaseInOutQuad);
        // pie.spin(2000, 0, 360);

        Legend l = pie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    //片区饼状图
    private void pqPie(PieChart pie, String title, ArrayList<Float> datas, ArrayList<String> lables) {
        pie.setUsePercentValues(false);
        pie.getDescription().setEnabled(false);
        pie.setExtraOffsets(5f, 10f, 5f, 5f);

        pie.setDragDecelerationFrictionCoef(0.95f);

        pie.setExtraOffsets(20f, 0f, 20f, 0f);
        pie.setDrawHoleEnabled(true);
        pie.setHoleColor(Color.WHITE);

        pie.setTransparentCircleColor(Color.WHITE);
        pie.setTransparentCircleAlpha(110);

        pie.setHoleRadius(58f);
        pie.setTransparentCircleRadius(61f);

        pie.setDrawCenterText(true);

        pie.setCenterText(title);
        pie.setCenterTextSize(16f);
        pie.setRotationAngle(0f);
        // enable rotation of the chart by touch
        pie.setRotationEnabled(true);
        pie.setHighlightPerTapEnabled(true);
        pie.setEntryLabelColor(Color.BLACK);
        setRKData(pie, datas, lables);

        pie.animateY(1400, Easing.EaseInOutQuad);
        // pie.spin(2000, 0, 360);

        Legend l = pie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void setRKData(PieChart pie, List<Float> datas, List<String> lables) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        for (int i = 0; i < datas.size(); i++) {
            entries.add(new PieEntry(datas.get(i), lables.get(i)));
        }


        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.argb(199, 1, 113, 209));
        colors.add(Color.argb(199, 201, 85, 1));
        colors.add(Color.argb(199, 164, 1, 1));
        colors.add(Color.argb(199, 1, 53, 103));
        colors.add(Color.argb(199, 1, 95, 21));
        /*for (c in ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c)

        for (c in ColorTemplate.JOYFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.COLORFUL_COLORS)
            colors.add(c)

        for (c in ColorTemplate.LIBERTY_COLORS)
            colors.add(c)

        for (c in ColorTemplate.PASTEL_COLORS)
            colors.add(c)

        colors.add(ColorTemplate.getHoloBlue())*/

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);


        dataSet.setValueLinePart1OffsetPercentage(80f);
        dataSet.setValueLinePart1Length(0.2f);
        dataSet.setValueLinePart2Length(0.4f);
        //dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        pie.setData(data);

        // undo all highlights
        pie.highlightValues(null);

        pie.invalidate();
    }

}
