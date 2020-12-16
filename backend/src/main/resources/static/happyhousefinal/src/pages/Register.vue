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
      <q-form action method="post" @submit.prevent="onClickRegister">
        <div class="row input">
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="user.userid"
            label="*아이디"
            style="width: 100%"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
          />
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="user.userpwd"
            filled
            label="*비밀번호"
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
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="userpwdok"
            filled
            label="*비밀번호 확인"
            style="width: 100%"
            :type="isPwdok ? 'password' : 'text'"
            lazy-rules
            :rules="[
              val => (val && val.length > 0) || '입력해주세요.',
              val => val == user.userpwd || '비밀번호와 일치하지 않습니다.'
            ]"
          >
            <template v-slot:append>
              <q-icon
                :name="isPwdok ? 'visibility_off' : 'visibility'"
                class="cursor-pointer"
                @click="isPwdok = !isPwdok"
              />
            </template>
          </q-input>
          <q-input
            outlined
            clearable
            color="secondary"
            bg-color="white"
            v-model="user.username"
            label="*이름"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
            style="width: 100%"
          />
          <q-input
            outlined
            color="secondary"
            bg-color="white"
            v-model="user.userbirth"
            type="date"
            label="생년월일"
            stack-label
            style="width: 100%"
          />
          <q-input
            outlined
            color="secondary"
            bg-color="white"
            v-model="user.userphone"
            label="휴대폰번호"
            mask="###-####-####"
            unmasked-value
            style="width: 100%"
          />
        </div>
        <div class="row">
          <q-toggle
            v-model="accept"
            color="secondary"
            label="이용약관에 동의합니다."
            style="color:white"
          />
          <q-btn
            label="회원가입"
            type="submit"
            color="secondary"
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
//import { Loading, LocalStorage } from "quasar";
import { SessionStorage } from "quasar";

Vue.use(VueRouter);

export default {
  name: "Register",
  data() {
    return {
      user: {
        userid: "",
        userpwd: "",
        username: "",
        userbirth: "",
        userphone: ""
      },
      userpwdok: "",
      isPwd: true,
      isPwdok: true,
      idck: true,
      accept: false
    };
  },
  methods: {
    onClickRegister: function() {
      if (this.accept !== true) {
        this.$q.notify({
          color: "accent",
          textColor: "white",
          icon: "warning",
          message: "정보를 올바르게 입력해주세요."
        });
      } else {
        api
          .post(`http://localhost:8197/project/member/join`, this.user)
          .then(response => {
            if (response.data == "success") {
              this.$q.notify({
                color: "primary",
                textColor: "white",
                icon: "done_outline",
                message: "회원가입 성공"
              });
              this.$router.push("/login");
            } else {
              this.$q.notify({
                color: "accent",
                textColor: "white",
                icon: "warning",
                message:
                  "서버오류로 인해 회원가입에 실패하였습니다. 다시 시도 해주세요."
              });
            }
          });
      }
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
