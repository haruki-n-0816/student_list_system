import Vue from 'vue'
import VueRouter from 'vue-router'
import TopPage from '../components/TopPage.vue'
import UserIndex from '../components/UserIndex.vue'
import UserCreate from '../components/UserCreate.vue'
import UserCreateConfirm from '../components/UserCreateConfirm.vue'
import UserUpdate from '../components/UserUpdate.vue'
import UserUpdateConfirm from '../components/UserUpdateConfirm.vue'

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
    name: 'userCreateConfirm',
    component: UserCreateConfirm
  },
  {
    path: '/userUpdate',
    name: 'userUpdate',
    component: UserUpdate
  },
  {
    path: '/userUpdateConfirm',
    name: 'userUpdateConfirm',
    component: UserUpdateConfirm
  }
]

const router = new VueRouter({
  routes
})

export default router
