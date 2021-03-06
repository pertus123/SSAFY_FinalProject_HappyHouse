//import store from "../store";
//import { Loading, LocalStorage } from 'quasar'
import { SessionStorage } from "quasar";

const requireAuth = () => (to, from, next) => {
  const nextRoute = to.path;

  if (!SessionStorage.isEmpty()) {
    return next();
  }
  else next("/login" + nextRoute);
};

const gotoMain = () => (to, from, next) => {
  if (SessionStorage.isEmpty()) {
    return next();
  }
  else next("/");
};

const isAdmin = () => (to, from, next) => {
  const fromRoute = from.path;
  if (SessionStorage.getItem('isAdmin') == 'true') {
    return next();
  }
  else {
    alert("접근이 불가능한 페이지입니다.");
    next(fromRoute);
  }
}

const routes = [
  {
    path: '/login',
    component: () => import('pages/Login.vue'),
    beforeEnter: gotoMain()
  },
  {
    path: "/login/:nextRoute",
    component: () => import('pages/Login.vue'),
    beforeEnter: gotoMain()
  },
  {
    path: '/register',
    component: () => import('pages/Register.vue'),
  },
  {
    path: '/pwdvalidate',
    component: () => import('pages/PwdValidate.vue'),
  },
  {
    path: '/pwdupdate/:authUserid',
    component: () => import('pages/PwdUpdate.vue'),
    props: true
  },
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    beforeEnter: requireAuth(),
    children: [
          { path: '', component: () => import('pages/Main.vue') }
        ]
  },
  {
    path: '/notice',
    component: () => import('layouts/MainLayout.vue'),
    beforeEnter: requireAuth(),
    children: [
      { path: '', component: () => import('pages/notice/NoticeList.vue') },
      { path: '/notice/insert', beforeEnter: isAdmin(), component: () => import('pages/notice/NoticeInsert.vue') },
      { path: '/notice/detail/:no', component: () => import('pages/notice/NoticeDetail.vue'), props:true },
      { path: '/notice/update/:no', beforeEnter: isAdmin(), component: () => import('pages/notice/NoticeUpdate.vue'), props:true }
        ]
  },
  {
    path: '/qna',
    component: () => import('layouts/MainLayout.vue'),
    beforeEnter: requireAuth(),
    children: [
      { path: '', component: () => import('pages/qna/QnaList.vue') },
      { path: '/qna/insert',component: () => import('pages/qna/QnaInsert.vue') },
      { path: '/qna/detail/:no', component: () => import('pages/qna/QnaDetail.vue'), props:true },
      { path: '/qna/update/:no',component: () => import('pages/qna/QnaUpdate.vue'), props:true }
        ]
  },
  {
    path: '/search/',
    name: 'Search',
    component: () => import('pages/search/Search.vue'),
    beforeEnter: requireAuth(),
    props: true,
  },


  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
