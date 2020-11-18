<template>
<div id="app">
<!-- <div class='headtitle'>
  <h2>SSAFY HRM LIST</h2>
</div>
<div class='search_box'>
 <nav>
        <router-link class="btn btn-primary" to="/">모든게시글 보기</router-link> |
         <router-link class="btn btn-primary" to="/insert">게시글 추가</router-link> |
 </nav>
</div> -->
        <router-view/>
</div>
</template>

<script>

export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
