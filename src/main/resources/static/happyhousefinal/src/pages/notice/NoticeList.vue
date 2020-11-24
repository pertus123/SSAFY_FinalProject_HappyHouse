<template>
  <div class="flex flex-center">
    <div class="banner">
      공지사항
    </div>
    <div class="col-10" style="margin-top:15px;">
      <q-table
        :data="articles"
        :columns="columns"
        row-key="name"
        :pagination.sync="pagination"
        hide-pagination
        :filter="filter"
      >
        <template v-slot:top-left>
          <q-btn
            color="secondary"
            label="글작성"
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
  name: "NoticeList",
  data() {
    return {
      upHere: false,
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
          style: "width: 700px"
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
    detailArticle(items) {
      this.$router.push("/notice/detail/" + items.articleno);
    },
    retrieveArticles() {
      api
        .get("/notice/noticelist")
        .then(response => (this.articles = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    insertArticle() {
      this.$router.push("/notice/insert");
    }
  },
  mounted() {
    this.retrieveArticles();
    // 어드민이 아니면 false
    //console.log(this.articles);
    this.adminCondition = true;
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
