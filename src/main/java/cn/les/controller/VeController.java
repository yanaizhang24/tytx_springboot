package cn.les.controller;

import cn.les.Utils.LinkListForData;
import cn.les.entity.VeKey;
import cn.les.entity.VeLocation;
import cn.les.entity.VePeSdto;
import cn.les.entity.VePedto;
import cn.les.service.VeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */
@RestController
@RequestMapping("/ve")
@EnableAutoConfiguration
public class VeController {
    @Autowired
    //@Qualifier("veService")
    private VeService veService;

//    public VeService getVeService() {
//        return veService;
//    }
//
//    public void setVeService(VeService veService) {
//        this.veService = veService;
//    }
@RequestMapping(value="/getAllVes1")
public List<VePedto> getAllVes(){

    List<VePedto> list=veService.queryForVePe();
    //List<VePeSdto> list2=new ArrayList<VePeSdto>();

    return list;
}
    @RequestMapping(value="/getAllVes")
    public List<VePeSdto> getAllVes_1(){

        List<VePedto> list=veService.queryForVePe();
        List<VePeSdto> list2=new ArrayList<VePeSdto>();
        for(VePedto vePedto:list){
            VePeSdto vePeSdto=new VePeSdto();
            if(1==vePedto.getVe_atom()){
                vePeSdto.setAtom(true);
            }else{
                continue;
            }
            if(0==vePedto.getState()){
                vePeSdto.setState("006");
            }else{
                vePeSdto.setState("001");
            }
            if(!StringUtils.isEmpty(vePedto.getVe_description())){
                String d=vePedto.getVe_description();
                if(d.length()>=4){
                    vePeSdto.setDescription(d.substring(3));
                    vePeSdto.setType(d.substring(0,3));
                }
            }
            if(!StringUtils.isEmpty(vePedto.getOtherInfo())){
                vePeSdto.setOtherInfo(vePedto.getOtherInfo());
            }
            if(!StringUtils.isEmpty(vePedto.getVe_name())){
                vePeSdto.setName(vePedto.getVe_name());
            }
            if(!StringUtils.isEmpty(vePedto.getVe_id())){
                vePeSdto.setId(vePedto.getVe_id());
            }
            list2.add(vePeSdto);
        }
        return list2;
    }
    @RequestMapping(value="/getAllOnline")
    public int getAllOnline(){
        //spring boot
//        List<VePedto> list=veService.queryForVePe();
//        int i=0;
//        for (VePedto vePedto:list){
//            if(1==vePedto.getState()) {
//                i++;
//            }
//        }
        return  veService.countForOnline();
    }
    @RequestMapping(value="/getVeKey")
    public String getVeKey(String veId,String service_name){
        List<VeKey> vekeys=veService.getVeKey(veId);
        String result;
        for(VeKey vk:vekeys){
            if(!StringUtils.isEmpty(service_name)){
                if(service_name.equals(vk.getService_name())){
                    String key=vk.getBase_key();
                    String service_id=vk.getService_id();
                    result="{\"key\":\""+key+"\",\"service_id\":\""+service_id+"\"}";
                    return result;
                }
            }
        }
        return null;
    }
    private LinkListForData linkedList=new LinkListForData();
    //database id
    private static int id=0;
    //data
    @RequestMapping(value="/saveData")
    public String saveData(String data,String date,String time){
        //list into memory
        String result="{\"zvalue\":{\"hum\":\""+data+"\"},\"time\":\""+date+" "+time+"\"}";
        linkedList.put(result);
        //into database
        id++;
        if(id==21){
            id=1;
        }
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        SimpleDateFormat formatter_2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_f=new Date();
        try {
            date_f=formatter.parse(date+" "+time);
            time=formatter_2.format(date_f);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        veService.saveData(id,data,time);

        return "success";
    }
    //senddata
    @RequestMapping(value="/sendData")
    public List sendData(){
        return linkedList.getLinkedList();
    }
    //location
    @RequestMapping(value="/getLoactionByVeId")
    public VeLocation getLocationByVeId(String veId){
        return veService.getLocationByVeId(veId);
    }


}
