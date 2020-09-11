<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
      height="80"
    >
    <v-toolbar-title>
        <router-link tag="span" to="/">
        <span class="ml-2 display-1 font-weight-light white--text">Empresa</span>
        </router-link>
    </v-toolbar-title>

      <!-- <v-btn dark icon v-on="on">Gestionar Promociones</v-btn> -->
      
      <v-spacer></v-spacer>
            <!-- MENU DESPLEGABLE INICIO -->
      <v-menu rounded="b-xl" offset-y
      >
        <template v-slot:activator="{ on }">
          <v-btn
            v-on="on"
            text
          >
           <span class="title font-weight-light"> Gestionar Prospectos</span>
           <v-icon>mdi-chevron-down-box</v-icon>
          </v-btn>
        </template>
  
        <v-list>
          <v-list-item
            v-for="item in items" :key="item.title"
            link
            router :to="item.route"
          >
            <v-list-item-title>{{item.title}}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
          <!-- MENU DESPLEGABLE FIN -->
      <v-icon>mdi-weather-sunny</v-icon>
      <v-switch v-model="switch1"
      class="mt-5 "
      @click="changeTheme"
      ></v-switch>
      <v-icon>mdi-weather-night</v-icon>
      <!-- <v-btn
        target="_blank"
        text
      >
        <span class="ml-2">Acerca</span>
        <v-icon>mdi-open-in-new</v-icon>
      </v-btn> -->
    </v-app-bar>

    <v-main>
        <v-container fluid>
          <router-view></router-view>
        </v-container>
      </v-main>
    <Footer/>
  </v-app>
</template>

<script>
// import 'primevue/resources/themes/arya-blue/theme.css'
import 'primevue/resources/themes/saga-blue/theme.css'
// import 'primevue/resources/themes/vela-blue/theme.css'
import Footer from './components/Footer.vue'
export default {
  name: 'App',
  components: {
    Footer
  },

  data: () => ({
    switch1: false,
    items: [
      { title: 'Capturar Prospecto', route: '/capturaprospecto' },
      { title: 'Listado/Evaluación', route: '/listaprospectos' },
    ]
  }),
  created(){
    // this.$vuetify.theme.dark != this.switch1
  },
  methods:{
    changeTheme(){
      console.log('cambiotema')
      this.$vuetify.theme.dark = this.switch1
    }
  }
};
</script>
