<template>
  <div>
    <div class="banner">
      공지사항
    </div>
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8" style="margin-top:15px;">
        <form
          id="writeform"
          method="post"
          action=""
          @submit.prevent="updateArticle"
        >
          <div class="outline">
            <div class="row">
              <q-input
                outlined
                v-model="article.subject"
                label="제목"
                bg-color="white"
                style="width:100%; margin-bottom:15px"
              />
            </div>
            <div class="row">
              <q-input
                v-model="article.content"
                filled
                type="textarea"
                label="내용"
                rows="20"
                style="width:100%;"
              />
            </div>
          </div>
          <div
            class="q-gutter-md"
            style="text-align:center;margin-top:5px;margin-bottom:15px"
          >
            <q-btn
              color="primary"
              label="글 수정"
              size="15px"
              type="submit"
              style="padding:5px;"
            />
            <q-btn
              color="secondary"
              label="글 목록"
              size="15px"
              type="submit"
              style="padding:5px;"
              @click="retrieveArticles"
            />
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
    updateArticle() {
      if (this.subject == "") {
        this.$q.notify({
          color: "secondary",
          textColor: "white",
          icon: "warning",
          position: "top",
          timeout: 1500,
          message: "제목을 입력하세요."
        });
        return;
      }
      if (this.content == "") {
        this.$q.notify({
          color: "secondary",
          textColor: "white",
          icon: "warning",
          position: "top",
          timeout: 1500,
          message: "내용을 입력하세요."
        });
        return;
      }
      api
        .put("/notice/noticeupdate", {
          articleno: this.article.articleno,
          author: this.article.author,
          subject: this.article.subject,
          content: this.article.content
        })
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "게시글 수정 성공!"
            });
            this.$router.push("/notice");
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "게시글 수정 실패. 다시 시도해주세요."
            });
          }
        })
        .catch(() => {
          alert();
        });
    },
    retrieveArticles() {
      console.log(this.article);
      this.$router.push("/notice");
    }
  },
  mounted() {
    api
      .get("/notice/noticedetail/" + this.no)
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

.outline {
  width: 100%;
  border: 1px solid #4f463e;
  border-radius: 10px;
  padding-top: 35px;
  padding-left: 40px;
  padding-right: 40px;
  padding-bottom: 40px;
  min-height: 500px;
}
</style>
