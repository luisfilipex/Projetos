import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import cliente from '../views/cliente.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/home',
      name: 'HomeView.vue',
      component: HomeView
    },
    {
      path: '/about',
      name: 'AboutView.vue',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/login',
      name: 'login.vue',

      component: () => import('../views/login.vue')
    },
    {
      path: '/cliente',
      name: 'cliente',
      component: cliente
    },
  ]
})

export default router
