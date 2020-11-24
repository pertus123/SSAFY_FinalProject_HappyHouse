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
      <q-form action method="post" @submit.prevent="onClickIdAuth">
        <div class="row input">
          <q-input
            outlined
            clearable
            color="primary"
            bg-color="white"
            v-model="authUserid"
            label="비밀번호를 찾을 아이디"
            style="width: 100%"
            lazy-rules
            :rules="[val => (val && val.length > 0) || '입력해주세요.']"
            :authUserid="authUserid"
          />
        </div>
        <div class="row">
          <q-btn
            label="아이디 인증"
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
  name: "PwdValidate",
  data() {
    return {
      authUserid: ""
    };
  },
  computed: {
    nextRoute() {
      return this.$route.params.nextRoute ? this.$route.params.nextRoute : "";
    }
  },
  methods: {
    onClickIdAuth: function() {
      api
        .post(
          `http://localhost:8197/project/member/idValidate`,
          this.authUserid
        )
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              message: "아이디 인증 성공"
            });
            this.$router.push("/pwdupdate/" + this.authUserid);
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              message: "존재하지 않는 아이디입니다. 확인해주세요."
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
