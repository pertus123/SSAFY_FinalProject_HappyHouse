<template>
  <div>
    <div class="banner">
      <h2>Q&A</h2>
    </div>
    <div class="container" align="center">
      <div class="col-md-10">
        <form id="writeform" method="post" action=""  @submit.prevent="writeArticle">
          <div class="content">
            <table style="  border-collapse: separate; border-spacing: 0 10px">
              <thead style="background-color: white;">
                <tr>
                  <th>제목</th>
                  <td>
                    <input
                      data-msg="제목"
                      class = "form-control"
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
                  <td v-text="article.author">
                     
                  </td>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th style="valign : top">내용</th>
                  <td>
                    <textarea
                      data-msg="내용"
                      class = "form-control"
                      rows = "15"
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
            <button type="submit"  class="btn btn-secondary btn-lg" name="button">글 수정</button>
            <button class="btn btn-secondary btn-lg" name = "button" v-on:click="retrieveArticles()" style = "margin:10px;">
              글 목록
            </button>
            
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import http from '../http-common';
export default {
  name: 'QnaUpdate',
  props:['no'],
  data() {
    return {
      subject : null,
      content : null,
      author : null,
      upHere: false,
      article: {},
      loading: true,
      errored: false,
    };
  },
  methods: {
    writeArticle() {
      if (this.subject == '') {
        alert('제목을 입력하세요.');
        return;
      }
      if (this.author == '') {
        alert('작성자를 입력하세요.');
        return;
      }
      if (this.content == '') {
        alert('내용을 입력하세요.');
        return;
      }

      http
        .put('/qnanotice/qnanoticeupdate', {
          articleno: this.article.articleno,
          author: this.article.author,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then((response) => {
          if (response.data == 'success') {
            alert('게시글을 등록하였습니다.');
            this.$router.push('/');
          } else {
            alert('게시글을 등록하지못했습니다.');
          }
        });
      this.submitted = true;
    },
    retrieveArticles() {
      console.log(this.article);
       this.$router.push('/');
    },


  },
  mounted() {
    http
      .get('/qnanotice/qnanoticedetail/' + this.no)
      .then((response) => (this.article = response.data))
      .catch(() => {
        this.errored = true;
      })
      .finally(() => (this.loading = false));
  }, //
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
