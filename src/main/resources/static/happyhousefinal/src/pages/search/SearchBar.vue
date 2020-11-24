<template>
  <div class="home">
    <!-- <select class="custom-select" id="searchType" name="searchType" v-model="searchCode.searchType">
      <option value="0" selected>동으로 검색</option>
      <option value="1">건물명으로 검색</option>
      <option value="2">역이름으로 검색</option>
    </select> -->

    <!-- <q-input color="purple-12" v-model="searchCode.keyword" label="Label">
      <template v-slot:prepend>
        <q-icon name="event" />
      </template>
    </q-input> -->

    <input
      class="form-control mr-sm-3"
      id="keyword"
      name="keyword"
      type="search"
      placeholder="Search"
      aria-label="Search"
      v-model="searchCode.keyword"
      @keypress.enter="searchSendCode"
    />
    <!-- <q-input
      style="z-index:3"
      bottom-slots
      v-model="searchCode.keyword"
      label="Label"
      counter
      :dense="dense"
    >
      <template v-slot:prepend>
        <q-icon name="place" />
      </template>
      <template v-slot:append>
        <q-icon name="close" @click="text = ''" class="cursor-pointer" />
      </template>
    </q-input> -->

    <button style="z-index:4" type="button" id="searchbtn" @click="searchSendCode">
      <i class="fa fa-search"></i> 아이콘 머지
    </button>
    <!-- 222
    <input type="text" v-model="searchCode" @keypress.enter="searchSendCode"/>
      <button @click="searchSendCode">결과가 잘가나?</button> -->
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'SearchBar',

  props: {
    firstData: {},
  },

  data() {
    return {
      searchCode: {
        dealType: '1',
        searchType: '',
        keyword: '',
      },
      dense: false,
    };
  },
  methods: {
    searchSendCode: function() {
      //   alert('검색');
      alert(this.searchCode.keyword);
      alert(this.searchCode.searchType);
      this.$emit('search-send-code', this.searchCode);
      //this.$emit('search-send-map', this.searchCode);
    },
  },
  mounted() {
    // this.searchCode.searchType = '0';
    this.searchCode.keyword = this.firstData.keyword;
    this.searchCode.searchType = this.firstData.searchType;
  },
};
</script>

<style scoped>
@charset "UTF-8";

@import url('https://fonts.googleapis.com/css2?family=Nanum+Myeongjo:wght@700;800&family=Noto+Sans+KR:wght@500&display=swap');
* {
  font-family: 'Noto Sans KR', sans-serif;
}

body {
  width: 100%;
  height: 100%;
  display: table;
  margin: 0;
}

.search {
  background-color: rgb(245, 243, 239);
}

.search div {
  width: 100%;
  margin: 0;
}

.buildinglist {
  width: 100%;
  border: 1px #cfb8a1;
}

.detail {
  border: 1px #cfb8a1;
}

#detail_img {
  width: 100%;
}

.search-container {
  width: 100%;
  padding-bottom: 20px;
}

.custom-select {
  width: 150px;
}

.nearbyinfo {
  text-align: center;
}

.nearbyinfo .btn {
  width: 50%;
  color: white;
  background-color: #cfb8a1;
  margin-bottom: 20px;
}

#map_table {
  margin: 0;
  width: 100%;
  height: 100%;
}

#map_button {
  height: 30px;
}

.btn.subfuncbtn {
  width: 30%;
}
</style>
