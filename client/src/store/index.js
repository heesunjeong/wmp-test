import Vue from 'vue'
import Vuex from 'vuex'
import * as types from './types'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    result: ''
  },
  mutations: {
    [types.GET_CRAWLING]: (state, payload) => {
      console.log(state, payload);
      state.result = payload;
    }
  },
  actions: {
    [types.GET_CRAWLING]: ({commit}, {url, includeTags, divisor}) => {
      return axios.get("/api/crawling", {params: {url, includeTags, divisor}})
        .then(res => commit(types.GET_CRAWLING, res.data));
    }
  }
})
