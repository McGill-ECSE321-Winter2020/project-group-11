import Vue from 'vue'
import Router from 'vue-router'
import Hello from '@/components/Hello'
import ProjectGroup11 from '@/components/ProjectGroup11'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Hello',
      component: Hello
    },
    {
      path: '/app',
      name: 'ProjectGroup11',
      component: ProjectGroup11
    }
  ]
})
