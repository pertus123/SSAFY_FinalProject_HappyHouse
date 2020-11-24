<template>
  <q-layout view="hHh lpR fFf">
    <q-header reveal elevated class="bg-white text-black" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <img
            src="~assets/logo.png"
            width="150px"
            @click="onClickLogo"
            style="cursor:pointer"
          />
        </q-toolbar-title>
        <span style="margin-right:15px; font-size:15px;"
          >{{ this.$q.sessionStorage.getItem("userId") }}({{
            this.$q.sessionStorage.getItem("userName")
          }})님 환영합니다.</span
        >
        <q-btn
          label="회원관리"
          v-if="this.$q.sessionStorage.getItem('isAdmin')"
          color="secondary"
          style="margin:5px;"
          @click="adminMemberList"
        />
        <q-btn
          label="마이페이지"
          color="primary"
          style="margin:5px;"
          @click="onClickMypage"
        />
        <q-btn
          label="로그아웃"
          color="accent"
          style="margin:5px;"
          @click="onClickLogout"
        />
      </q-toolbar>

      <q-tabs align="left">
        <q-route-tab to="/notice" label="공지사항" />
        <q-route-tab to="/qna" label="Q&A" />
      </q-tabs>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import Vue from "vue";
//import { Loading, LocalStorage } from "quasar";
import { SessionStorage } from "quasar";
// //mapGetters 등록
// import { mapGetters } from "vuex";

export default {
  data() {
    return {
      isLogin: false
    };
  },
  methods: {
    onClickLogo: function() {
      this.$router.push("/");
    },
    onClickLogout: function() {
      SessionStorage.clear();
      this.$router.push("/login");
    },
    adminMemberList() {},
    onClickMypage() {}
  }
};
</script>
<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700;800&family=Noto+Sans+KR:wght@500&display=swap");

* {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
