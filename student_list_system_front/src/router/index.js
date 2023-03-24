import Vue from 'vue'
import VueRouter from 'vue-router'
import TopPage from '../components/TopPage.vue'
import UserIndex from '../components/UserIndex.vue'
import UserCreate from '../components/UserCreate.vue'
import UserCreateConfirm from '../components/UserCreateConfirm.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'topPage',
    component: TopPage
  },
  {
    path: '/userIndex',
    name: 'userIndex',
    component: UserIndex
  },
  {
    path: '/userCreate',
    name: 'userCreate',
    component: UserCreate
  },
  {
    path: '/userCreateConfirm',
    name: '/userCreateConfirm',
    component: UserCreateConfirm
  }
]

const router = new VueRouter({
  routes
})

export default router
