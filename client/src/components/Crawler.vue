<template>
  <div class="crawler">
    <label for="url">URL</label>
    <input id="url" v-model="url" type="text" class="input-url input" placeholder="url을 입력해주세요."/>

    <label for="include-tags">Type</label>
    <select id="include-tags" class="input" v-model="includeTags">
      <option value="false">HTML 태그 제외</option>
      <option value="true">Text 전체</option>
    </select>

    <div>
      <label for="divisor">출력 묶음 단위 (자연수)</label>
      <input id="divisor" class="input" type="number" min="0" v-model="divisor"/>
    </div>

    <button class="crawler-btn" v-on:click="requestResult">출력</button>

    <DivisionResult/>

  </div>
</template>

<script>
  import DivisionResult from './DivisionResult';
  import * as types from '../store/types';

  export default {
    name: 'Crawler',
    components: {DivisionResult},
    data() {
      return {
        url: '',
        includeTags: false,
        divisor: 0,
      }
    },
    methods: {
      requestResult: function () {
        if (!this.validateUrl()) {
          alert("url 형식에 맞추어 입력해주세요.")
          return false;
        }

        if (!this.validateNaturalNumber()) {
          alert("자연수만 입력해주세요.");
          this.divisor = 0;
          return false;
        }

        this.$store.dispatch(types.GET_CRAWLING, {
          url: this.url,
          includeTags: this.includeTags,
          divisor: this.divisor
        }).catch(error => {
          if (error.response.status == 500) {
            alert("크롤링에 실패하였습니다.");
            return;
          }
          alert(error.response.data);
        });
      },

      validateNaturalNumber: function () {
        const regex = /^[0-9]\d*/;
        return regex.test(this.divisor);
      },

      validateUrl: function () {
        const urlRegex = /https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)/;
        return urlRegex.test(this.url);
      },
    }
  }
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
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

  .input-url {
    width: 200px;
  }

  .input-url {
    padding: 5px;
  }

  .crawler-btn {
    border: none;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    background-color: #e7e7e7;
    color: black;
  }

  input[type=text], input[type=number], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    box-sizing: border-box;
  }

</style>
