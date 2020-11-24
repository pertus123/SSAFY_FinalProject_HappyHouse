<template>
  <!-- 여기 -->
  <!-- <div class="home">
    <div id="header">
      <main-header />
    </div>

    <div class="search" style="padding-top: 20px;">
      <div class="row">
        <div class="col-lg-12">
          <div class="row">
            <div class="col-md-5">
              <div class="row">
                <div class="search-container form-inline">
                  <search-bar
                    @search-send-code="searchSendCode"
                    :firstData="no"
                  >
                  </search-bar>
                </div>
              </div>

              <list-bar :aptlist="aptlist" @select-apt="selectApt"></list-bar>
             
            </div>

            <div class="col-md-7">
              <map-bar :aptlist="aptlist" :apt="selectedApt"></map-bar>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div> -->
  <q-layout view="hHh lpR fFf">
    <q-header reveal class="bg-white text-black" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <img src="~assets/logo.png" width="150px" @click="onClickLogo" style="cursor:pointer" />
        </q-toolbar-title>
        <q-btn dense flat round icon="menu" @click="left = !left" />

        <span style="margin-right:15px; font-size:15px;"
          >{{ this.$q.sessionStorage.getItem('userId') }}({{
            this.$q.sessionStorage.getItem('userName')
          }})님 환영합니다.</span
        >
        <q-btn
          label="회원관리"
          v-if="this.$q.sessionStorage.getItem('isAdmin')"
          color="secondary"
          style="margin:5px;"
          @click="adminMemberList"
        />
        <q-btn label="마이페이지" color="primary" style="margin:5px;" @click="onClickMypage" />
        <q-btn label="로그아웃" color="accent" style="margin:5px;" @click="onClickLogout" />
        <!--  -->

        <!-- <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg" />
          </q-avatar>
          Title
        </q-toolbar-title> -->
        <!--  -->
      </q-toolbar>
    </q-header>

    <q-drawer v-model="left" side="left" overlay elevated>
      <!--  -->
      <list-bar :aptlist="aptlist" @select-apt="selectApt"></list-bar>
      <!-- drawer content -->
    </q-drawer>

    <q-page-container>
      <div class="row" style="float:right">
        <search-bar @search-send-code="searchSendCode" :firstData="no"> </search-bar>
      </div>
      <div>
        <map-bar :aptlist="aptlist" :apt="selectedApt"></map-bar>
      </div>
    </q-page-container>
  </q-layout>
</template>

<script>
import ListBar from 'pages/search/ListBar.vue';
import MapBar from 'pages/search/MapBar.vue';
import SearchBar from 'pages/search/SearchBar.vue';
import { api } from 'boot/axios';
export default {
  name: 'Search',
  props: {
    no: {},
  },

  components: {
    ListBar,
    MapBar,
    SearchBar,
  },

  data() {
    return {
      left: false,
      dongCode: '',
      selectedApt: '',
      apts: [],
      aptlist: [],
    };
  },
  methods: {
    onClickLogo: function() {
      this.$router.push('/');
    },
    onClickLogout: function() {
      SessionStorage.clear();
      this.$router.push('/login');
    },
    adminMemberList() {},
    onClickMypage() {},
    //

    searchSendCode: function(searchInfo) {
      // 값 저장하게
      //alert(">>>>>" + dongCode);
      // alert(searchInfo);
      // alert(searchInfo.keyword);
      // alert(searchInfo.searchType);
      // alert(searchInfo.dealType);
      //alert('1');
      api
        .get('/searchdata/', {
          params: {
            dealType: '1',
            searchType: searchInfo.searchType,
            keyword: searchInfo.keyword,
          },
        })
        .then(response => {
          console.log(response); //apt list
          alert('test1');
          this.aptlist = response.data;
        })
        .catch(error => {
          alert('error');
        });
    },
    selectApt: function(apt) {
      this.selectedApt = apt;
    },
  },

  mounted() {
    alert('ㅇㄱ');

    api
      .get('/searchdata/', {
        params: {
          dealType: '1',
          searchType: this.no.searchType,
          keyword: this.no.keyword,
        },
      })
      .then(response => {
        alert('wow2');
        console.log(response); //apt list

        this.aptlist = response.data;
        //   alert("succ");
      })
      .catch(error => {
        alert('wow');
        console.log(error);
        //    alert(error);
      });
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
</style>
