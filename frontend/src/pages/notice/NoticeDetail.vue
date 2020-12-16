<template>
  <div>
    <div class="banner">
      공지사항
    </div>
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8" style="margin-top:15px;">
        <div class="outline">
          <div class="row" style="font-size:35px;weight:500">
            {{ article.subject }}
          </div>
          <div
            class="row text-primary"
            style="font-size:15px;border-bottom:1px solid #4f463e; margin-bottom : 10px;"
          >
            {{ article.regidate }}
          </div>
          <div class="row" style="font-size:15px;">
            <q-input
              v-model="article.content"
              filled
              type="textarea"
              rows="20"
              style="width:100%;"
              readonly
            />
          </div>
        </div>
        <div
          class="q-gutter-md"
          style="text-align:center;margin-top:5px;margin-bottom:15px"
        >
          <q-btn
            color="secondary"
            label="글 목록"
            size="15px"
            style="padding:5px;"
            @click="retrieveArticles"
          />
          <q-btn
            color="primary"
            v-if="this.$q.sessionStorage.getItem('isAdmin') == 'true'"
            label="글 수정"
            size="15px"
            style="padding:5px;"
            @click="updateArticle"
          />
          <q-btn
            color="accent"
            v-if="this.$q.sessionStorage.getItem('isAdmin') == 'true'"
            label="글 삭제"
            size="15px"
            style="padding:5px;"
            @click="deleteArticle"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { api } from "boot/axios";

export default {
  name: "NoticeDetail",
  props: ["no"],
  data() {
    return {
      article: {},
      loading: true,
      errored: false
    };
  },
  methods: {
    deleteArticle() {
      ///deleteEmployee/
      api
        .delete("/notice/noticedelete/" + this.no)
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "공지사항 삭제 성공!"
            });
            this.$router.push("/notice");
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "공지사항 삭제 실패. 다시 시도해주세요."
            });
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    updateArticle() {
      this.$router.push("/notice/update/" + this.no);
    },
    retrieveArticles() {
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
