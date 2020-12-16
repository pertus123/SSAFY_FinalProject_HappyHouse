<template>
  <div>
    <div class="banner">
      Q&A
    </div>
    <div class="row">
      <div class="col-2"></div>
      <div class="col-8" style="margin-top:15px;">
        <div class="outline">
          <div class="row" style="font-size:35px;weight:500">
            {{ article.subject }}
          </div>
          <div class="row" style="font-size:15px; margin-bottom : 5px;">
            작성자 : {{ article.author }}
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
            v-if="this.$q.sessionStorage.getItem('userId') == article.author"
            label="글 수정"
            size="15px"
            style="padding:5px;"
            @click="updateArticle"
          />
          <q-btn
            color="accent"
            v-if="this.$q.sessionStorage.getItem('userId') == article.author"
            label="글 삭제"
            size="15px"
            style="padding:5px;"
            @click="deleteArticle"
          />
        </div>
        <div class="row" style="margin-bottom:20px">
          <div class="col-10">
            <q-input
              outlined
              v-model="replycontent"
              bg-color="white"
              color="secondary"
              style="width:100%; margin-right:10px"
            />
          </div>
          <div class="col-2">
            <q-btn
              color="secondary"
              label="댓글등록"
              size="15px"
              style="padding:5px; width:100%; height:100%"
              @click="insertReply"
            />
          </div>
        </div>
        <div>
          <q-table
            :data="reply_data"
            :columns="reply_columns"
            row-key="replyno"
            hide-header
            hide-bottom
          >
            <template v-slot:body="props">
              <q-tr v-show="!props.expand" :props="props">
                <q-td v-for="col in props.cols" :key="col.name" :props="props">
                  {{ col.value }}
                </q-td>
                <q-td width="50px">
                  <q-btn
                    color="primary"
                    label="수정"
                    @click="props.expand = !props.expand"
                  />
                </q-td>
                <q-td width="50px">
                  <q-btn
                    color="accent"
                    label="삭제"
                    @click="deleteReply(props.row.replyno)"
                  />
                </q-td>
              </q-tr>
              <q-tr v-show="props.expand" :props="props">
                <q-td> </q-td>
                <q-td>
                  {{ props.row.replyauthor }}
                </q-td>
                <q-td>
                  <q-input
                    outlined
                    v-model="props.row.replycontent"
                    bg-color="white"
                  />
                </q-td>
                <q-td align="right">
                  {{ props.row.replyregidate }}
                </q-td>
                <q-td>
                  <q-btn
                    color="primary"
                    label="수정"
                    @click="
                      updateReply(
                        props.row.replycontent,
                        props.row.replyno,
                        props
                      )
                    "
                  />
                </q-td>
              </q-tr>
            </template>
          </q-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { api } from "boot/axios";
import { SessionStorage } from "quasar";
export default {
  name: "QnaDetail",
  props: ["no"],
  data() {
    return {
      reply_data: [],
      reply_columns: [
        { name: "replyno", label: "댓글번호", field: "", style: "width:0px" },
        {
          name: "replyauthor",
          label: "작성자",
          align: "center",
          field: "replyauthor",
          style: "width:50px"
        },
        {
          name: "replycontent",
          align: "left",
          label: "제목",
          field: "replycontent"
        },
        {
          name: "replyregidate",
          align: "right",
          label: "작성일",
          field: "replyregidate"
        }
      ],
      replycontent: "",
      article: {},
      loading: true,
      errored: false
    };
  },
  methods: {
    deleteArticle() {
      api
        .delete("/qnanotice/qnanoticedelete/" + this.no)
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "QnA 삭제 성공!"
            });
            this.$router.push("/qna");
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "QnA 삭제 실패. 다시 시도해주세요."
            });
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    updateArticle() {
      //console.log(this.article);
      this.$router.push("/qna/update/" + this.no);
    },
    retrieveArticles() {
      this.$router.push("/qna");
    },
    retrieveReplys() {
      api
        .get("/qnareply/replylist/" + this.no)
        .then(response => (this.reply_data = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    insertReply() {
      api
        .post("/qnareply/replywrite", {
          articleno: this.no,
          replyauthor: SessionStorage.getItem("userId"),
          replycontent: this.replycontent
        })
        .then(response => {
          if (this.replycontent == "") {
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
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "댓글 등록 성공!"
            });
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "댓글 등록 실패. 다시 시도해주세요."
            });
          }
          this.retrieveReplys();
          this.replycontent = "";
        });
    },
    deleteReply(replyno) {
      api.delete("/qnareply/replydelete/" + replyno).then(response => {
        if (response.data == "success") {
          this.$q.notify({
            color: "primary",
            textColor: "white",
            icon: "done_outline",
            position: "top",
            timeout: 1000,
            message: "댓글 삭제 성공!"
          });
        } else {
          this.$q.notify({
            color: "accent",
            textColor: "white",
            icon: "warning",
            position: "top",
            timeout: 1500,
            message: "댓글 삭제 실패. 다시 시도해주세요."
          });
        }
        this.retrieveReplys();
        this.replycontent = "";
      });
    },
    updateReply(replycontent, replyno, props) {
      if (replycontent == "") {
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
        .put("/qnareply/replyupdate", {
          replyno: replyno,
          replycontent: replycontent
        })
        .then(response => {
          if (response.data == "success") {
            this.$q.notify({
              color: "primary",
              textColor: "white",
              icon: "done_outline",
              position: "top",
              timeout: 1000,
              message: "댓글 수정 성공!"
            });
          } else {
            this.$q.notify({
              color: "accent",
              textColor: "white",
              icon: "warning",
              position: "top",
              timeout: 1500,
              message: "댓글 수정 실패. 다시 시도해주세요."
            });
          }
          props.expand = !props.expand;
          this.retrieveReplys();
        });
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
