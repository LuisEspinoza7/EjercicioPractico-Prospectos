import Vue from 'vue'
import Router from 'vue-router'
import Home from './views/Home'
import CapturaProspecto from './views/CapturaProspecto'
import ListaProspectos from './views/ListaProspectos'
import ProspectoDetails from './views/ProspectoDetails'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
      {
        path: '/',
        name: 'home',
        component: Home
      },
      {
        path: '/404',
        redirect: '/error'
      },
      {
        path: '/capturaprospecto',
        name: 'capturaprospecto',
        component: CapturaProspecto
      },
      {
        path: '/listaprospectos',
        name: 'listaprospectos',
        component: ListaProspectos
      },
      {
        path: '/prospectodetails/:idprospecto',
        name: 'prospectodetails',
        component: ProspectoDetails
      }
    ]
})