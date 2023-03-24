import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userName:"",
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
