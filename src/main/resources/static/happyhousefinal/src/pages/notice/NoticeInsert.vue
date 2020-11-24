<template>
  <div class="flex flex-center">
    <div class="banner">
      공지사항
    </div>
    <div class="col-10" style="margin-top:15px;">
      <form
        id="writeform"
        method="post"
        action=""
        @submit.prevent="writeArticle"
      >
        <div class="content">
          <table style="  border-collapse: separate; border-spacing: 0 10px">
            <thead style="background-color: white;">
              <tr style="text-align:center">
                <td>
                  <q-input
                    outlined
                    v-model="subject"
                    label="제목"
                    bg-color="white"
                    style="width:100%; margin-right:10px"
                  />
                </td>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>
                  <q-input
                    v-model="content"
                    filled
                    type="textarea"
                    label="내용"
                  />
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="buttonDiv">
          <q-btn
            color="primary"
            label="글작성"
            size="15px"
            type="submit"
            style="padding:5px;"
          />
          <q-btn
            color="secondary"
            label="글목록"
            size="15px"
            type="submit"
            style="padding:5px;"
            @click="retrieveArticles"
          />
        </div>
      </form>
    </div>
  </div>
</template>
<script>
import { api } from "boot/axios";

export default {
  name: "QnaInsert",
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
      if (this.author == "") {
        alert("작성자를 입력하세요.");
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
            alert("게시글을 등록하였습니다.");
            this.$router.push("/notice");
          } else {
            alert("게시글을 등록하지못했습니다.");
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
</style>
