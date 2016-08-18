/**
 * Created by Administrator on 2016/7/28 0028.
 */


var ws_detail = null;
var ws_data=null;
function doClient() {

}
function startServer() {
    //var url =  "ws://" + wsUrl + "/IoT_Harbor/websocketInterface";
    var url = "ws://192.168.40.240:8880/IoT_Harbor/websocketInterface";


    if ("WebSocket" in window) {

        ws_detail = new WebSocket(url);
        //alert(ws_detail.toString());
        //sendMessage();
    } else if ("MozWebSocket" in window) {

        ws_detail = new MozWebSocket(url);
        //alert(ws_detail.toString());
    } else {
        alert("unsupported");
        return;
    }
    ws_detail.onopen = function () {
        //alert("opened!");
        alarm_on();
    };
    ws_detail.onmessage = function (event) {
        //alert(event.data);
        //temp = parseInt(event.data.substring(26,28));
        //hum = parseInt(event.data.substring(18, 20));
//            alert(hum);
        //$("#veData").text(event.data);
        if(!event){
            console.log("device not online");
            return;
        }
        console.log(event.data);
        ws_data=event.data;
        var deviceData=event.data;
        $.post("http://"+window.location.host+"/lesframe/Device/saveData",{"saveData":deviceData},function (data){
            // var result = jQuery.parseJSON(data);
            // key=result.key;
            // serviceId=result.service_id;
        });
        //setChartData();
//			setHumiData();

    };
    ws_detail.onclose = function () {
        //alert("closed!");
    };
}


function alarm_on() {
//		var veId = "102151104105203192168402401053";
//		var key = "832a7055-57be-4b17-9aa9-0864216f3f2c";
//		var serviceId = "105151104105203192168402401048";//sensor-control_down
//		var param = "\"control\":\"on\",\"time\":10";
//
//		var key1 = "832a7055-57be-4b17-9aa9-0864216f3f2c";
//		var serviceId1 = "105151104105203192168402401046";//sensor-get_up
//		var aaaa = "on";
//		var veId = "102160613134951192168402401009";
//		var key1 = "47d53e8e-31a0-4e9a-849b-aaa6ed12b79e";
//		var serviceId1 = "105151104105203192168402401005";
//		var param = "\"control\":\"on\",\"time\":10";
//
//		var key= "5d4e1edf-cf1e-457f-b473-f11fa90b4e34";
//		var serviceId = "105151104105203192168402401002";
//		var aaaa = "on";

    //var veId=$("#veId").html();
    var veId="102160613134951192168402401009";
    var serviceId;
    var key;
    $.ajaxSetup({
        async:false
    })
    $.get("http://"+window.location.host+"/lesframe/Device/getVeKey?veId="+veId+"&service_name=sensor-control_down", function (data){
        var result = jQuery.parseJSON(data);
        key=result.key;
        serviceId=result.service_id;
    });

    var param="\"control\":\"on\",\"time\":10";

    var key1;
    var serviceId1;
    $.get("http://"+window.location.host+"/lesframe/Device/getVeKey?veId="+veId+"&service_name=sensor-get_up", function (data){
        var result = jQuery.parseJSON(data);
        key1=result.key;
        serviceId1=result.service_id;
    });
    var aaaa="on";
    //alert(param);
    var str = '{"veId":"' + veId + '","serviceId":"' + serviceId + '","key":"' + key + '","is_atom":"1","param":{' + param + '}}';
    console.log(str);
    var str1 = '{"veId":"' + veId + '","serviceId":"' + serviceId1 + '","key":"' + key1 + '","is_atom":"1","param":{"subscribe":"' + aaaa + '"}}';
    console.log(str1);
    //alert(str);
    if (ws_detail != null && str != "") {
        //发送控制指令
        ws_detail.send(str);
        //发送数据订阅
        ws_detail.send(str1);
    }
}
//
// function createCharts() {
//     chart = new Highcharts.Chart({
//         chart: {
//             renderTo: 'tempChart',
//             type: 'spline',
//             marginRight: 5,
//         },
//         title: {
//             text: '温度数据'
//         },
//         xAxis: {
//             title: {
//                 text: 'Time'
//             },
//             type: 'datetime',
//             tickPixelInterval: 150
//         },
//         yAxis: {
//             title: {
//                 text: 'Temp'
//             },
//             plotLines: [{
//                 value: 0,
//                 width: 1,
//                 color: '#808080'
//             }],
//             min: 0,
//             max: 100
//         },
//         legend: {
//             enabled: false
//         },
//         exporting: {
//             enabled: false
//         },
//         credits: {
//             enabled: false
//         },
//         series: [{
//             name: 'Temperature data',
//             data: (function () {
//                 // generate an array of random data
//                 var data = [], time = (new Date()).getTime(), i;
//
//                 for (i = -29; i <= 0; i++) {
//                     data.push({
//                         x: time + i * 1000,
//                         y:0
// //                            y: Math.round(Math.random()*100)
//                     });
//                 }
//                 return data;
//             })()
//         }]
//     });
// }
//
// //temperature charts
// var chart;
// function setChartData() {
//     var x = (new Date()).getTime();
//     var y = hum;
//     var series = chart.series[0];
//     series.addPoint([x, y], true, true);
// }


//	function setHumiData() {
//
//		var point = humichart.series[0].points[0];
//		point.y = hum;
//		point.update(point.y);
//	}


