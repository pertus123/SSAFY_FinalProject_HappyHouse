import Vue from 'vue';
import Router from 'vue-router';
import QnaInsert from './components/QnaInsert.vue';
import QnaList from './components/QnaList.vue';
import QnaDetail from './components/QnaDetail.vue';
import QnaUpdate from './components/QnaUpdate.vue';
Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'QnaList',
      component: QnaList,
    },
    {
      path: '/insert',
      name: 'InsertBoard',
      component: QnaInsert,
    },
    {
      path: '/detail/:no',
      name: 'QnaDetail',
      component: QnaDetail,
      props: true,
    },
    {
      path: '/update/:no',
      name: 'QnaUpdate',
      component: QnaUpdate,
      props: true,
    },
  ],
});

