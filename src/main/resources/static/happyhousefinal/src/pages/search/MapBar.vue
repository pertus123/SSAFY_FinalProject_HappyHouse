<template>
  <div class="home">
    <div style="display:none">{{ this.aptlist }}</div>

    <div class="nearbyinfo row" style="text-align:right">
      <!-- <div class="col-md-2"></div> -->
      <!--   <div class="col-md-6" id="detail"> -->
      <!-- v-model="searchCode.searchType" -->
      <!-- <select class="custom-select" id="searchType" name="searchType" style="z-index:10; position: absolute; left: 500px" v-model="commerClass">
        <option value="선택하세요" selected>선택하세요</option>
        <option value="모두">모두</option>
        <option value="관광/여가/오락">관광/여가/오락</option>
        <option value="부동산">부동산</option>
        <option value="생활서비스">생활서비스</option>
        <option value="소매">소매</option>
        <option value="숙박">숙박</option>
        <option value="스포츠">스포츠</option>
        <option value="음식">음식</option>
        <option value="학문/교육">학문/교육</option>
      </select>  -->

      <q-page-sticky
        position="bottom-right"
        :offset="[18, 18]"
        style="z-index:10; position: absolute; right: 5%;"
      >
        <q-fab
          v-model="fabUp"
          label="상권 정보"
          label-position="right"
          color="brown-13"
          icon="keyboard_arrow_up"
          direction="up"
        >
          <q-fab-action
            color="pink-9"
            label-position="left"
            @click="commerceInfo('모두')"
            icon="search"
            label="모두"
            value="모두"
          />
          <q-fab-action
            color="pink-8"
            label-position="left"
            @click="commerceInfo('관광/여가/오락')"
            icon="videogame_asset"
            label="관광/여가/오락"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-7"
            label-position="left"
            @click="commerceInfo('부동산')"
            icon="home"
            label="부동산"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-6"
            label-position="left"
            @click="commerceInfo('생활서비스')"
            icon="layers"
            label="생활서비스"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-5"
            label-position="left"
            @click="commerceInfo('소매')"
            icon="local_grocery_store"
            label="소매"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-4"
            label-position="left"
            @click="commerceInfo('숙박')"
            icon="hotel"
            label="숙박"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-11"
            label-position="left"
            @click="commerceInfo('스포츠')"
            icon="sports_kabaddi"
            label="스포츠"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-3"
            label-position="left"
            @click="commerceInfo('음식')"
            icon="local_dining"
            label="음식"
            value="관광/여가/오락"
          />
          <q-fab-action
            color="pink-2"
            label-position="left"
            @click="commerceInfo('학문/교육')"
            icon="school"
            label="학문/교육"
            value="관광/여가/오락"
          />
        </q-fab>
      </q-page-sticky>
    </div>

    <div id="map" class="map" style="height:685px">
      <!--  -->
      <div class="row" style="float:right; margin:10%;">
        <q-page-sticky
          position="bottom-right"
          :offset="[18, 18]"
          style="z-index:10; position: absolute;"
        >
          <q-fab
            v-model="fabRight"
            vertical-actions-align="right"
            color="blue-grey-9"
            glossy
            icon="keyboard_arrow_up"
            direction="up"
          >
            <q-fab-action
              label-position="left"
              color="blue-grey-7"
              @click="envirInfo"
              icon="markunread_mailbox"
              label="환경 시설물"
            />
            <q-fab-action
              label-position="left"
              color="blue-grey-5"
              @click="getHospital(0)"
              icon="medical_services"
              label="선별 진료소"
            />
            <q-fab-action
              label-position="left"
              color="blue-grey-3"
              @click="getHospital(1)"
              icon="local_hospital"
              label="선별 병원"
            />
          </q-fab>
        </q-page-sticky>
      </div>
      <!--  -->
    </div>
    <!-- 이건 테스트용 -->
    <!-- /*  <table>
  
      <tbody>
        <tr>
          <td>{{ this.apt.no }}</td>
          <td>{{ this.apt.aptName }}</td>
          <td>{{ this.apt.dong }}</td>
          <td>{{ this.apt.jibun }}</td>
          <td>{{ this.apt.floor }}</td>
          <td>{{ this.apt.lat }}</td>
          <td>{{ this.apt.lng }}</td>
        </tr>
      </tbody>
    </table>*/ -->
  </div>
