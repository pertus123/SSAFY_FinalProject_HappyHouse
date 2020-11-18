<template>
  <div>
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
                <td id="author">{{ article.author }}, {{ article.regidate }}</td>
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
          <button type="button" class="btn-secondary btn-lg" v-on:click="retrieveArticles()" style="margin:5px;">글 목록</button>
          <button type="button" class="btn-secondary btn-lg" v-on:click="updateArticle(article.articleno)" style="margin:5px;">글 수정</button>
          <button type="button" class="btn-secondary btn-lg" v-on:click="deleteArticle(article.articleno)" style="margin:5px;">글 삭제</button>
        </div>
        <b-row>
          <b-col cols="12" sm>
            <input type="text" style="width:85%;height:50px;" v-model="replycontent"/>
            <button type="button" class="btn-secondary" v-on:click="insertReply()" style="margin:5px;">댓글 등록</button>
          </b-col>
        </b-row>
        <div>
          <b-table show-empty :items="replys" :fields="fields" thead-class="d-none">
            <template #cell(update)>
              <b-button size="md"  class="mr-0">수정</b-button>
            </template>
            <template #cell(delete)="row">
              <b-button size="md" @click="deleteReply(row.item)" class="mr-0">삭제</b-button>
            </template>
          </b-table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../http-common';
export default {
  name: 'QnaDetail',
  props: ['no'],
  data() {
    return {
      upHere: false,
      article: {},
      fields: [
        { key: 'replyauthor', label: '작성자' },
        { key: 'replycontent', label: '댓글내용' },
        { key: 'replyregidate', label: '작성일' },
        { key: 'update', label: 'update' },
        { key: 'delete', label: 'delete' },
      ],
      replycontent:'',
      replys: [],
      replyinsertcliked: false,
      loading: true,
      errored: false,
    };
  },
  methods: {
    deleteArticle(no) {
      ///deleteEmployee/
      http
        .delete('/qnanotice/qnanoticedelete/' + no)
        .then((response) => {
          if (response.data == 'success') {
            alert('게시글 삭제 처리를 하였습니다.');
            this.$router.push('/');
          } else {
            alert('게시글 삭제 처리를 하지 못했습니다.');
          }
        })
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    updateArticle(no) {
      //console.log(this.article);
      this.$router.push('/update/' + no);
    },
    retrieveArticles() {
      this.$router.push('/');
    },
    retrieveReplys() {
      http
        .get('/qnareply/replylist/' + this.no)
        .then((response) => (this.replys = response.data))
        .catch(() => {
          this.errored = true;
        })
        .finally(() => (this.loading = false));
    },
    insertReply() {
      http
        .post('/qnareply/replywrite', {
          articleno: this.no,
          replyauthor: 'ssafy',
          replycontent: this.replycontent,
        })
        .then((response) => {
          if (response.data == 'success') {
            alert('댓글을 등록하였습니다.');
          } else {
            alert('댓글을 등록하지못했습니다.');
          }
          this.retrieveReplys();
          this.replycontent='';
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
    },
  },
  mounted() {
    http
      .get('/qnanotice/qnanoticedetail/' + this.no)
      .then((response) => (this.article = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => ((this.loading = false), this.retrieveReplys()));
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
