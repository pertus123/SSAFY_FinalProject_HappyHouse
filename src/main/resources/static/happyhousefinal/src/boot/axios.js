import Vue from 'vue'
import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8197/project',
  timeout: 3000,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

Vue.prototype.$api = api

export { api }