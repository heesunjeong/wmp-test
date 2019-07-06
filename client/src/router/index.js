import Vue from 'vue'
import Router from 'vue-router'
import Crawler from '../components/Crawler'
import Vuex from 'vuex'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HTML Crawler',
      component: Crawler
    }
  ]
})
