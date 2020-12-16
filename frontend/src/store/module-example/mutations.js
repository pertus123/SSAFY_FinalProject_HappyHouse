export function LOGIN(state, payload) {
  state.accessToken = payload["auth-token"];
  state.userId = payload["user-id"];
  state.userName = payload["user-name"];
}

export function LOGOUT(state) {
  state.accessToken = null;
  state.userId = "";
  state.userName = "";
}
