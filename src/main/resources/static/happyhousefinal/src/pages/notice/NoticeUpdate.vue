<template>
  <div>
    <div class="banner">
      <h2>공지사항</h2>
    </div>
    <div class="container" align="center">
      <div class="col-md-10">
        <form
          id="writeform"
          method="post"
          action=""
          @submit.prevent="writeArticle"
        >
          <div class="content">
            <table style="  border-collapse: separate; border-spacing: 0 10px">
              <thead style="background-color: white;">
                <tr>
                  <th>제목</th>
                  <td>
                    <input
                      data-msg="제목"
                      class="form-control"
                      type="text"
                      name="subject"
                      id="subj"
                      v-model="article.subject"
                      style="width:100%"
                    />
                  </td>
                </tr>
                <tr>
                  <th>작성자</th>
                  <td v-text="article.author"></td>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th style="valign : top">내용</th>
                  <td>
                    <textarea
                      data-msg="내용"
                      class="form-control"
                      rows="15"
                      type="text"
                      name="content"
                      id="cont"
                      size="30"
                      v-model="article.content"
                      style="width:100%;"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="buttonDiv">
            <button
              type="submit"
              class="btn btn-secondary btn-lg"
              name="button"
            >
              글 수정
            </button>
            <button
              class="btn btn-secondary btn-lg"
              name="button"
              v-on:click="retrieveArticles()"
              style="margin:10px;"
            >
              글 목록
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { api } from "boot/axios";
export default {
  name: "QnaUpdate",
  props: ["no"],
  data() {
    return {
      subject: null,
      content: null,
      author: null,
      upHere: false,
      article: {},
      loading: true,
      errored: false
    };
  },
  methods: {
    writeArticle() {
      if (this.subject == "") {
        alert("제목을 입력하세요.");
        return;
      }
      if (this.author == "") {
        alert("작성자를 입력하세요.");
        return;
      }
      if (this.content == "") {
        alert("내용을 입력하세요.");
        return;
      }
      //alert("ajdi");
      api
        .put("/qnanotice/qnanoticeupdate", {
          articleno: this.article.articleno,
          author: this.article.author,
          subject: this.article.subject,
          content: this.article.content
        })
        .then(response => {
          if (response.data == "success") {
            alert("게시글을 등록하였습니다.");
            this.$router.push("/notice");
          } else {
            alert("게시글을 등록하지못했습니다.");
          }
        })
        .catch(() => {
          alert();
        });

      this.submitted = true;
    },
    retrieveArticles() {
      console.log(this.article);
      this.$router.push("/notice");
    }
  },
  mounted() {
    api
      .get("/qnanotice/qnanoticedetail/" + this.no)
      .then(response => (this.article = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  } //
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

button {
  background: #586282;
  color: white;
  padding: 14px 20px;
  margin: 20px 10px;
  cursor: pointer;
}

button:hover {
  color: #fff;
  background: linear-gradient(-45deg, #4f463e, #cfb8a1);
  background-size: 500% 500%;
  animation: AnimationName 10s ease infinite;
}

.content {
  width: 100%;
  margin-top: 50px;
  border: 1px solid #4f463e;
  border-radius: 10px;
  padding-top: 35px;
  padding-left: 40px;
  padding-right: 40px;
  padding-bottom: 40px;
  min-height: 500px;
}

table {
  width: 100%;
  text-align: left;
  padding: 20px;
}

#subject {
  font-size: 30px;
  font-weight: 500;
}

#date {
  color: #dbc3ab;
  border-bottom: 1px solid #4f463e;
}

#content {
  padding-top: 20px;
}

.buttonDiv {
  display: flex;
  justify-content: center; /* 요소 오른쪽정렬 */
  align-items: center; /* 요소 세로방향 가운데정렬 */
}
</style>
