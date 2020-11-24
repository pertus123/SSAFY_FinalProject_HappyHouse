<template>
  <div class="home">
    <div class="row">
      <q-list bordered class="rounded-borders">
        <q-expansion-item
          v-for="(apt, index) in aptlist"
          :key="index"
          expand-separator
          icon="perm_identity"
          :label="apt.aptName"
          @click="mapBarMove(apt)"
        >
          <q-card>
            <q-card-section>
              <ul>
                <li>이 름 : {{ apt.aptName }}</li>
                <li>
                  주 소 :
                  {{ apt.dong }}
                  {{ apt.jibun }}번지 {{ apt.floor }} 층
                </li>
                <li>거래금액 : {{ apt.dealAmount }}</li>
                <li>건축연도 : {{ apt.buildYear }}년</li>
                <li>전용면적 : {{ apt.area }}m<sup>3</sup></li>
              </ul>
            </q-card-section>
          </q-card>
        </q-expansion-item>
      </q-list>

      <!-- <q-card v-for="(apt, index) in aptlist" :key="index" class="my-card" flat bordered>
        <q-card-section>
          <div class="text-caption">
            <tbody>
              <tr>
                <td>{{ index + 1 }}</td>
                <td>{{ apt.dong }}</td>
                <td>{{ apt.aptName }}</td>
              </tr>
            </tbody>
          </div>
        </q-card-section>

        <q-card-actions>
          <q-btn
            round
            flat
            dense
            :icon="expanded ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"
            @click="detailInfo(apt.no)"
          />
        </q-card-actions>

        <q-slide-transition>
          <div v-show="expanded">
            <q-separator />
            <q-card-section class="text-subitle2">sdfdsf </q-card-section>
          </div>
        </q-slide-transition>
      </q-card> -->
    </div>

    <!-- <div class="row">
      <div id="rowbuildinglist" style="height: 300px; overflow:scroll;">
        <table class="table table-hover table-sm">
          <tbody>
            <tr v-for="(apt, index) in aptlist" :key="index" @click="mapBarMove(apt)">
              <td>{{ index + 1 }}</td>
              <td>{{ apt.dong }}</td>
              <td>{{ apt.aptName }}</td>
              <td><button @click="detailInfo(apt.no)">상세정보</button></td>

              - <td>{{apt.regidate}}</td> --
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="row">
      <div id="detaildiv">
        <table class="table table-sm">
          <tbody id="detailbody">
            <tr>
              <td style="width:20%;">이름</td>
              <td>{{ detailApt.aptName }}</td>
            </tr>
            <tr>
              <td>주소</td>
              <td>{{ detailApt.dong }} {{ detailApt.jibun }} 번지 {{ detailApt.floor }} 층</td>
            </tr>
            <tr>
              <td>거래금액</td>
              <td>{{ detailApt.dealAmount }}</td>
            </tr>
            <tr>
              <td>건축연도</td>
              <td>{{ detailApt.buildYear }}</td>
            </tr>
            <tr>
              <td>전용면적</td>
              <td>{{ detailApt.area }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div> -->

    <!-- {{aptlist}} -->
  </div>
</template>

<script>
// @ is an alias to /src
import { api } from 'boot/axios';
export default {
  name: 'ListBar',

  data() {
    return {
      expanded: false,
      detailApt: {},
    };
  },

  props: {
    aptlist: null,
  },
  methods: {
    mapBarMove: function(apt) {
      this.$emit('select-apt', apt);
    },
    detailInfo: function(no) {
      api
        .get('/detail/' + no)
        .then(response => {
          console.log(response); //apt list
          this.detailApt = response.data;
        })
        .catch(error => {
          console.log(error);
          //    alert(error);
        });

      //this.detailApt = apt;
    },
  },
  mounted() {
    //   alert(this.aptlist);
  },
};
</script>

<style scoped>
@charset "UTF-8";

@import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700;800&family=Noto+Sans+KR:wght@500&display=swap');
* {
  font-family: 'Noto Sans KR', sans-serif;
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

.my-card {
  width: 100%;
  max-width: 350px;
}
</style>
