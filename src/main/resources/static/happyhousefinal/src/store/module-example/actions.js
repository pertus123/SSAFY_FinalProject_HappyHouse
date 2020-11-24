import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = process.env.VUE_APP_SERVER_URL;

export async function LOGIN(context, user) {
    const response = await axios
    .post(`${SERVER_URL}/member/login`, user);
  context.commit("LOGIN", response.data);
  axios.defaults.headers.common["auth-token"] = `${response.data["auth-token"]}`;
}
// export function LOGOUT(context) {
//     context.commit("LOGOUT");
//     // 아래와 같이 스토리지에 저장하는 부분을 로그인에 넣으면 오류를 방지할수 있다.
//     // 단 로그아웃이 되지않고 브라우저를 종료한 경우 문제가 될수 있으므로
//     // 로그인 순간에 옛날에 만들었던 토큰을 지워야함.
//     axios.defaults.headers.common["auth-token"] = undefined;
//   }
