<template>
  <div class="flex flex-center">
    <div class="banner">
      Q&A
    </div>
    <div class="col-10" style="margin-top:15px;">
      <q-table
        :data="articles"
        :columns="columns"
        row-key="articleno"
        :pagination.sync="pagination"
        hide-pagination
        :filter="filter"
        @row-click="detailArticle"
      >
        <template v-slot:top-left>
          <q-btn
            color="secondary"
            label="글 작성"
            size="15px"
            style="padding:5px;"
            @click="insertArticle"
          />
        </template>
        <template v-slot:top-right>
          <q-input
            outlined
            dense
            debounce="300"
            color="primary"
            v-model="filter"
          >
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
        </template>
      </q-table>

      <div class="row justify-center q-mt-md">
        <q-pagination
          v-model="pagination.page"
          color="primary"
          :max="pagesNumber"
          :max-pages="10"
          size="md"
          :direction-links="true"
          :boundary-links="true"
          style="margin-bottom:20px"
        />
      </div>
    </div>
  </div>
</template>

<script>
import { api } from "boot/axios";
export default {
  name: "ArticleList",
  data() {
    return {
      columns: [
        {
          name: "articleno",
          align: "center",
          label: "글번호",
          field: "articleno",
          sortable: true,
          style: "width: 50px"
        },
        {
          name: "subject",
          align: "left",
          label: "제목",
          field: "subject",
          sortable: true,
          style: "width: 620px"
        },
        {
          name: "author",
          align: "left",
          label: "작성자",
          field: "author",
          sortable: true,
          style: "width: 50px"
        },
        {
          name: "regidate",
          align: "center",
          label: "작성일",
          field: "regidate",
          sortable: true,
          style: "width: 100px"
        }
      ],
      articles: [],
      pagination: {
        sortBy: "desc",
        descending: false,
        page: 1,
        rowsPerPage: 10
        // rowsNumber: xx if getting data from a server
      },
      filter: ""
    };
  },
  methods: {
    insertArticle() {
      this.$router.push("/qna/insert");
    },
    detailArticle(evt, row) {
      this.$router.push("/qna/detail/" + row.articleno);
    },
    retrieveArticles() {
      api
        .get("/qnanotice/qnanoticelist")
        .then(response => (this.articles = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(
          () => (
            (this.loading = false), (this.totalRows = this.articles.length)
          )
        );
    }
  },
  mounted() {
    this.retrieveArticles();
  },
  computed: {
    pagesNumber() {
      return Math.ceil(this.articles.length / this.pagination.rowsPerPage);
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.banner {
  color: #fff;
  padding-top: 70px;
  padding-bottom: 70px;
  background-image: url("~assets/notice_banner.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  text-align: center;
  width: 100%;
  height: 200px;
  font-size: 35px;
}
</style>
