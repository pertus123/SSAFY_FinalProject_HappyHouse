<template>
  <div>
    <div class="banner">
      <h2>Q&A</h2>
    </div>
    <div class="container" align="center">
      <div class="col-md-10">
        <b-container>
          <b-row>
            <b-col lg="6" class="my-1">
              <!-- 글작성 버튼 생성 -->
              <button id="noticewrite" class="btn-secondary btn-sm" @click="insertArticle">글작성</button>
            </b-col>
            <b-col lg="6" class="my-1">
              <b-form-group label="검색" label-cols-sm="3" label-align-sm="right" label-size="sm" label-for="filterInput" class="mb-0">
                <b-input-group size="sm">
                  <b-form-input v-model="filter" type="search" id="filterInput" placeholder="검색할 글제목 입력"></b-form-input>
                  <b-input-group-append>
                    <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                  </b-input-group-append>
                </b-input-group>
              </b-form-group>
            </b-col>
          </b-row>
          <b-table
            show-empty
            small
            stacked="md"
            hover
            :items="articles"
            :fields="fields"
            :current-page="currentPage"
            :per-page="perPage"
            :filter="filter"
            :filter-included-fields="filterOn"
            @filtered="onFiltered"
            @row-clicked="detailArticle"
          >
          </b-table>
          <b-row align-h="center">
            <b-pagination v-model="currentPage" :total-rows="totalRows" :per-page="perPage" align="fill" size="sm" class="my-0" first-number
        last-number></b-pagination>
          </b-row>
        </b-container>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../http-common';
export default {
  name: 'ArticleList',
  data() {
    return {
      upHere: false,
      fields: [
        { key: 'articleno', label: '글번호' },
        { key: 'subject', label: '제목' },
        { key: 'author', label: '작성자' },
        { key: 'regidate', label: '작성일' },
      ],
      articles: [],
      loading: true,
      errored: false,
      totalRows: 1,
      currentPage: 1,
      perPage: 10,
      filter: null,
      filterOn: [],
    };
  },
  methods: {
    insertArticle(){
       this.$router.push('/insert');
    },
    detailArticle(items) {
      //alert(no+"게시글의 정보입니다.");
      this.$router.push('/detail/' + items.articleno);
    },
    retrieveArticles() {
      http
        .get('/qnanotice/qnanoticelist')
        .then((response) => (this.articles = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (
          this.loading = false,
          this.totalRows = this.articles.length
        ));
    },
    QnaArticleWrite() {},
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    },
  },
  mounted() {
    this.retrieveArticles();
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<!--<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>-->
