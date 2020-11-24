<template>
  <div id="body" class="fullscreen flex flex-center">
    <div id="blackbox">
      <img
        alt="logo"
        src="~assets/logo_text1.png"
        width="50%"
        @click="onClickLogo"
        style="cursor:pointer"
      />
      <q-form action method="post" @submit.prevent="onClickPwdUpdate">
        <div class="row input">
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="newuserpwd"
            filled
            label="*새로운 비밀번호"
            style="width: 100%"
            :type="isnewpwd ? 'password' : 'text'"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
          >
            <template v-slot:append>
              <q-icon
                :name="isnewpwd ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isnewpwd = !isnewpwd"
              />
            </template>
          </q-input>
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="newuserpwdok"
            filled
            label="*새로운 비밀번호 확인"
            style="width: 100%"
            :type="isnewpwdok ? 'password' : 'text'"
            lazy-rules
            :rules="[
              val => (val && val.length > 0) || '입력해주세요.',
              val => val == newuserpwd || '비밀번호와 일치하지 않습니다.'
            ]"
          >
            <template v-slot:append>
              <q-icon
                :name="isnewpwdok ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isnewpwdok = !isnewpwdok"
              />
            </template>
          </q-input>
        </div>
        <div class="row">
          <q-btn
            label="비밀번호 변경"
            type="submit"
            color="primary"
            style="width:100%; padding : 5px;"
          />
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import { api } from "boot/axios";

Vue.use(VueRouter);

export default {
  name: "PwdUpdate",
  props: {
    authUserid: String
  },
  data() {
    return {
      newuserpwd: "",
      newuserpwdok: "",
      isnewpwd: true,
      isnewpwdok: true
    };
  },
  methods: {
    onClickPwdUpdate: function() {
      api
        .put(`http://localhost:8197/project/member/updatePassword`, {
          userid: this.authUserid,
          userpwd: this.newuserpwd
        })
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              message: "비밀번호 변경 성공"
            });
            this.$router.push("/login");
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              message:
                "서버오류로 인해 비밀번호 변경에 실패했습니다. 다시 시도 해주세요."
            });
          }
        });
    },
    onClickLogo: function() {
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
#body {
  background: url("~assets/main_background.png") no-repeat center center;
  background-size: cover;
}

#blackbox {
  width: 500px;
  background: url("~assets/main_blackbox.png");
  text-align: center;
  padding-bottom: 10px;
  padding-left: 2%;
  padding-right: 2%;
}

img {
  margin-top: 50px;
  margin-bottom: 30px;
}

label {
  font-size: 18px;
  padding-bottom: 10px;
}

.psw {
  color: rgb(255, 255, 255);
  font-size: 17px;
}
.signup {
  background: url("~assets/main_blackbox.png");
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
  font-style: bold;
  font-size: 20px;
  width: 100%;
}

span:hover {
  font-weight: 600;
}

.input > * {
  margin-bottom: 15px;
}
</style>
