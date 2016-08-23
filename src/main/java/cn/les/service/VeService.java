package cn.les.service;

import cn.les.Utils.LinkListForData;
import cn.les.entity.VeKey;
import cn.les.entity.VeLocation;
import cn.les.entity.VePedto;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by 严峰 on 2016/7/11 0011.
 */
@Service("veService")
public class VeService {
//    @Autowired
//    // @Qualifier("jdbc")
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);

    private static Logger logger = Logger.getLogger(VeService.class);

    //    public JdbcTemplate getJdbcTemplate() {
//        return jdbcTemplate;
//    }
//
//    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//获取设备
    public List<VePedto> queryForVePe() {

        String sql = "select \n" +
                "\tyy.*,\n" +
                "\tp.pe_state as state\n" +
                "from (SELECT\n" +
                "\txx.*,vp.pe_id from (\n" +
                "\t\t\tselect \n" +
                "\t\t\tA.ve_id, \n" +
                "\t\t\tA.ve_atom,\n" +
                "\t\t\tA.ve_description,\n" +
                "\t\t\tA.ve_name,\n" +
                "\t\t\tB.geolocation_id,\n" +
                "\t\t\tB.otherInfo\n" +
                "\t\tFROM\n" +
                "\t\t\tiot_ve AS A\n" +
                "\t\tLEFT OUTER JOIN iot_ve_geolocation AS B ON A.ve_id = B.ve_id\n" +
                "\t\twhere A.ve_atom=1\n" +
                "\t) AS xx\n" +
                "LEFT OUTER JOIN " +
                "(SELECT DISTINCT vepe.ve_id,vepe.pe_id from iot_ve_pe_bind AS vepe) " +
                "AS vp ON xx.ve_id = vp.ve_id) as yy " +
                "LEFT JOIN iot_pe  as p on yy.pe_id=p.pe_id";
        return (List<VePedto>) jdbcTemplate.query(sql, new RowMapper<VePedto>() {

            @Override
            public VePedto mapRow(ResultSet rs, int rowNum) throws SQLException {
                VePedto stu = new VePedto();
                stu.setVe_atom(rs.getInt("ve_atom"));
                stu.setGeolocation_id(rs.getString("geolocation_id"));
                stu.setOtherInfo(rs.getString("otherInfo"));
                stu.setState(rs.getInt("state"));
                stu.setVe_id(rs.getString("ve_id"));
                stu.setVe_description(rs.getString("ve_description"));
                stu.setPe_id(rs.getString("pe_id"));
                stu.setVe_name(rs.getString("ve_name"));
                return stu;
            }

        });

    }

    //获取设备个数
    public int countForOnline() {
        String sql = "select COUNT(pe_state) from iot_pe where pe_state=1";
        Integer n = jdbcTemplate.queryForObject(sql, Integer.class);
        return n;
    }

    //获取vekey
    public List<VeKey> getVeKey(String veId) {

        StringBuffer sql = new StringBuffer("SELECT ve.ve_id,ve.service_id,ve.base_key,ve.service_name\n" +
                "FROM (select vkc.ve_id,vkc.service_id,vkc.base_key,vws.service_name \n" +
                "from iot_ve_key_config vkc \n" +
                "LEFT JOIN iot_ve_websocket_service vws \n" +
                "on vkc.service_id=vws.service_id) ve\n" +
                "WHERE ve_id LIKE ");
        veId = "'%" + veId + "%' ";
//        Connection conn = null;
//        conn= JdbcUtil.getC
//        PreparedStatement preparedStatement=conn.
        sql.append(veId);
        return (List<VeKey>) jdbcTemplate.query(sql.toString(), new RowMapper<VeKey>() {

            @Override
            public VeKey mapRow(ResultSet rs, int rowNum) throws SQLException {
                VeKey stu = new VeKey();
                stu.setVe_id(rs.getString("ve_id"));
                stu.setService_id(rs.getString("service_id"));
                stu.setBase_key(rs.getString("base_key"));
                stu.setService_name(rs.getString("service_name"));
                return stu;
            }
        });
    }

    //getLocation
    public VeLocation getLocationByVeId(String veId) {
        String sql = "select vel.veId,vel.ve_name,vel.longitude,vel.latitude,vel.altitude from iot_ve_location as vel where veId=";
        sql = sql + "'" + veId + "'";
        List<VeLocation> list = (List<VeLocation>) jdbcTemplate.query(sql, new RowMapper<VeLocation>() {
            @Override
            public VeLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
                VeLocation stu = new VeLocation();
                stu.setVeId(rs.getString("veId"));
                stu.setVe_name(rs.getString("ve_name"));
//                private String longitude;
//                private String latitude;
//                private String altitude;
                stu.setLongitude(rs.getString("longitude"));
                stu.setLatitude(rs.getString("latitude"));
                stu.setAltitude(rs.getString("altitude"));
                return stu;
            }
        });
        if (list.size() > 0) {
            return list.get(0);
        }
        logger.info("未找到相应ve");
        return null;
    }
//存入数据库
    public String saveData(int id, String data, String time) {
        String sql = "update iot_ve_data set data= " + data + ",time=\'" + time + "\' where id=" + id;
        int i = jdbcTemplate.update(sql);
        if (i == 1) {
            return "success";
        }
        return "fail";
    }
    public String saveData(int id, String message) {
        JSONObject jb=new JSONObject(message);
        JSONObject zvalue=jb.getJSONObject("zvalue");
        String data=zvalue.get("hum").toString();
        String date=jb.getString("time");
        String sql = "update iot_ve_data set data= " + data + ",time=\'" + date + "\' where id=" + id;
        int i = jdbcTemplate.update(sql);
        if (i == 1) {
            return "success";
        }
        return "fail";
    }
    //存入内存
    public void putInList(String data, LinkListForData linkList){
        linkList.put(data);
    }

    //    public String savaData(int id,String json) throws JSONException{
//        JSONObject jb=new JSONObject(json);
//        String sql="update iot_ve_data set data= "+data+",time=\'"+time+"\' where id="+id;
//        int i=jdbcTemplate.update(sql);
//        if (i==1){
//            return "success";
//        }
//        return "fail";
//    }
    public void textc(String message) {
        System.out.println(message);
        JSONObject jb=new JSONObject(message);
        JSONObject zvalue=jb.getJSONObject("zvalue");
        String data=zvalue.get("hum").toString();
        String date=jb.getString("time");
        saveData(1,data,date);
    }
    //获取设备状态
    public String getVeStateById(String veId){
        String sql="select pe_state from iot_pe where pe_id=(select DISTINCT pe_id from iot_ve_pe_bind where ve_id='"+veId+"')";
        String state=jdbcTemplate.queryForObject(sql,String.class);

        return state;
    }
}
