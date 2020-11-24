<template>
  <div>
    <div id="header">
      <main-header />
    </div>
    <div class="banner">
      <h2>Q&A</h2>
    </div>

    <div class="container" align="center">
      <div class="col-md-10">
        <div class="content">
          <table>
            <thead style="background-color: white;">
              <tr>
                <td id="subject">{{ article.subject }}</td>
              </tr>

              <tr>
                <td id="author">
                  {{ article.author }}, {{ article.regidate }}
                </td>
              </tr>
              <!-- <tr>
							<td>{{article.regidate}}</td>
						</tr> -->
            </thead>
            <hr />
            <tbody>
              <tr>
                <td id="content">{{ article.content }}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <div>
          <button
            type="button"
            class="btn-secondary btn-lg"
            v-on:click="retrieveArticles()"
            style="margin:5px;"
          >
            글 목록
          </button>
          <button
            type="button"
            class="btn-secondary btn-lg"
            v-on:click="updateArticle(article.articleno)"
            style="margin:5px;"
          >
            글 수정
          </button>
          <button
            type="button"
            class="btn-secondary btn-lg"
            v-on:click="deleteArticle(article.articleno)"
            style="margin:5px;"
          >
            글 삭제
          </button>
        </div>
        <b-row>
          <b-col cols="12" sm>
            <input
              type="text"
              style="width:85%;height:50px;"
              v-model="replycontent"
            />
            <button
              type="button"
              class="btn-secondary"
              v-on:click="insertReply()"
              style="margin:5px;"
            >
              댓글 등록
            </button>
          </b-col>
        </b-row>
        <div>
          <b-table
            show-empty
            :items="replys"
            :fields="fields"
            thead-class="d-none"
          >
            <template #cell(update)>
              <b-button size="md" class="mr-0">수정</b-button>
            </template>
            <template #cell(delete)="row">
              <b-button size="md" @click="deleteReply(row.item)" class="mr-0"
                >삭제</b-button
              >
            </template>
          </b-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { api } from "boot/axios";
export default {
  name: "QnaDetail",
  props: ["no"],
  data() {
    return {
      upHere: false,
      article: {},
      fields: [
        { key: "replyauthor", label: "작성자" },
        { key: "replycontent", label: "댓글내용" },
        { key: "replyregidate", label: "작성일" },
        { key: "update", label: "update" },
        { key: "delete", label: "delete" }
      ],
      replycontent: "",
      replys: [],
      replyinsertcliked: false,
      loading: true,
      errored: false
    };
  },
  methods: {
    deleteArticle(no) {
      ///deleteEmployee/
      api
        .delete("/qnanotice/qnanoticedelete/" + no)
        .then(response => {
          if (response.data == "success") {
            alert("게시글 삭제 처리를 하였습니다.");
            this.$router.push("/qnanotice");
          } else {
            alert("게시글 삭제 처리를 하지 못했습니다.");
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    updateArticle(no) {
      //console.log(this.article);
      this.$router.push("/qnaupdate/" + no);
    },
    retrieveArticles() {
      this.$router.push("/qnanotice");
    },
    retrieveReplys() {
      http
        .get("/qnareply/replylist/" + this.no)
        .then(response => (this.replys = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    insertReply() {
      api
        .post("/qnareply/replywrite", {
          articleno: this.no,
          replyauthor: "ssafy",
          replycontent: this.replycontent
        })
        .then(response => {
          if (response.data == "success") {
            alert("댓글을 등록하였습니다.");
          } else {
            alert("댓글을 등록하지못했습니다.");
          }
          this.retrieveReplys();
          this.replycontent = "";
        });
    },
    deleteReply(item) {
      alert(item);
      // http
      //   .delete('/qnareply/replydelete/' + item.replyno)
      //   .then((response) => {
      //     if (response.data == 'success') {
      //       alert('댓글 삭제 처리를 하였습니다.');
      //     } else {
      //       alert('댓글 삭제 처리를 하지 못했습니다.');
      //     }
      //   })
      //   .catch(() => {
      //     this.errored = true;
      //   })
      //   .finally(() => (this.loading = false));
    }
  },
  mounted() {
    api
      .get("/qnanotice/qnanoticedetail/" + this.no)
      .then(response => (this.article = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => ((this.loading = false), this.retrieveReplys()));
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
