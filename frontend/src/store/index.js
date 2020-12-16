import Vue from 'vue'
import Vuex from 'vuex'
//import example from './module-example'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    //example
  },
  strict: process.env.DEBUGGING
});
