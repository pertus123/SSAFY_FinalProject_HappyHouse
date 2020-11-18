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
                      v-model="subject"
                      style="width:100%"
                    />
                  </td>
                </tr>
                <tr>
                  <th>작성자</th>
                  <td>
                    <input
                      data-msg="작성자"
                      class = "form-control"
                      type="text"
                      name="author"
                      id="_author"
                      v-model="author"
                      style="width:100%"
                    />
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
                      v-model="content"
                      style="width:100%;"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
          <div class="buttonDiv">
            <button type="submit"  class="btn btn-secondary btn-lg" name="button">글 작성</button>
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
  name: 'QnaInsert',
  data() {
    return {
      info: null,
      loading: true,
      errored: false,
      subject: '',
      author: '',
      content: '',
      submitted: false,
    };
  },
  mounted() {},
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
        .post('/qnanotice/qnanoticewrite', {
          author: this.author,
          subject: this.subject,
          content: this.content,
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
    newArticle() {
      (this.submitted = false), (this.title = ''), (this.writer = ''), (this.content = '');
    },
    retrieveArticles() {
       this.$router.push('/');
    },
  },
};
</script>

<style scoped></style>
