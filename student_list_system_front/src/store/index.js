import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    id:"",
    mailAddress:"",
  },
  getters: {
  },
  mutations: {
    setName(state,userName){
      state.userName = userName;
    },
  },
  actions: {
  },
  modules: {
  }
})
