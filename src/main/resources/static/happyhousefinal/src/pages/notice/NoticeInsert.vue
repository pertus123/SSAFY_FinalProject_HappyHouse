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
          @submit.prevent="writeArticle"
        >
          <div class="outline">
            <div class="row">
              <q-input
                outlined
                v-model="subject"
                label="제목"
                bg-color="white"
                style="width:100%; margin-bottom:15px"
              />
            </div>
            <div class="row">
              <q-input
                v-model="content"
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
              label="글 작성"
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
  name: "NoticeInsert",
  data() {
    return {
      info: null,
      loading: true,
      errored: false,
      subject: "",
      author: "",
      content: "",
      submitted: false
    };
  },
  mounted() {},
  methods: {
    writeArticle() {
      if (this.subject == "") {
        alert("제목을 입력하세요.");
        return;
      }
      if (this.content == "") {
        alert("내용을 입력하세요.");
        return;
      }

      api
        .post("/notice/noticewrite", {
          author: this.author,
          subject: this.subject,
          content: this.content
        })
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "게시글 등록 성공!"
            });
            this.$router.push("/notice");
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "게시글 등록 실패. 다시 시도해주세요."
            });
          }
        });
      this.submitted = true;
    },
    newArticle() {
      (this.submitted = false),
        (this.title = ""),
        (this.writer = ""),
        (this.content = "");
    },
    retrieveArticles() {
      this.$router.push("/notice");
    }
  }
};
</script>

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
