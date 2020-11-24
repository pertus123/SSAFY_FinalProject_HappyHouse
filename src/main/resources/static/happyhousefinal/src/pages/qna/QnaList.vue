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
              <button
                id="noticewrite"
                class="btn-secondary btn-sm"
                @click="insertArticle"
              >
                글작성
              </button>
            </b-col>
            <b-col lg="6" class="my-1">
              <b-form-group
                label="검색"
                label-cols-sm="3"
                label-align-sm="right"
                label-size="sm"
                label-for="filterInput"
                class="mb-0"
              >
                <b-input-group size="sm">
                  <b-form-input
                    v-model="filter"
                    type="search"
                    id="filterInput"
                    placeholder="검색할 글제목 입력"
                  ></b-form-input>
                  <b-input-group-append>
                    <b-button :disabled="!filter" @click="filter = ''"
                      >Clear</b-button
                    >
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
            <b-pagination
              v-model="currentPage"
              :total-rows="totalRows"
              :per-page="perPage"
              align="fill"
              size="sm"
              class="my-0"
              first-number
              last-number
            ></b-pagination>
          </b-row>
        </b-container>
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
      upHere: false,
      fields: [
        { key: "articleno", label: "글번호" },
        { key: "subject", label: "제목" },
        { key: "author", label: "작성자" },
        { key: "regidate", label: "작성일" }
      ],
      articles: [],
      loading: true,
      errored: false,
      totalRows: 1,
      currentPage: 1,
      perPage: 10,
      filter: null,
      filterOn: []
    };
  },
  methods: {
    insertArticle() {
      this.$router.push("/qnainsert");
    },
    detailArticle(items) {
      //alert(no+"게시글의 정보입니다.");
      this.$router.push("/qnadetail/" + items.articleno);
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
            //      alert(this.articles),
            (this.loading = false), (this.totalRows = this.articles.length)
          )
        );
    },
    QnaArticleWrite() {},
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length;
      this.currentPage = 1;
    }
  },
  mounted() {
    this.retrieveArticles();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@charset "UTF-8";
.banner {
  color: #fff;
  padding-top: 70px;
  padding-bottom: 70px;
  background-image: url("~assets/notice_banner.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  text-align: center;
}
.search-container {
  display: flex;
  justify-content: flex-end; /* 요소 오른쪽정렬 */
  align-items: center; /* 요소 세로방향 가운데정렬 */
}

#key {
  width: 15%;
  margin-right: 5px;
}

#word {
  width: 30%;
  margin-right: 5px;
}

#searchBtn {
  width: 37px;
  height: 37px;
  line-height: 0px; /* 글씨 세로정렬 */
  padding: 0 0;
  border-radius: 2px;
}

.articleno {
  width: 100px;
  text-align: center;
}

.articledate {
  width: 150px;
  text-align: center;
}

#searchform {
  margin-top: 20px;
  margin-bottom: 20px;
}

button {
  background: #586282;
  color: white;
  padding: 14px 20px;
  border: none;
  cursor: pointer;
  margin-right: 10px;
}

button:hover {
  color: #fff;
  background: linear-gradient(-45deg, #4f463e, #cfb8a1);
  background-size: 500% 500%;
  animation: AnimationName 10s ease infinite;
}
thead {
  background-color: #dbc3ab;
}

.page-link {
  color: #75612d;
}

.page-item.active .page-link {
  background-color: #dbc3ab;
  border-color: #dbc3ab;
}

#noticewrite {
  margin-bottom: 20px;
  text-align: center;
  width: 80px;
  height: 40px;
  float: left;
}
</style>