</template>

<script>
import { api } from "boot/axios";
//import http from '../../http-common';
import axios from "axios";
export default {
  name: "MapBar",
  props: {
    apt: null,
    aptlist: null
  },

  data() {
    return {
      envirList: {
        item: []
      }, // envir
      fab1: true,
      fab2: true,
      hideLabels: false,
      fabUp: false, // right
      fabRight: false, // right
      fabUpCheck: false,
      fabRightCheck: false,
      fabCheck: false,
      map: null,
      tmpAptlist: null,
      tmpApt: null, //////
      marker: null, /////
      multimarker: "",
      markers: [],
      h0markers: [],
      h1markers: [],
      dealicon: "",
      hos0icon: "",
      hos1icon: "",
      curGungu: "",
      curDong: "", //
      hos0flag: "", // = false;
      hos1flag: "", // = false;
      enviricon: "", // 환경정보
      envirmarkers: [],
      envirflag: "",
      // 상권 정보
      commerflag: "",
      commericon: "",
      commermarkers: [],
      //상권 분류
      commerClass: "",
      width: window.innerWidth,
      height: window.innerHeight
      //selectOptions: ["선택하세요", "모두", "관광/여가/오락", "부동산", "생활서비스", "소매", "숙박","스포츠","음식","학문/교육"],
    };
  },

  watch: {
    apt: function(newVal, oldVal) {
      this.fabUp = false;
      this.fabRight = false;
      while (this.envirmarkers.length > 0) {
        this.envirmarkers.pop().setMap(null);
      }
      this.addMarkers(newVal);
    },
    aptlist: function(newVal, oldVal) {
      this.fabUp = false;
      this.fabRight = false;
      // alert('zz');
      while (this.envirmarkers.length > 0) {
        this.envirmarkers.pop().setMap(null);
      }
      this.addMarkers2(newVal);
    }
  },

  methods: {
    // testfunction() {
    //   //this.commerClass = "";
    //   alert(this.commerClass);

    //   console.log("sadsd");
    //   alert("1");
    // },

    commerceInfo(commerceName) {
      this.commerClass = commerceName;

      // alert(this.commerClass + " " + this.curDong);
      while (this.commermarkers.length > 0) {
        this.commermarkers.pop().setMap(null);
      }

      var avglat = 0.0;
      var avglng = 0.0;

      api
        .get("/commerce/", {
          params: {
            curDong: this.curDong,
            commerClass: this.commerClass
          }
        })
        .then(response => {
          //
          var tmp;
          var st = 1;
          response.data.forEach(vo => {
            st = 2;
            tmp = new kakao.maps.Marker({
              position: new kakao.maps.LatLng(
                parseFloat(vo.lat),
                parseFloat(vo.lng)
              ),
              image: this.commericon
            });
            //       alert(vo.name);
            //      alert(vo.tel);

            //
            var infowindow = new kakao.maps.InfoWindow({
              content:
                "<div>" +
                vo.storeName +
                "</div><div>" +
                vo.bigClass +
                ", " +
                vo.smallClass +
                "</div>" // 인포윈도우에 표시할 내용
            });

            kakao.maps.event.addListener(
              tmp,
              "mouseover",
              this.makeOverListener(this.map, tmp, infowindow)
            );
            kakao.maps.event.addListener(
              tmp,
              "mouseout",
              this.makeOutListener(infowindow)
            );

            avglat += parseFloat(vo.lat);
            avglng += parseFloat(vo.lng);

            tmp.setMap(this.map);
            this.commermarkers.push(tmp);
            // this.map.setLevel(6);
          });
          if (st == 1) return;
          //

          this.commerflag = true;

          avglat /= this.commermarkers.length;
          avglng /= this.commermarkers.length;

          this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
          this.map.setLevel(5);
        });

      //end
    },

    getHospital(t) {
      var avglat = 0.0;
      var avglng = 0.0;
      if (t == 0) {
        if (this.hos0flag) {
          while (this.h0markers.length > 0) {
            this.h0markers.pop().setMap(null);
          }
          this.hos0flag = false;
        } else {
          api
            .get("/hospital/", {
              params: {
                type: t,
                code: this.curGungu
              }
            })
            .then(response => {
              var tmp;
              response.data.forEach(vo => {
                tmp = new kakao.maps.Marker({
                  position: new kakao.maps.LatLng(
                    parseFloat(vo.lat),
                    parseFloat(vo.lng)
                  ),
                  image: this.hos0icon
                });
                //       alert(vo.name);
                //      alert(vo.tel);

                //
                var infowindow = new kakao.maps.InfoWindow({
                  content:
                    "<div>" +
                    vo.name +
                    "</div>" +
                    "<div> tel : " +
                    vo.tel +
                    "</div> <div> open : " +
                    vo.weekdayop +
                    "</div>" // 인포윈도우에 표시할 내용
                });

                kakao.maps.event.addListener(
                  tmp,
                  "mouseover",
                  this.makeOverListener(this.map, tmp, infowindow)
                );
                kakao.maps.event.addListener(
                  tmp,
                  "mouseout",
                  this.makeOutListener(infowindow)
                );

                avglat += parseFloat(vo.lat);
                avglng += parseFloat(vo.lng);

                tmp.setMap(this.map);
                this.h0markers.push(tmp);
                // this.map.setLevel(6);
              });

              //

              this.hos0flag = true;

              avglat /= this.h0markers.length;
              avglng /= this.h0markers.length;

              this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
              this.map.setLevel(6);
            })
            .catch(() => {});
        }
      } else {
        if (this.hos1flag) {
          while (this.h1markers.length > 0) this.h1markers.pop().setMap(null);
          this.hos1flag = false;
        } else {
          api
            .get("/hospital/", {
              params: {
                type: t,
                code: this.curGungu
              }
            })
            .then(response => {
              var tmp;
              response.data.forEach(vo => {
                tmp = new kakao.maps.Marker({
                  position: new kakao.maps.LatLng(
                    parseFloat(vo.lat),
                    parseFloat(vo.lng)
                  ),
                  image: this.hos1icon
                });

                ////
                var infowindow = new kakao.maps.InfoWindow({
                  content:
                    "<div>" +
                    vo.name +
                    "</div>" +
                    "<div> tel : " +
                    vo.tel +
                    "</div> <div> open : " +
                    vo.weekdayop +
                    "</div>" // 인포윈도우에 표시할 내용 // 인포윈도우에 표시할 내용
                });

                kakao.maps.event.addListener(
                  tmp,
                  "mouseover",
                  this.makeOverListener(this.map, tmp, infowindow)
                );
                kakao.maps.event.addListener(
                  tmp,
                  "mouseout",
                  this.makeOutListener(infowindow)
                );

                avglat += parseFloat(vo.lat);
                avglng += parseFloat(vo.lng);

                tmp.setMap(this.map);
                this.h1markers.push(tmp);
                //     this.map.setLevel(6);
              });
              this.hos1flag = true;
              avglat /= this.h1markers.length;
              avglng /= this.h1markers.length;

              this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
              this.map.setLevel(6);
            })
            .catch(() => {});
        }
      }
    },

    envirInfo() {
      if (this.envirflag) {
        while (this.envirmarkers.length > 0) {
          this.envirmarkers.pop().setMap(null);
        }
        this.envirflag = false;
      } else {
        //this.guMethod(this.curGungu);
        //    alert("wow")
        var geocoder = new kakao.maps.services.Geocoder();
        //alert(this.envirList[1].WRKP_ADDR);
        var tmpDong = this.curDong;
        var tmp;

        //method
        var makeover = this.makeOverListener;
        var makeout = this.makeOutListener;
        //
        var map2 = this.map;
        var envirmarkers2 = this.envirmarkers;
        //alert(this.curGungu);
        var icons = this.enviricon;

        this.envirList.forEach(function(element) {
          // alert(element.WRKP_ADDR);
          if (element.WRKP_ADDR != "") {
            geocoder.addressSearch(element.WRKP_ADDR, function(result, status) {
              //      alert("1dddd");
              if (status === kakao.maps.services.Status.OK) {
                if (tmpDong == result[0].address.region_3depth_name) {
                  tmp = new kakao.maps.Marker({
                    position: new kakao.maps.LatLng(
                      parseFloat(result[0].y),
                      parseFloat(result[0].x)
                    ),
                    //image: this.enviricon,
                    //content: '<div>카카오</div>',
                    image: icons
                  });
                  //
                  // 여기 호버 다시
                  var infowindow = new kakao.maps.InfoWindow({
                    content:
                      "<div>" +
                      element.WRKP_NM +
                      "</div>" +
                      "<div>" +
                      element.UPCH_COB_NM +
                      "</div>" // 인포윈도우에 표시할 내용
                  });

                  kakao.maps.event.addListener(
                    tmp,
                    "mouseover",
                    makeover(map2, tmp, infowindow)
                  );
                  kakao.maps.event.addListener(
                    tmp,
                    "mouseout",
                    makeout(infowindow)
                  );
                  //
                  //   alert("wow");
                  tmp.setMap(map2);
                  envirmarkers2.push(tmp);
                }
              }
            });
          }
        });

        this.envirflag = true;
        //   alert('final');
      }
    },

    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=1a450fc21a33b30948dc64fee5b56479&libraries=drawing,services,clusterer";
      document.head.appendChild(script);
    },

    initMap() {
      var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
      var options = {
        //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.527541, 126.999239), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
      }; //var map = new kakao.maps.Map(container, options);
      this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

      this.commericon = new kakao.maps.MarkerImage(
        "https://cdn2.iconfinder.com/data/icons/e-commerce-icons-2/256/Ecommerce_Icons_Rose_Color-43-256.png",
        new kakao.maps.Size(40, 40)
      );
      this.hos0icon = new kakao.maps.MarkerImage(
        "https://cdn1.iconfinder.com/data/icons/medicine-pt-8/100/051_-_hospital_map_marker_pin_doctor-128.png",
        new kakao.maps.Size(40, 40)
      );
      this.hos1icon = new kakao.maps.MarkerImage(
        "https://cdn1.iconfinder.com/data/icons/medicine-pt-7/100/051_-_hospital_map_marker_pin_doctor-128.png",
        new kakao.maps.Size(40, 40)
      );
      //https://cdn4.iconfinder.com/data/icons/recycle-and-environment/600/pointer-marker-pin-Conservation-green-recycle-recycling-Ecology-environment-packaging-256.png
      this.enviricon = new kakao.maps.MarkerImage(
        "https://cdn4.iconfinder.com/data/icons/recycle-and-environment/600/pointer-marker-pin-Conservation-green-recycle-recycling-Ecology-environment-packaging-256.png",
        new kakao.maps.Size(40, 40)
      );

      //alert("init!");
    },

    guMethod(gugu) {
      //var tmpEnvir = this.envirList;
      if (gugu == "11320") {
        //도봉구
        axios
          .get(
            "http://openAPI.dobong.go.kr:8088/594a4779786f706538344c42526b75/json/DobongListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.DobongListEnvGuideCheck.row)
          );
      } else if (gugu == "11530") {
        //구로구
        // alert("1");
        axios
          .get(
            "http://openAPI.guro.go.kr:8088/594a4779786f706538344c42526b75/json/GuroListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GuroListEnvGuideCheck.row)
          );
      } else if (gugu == "11110") {
        //"종로구"
        axios
          .get(
            "http://openAPI.jongno.go.kr:8088/594a4779786f706538344c42526b75/json/JongnoListEnvGuideCheck/1/982"
          )
          .then(
            response =>
              (this.envirList = response.data.JongnoListEnvGuideCheck.row)
          );
      } else if (gugu == "11140") {
        // "중구"
        axios
          .get(
            "http://openAPI.junggu.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungguListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.JungguListEnvGuideCheck.row)
          );
      } else if (gugu == "11560") {
        //영등포구
        axios
          .get(
            "http://openAPI.ydp.go.kr:8088/594a4779786f706538344c42526b75/json/YdpListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.YdpListEnvGuideCheck.row)
          );
      } else if (gugu == "11740") {
        //강동구
        axios
          .get(
            "http://openAPI.gd.go.kr:8088/594a4779786f706538344c42526b75/json/GdListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GdListEnvGuideCheck.row)
          );
      } else if (gugu == "11680") {
        //강남구
        axios
          .get(
            "http://openAPI.gangnam.go.kr:8088/594a4779786f706538344c42526b75/json/GnListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GnListEnvGuideCheck.row)
          );
      } else if (gugu == "11200") {
        //"성동구"
        axios
          .get(
            "http://openAPI.sd.go.kr:8088/594a4779786f706538344c42526b75/json/SdListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SdListEnvGuideCheck.row)
          );
      } else if (gugu == "11410") {
        //서대문구
        axios
          .get(
            "http://openAPI.sdm.go.kr:8088/594a4779786f706538344c42526b75/json/SeodaemunListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.SeodaemunListEnvGuideCheck.row)
          );
      } else if (gugu == "11440") {
        //마포구
        axios
          .get(
            "http://openAPI.mapo.go.kr:8088/594a4779786f706538344c42526b75/json/MpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.MpListEnvGuideCheck.row)
          );
      } else if (gugu == "11290") {
        //"성북구"
        axios
          .get(
            "http://openAPI.sb.go.kr:8088/594a4779786f706538344c42526b75/json/SbListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SbListEnvGuideCheck.row)
          );
      } else if (gugu == "11650") {
        //서초구
        axios
          .get(
            "http://openAPI.seocho.go.kr:8088/594a4779786f706538344c42526b75/json/ScListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.ScListEnvGuideCheck.row)
          );
      } else if (gugu == "11590") {
        //동작구
        axios
          .get(
            "http://openAPI.dongjak.go.kr:8088/594a4779786f706538344c42526b75/json/DjListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.DjListEnvGuideCheck.row)
          );
      } else if (gugu == "11710") {
        //송파구
        axios
          .get(
            "http://openAPI.songpa.seoul.kr:8088/594a4779786f706538344c42526b75/json/SpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.SpListEnvGuideCheck.row)
          );
      } else if (gugu == "11470") {
        //양천구
        axios
          .get(
            "http://openAPI.yangcheon.go.kr:8088/594a4779786f706538344c42526b75/json/YcListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.YcListEnvGuideCheck.row)
          );
      } else if (gugu == "11230") {
        //"동대문구"
        axios
          .get(
            "http://openAPI.ddm.go.kr:8088/594a4779786f706538344c42526b75/json/DongdeamoonListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.DongdeamoonListEnvGuideCheck.row)
          );
      } else if (gugu == "11350") {
        //노원구
        axios
          .get(
            "http://openAPI.nowon.go.kr:8088/594a4779786f706538344c42526b75/json/NwListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.NwListEnvGuideCheck.row)
          );
      } else if (gugu == "11170") {
        //"용산구"
        axios
          .get(
            "http://openAPI.yongsan.go.kr:8088/594a4779786f706538344c42526b75/json/YsListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.YsListEnvGuideCheck.row)
          );
      } else if (gugu == "11215") {
        //"광진구"
        axios
          .get(
            "http://openAPI.gwangjin.go.kr:8088/594a4779786f706538344c42526b75/json/GwangjinListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GwangjinListEnvGuideCheck.row)
          );
      } else if (gugu == "11500") {
        //강서구
        axios
          .get(
            "http://openAPI.gangseo.seoul.kr:8088/594a4779786f706538344c42526b75/json/GangseoListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GangseoListEnvGuideCheck.row)
          );
      } else if (gugu == "11545") {
        //금천구
        axios
          .get(
            "http://openAPI.geumcheon.go.kr:8088/594a4779786f706538344c42526b75/json/GeumcheonListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.GeumcheonListEnvGuideCheck.row)
          );
      } else if (gugu == "11260") {
        //"중랑구"
        axios
          .get(
            "http://openAPI.jungnang.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungnangListEnvGuideCheck/1/50"
          )
          .then(
            response =>
              (this.envirList = response.data.JungnangListEnvGuideCheck.row)
          );
      } else if (gugu == "11620") {
        //관악구
        axios
          .get(
            "http://openAPI.gwanak.go.kr:8088/594a4779786f706538344c42526b75/json/GaListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.GaListEnvGuideCheck.row)
          );
      } else if (gugu == "11380") {
        //은평구
        axios
          .get(
            "http://openAPI.ep.go.kr:8088/594a4779786f706538344c42526b75/json/EpListEnvGuideCheck/1/50"
          )
          .then(
            response => (this.envirList = response.data.EpListEnvGuideCheck.row)
          );
      } else {
        alert("에러!");
      }
      //  alert("end");
      // else{
      //   alert("1");
      // }
    },

    addMarkers2(data) {
      //alert("!");

      // map: this.map,
      // position: new kakao.maps.LatLng(vo.lat, vo.lng),
      var tmp;
      // var hoverText;
      var avglat = 0.0;
      var avglng = 0.0;
      while (this.markers.length > 0) {
        this.markers.pop().setMap(null);
      }
      while (this.h0markers.length > 0) {
        this.h0markers.pop().setMap(null);
        this.hos0flag = false;
      }
      while (this.h1markers.length > 0) {
        this.h1markers.pop().setMap(null);
        this.hos1flag = false;
      }
      if (this.marker != null) this.marker.setMap(null);

      data.forEach(vo => {
        // alert("?" + vo.lat + " " + vo.lng);
        tmp = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(
            parseFloat(vo.lat),
            parseFloat(vo.lng)
          )
        });
        //
        var infowindow = new kakao.maps.InfoWindow({
          content:
            "<div>" +
            vo.aptName +
            "</div>" +
            "<div> 거래가격 : " +
            vo.dealAmount +
            "</div>" // 인포윈도우에 표시할 내용
        });

        //hoverText = new

        avglat += parseFloat(vo.lat);
        avglng += parseFloat(vo.lng);
        this.curGungu = vo.gunguCode;
        this.curDong = vo.dong;

        tmp.setMap(this.map);
        this.markers.push(tmp);
        //
        kakao.maps.event.addListener(
          tmp,
          "mouseover",
          this.makeOverListener(this.map, tmp, infowindow)
        );
        kakao.maps.event.addListener(
          tmp,
          "mouseout",
          this.makeOutListener(infowindow)
        );
      });
      //alert(this.curGungu);
      this.guMethod(this.curGungu); /////////////////////////

      avglat /= this.markers.length;
      avglng /= this.markers.length;

      this.map.setCenter(new kakao.maps.LatLng(avglat, avglng));
      this.map.setLevel(4);
    },

    //
    makeOverListener(map, marker, infowindow) {
      return function() {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function() {
        infowindow.close();
      };
    },

    //

    addMarkers(vo) {
      while (this.markers.length > 0) {
        this.markers.pop().setMap(null);
      }
      while (this.h0markers.length > 0) {
        this.h0markers.pop().setMap(null);
        this.hos0flag = false;
      }
      while (this.h1markers.length > 0) {
        this.h1markers.pop().setMap(null);
        this.hos1flag = false;
      }
      if (this.marker != null) this.marker.setMap(null);

      this.marker = new kakao.maps.Marker({
        map: this.map,
        position: new kakao.maps.LatLng(vo.lat, vo.lng)
      });

      //
      var infowindow = new kakao.maps.InfoWindow({
        content:
          "<div>" +
          vo.aptName +
          "</div>" +
          "<div> 거래가격 : " +
          vo.dealAmount +
          "</div>" // 인포윈도우에 표시할 내용
      });

      kakao.maps.event.addListener(
        this.marker,
        "mouseover",
        this.makeOverListener(this.map, this.marker, infowindow)
      );
      kakao.maps.event.addListener(
        this.marker,
        "mouseout",
        this.makeOutListener(infowindow)
      );
      //

      this.map.setCenter(new kakao.maps.LatLng(vo.lat, vo.lng));
      this.map.setLevel(3);
    }
  },
  created() {
    // axios
    // .get('http://openAPI.jongno.go.kr:8088/594a4779786f706538344c42526b75/json/JongnoListEnvGuideCheck/1/5')
    // .then((response) => (this.envirList = response.data.JongnoListEnvGuideCheck.row));
    // axios
    // .get('http://openAPI.junggu.seoul.kr:8088/594a4779786f706538344c42526b75/json/JungguListEnvGuideCheck/1/982')
    // .then((response) => (this.envirList += response.data.JungguListEnvGuideCheck.row));
    // axios
    // .get('http://openAPI.guro.go.kr:8088/594a4779786f706538344c42526b75/json/GuroListEnvGuideCheck/1/100')
    // .then((response) => (this.envirList = response.data.GuroListEnvGuideCheck.row));
    // console.log("완료");
  },
  mounted() {
    this.tmpApt = this.apt;
    window.kakao && window.kakao.maps
      ? this.initMap()
      : this.addKakaoMapScript();
  },
  updated() {
    this.$nextTick(function() {
      if (this.fabUp == true && this.fabCheck == false) {
        this.fabRight = false;
        this.fabCheck = true;
        // this.fabUpCheck = true;
        // this.fabRightCheck = false;
      }
      if (this.fabRight == true && this.fabCheck == true) {
        this.fabUp = false;
        this.fabCheck = false;
        // this.fabUpCheck = false;
        // this.fabRightCheck = true;
      }
      // alert("데이터 바뀜");
      while (this.commermarkers.length > 0) {
        this.commermarkers.pop().setMap(null);
      }
      if (this.tmpApt != this.apt) {
        // this.addMarkers(this.apt); //.lat, this.apt.lng
        this.tmpApt = this.apt;
      } else {
        if (this.tmpAptlist != this.aptlist) {
          //    this.addMarkers2(this.aptlist);
          this.tmpAptlist = this.aptlist;
        } else if (this.commerClass != "선택하세요") {
          //this.testfunction();

          this.commerceInfo();
        }
        // if(this.commerClass != ""){
        //     //this.testfunction();
        //     //this.commerClass= "";
        // }
        // else{
        //   this.addMarkers2(this.aptlist);
        // }
        //this.testfunction();
        //this.commerClass = "";
      }

      // else if( this.aptlist.length != 0 && this.commerClass == "" ){ //
      //   this.addMarkers2(this.aptlist);
      //   //this.aptlist = null;
      // }
      // else if(this.commerClass != ""){
      //   this.testfunction();
      //   this.commerClass = "";
      // }

      //  else{
      //     this.addMarkers2(this.aptlist);
      //  }
    });
  }
};
</script>

<style scoped>
@charset "UTF-8";

@import url("https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700;800&family=Noto+Sans+KR:wght@500&display=swap");
* {
  font-family: "Noto Sans KR", sans-serif;
}

body {
  width: 100%;
  height: 100%;
  display: table;
  margin: 0;
}

.search {
  background-color: rgb(245, 243, 239);
}

.search div {
  width: 100%;
  margin: 0;
}

.buildinglist {
  width: 100%;
  border: 1px #cfb8a1;
}

.detail {
  border: 1px #cfb8a1;
}

#detail_img {
  width: 100%;
}

.search-container {
  width: 100%;
  padding-bottom: 20px;
}

.custom-select {
  width: 150px;
}

.nearbyinfo {
  text-align: center;
}

.nearbyinfo .btn {
  width: 50%;
  color: white;
  background-color: #cfb8a1;
  margin-bottom: 20px;
}

#map_table {
  margin: 0;
  width: 100%;
  height: 100%;
}

#map_button {
  height: 30px;
}

.btn.subfuncbtn {
  width: 30%;
}
</style>
