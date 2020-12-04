/**
 * Created by helloworld on 2017-02-21.
 */
var alert_msg = {
    'ajax_fail' : '다시 시도하여 주세요.',
    'geolocation' : {
        'notsupport_brower' : '사용하시는 브라우저가 위치정보를 지원하지 않습니다.',
        'fail_msg' : {
            0 : '위치 정보를 알 수 없습니다.',
            1 : '위치정보를 사용할 수 없습니다.\n브라우저의 위치사용을 허용해 주세요.',
            2 : '위치 확인에 실패하였습니다.\n재시도 해주세요.',
            3 : '위치 확인에 실패하였습니다.\n재시도 해주세요.3'
        },
        'fail_msg_footer' : '\n\n위치를 찾을 수 없기 때문에 직접 위치를 설정하여 주시기 바랍니다.'
    }
}

//위치 정보(대기시간 10초)
function geoFindMe(setCookie, reload, showMsg) {

    if (!navigator.geolocation){
        alert_Msg(alert_msg['geolocation']['notsupport_brower'], 0, '', 1);
        // location.reload(true);
    }

    var geoOption = {
        maximumAge : 1 * 60 * 1000,
        timeout: 10 * 1000,
        enableHighAccuracy: false //정밀한 위치 (모바일 성능 고려 on/off)
    }

    //위치검색 허용 안함
    function error(e) {
        // (1)권한 없음(permission denied), (2) 위치확인 불가(position unavailable), (3) 시간초과(timeout)
        if(showMsg == true) alert_Msg(alert_msg['geolocation']['fail_msg'][e.code]);
        //권한 문제시 리로드안함
        if(e.code != 1 && e.code != 2 && e.code != 3) location.reload(true);
    };

    //위치검색 허용
    function success(position) {
        var lat  = position.coords.latitude; //위도
        var lon = position.coords.longitude; //경도

        if(setCookie == true) setGeoPosition(lat,lon);

        close_layer();

        var browser = navigator.userAgent.toLowerCase();
        var win_w = $(window).width();
        /*if (win_w > 1023){
            if ( -1 == browser.indexOf('chrome') ) {
                if(showMsg == true) {
                    alert_Msg('현재 위치를 지원하지 않는 브라우저 입니다.');
                    reload = false;
                }
            }
        }*/

        if(reload == true) {
            //location.reload(true);
            pop_twobtn('ell','위치 정보를 기반으로 새로고침 하시겠습니까?','취소','확인','close_layer()','location.replace(window.location.origin + window.location.pathname)')
        }

        // if($('body').hasClass('pcweb')) aroundRefresh();
    };

    navigator.geolocation.getCurrentPosition(success, error, geoOption);
}

function fn_current_loc_set()
{
    var chk = confirm("지금 계신 지역을 현재위치로 설정하시겠습니까?");
    if (chk)
    {
        fn_deleteCookie("loc_flag");
        fn_deleteCookie("addr_st");

        if (fn_getCookie('loc_flag') == "")
        {
            if (!!navigator.geolocation) {
                fn_setCookie("loc_flag", 1, 5);
                navigator.geolocation.getCurrentPosition(fn_callbackSuccess,fn_callbackError);
            } else {
                alert("이 브라우저는 Geolocation를 지원하지 않습니다");
            }
        }
    }
}

//위치정보 쿠키 저장 (주소값 변환)
function setGeoPosition(lat,lon){
    var obj = new Object();
    var data = {
        x: lon,
        y: lat
    };

    if (xReturnNumber(lon.toString().replace(/[^0-9]/g,'')) == 0 ||
        xReturnNumber(lat.toString().replace(/[^0-9]/g,'')) == 0) {
        data = {
            x: '37.566623558063924',
            y: '126.97840605405327'
        };
    }

    $.xResponse('/product/get_kakao_address_non', data).done(function (result) {
    	if (result.meta.total_count > 0) {
    		var doc = result.documents[0],
				addrObj = (doc.address == null)? doc.road_address:doc.address,
			    addr = (addrObj == null)? '':addrObj.address_name,
				region = (addrObj == null)? '':addrObj.region_1depth_name + ' ' + addrObj.region_2depth_name + ' ' + addrObj.region_3depth_name;

			obj.adr = addr;
			obj.region = region;
			obj.lat = lat;
			obj.lon = lon;

			$.fn.cookieList("geoList").add(obj);
		}
    });
}

//위치정보 쿠키 불러오기
function getGeoPosition(){
    return $.fn.cookieList("geoList").items();
}

$(function () {
    var positionList = getGeoPosition();

    if(positionList.length == 0) {
        geoFindMe(true,false,false);
    }else{
        if(positionList.length > 0){
           // console.log(positionList)
            //위치검색기록
            /*
            $('.reset_history').show();
            $.each(positionList,function(idx,obj){
                var $history = $('#areaReset .history_dummy').clone().removeClass('history_dummy').show();
                $history.find('a').data('lat',obj.lat).data('lon',obj.lon).data('idx',idx);
                $history.find('span.adr').text(obj.adr);
                $('.reset_history').prepend($history);
            });
            */
        }
    }
    // console.log(positionList);
});
