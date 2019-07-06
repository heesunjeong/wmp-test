<template>
  <div v-if="result" class="result-area">
    <div v-if="result.quotient" class="quotient">
      <div class="title">몫</div>
      <div class="result">{{result.quotient}}</div>
    </div>
    <div v-if="result.rest" class="rest">
      <div class="title">나머지</div>
      <div class="result">{{result.rest}}</div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Result",
    data: {},
    computed: {
      result: function () {
        return this.$store.state.result;
      },
    },
  }
</script>

<style scoped>
  .title {
    font-weight: bold;
  }

  .result {
    overflow: hidden;
    overflow-y: auto;
    width: 95%;
    word-break: break-all;
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
  }
</style>
