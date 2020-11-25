<template>
  <div>
    <div class="row flex-center" style="margin-top:10px;margin-bottom:10px">
      <form
        class="col-10 bg-primary row inline flex-center"
        id="searchContainer"
      >
        <q-select
          outlined
          v-model="searchCode.searchType"
          :options="selectOptions"
          label="검색분류"
          bg-color="white"
          style="width:160px; margin-right:10px"
        />
        <q-input
          outlined
          v-model="searchCode.keyword"
          label="검색어"
          bg-color="white"
          style="width:300px; margin-right:10px"
          @keydown.enter.prevent="onClickSearch"
        />
        <q-btn
          label="검색"
          style="margin:5px"
          color="white"
          text-color="secondary"
          @click="onClickSearch"
        />
      </form>
    </div>
    <div class="row flex-center">
      <div class="col-10">
        <q-carousel
          animated
          v-model="slide"
          infinite
          navigation
          :autoplay="autoplay"
          arrows
          transition-prev="slide-right"
          transition-next="slide-left"
          @mouseenter="autoplay = false"
          @mouseleave="autoplay = true"
          navigation-icon="layers"
        >
          <q-carousel-slide :name="1" img-src="~assets/slide1.jpg">
            <div class="absolute-bottom custom-caption">
              <div class="text-h4">어떤 집에서 살고 싶나요?</div>
              <div class="text-subtitle1 text-css">
                집은 사람이나 동물이 거주하기 위해 지은 건물로, 보통 벽과 지붕이
                있으며, 추위와 더위, 비바람을 막아 줍니다.<br />
                좁은 뜻으로는 인간이 사는 집, 곧 주택만을 가리키기도 한다.
                때때로는 민가라는 이칭을 가지고 있습니다.
              </div>
            </div>
          </q-carousel-slide>
          <q-carousel-slide :name="2" img-src="~assets/slide2.jpg">
            <div class="absolute-bottom custom-caption">
              <div class="text-h4">
                주택임대차계약서 작성 시 알아두면 좋은 법률 상식
              </div>
              <div class="text-subtitle1 text-css">
                마음에 드는 집을 구했다면, 이제 계약서를 써야 할 때! 하지만
                주택임대차계약서... 이름만 들어도 어렵다구요? <br />
                주택임대차계약서 어려워마시고 게시물만 따라서 꼼꼼하게 확인만
                하면 된답니다!
              </div>
            </div>
          </q-carousel-slide>
          <q-carousel-slide :name="3" img-src="~assets/slide3.jpg">
            <div class="absolute-bottom custom-caption">
              <div class="text-h4">ssafy Happy House TV 광고 이벤트!</div>
              <div class="text-subtitle1 text-css">
                지금은 TV 광고 준비가 막바지에 이르렀는데요, 준비하면서 저희
                스스로도 감탄하는 정말 역대급! TV 광고입니다. <br />이렇게 좋은
                광고를 저희만 볼 수 없어, 여러 차례 노출시킬 예정이니 조금만
                기다려주세요~
              </div>
            </div>
          </q-carousel-slide>
          <q-carousel-slide :name="4" img-src="~assets/slide4.jpg">
            <div class="absolute-bottom custom-caption">
              <div class="text-h4">
                고양이와 함께 살고 싶은 집을 찾으시나요?
              </div>
              <div class="text-subtitle1 text-css">
                소중한 반려묘와 함께 살 집에 대한 정보는 이곳에!!
              </div>
            </div>
          </q-carousel-slide>
        </q-carousel>
      </div>
    </div>
    <div class="row flex-center" style="margin-top:10px">
      <div class="col-4">
        <q-table
          title="공지사항"
          :data="notice_data"
          :columns="notice_columns"
          row-key="name"
          hide-header
          hide-bottom
        />
      </div>
      <div class="col-4" style="padding:10px">
        <q-table
          title="Q&A"
          :data="qna_data"
          :columns="qna_columns"
          row-key="name"
          hide-header
          hide-bottom
        />
      </div>
      <div class="col-2"><img src="~assets/ad.png" width="100%" /></div>
    </div>
  </div>
</template>
<script>
import { api } from "boot/axios";
export default {
  data() {
    return {
      slide: 1,
      autoplay: true,
      searchCode: {
        dealType: "1",
        searchType: "",
        keyword: ""
      },
       tmpSearchCode: {
        dealType: "1",
        searchType: "",
        keyword: ""
      },
      selectOptions: ["동으로 검색", "건물명으로 검색"],
      notice_data: [],
      notice_columns: [
        { name: "notice", align: "left", label: "제목", field: "subject" },
        { name: "date", label: "작성일", field: "regidate" }
      ],
      qna_data: [],
      qna_columns: [
        { name: "notice", align: "left", label: "제목", field: "subject" },
        { name: "date", label: "작성일", field: "regidate" }
      ]
    };
  },
  methods: {
    onClickSearch() {
   //   alert(this.searchCode.searchType);
    //  alert(this.searchCode.keyword);
      if(this.searchCode.searchType == "동으로 검색") this.tmpSearchCode.searchType = '0';
      else this.tmpSearchCode.searchType = '1';
 //    this.tmpSearchCode.searchType = this.searchCode.searchType;
      this.tmpSearchCode.keyword = this.searchCode.keyword;

      this.$router.push({ name: "Search", params: { no: this.tmpSearchCode } });
    },
    loadingNotice: function() {
      api
        .get("/notice/mainnoticelist")
        .then(response => {
          console.log(response); //apt list
          this.notice_data = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    },
    loadingQna: function() {
      api
        .get("/qnanotice/mainqnanoticelist")
        .then(response => {
          console.log(response); //apt list
          this.qna_data = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  mounted() {
    this.loadingNotice();
    this.loadingQna();
  }
};
</script>
<style scoped>
.custom-caption {
  text-align: center;
  color: white;
  background-color: rgba(0, 0, 0, 0.3);
  padding-bottom: 60px;
}
.t {
  padding-left: 20%;
  padding-right: 20%;
}

#searchContainer {
  padding-top: 10px;
  padding-bottom: 10px;
}
</style>
