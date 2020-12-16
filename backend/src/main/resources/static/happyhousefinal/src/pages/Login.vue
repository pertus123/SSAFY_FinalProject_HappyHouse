<template>
  <div id="body" class="fullscreen flex flex-center">
    <div id="blackbox">
      <img alt="logo" src="~assets/logo_text1.png" width="50%" />
      <q-form action method="post" @submit.prevent="onClickLogin">
        <div class="row input">
          <q-input
            outlined
            clearable
            color="primary"
            bg-color="white"
            v-model="user.userid"
            label="ID"
            style="width: 100%"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
          />
          <q-input
            outlined
            clearable
            color="primary"
            bg-color="white"
            v-model="user.userpwd"
            filled
            label="Password"
            style="width: 100%"
            :type="isPwd ? 'password' : 'text'"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
          >
            <template v-slot:append>
              <q-icon
                :name="isPwd ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isPwd = !isPwd"
              />
            </template>
          </q-input>
        </div>
        <div class="row login">
          <q-btn
            label="로그인"
            type="submit"
            color="primary"
            style="width:100%; padding : 5px;"
          />
        </div>
        <div class="row">
          <div class="col-6">
            <q-checkbox
              dark
              v-model="idck"
              label="아이디 저장"
              color="primary"
              style="color:white"
              class="float-left"
            />
          </div>
          <div class="col-6">
            <div class="psw float-right">
              Forgot
              <span
                class="text-amber-12"
                @click="onClickPwdValidate"
                style="cursor:pointer"
                >password?</span
              >
            </div>
          </div>
        </div>
        <div class="row">
          <div class="signup flex flex-center">
            <div>
              아직 회원이 아니신가요?
              <span
                class="text-amber-12"
                @click="onClickRegister"
                style="cursor:pointer"
                >회원가입</span
              >
            </div>
          </div>
        </div>
      </q-form>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import VueRouter from "vue-router";
import { api } from "boot/axios";
//import { Loading, LocalStorage } from "quasar";
import { SessionStorage } from "quasar";

Vue.use(VueRouter);

export default {
  name: "Login",
  data() {
    return {
      user: {
        userid: "ssafy",
        userpwd: ""
      },
      isPwd: true,
      idck: true
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    onClickLogin: function() {
      api
        .post(`http://localhost:8197/project/member/login`, this.user)
        .then(response => {
          if (response.data["status"] == "success") {
            SessionStorage.set("accessToken", `${response.data["auth-token"]}`);
            SessionStorage.set("userId", `${response.data["user-id"]}`);
            SessionStorage.set("userName", `${response.data["user-name"]}`);
            SessionStorage.set("isAdmin", `${response.data["is-admin"]}`);
            api.defaults.headers.common[
              "auth-token"
            ] = `${response.data["auth-token"]}`;
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              message:
                "로그인 성공! " +
                response.data["user-id"] +
                "(" +
                response.data["user-name"] +
                ")님 환영합니다."
            });
            this.$router.replace(`/${this.nextRoute}`);
          } else {
            this.user.userpwd = "";
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              message: "아이디 혹은 비밀번호를 확인해주세요."
            });
          }
        });
    },
    onClickRegister: function() {
      this.$router.push("/register");
    },
    onClickPwdValidate: function() {
      this.$router.push("/pwdvalidate");
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
